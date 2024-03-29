package com.it.blog.controller;

import com.it.blog.domain.Article;
import com.it.blog.dto.ArticleViewResponse;
import com.it.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class BlogViewController {
  private final BlogService blogService;

  @GetMapping("/articles")
  public String getArticles(Model model) {
    List<ArticleViewResponse> articles = blogService.findAll().stream().map(ArticleViewResponse::new).toList();

    model.addAttribute("articles", articles);

    return "tables-basic";
  }

  @GetMapping("/articles/{id}")
  public String getArticle(@PathVariable Long id, Model model) {
    Article article = blogService.findById(id);
    model.addAttribute("article", new ArticleViewResponse(article));

    return "article";
  }

  @GetMapping("/new-article")
  public String newArticle(@RequestParam(required = false) Long id, Model model) {
    if (id == null){
      model.addAttribute("article", new ArticleViewResponse());
    } else {
      Article article = blogService.findById(id);
      model.addAttribute("article", new ArticleViewResponse(article));
    }

    return "newArticle";
  }

  @GetMapping("/dealMeter")
  public String dealMeterPage() {
    return "dealMeter"; // templates 폴더에 dealMeter.html 파일이 있어야 합니다.
  }

  @GetMapping("/tableBasic")
  public String tableBasic() {
    return "/tableBasic";  //templates 폴더에 tableBasic.html 파일이 있어야 합니다.
  }
}

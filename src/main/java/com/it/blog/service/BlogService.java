package com.it.blog.service;


import com.it.blog.domain.Article;
import com.it.blog.dto.AddArticleRequest;
import com.it.blog.dto.UpdateArticleRequest;
import com.it.blog.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor // final에 붙거나 @NotNull 이 붙는 필드의 생성자 추가
@Service
public class BlogService {

  private final BlogRepository blogRepository;

  //블로그 글 추가
  public Article save(AddArticleRequest request){
    return blogRepository.save(request.toEntity());
  }

  //
  public List<Article> findAll(){
    return blogRepository.findAll(Sort.by("createdAt").descending());
  }


  // 블로그 아이디 로 조회
  public Article findById(Long id){
    return blogRepository.findById(id).orElseThrow(() -> new IllegalStateException("Not found : " + id));
  }

  // 블로그 글 삭제
  public void delete(long id){
    blogRepository.deleteById(id);
  }


  // 블로그 글 수정
  @Transactional
  public Article update(long id, UpdateArticleRequest request){
    Article article = blogRepository.findById(id).orElseThrow(() -> new IllegalStateException("Not found : " + id));

    article.update(request.getTitle(), request.getContent());

    return article;
  }
}

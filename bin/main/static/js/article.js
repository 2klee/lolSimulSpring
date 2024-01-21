const listButton = document.getElementById('list-btn');

if (listButton){
    listButton.addEventListener('click', function () {
                location.replace('/articles');
    });
}


// 삭제기능
const deleteButton = document.getElementById('delete-btn');

if (deleteButton){
    deleteButton.addEventListener('click', e => {
        let id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}`, {
            method: 'DELETE'
        })
        .then(() =>{
            alert('삭제완료');
            location.replace('/articles');
        });
    });
}

const modifyButton = document.getElementById('modify-btn');

if (modifyButton){
    modifyButton.addEventListener('click', function () {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');
        console.log(params);

        fetch(`/api/articles/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
            .then(() =>{
                alert('수정완료');
                location.replace(`/articles/${id}`);
            })
    })
}

const createBtn = document.getElementById('create-btn');

if (createBtn){
    createBtn.addEventListener('click', function (){
        fetch('/api/articles', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
            .then(() =>{
                alert('등록완료');
                location.replace(`/articles`);
            })
    });
}
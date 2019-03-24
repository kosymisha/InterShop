<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Announcement</title>
</head>
<body>
<body>
            <br/>
            <img width="100" height="100" src="/img/${announcement.product.photoURL}" /> <br/>
            <a href="${announcement.productURL}">${announcement.product.title}</a> <br/>
            <i>${announcement.product.category.categoryName}</i> <br/>
            <i>${announcement.currency} <b>${announcement.price}</b></i> <br/>
            <br/>
    <div>Comments: </div>
    <form action="/announcements/${announcement.id}/comments/create" method="post" >
        <input type="text" name="commentBox" placeholder="input your comment" />
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="submit" value="Ok" />
    </form>
    <#list comments as comment>
        <div>${comment.author.firstName} ${comment.author.lastName} ${comment.getStringDate()}</div>
        <div>${comment.message}</div>
        <#list user.roles as role>
            <#if role == 'ADMIN' || comment.author.id == user.id>
                <div><a href="/announcements/${announcement.id}/comments/${comment.id}/delete">delete</a></div>
            </#if>
        </#list>
        <br>
    <#else>
        No comments.
    </#list>
</body>
</html>
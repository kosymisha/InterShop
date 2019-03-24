<div id="comm" name="commentsList">Comments:
    <div><input type="text" id="comBox" name="commentBox" placeholder="input your comment" />
        <button onclick="addCom(${shop.id}, '${_csrf.token}')">Send</button></div>
<#list comments as comment>
        <div>${comment.author.firstName} ${comment.author.lastName} ${comment.getStringDate()}</div>
        <div>${comment.message}</div>
    <#list user.roles as role>
        <#if role == 'ADMIN' || comment.author.id == user.id>
                <div><button onclick="loadDoc(${shop.id}, ${comment.id})">delete</button></div>
        </#if>
    </#list>
        <br>
<#else>
        No comments.
</#list>
</div>
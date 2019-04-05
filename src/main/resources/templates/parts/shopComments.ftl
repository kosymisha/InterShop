<#include "../parts/security.ftl" />
<div id="comm" name="commentsList">Comments:
    <div>
        <input type="text" id="comBox" name="commentBox" placeholder="input your comment" />
        <button onclick="createShopComment(${shopId}, '${_csrf.token}')">Send</button>
    </div>
        <#if comments??>
            <#list comments as comment>
                <div>${comment.author.firstName} ${comment.author.lastName} ${comment.getStringDate()}</div>
                <div>${comment.message}</div>
                <#if isAdmin || comment.author.id == currentUserId>
                    <div><button onclick="deleteShopComment(${shopId}, ${comment.id})">delete</button></div>
                </#if>
                <br>
            </#list>
        <#else>
            No comments.
        </#if>
</div>
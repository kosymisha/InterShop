<#include "../parts/security.ftl" />
<div id="comm" name="commentsList">Comments:
    <div>
        <input type="text" id="advComBox" name="commentBox" placeholder="input your comment" />
        <button onclick="createAdvertComment(${advertId}, '${_csrf.token}')">Send</button>
    </div>
        <#if comments??>
            <#list comments as comment>
                <div>${comment.author.firstName} ${comment.author.lastName} ${comment.getStringDate()}</div>
                <div>${comment.message}</div>
                <#if isAdmin || comment.author.id == currentUserId>
                    <div><button onclick="deleteAdvertComment(${advertId}, ${comment.id})">delete</button></div>
                </#if>
                <br>
            </#list>
        <#else>
            No comments.
        </#if>
</div>
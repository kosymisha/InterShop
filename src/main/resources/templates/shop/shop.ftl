<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl" />
<@c.page "InterShop">
<script src="/js/shopComments.js" type="text/javascript" xmlns="http://www.w3.org/1999/html"></script>
    <div><img width="200" height="200" src="${shop.photoURL}" /></div>
    <div><label>Name: </label>${shop.nameShop}</div>
    <div><label>URL: </label>${shop.url}</div>
    <div><label>Owner: </label>${shop.owner.firstName} ${shop.owner.lastName}</div>
    <div><label>Description: </label>${shop.description}</div>
    <br/>
        <#if isAdmin || shop.owner.id == currentUserId>
        <a href="/shops/${shop.id}/delete">delete</a>
        </#if>
    <br/>


    <div id="comm" name="commentsList">Comments:
        <div>
            <input type="text" id="comBox" name="commentBox" placeholder="input your comment" />
            <button onclick="createShopComment(${shop.id}, '${_csrf.token}')">Send</button>
        </div>
        <#if shop.comments??>
            <#list shop.getCommentsOrderByDate() as comment>
                <div>${comment.author.firstName} ${comment.author.lastName} ${comment.getStringDate()}</div>
                <div>${comment.message}</div>
                <#if isAdmin || comment.author.id == currentUserId>
                    <div><button onclick="deleteShopComment(${shop.id}, ${comment.id})">delete</button></div>
                </#if>
                <br>
            </#list>
        <#else>
            No comments.
        </#if>
    </div>


</@c.page>
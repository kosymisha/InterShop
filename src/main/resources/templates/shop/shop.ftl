<#import "../parts/common.ftl" as c>
<@c.page "InterShop">
<script src="/js/shopComments.js" type="text/javascript" xmlns="http://www.w3.org/1999/html"></script>
    <div><img width="200" height="200" src="${shop.photoURL}" /></div>
    <div><label>Name: </label>${shop.nameShop}</div>
    <div><label>URL: </label>${shop.url}</div>
    <div><label>Owner: </label>${shop.owner.firstName} ${shop.owner.lastName}</div>
    <div><label>Description: </label>${shop.description}</div>
    <br/>
    <#list user.roles as role>
        <#if role == 'ADMIN'>
        <a href="/shops/${shop.id}/delete">delete</a>
        <#elseif shop.owner.id == user.id>
        <a href="/shops/${shop.id}/delete">delete</a>
        </#if>
    </#list>
    <br/>


    <div id="comm" name="commentsList">Comments:
        <div><input type="text" id="comBox" name="commentBox" placeholder="input your comment" />
            <button onclick="createShopComment(${object.id}, '${_csrf.token}')">Send</button></div>
<#if comments??>
<#list comments as comment>
        <div>${comment.author.firstName} ${comment.author.lastName} ${comment.getStringDate()}</div>
        <div>${comment.message}</div>
    <#list user.roles as role>
        <#if role == 'ADMIN' || comment.author.id == user.id>
                <div><button onclick="deleteShopComment(${object.id}, ${comment.id})">delete</button></div>
        </#if>
    </#list>
        <br>
</#list>
<#else>
        No comments.
</#if>
    </div>


</@c.page>
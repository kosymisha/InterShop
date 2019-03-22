<#import "../parts/common.ftl" as c>

<@c.page "${shop.nameShop}">
    <div><img width="200" height="200" src="/img/${shop.photoURL}" /></div>
    <div><label>Name: </label>${shop.nameShop}</div>
    <div><label>URL: </label>${shop.url}</div>
    <div><label>Owner: </label>${shop.owner.firstName} ${shop.owner.lastName}</div>
    <div><label>Description: </label>${shop.description}</div>
    <br/>
    <div>Comments: </div>
    <form action="/shops/${shop.id}/comments/create" method="post" >
        <input type="text" name="commentBox" placeholder="input your comment" />
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="submit" value="Ok" />
    </form>
    <#list comments as comment>
        <div>${comment.author.firstName} ${comment.author.lastName} ${comment.getStringDate()}</div>
        <div>${comment.message}</div>
        <#list user.roles as role>
            <#if role == 'ADMIN' || comment.author.id == user.id>
                <div><a href="/shops/${shop.id}/comments/${comment.id}/delete">delete</a></div>
            </#if>
        </#list>
        <br>
    <#else>
        No comments.
    </#list>
</@c.page>
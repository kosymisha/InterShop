<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl" />
<@c.page "InterShop">
<script src="/js/advertComments.js" type="text/javascript" xmlns="http://www.w3.org/1999/html"></script>
    <br/>
    <img width="100" height="100" src="${advert.product.photoURL}" /> <br/>
    <a href="${advert.productURL}">${advert.product.title}</a> <br/>
    <i>${advert.product.category.categoryName}</i> <br/>
    <i>${advert.currency} <b>${advert.price}</b></i> <br/>
    <i>Views: ${advert.views}</i>
    <br/>
        <#if isAdmin || advert.shop.owner.id == currentUserId>
        <a href="/adverts/${advert.id}/delete">delete</a>
        </#if>
    <br/>



    <div id="comm" name="commentsList">Comments:
        <div><input type="text" id="advComBox" name="commentBox" placeholder="input your comment" />
        <button onclick="createAdvertComment(${advert.id}, '${_csrf.token}')">Send</button></div>
        <#if advert.comments??>
            <#list advert.getCommentsOrderByDate() as comment>
                <div>${comment.author.firstName} ${comment.author.lastName} ${comment.getStringDate()}</div>
                <div>${comment.message}</div>

                    <#if isAdmin || comment.author.id == currentUserId>
                        <div><button onclick="deleteAdvertComment(${advert.id}, ${comment.id})">delete</button></div>
                    </#if>
                <br>
            </#list>
        <#else>
            No comments.
        </#if>
    </div>



</@c.page>
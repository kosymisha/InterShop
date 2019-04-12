<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl" />
<@c.page "${shop.nameShop}">
<script src="/js/shopComments.js" type="text/javascript" xmlns="http://www.w3.org/1999/html"></script>
<link rel="stylesheet" type="text/css"  href="/css/shop.css" media="all">
<div class="row">
    <div class="col ml-8 mr-8 mt-5" id="shop">
        <div class="row">
            <div class="col">
                <img width="250" src="${shop.photoURL}" />
            </div>
            <div class="col">
                <h3>${shop.nameShop}</h3>
                <a href="${shop.url}">${shop.url}</a><br/>
                <#if isAdmin>
                    <label>Owner: </label><a href="/profiles/${shop.owner.id}" > ${shop.owner.firstName} ${shop.owner.lastName}</a><br/>
                <#else>
                    <label>Owner: </label> ${shop.owner.firstName} ${shop.owner.lastName}<br/>
                </#if>
                <label>Description: </label> ${shop.description}<br/>
            </div>
        </div>
        <div class="row mt-5">
            <div class="col">
                <div class="btn-group" role="group" aria-label="Basic example">
                    <a class="btn btn-secondary" href="/adverts?shop=${shop.id}" >All ${shop.nameShop} products</a>
                    <#if shop.owner.id == currentUserId>
                        <a class="btn btn-secondary" href="/sales/${shop.id}">Sales of ${shop.nameShop}</a>
                        <a class="btn btn-secondary" href="/shops/${shop.id}/options">Change info</a>
                    </#if>
                    <#if isAdmin || shop.owner.id == currentUserId>
                        <a class="btn btn-danger" href="/shops/${shop.id}/delete">Delete ${shop.nameShop}</a>
                    </#if>
                </div>
            </div>
        </div>
        <div class="row mt-5">
            <div class="col">
                <div id="comm" name="commentsList"><h5>Comments</h5>
                    <#if currentUserId != 0>
                        <div class="input-group mb-3">
                            <input type="text" id="comBox" maxlength="200" name="commentBox" class="form-control" placeholder="Input your comment" aria-label="Recipient's username" aria-describedby="button-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="button" id="button-addon2" onclick="createShopComment(${shop.id})">Send</button>
                            </div>
                        </div>
                    </#if>
                    <#if shop.comments??>
                        <#list shop.getCommentsOrderByDate() as comment>
                            <div class="row mt-2">
                                <div class="col-10">
                                    <div class="media">
                                        <img width="65" src="${comment.author.photoURL}" class="mr-3" >
                                        <div class="media-body">
                                            <#if isAdmin>
                                                <a href="/profiles/${comment.author.id}"><b class="mt-0">${comment.author.firstName} ${comment.author.lastName}</b></a><i> ${comment.getStringDate()}</i>
                                            <#else>
                                                <b class="mt-0">${comment.author.firstName} ${comment.author.lastName}</b><i> ${comment.getStringDate()}</i>
                                            </#if>
                                            <p>${comment.message}</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-2">
                                    <#if isAdmin || comment.author.id == currentUserId>
                                        <div><button class="btn btn-danger" onclick="deleteShopComment(${shop.id}, ${comment.id})">DELETE</button></div>
                                    </#if>
                                </div>
                            </div>
                        </#list>
                    <#else>
                        No comments.
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>
</@c.page>
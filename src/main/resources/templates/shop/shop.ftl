<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl" />
<@c.page "InterShop">
<script src="/js/shopComments.js" type="text/javascript" xmlns="http://www.w3.org/1999/html"></script>
<link rel="stylesheet" type="text/css"  href="/css/shop.css" media="all">
<div class="row">
    <div class="col ml-8 mr-8 mt-5" id="shop">
        <div class="row">
            <div class="col">
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
                                        <img width="65" height="65" src="${comment.author.photoURL}" class="mr-3" >
                                        <div class="media-body">
                                            <b class="mt-0">${comment.author.firstName} ${comment.author.lastName}</b><i> ${comment.getStringDate()}</i>
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
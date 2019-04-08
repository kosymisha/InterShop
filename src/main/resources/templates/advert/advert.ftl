<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl" />
<@c.page "InterShop">
<script src="/js/advertComments.js" type="text/javascript" xmlns="http://www.w3.org/1999/html"></script>
<link rel="stylesheet" type="text/css"  href="/css/advert.css" media="all">
<div class="row">
    <div class="col ml-8 mr-8 mt-5" id="advert">
        <div class="row">
            <div class="col">
                <img width="250" src="${advert.product.photoURL}" /> <br/>
            </div>
            <div class="col">
                <h3>${advert.product.title}</h3>
                <label><a href="${advert.productURL}">go to ${advert.shop.nameShop}</a></label><br/>
                <label>Category:</label><i> ${advert.product.category.categoryName}</i><br/>
                <label>Price:</label><i> <b>${advert.price}</b> ${advert.currency}</i> <br/>
                <label>Views: </label><i> ${advert.views}</i><br/>
                <label>Description:</label><i id="desc"> ${advert.description}</i><br/>
                <br/>
                    <#if isAdmin || advert.shop.owner.id == currentUserId>
                        <button class="btn btn-danger" onclick="window.location.href = '/adverts/${advert.id}/delete';">DELETE</button>
                    <#elseif isUser>
                        <button type="button" class="btn btn-warning">BUY</button>
                    </#if>
                <br/>
            </div>
        </div>
        <div class="row mt-5">
            <div class="col">
                <div id="comm" name="commentsList"><h5>Comments</h5>
                    <#if currentUserId != 0>
                        <div class="input-group mb-3">
                            <input type="text" id="advComBox" maxlength="200" name="commentBox" class="form-control" placeholder="Input your comment" aria-label="Recipient's username" aria-describedby="button-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="button" id="button-addon2" onclick="createAdvertComment(${advert.id}, '${_csrf.token}')">Send</button>
                            </div>
                        </div>
                    </#if>
                    <#if advert.comments??>
                        <#list advert.getCommentsOrderByDate() as comment>
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
                                        <div><button class="btn btn-danger" onclick="deleteAdvertComment(${advert.id}, ${comment.id})">DELETE</button></div>
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
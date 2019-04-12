<#include "../parts/security.ftl" />
<div id="comm" name="commentsList"><h5>Comments</h5>
                    <#if currentUserId != 0>
                        <div class="input-group mb-3">
                            <input type="text" id="comBox" maxlength="200" name="commentBox" class="form-control" placeholder="Input your comment" aria-label="Recipient's username" aria-describedby="button-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="button" id="button-addon2" onclick="createShopComment(${shopId})">Send</button>
                            </div>
                        </div>
                    </#if>
                    <#if comments??>
                        <#list comments as comment>
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
                                        <div><button class="btn btn-danger" onclick="deleteShopComment(${shopId}, ${comment.id})">DELETE</button></div>
                                    </#if>
                                </div>
                            </div>
                        </#list>
                    <#else>
                        No comments.
                    </#if>
</div>
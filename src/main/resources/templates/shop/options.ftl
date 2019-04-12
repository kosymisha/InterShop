<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl" />
<@c.page "Shop options">
<script src="/js/createShop.js" type="text/javascript" xmlns="http://www.w3.org/1999/html"></script>
<link rel="stylesheet" type="text/css"  href="/css/shop.css" media="all">
<div class="row">
    <div class="col ml-8 mr-8 mt-5" id="shop">
        <div class="row">
            <div class="col">
                <form action="/shops/${shop.id}/options/save" method="post" enctype="multipart/form-data" onsubmit="return isValidChangingForm()">
                    <h5>Shop info</h5>
                    <div class="form-row">
                        <div class="form-group col-md-12">
                            <label id="labelInputShopName" for="inputShopName">Name of your shop</label>
                            <input type="text" name="shopName" class="form-control form-control-sm" id="inputShopName" maxlength="60" value="${shop.nameShop}">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-12">
                            <label id="labelInputShopUrl" for="inputShopUrl">Shop URL</label>
                            <input type="text" name="shopUrl" class="form-control form-control-sm" id="inputShopUrl" maxlength="80" value="${shop.url}">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-12">
                            <label id="labelInputDescription" for="inputDescription">Description</label>
                            <textarea name="description" class="form-control form-control-sm" id="inputDescription" maxlength="500" >${shop.description}</textarea>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label id="labelInputPhoto" for="inputPhoto">Photo</label><br>
                            <input id="inputPhoto" type="file" name="photo_url" value="Add file" />
                        </div>
                    </div>
                    <div class="btn-group" role="group" aria-label="Basic example">
                        <input class="btn btn-secondary" type="submit" value="Save" />
                        <a class="btn btn-secondary" href="/shops/${shop.id}" >Back</a>
                    </div><br>
                    <#if message??>${message}</#if>
                </form>
            </div>
        </div>
    </div>
</div>
</@c.page>
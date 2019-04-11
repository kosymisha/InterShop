<#import "../parts/common.ftl" as c>
<@c.page "InterShop">
<script src="/js/createShop.js" type="text/javascript" xmlns="http://www.w3.org/1999/html"></script>
<div class="row mt-3 ml-3">
    <div class="col col-md-6">
        <form action="/shops/create" method="post" enctype="multipart/form-data" onsubmit="return isValidForm()">
            <h5>Shop info</h5>
            <div class="form-row">
                <div class="form-group col-md-12">
                    <label id="labelInputShopName" for="inputShopName">Name of your shop</label>
                    <input type="text" name="shopName" class="form-control form-control-sm" id="inputShopName" maxlength="60" placeholder="Name">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-12">
                    <label id="labelInputShopUrl" for="inputShopUrl">Shop URL</label>
                    <input type="text" name="shopUrl" class="form-control form-control-sm" id="inputShopUrl" maxlength="80" placeholder="URL">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-12">
                    <label id="labelInputDescription" for="inputDescription">Description</label>
                    <textarea name="description" class="form-control form-control-sm" id="inputDescription" maxlength="500" placeholder="Description"></textarea>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label id="labelInputPhoto" for="inputPhoto">Photo</label><br>
                    <input id="inputPhoto" type="file" name="photo_url" value="Add file" />
                </div>
            </div>
            <div><input class="btn btn-secondary" type="submit" value="Create" /></div>
        </form>
    </div>
</div>
</@c.page>
<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl" />
<@c.page "Create advert">
<script src="/js/searchProduct.js" type="text/javascript" xmlns="http://www.w3.org/1999/html"></script>
<nav>
    <div class="nav nav-tabs" id="nav-tab">
        <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">Create with new product</a>
        <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">Create with existing product</a>
    </div>
</nav>
<div class="tab-content" id="nav-tabContent m-1">

        <div class="tab-pane fade show active m-2" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">

            <label id="categoryLabel">CATEGORY</label>
            <input type="text" class="form-control mb-1" placeholder="Search category..." id="keywordCategory" aria-describedby="basic-addon2" onkeyup="inputCategory()">
                <div class="form-row m-1">
                    <div class="col">
                        <form action="/adverts" method="post" enctype="multipart/form-data" onsubmit="return isValidFormCreateWithProd()">
                            <#include "../parts/categories.ftl" /> <br/>
                            <label id="titleLabel">TITLE</label>
                            <div><input type="text" name="title" class="form-control form-control-sm" id="title" placeholder="Input title" maxlength="80"/></div><br/>
                            <label id="photoLabel">PHOTO</label>
                            <div><input type="file" id="file" name="photo_url" value="Add file" /></div><br/>
                            <label>DESCRIPTION</label>
                            <div><input type="text" name="description" class="form-control form-control-sm" placeholder="Input description" maxlength="200"/></div><br/>
                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label id="priceLabel">PRICE</label>
                                    <div class="input-group input-group-sm">
                                        <div class="input-group-prepend">
                                            <span class="btn btn-outline-secondary">$</span>
                                        </div>
                                        <div><input type="text" name="price" id="priceInput" class="form-control form-control-sm" aria-label="Amount (to the nearest dollar)" pattern="^\d{1, 6}(\.?\d{2,1})$" onkeyup="isValidPrice('priceInput')" placeholder="0.00"/></div>
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label id="shopLabel">SHOP</label>
                                    <select id="shopInput" name="shop" class="form-control form-control-sm">
                                        <option value="EMPTY" >Choose...</option>
                                        <#list shops as shop>
                                            <option value="${shop.nameShop}">${shop.nameShop}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <div><button type="submit" class="btn btn-secondary" >Create</button></div>
                        </form>
                    </div>
                </div>
        </div>
        <div class="tab-pane fade m-3" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
                <div id="productForm">
                    <label>Search by title</label>
                    <div><input type="text" name="title" class="form-control form-control-sm" id="titleProd" placeholder="input title" onkeyup="inputProductTitle()" maxlength="80" /></div><br/>
                    <div id="prods">
                        <#if products??>
                            <div class="card-columns m-2">
                                <#list products as product>
                                    <object name="objectsProd">
                                        <div class="card m-2" >
                                            <img src="${product.photoURL}" /><br/>
                                            <div>${product.title}</div>
                                            <div>${product.category.categoryName}</div>
                                            <div><button class="btn btn-secondary" onclick="chooseProduct('${product.id}', '${currentUserId}')">Add</button></div>
                                        </div>
                                    </object>
                                </#list>
                            </div>
                        <#else>
                            No products.
                        </#if>
                    </div>
                </div>
        </div>
</div>
</@c.page>
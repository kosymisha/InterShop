<#import "../parts/common.ftl" as c>
<@c.page "InterShop">
<script src="/js/searchProduct.js" type="text/javascript" xmlns="http://www.w3.org/1999/html"></script>
<style>
    .catdiv{
        overflow-x: auto;
        overflow-y: hidden;
    }
</style>
<div class="row">
    <div class="col">
        <input type="text" class="form-control" placeholder="Search category..." id="keywordCategory" aria-describedby="basic-addon2" onkeyup="inputCategory()">
        <#include "../parts/categories.ftl" />
        <div class="row">
            <div class="col">
                <form action="/adverts" method="post" enctype="multipart/form-data">
                    <div><input type="text" name="title" id="title" placeholder="input title" onkeyup="inputProductTitle()" /></div>
                    <div><input type="file" name="photo_url" value="Add file" /></div>
                    <div><input type="text" name="description" placeholder="input description" /></div>
                    <div><input type="text" name="price" pattern="^\d{1, 6}(\.?\d{2,1})$" placeholder="input price"/></div>
                    <select name="shop">
                        <#list shops as shop>
                            <option value="${shop.nameShop}">${shop.nameShop}</option>
                        </#list>
                    </select>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <div><input type="submit" value="Create" /></div>
                </form>
            </div>
            <div class="col">
                <div id="prods">
                    Products:<br/>
                    <#if products??>
                        <#list products as product>
                            <object name="objectsProd">
                            <img width="100" height="100" src="${product.photoURL}" /><br/>
                            <div>${product.title}</div>
                            <div>${product.category.categoryName}</div>
                            <div><button ">Add</button></div>
                            <br/>
                            </object>
                        </#list>
                    <#else>
                    No products.
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>
</@c.page>
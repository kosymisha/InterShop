<#import "../parts/common.ftl" as c>
<@c.page "InterShop">
    <form action="/adverts" method="post" enctype="multipart/form-data">
        <input type="text" class="form-control" placeholder="Search category..." id="keywordCategory" aria-describedby="basic-addon2" onkeyup="inputCategory()">
        <#include "../parts/categories.ftl" />
        <div><input type="text" name="title" placeholder="input title" /></div>
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
</@c.page>
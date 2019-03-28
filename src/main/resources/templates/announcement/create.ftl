<#import "../parts/common.ftl" as c>
<@c.page "InterShop"  "">
    <form action="/announcements" method="post" enctype="multipart/form-data">
        <select name="category">
            <option value="Coats & Jackets">Coats & Jackets</option>
            <option value="Pants">Pants</option>
            <option value="Shirts">Shirts</option>
            <option value="Athletic Shoes">Athletic Shoes</option>
        </select>
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
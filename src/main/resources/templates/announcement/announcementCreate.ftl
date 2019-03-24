<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
</head>
<body>
<body>
    <form action="/announcements" method="post" enctype="multipart/form-data">
        <select name="category">
            <option value="Coats & Jackets">Coats & Jackets</option>
            <option value="Pants">Pants</option>
            <option value="Shirts">Shirts</option>
            <option value="Athletic Shoes">Athletic Shoes</option>
        </select>
        <div><input type="text" name="title" placeholder="input title" /></div>
        <div><input type="text" name="product_url" placeholder="input product url" /></div>
        <div><input type="file" name="photo_url" value="Add file" /></div>
        <div><input type="text" name="description" placeholder="input description" /></div>
        <div><input type="text" name="price" placeholder="input price" /></div>
        <select name="shop">
            <#list shops as shop>
                <option value="${shop.nameShop}">${shop.nameShop}</option>
            </#list>
        </select>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div><input type="submit" value="Create" /></div>
    </form>

</body>
</html>
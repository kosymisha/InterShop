<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shops</title>
</head>
<body>

List of shops:
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>URL</th>
        <th>Owner</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list shopList as shop>
<tr>
    <td>${shop.nameShop}</td>
    <td>${shop.url}</td>
    <td>${shop.owner.firstName} ${shop.owner.lastName} </td>
    <td>

<div>
    <#list user.roles as role>

        <#if role == 'ADMIN'>
        <a href="/shops/${shop.id}">more</a> <a href="/shops/${shop.id}/delete">delete</a>

        <#elseif shop.owner.id == user.id>
        <a href="/shops/${shop.id}">more</a> <a href="/shops/${shop.id}/delete">delete</a> <a href="">create</a>

        <#elseif role == 'USER' || role == 'SELLER'>
        <a href="/shops/${shop.id}">more</a>

        </#if>
    </#list>
</div>

    </td>
</tr>
    </#list>
    </tbody>
</table>
</body>
</html>
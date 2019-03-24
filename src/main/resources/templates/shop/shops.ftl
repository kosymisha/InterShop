<#import "../parts/common.ftl" as c>
<@c.page "InterShop" "">
<a href="/shops/create" >Create new shop</a>
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
    <td><a href="/shops/${shop.id}">more</a></td>
</tr>
    </#list>
    </tbody>
</table>
</@c.page>
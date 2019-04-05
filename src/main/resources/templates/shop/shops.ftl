<#import "../parts/common.ftl" as c>
<@c.page "InterShop">
<a href="/shops/create" >Create new shop</a>
<table>
    <thead>
    <tr>
        <th width="150"></th>
        <th width="200">Name</th>
        <th width="200">Owner</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list shopList as shop>
<tr>
    <td><img width="100" height="100" src="${shop.photoURL}" /></td>
    <td>${shop.nameShop}</td>
    <td>${shop.owner.firstName} ${shop.owner.lastName} </td>
    <td><a href="/shops/${shop.id}">more</a></td>
</tr>
    </#list>
    </tbody>
</table>
</@c.page>
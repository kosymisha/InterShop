<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl" />
<@c.page "Shops">
<#if isSeller>
<a href="/shops/create" class="btn btn-secondary mt-4 mb-4">Create new shop</a>
</#if>
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
    <#if isAdmin>
        <td><a href="/profiles/${shop.owner.id}">${shop.owner.firstName} ${shop.owner.lastName}</a> </td>
    <#else>
        <td>${shop.owner.firstName} ${shop.owner.lastName} </td>
    </#if>
    <td><a href="/shops/${shop.id}">more</a></td>
</tr>
    </#list>
    </tbody>
</table>
</@c.page>
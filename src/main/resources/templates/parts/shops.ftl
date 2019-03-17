<#macro shops shopList>
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
<#nested>
    </td>
</tr>
</#list>
    </tbody>
</table>
</#macro>
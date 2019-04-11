<#import "../parts/common.ftl" as c>
<@c.page "InterShop">
List of users:
<table>
    <thead>
    <tr>
        <th>name</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
<#list users as user>
    <tr>
        <td>${user.firstName}</td>
        <td><a href="/profiles/${user.id}">Edit</a></td>
    </tr>
</#list>
    </tbody>
</table>
</@c.page>
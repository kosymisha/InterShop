<#import "../parts/common.ftl" as c>
<@c.page "InterShop" "">
List of users:
<table>
    <thead>
    <tr>
        <th>Email</th>
        <th>Role</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
<#list users as user>
    <tr>
        <td>${user.email}</td>
        <td><#list user.roles as role>${role}<#sep>, </#list></td>
        <td><a href="/profiles/${user.id}">Edit</a></td>
    </tr>
</#list>
    </tbody>
</table>
</@c.page>
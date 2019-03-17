<#import "parts/common.ftl" as c>

<@c.page "User Manager">
User editor.
<form action="/profiles/${user.id}/options" method="post">
    <select name="combo">
        <#list roles as role>
            <option value="${role}">${role}</option>
        </#list>
    </select>
    <input type="hidden" value="${user.id}" name="userId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit" >Save</button>
</form>
</@c.page>
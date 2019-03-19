<#import "../parts/common.ftl" as c>

<@c.page "Options">
<form action="/profiles/current/options" method="post">
    <div><input type="text" name="firstname" value="${user.firstName}" /></div>
    <div><input type="text" name="lastname" value="${user.lastName}" /></div>
    <div><input type="text" name="email" value="${user.email}" /></div>
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <div><button type="submit">Save</button></div>
</form>

</@c.page>
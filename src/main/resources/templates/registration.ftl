<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page "Registration">
<form action="/registration" method="post">
    <div><label>First Name: <input type="text" name="firstName" /></label></div>

    <div><label>Last Name: <input type="text" name="lastName" /></label></div>

    <div><label>Email: <input type="text" name="email" /></label></div>

    <div><label>Password: <input type="password" name="password" /></label></div>

    <select name="role">
        <option value="USER">USER</option>
        <option value="ADMIN">ADMIN</option>
    </select>

    <input type="hidden" name="_csrf" value="${_csrf.token}" />

    <div><input type="submit" value="Ok" /></div>
</form>

</@c.page>
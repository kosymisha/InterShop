<#import "parts/login.ftl" as l>
<#import "parts/bootstrapLoad.ftl" as bs>
<#import "parts/common.ftl" as c>
<@c.page "InterShop" "">
<form action="/registration" method="post" enctype="multipart/form-data">
    <div><label>First Name: <input type="text" name="firstName" /></label></div>
    <div><label>Last Name: <input type="text" name="lastName" /></label></div>
    <div><input type="file" name="photo_url" value="Add file" /></div>
    <div><label>Email: <input type="text" name="email" /></label></div>
    <div><label>Password: <input type="password" name="password" /></label></div>
    <select name="role">
        <option value="USER">USER</option>
        <option value="SELLER">SELLER</option>
    </select>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <div><input type="submit" value="Ok" /></div>
</form>
</@c.page>
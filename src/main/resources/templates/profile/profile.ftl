<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
</head>
<body>
<div><img width="200" height="200" src="/img/${profileUser.photoURL?if_exists}" /></div>
<div><label>First Name: </label>${profileUser.firstName}</div>
<div><label>Last Name: </label>${profileUser.lastName}</div>
<div><label>Email: </label>${profileUser.email}</div>
    <#list user.roles as role>
        <#if role == 'ADMIN'>
            <form action="/profiles/${profileUser.id}/role" method="post">
                <select name="role">
                    <option value="USER">USER</option>
                    <option value="ADMIN">ADMIN</option>
                </select>
                <input type="hidden" value="${profileUser.id}" name="userId">
                <input type="hidden" value="${_csrf.token}" name="_csrf">
                <button type="submit" >Save</button>
            </form>
        </#if>
    </#list>
</body>
</html>
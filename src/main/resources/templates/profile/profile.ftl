<#import "../parts/common.ftl" as c>
<#import "../login.ftl" as l>
<#include "../parts/security.ftl" />

<@c.page "InterShop">
<#if currentUserId == profileUser.id>
    <br/>
    <@l.l.logout/>
</#if>
<div><img width="200" height="200" src="${profileUser.photoURL?if_exists}" /></div>
<div><label>First Name: </label>${profileUser.firstName}</div>
<div><label>Last Name: </label>${profileUser.lastName}</div>
<div><label>Email: </label>${profileUser.email}</div>
        <#if isAdmin>
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

</@c.page>
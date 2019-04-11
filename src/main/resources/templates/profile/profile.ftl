<#import "../parts/common.ftl" as c>
<#import "../login.ftl" as l>
<#include "../parts/security.ftl" />

<@c.page "InterShop">
<script src="/js/profile.js" type="text/javascript" xmlns="http://www.w3.org/1999/html"></script>
<link rel="stylesheet" type="text/css"  href="/css/profile.css" media="all">
<div class="row">
    <div class="col ml-8 mr-8 mt-5" id="profile">
        <div class="row">
            <div class="col">
                <img width="250" src="${profileUser.photoURL?if_exists}" />
            </div>
            <div class="col">
                <h3>Info</h3>
                <#if !profileUser.isActive()>
                    Profile is not active.
                </#if>
                <div><label>First Name: </label> ${profileUser.firstName}</div>
                <div><label>Last Name: </label> ${profileUser.lastName}</div>
                <div><label>Email: </label> ${profileUser.email}</div>
                <div><label>Role: </label> ${profileUser.roles[0]}</div>
                <#if currentUserId == profileUser.id>
                    <@l.l.logout/>
                </#if>
                <#if profileUser.id == currentUserId>
                    <form action="/profiles/${profileUser.id}/delete" method="get">
                        <button class="btn btn-danger" type="submit">Delete profile</button>
                    </form>
                <#elseif  isAdmin>
                    <#if profileUser.isActive()>
                    <form method="get" action="/profiles/${profileUser.id}/active">
                        <input type="hidden" name="value" value="false">
                        <button class="btn btn-danger">Set non active</button>
                    </form>
                    <#else >
                    <form method="get" action="/profiles/${profileUser.id}/active">
                        <input type="hidden" name="value" value="true">
                        <button class="btn btn-warning">Set active</button>
                    </form>
                    </#if>
                </#if>
                <#if isAdmin>
                    <form action="/profiles/${profileUser.id}/role" method="post">
                        <select name="role">
                            <option value="USER">USER</option>
                            <option value="ADMIN">ADMIN</option>
                        </select>
                        <input type="hidden" value="${profileUser.id}" name="userId">
                        <button type="submit" >Save</button>
                    </form>
                </#if>
            </div>
        </div>
    </div>
</div>
</@c.page>
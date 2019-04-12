<#import "../parts/common.ftl" as c>
<#import "../login.ftl" as l>
<#include "../parts/security.ftl" />
<@c.page "${profileUser.firstName} ${profileUser.lastName}">
<script src="/js/profile.js" type="text/javascript" xmlns="http://www.w3.org/1999/html"></script>
<link rel="stylesheet" type="text/css"  href="/css/profile.css" media="all">
<div class="row">
    <div class="col ml-8 mr-8 mt-5" id="profile">
        <div class="row">
            <div class="col-3">
                <img width="250" src="${profileUser.photoURL?if_exists}" />
            </div>
            <div class="col-9">
                <h3>Info</h3>
                <#if !profileUser.isActive()>
                    Profile is not active.
                </#if>
                <div><label>First Name: </label> ${profileUser.firstName}</div>
                <div><label>Last Name: </label> ${profileUser.lastName}</div>
                <div><label>Email: </label> ${profileUser.email}</div>
                <div><label>Role: </label> ${profileUser.roles[0]}</div>
                <#if isAdmin && !profileUser.isSeller()>
                    <form action="/profiles/${profileUser.id}/role" method="post" onsubmit="return isValidRolForm()">
                        <div class="input-group">
                            <select class="custom-select" id="inputRole" name="role" aria-label="Example select with button addon">
                                <option value="EMPTY">Choose...</option>
                                <option value="USER">USER</option>
                                <option value="ADMIN">ADMIN</option>
                            </select>
                            <input type="hidden" value="${profileUser.id}" name="userId">
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="submit" >Save</button>
                            </div>
                        </div>
                    </form>
                </#if>
                <div class="btn-group mt-1" role="group" aria-label="Basic example">
                    <#if currentUserId == profileUser.id> <!--
                        <form action="/logout" method="post">
                            <button class="btn btn-primary" type="submit">Sign out</button>
                        </form> -->
                        <a href="/logout" class="btn btn-primary" >Sign out</a>
                    </#if>
                    <#if profileUser.id == currentUserId>
                    <!--
                        <form action="/profiles/{profileUser.id}/delete" method="get">
                            <button class="btn btn-danger" type="submit">Delete profile</button>
                        </form> -->
                        <a href="/profiles/${profileUser.id}/delete" class="btn btn-danger">Delete profile</a>
                        <a href="/profiles/my/options" class="btn btn-secondary">Change info</a>
                        <a href="/" class="btn btn-secondary">Change password</a>
                    <#elseif  isAdmin>
                        <#if profileUser.isActive()> <!--
                        <form method="get" action="/profiles/{profileUser.id}/active">
                            <input type="hidden" name="value" value="false">
                            <button class="btn btn-danger">Set non active</button>
                        </form> -->
                        <a href="/profiles/${profileUser.id}/active?value=false" class="btn btn-danger">Set non active</a>
                        <#else > <!--
                        <form method="get" action="/profiles/${profileUser.id}/active">
                            <input type="hidden" name="value" value="true">
                            <button class="btn btn-warning">Set active</button>
                        </form> -->
                        <a href="/profiles/${profileUser.id}/active?value=true" class="btn btn-warning">Set active</a>
                        </#if>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>
</@c.page>
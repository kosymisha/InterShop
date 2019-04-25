<#import "../parts/common.ftl" as c>
<#import "../login.ftl" as l>
<#include "../parts/security.ftl" />
<@c.page "${profileUser.firstName} ${profileUser.lastName}">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="/js/profile.js" type="text/javascript" xmlns="http://www.w3.org/1999/html"></script>
<script src="/js/bankCard.js" type="text/javascript" xmlns="http://www.w3.org/1999/html"></script>
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
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputGroupSelect01">Role</label>
                            </div>
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
                    <#if currentUserId == profileUser.id>
                        <a href="/logout" class="btn btn-primary" >Sign out</a>
                    </#if>
                    <#if profileUser.id == currentUserId>
                        <a class="btn btn-danger" onclick="deleteProfile('${profileUser.id}')">Delete profile</a>
                        <a href="/profiles/my/options" class="btn btn-secondary">Settings</a>
                    <#elseif  isAdmin>
                        <#if profileUser.isActive()>
                        <a onclick="setNonActiveProfile('${profileUser.id}')" class="btn btn-danger">Set non active</a>
                        <#else >
                        <a onclick="setActiveProfile('${profileUser.id}')" class="btn btn-warning">Set active</a>
                        </#if>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>
</@c.page>
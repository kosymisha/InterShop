<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl" />
<@c.page "Password changing">
<script src="/js/profile.js" type="text/javascript" xmlns="http://www.w3.org/1999/html"></script>
<link rel="stylesheet" type="text/css"  href="/css/profile.css" media="all">
<div class="row">
    <div class="col ml-8 mr-8 mt-5" id="profile">
        <div class="row">
            <div class="col">
                <form action="/profiles/my/password" method="post" enctype="multipart/form-data" onsubmit="return isValidPasswordForm()">
                    <h5>Password changing</h5>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label id="labelCurrentPass" for="inputCurrentPass">Current password</label>
                            <input type="password" name="currentPassword" maxlength="20" class="form-control form-control-sm" id="inputCurrentPass" >
                            <#if message??><small>${message}</small></#if>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label id="labelPass" for="inputPass">Password</label>
                            <input type="password" name="password" maxlength="20" class="form-control form-control-sm" id="inputPass" placeholder="6-20">
                            <small id="hint"></small>
                        </div>
                        <div class="form-group col-md-6">
                            <label id="labelConfirmPass" for="inputConfirmPass">Confirm password</label>
                            <input type="password" name="confirmPassword" maxlength="20" class="form-control form-control-sm" id="inputConfirmPass" >
                        </div>
                    </div>
                    <div class="btn-group" role="group" aria-label="Basic example">
                        <button type="submit" class="btn btn-primary">Save</button>
                        <a href="/profiles/my" class="btn btn-secondary" >Back</a>
                    </div><br/>
                </form>
            </div>
        </div>
    </div>
</div>
</@c.page>
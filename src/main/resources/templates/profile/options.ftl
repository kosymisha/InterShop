<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl" />
<@c.page "Profile options">
<script src="/js/profile.js" type="text/javascript" xmlns="http://www.w3.org/1999/html"></script>
<link rel="stylesheet" type="text/css"  href="/css/profile.css" media="all">
<div class="row">
    <div class="col ml-8 mr-8 mt-5" id="profile">
        <div class="row">
            <div class="col">
                <form action="/profiles/my/options/save" method="post" enctype="multipart/form-data" onsubmit="return isValidChangingForm()">
                    <h5>User info</h5>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label id="labelFirstName" for="inputFirstName4">First name</label>
                            <input type="text" name="firstName" maxlength="20" class="form-control form-control-sm" id="inputFirstName4" value="${profileUser.firstName}">
                        </div>
                        <div class="form-group col-md-6">
                            <label id="labelLastName" for="inputLastName4">Last name</label>
                            <input type="text" name="lastName" maxlength="20" class="form-control form-control-sm" id="inputLastName4" value="${profileUser.lastName}">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label id="labelInputPhoto" for="inputPhoto4">Photo</label><br>
                            <input id="inputPhoto4" type="file" name="photo_url" value="Add file" />
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-12">
                            <label id="labelInputEmail" for="inputEmail4">Email</label>
                            <input type="email" name="email" class="form-control form-control-sm" id="inputEmail4" maxlength="50" value="${profileUser.email}">
                        </div>
                    </div>
                    <h5>Card info</h5>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label id="labelInputFNCard" for="inputFirstNameCard">First name</label>
                            <input type="text" name="firstNameCard" maxlength="20" class="form-control form-control-sm" id="inputFirstNameCard" value="${profileUser.card.firstNameCard}">
                        </div>
                        <div class="form-group col-md-6">
                            <label id="labelInputLNCard" for="inputLastNameCard">Last name</label>
                            <input type="text" name="lastNameCard" maxlength="20" class="form-control form-control-sm" id="inputLastNameCard" value="${profileUser.card.lastNameCard}">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label id="labelInputNumber" for="inputNumber">Number</label>
                            <input type="text" name="numberCard" maxlength="16" class="form-control form-control-sm" id="inputNumber" value="${profileUser.card.numberCard}">
                        </div>
                        <div class="form-group col-md-3">
                            <label id="labelInputMonth" for="inputMonth">Month</label>
                            <input type="text" name="monthCard" maxlength="2" class="form-control form-control-sm" id="inputMonth" value="${profileUser.card.month}">
                        </div>
                        <div class="form-group col-md-3">
                            <label id="labelInputYear" for="inputYear">Year</label>
                            <input type="text" name="yearCard" maxlength="2" class="form-control form-control-sm" id="inputYear" value="${profileUser.card.year}">
                        </div>
                    </div>
                    <div class="btn-group" role="group" aria-label="Basic example">
                        <button type="submit" class="btn btn-secondary">Save</button>
                        <a href="/profiles/my" class="btn btn-secondary" >Back</a>
                    </div><br/>
                    <#if message??>${message}</#if>
                </form>
            </div>
        </div>
    </div>
</div>
</@c.page>
<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl" />
<@c.page "Profile options">
<script src="/js/profile.js" type="text/javascript" xmlns="http://www.w3.org/1999/html"></script>
<script src="/js/bankCard.js" type="text/javascript" xmlns="http://www.w3.org/1999/html"></script>
<link rel="stylesheet" type="text/css"  href="/css/profile.css" media="all">
<nav>
    <div class="nav nav-tabs" id="nav-tab">
        <a class="nav-item nav-link active" id="nav-UserInfo-tab" data-toggle="tab" href="#nav-UserInfo" role="tab" aria-controls="nav-UserInfo" aria-selected="true">User info</a>
        <a class="nav-item nav-link" id="nav-CardInfo-tab" data-toggle="tab" href="#nav-CardInfo" role="tab" aria-controls="nav-CardInfo" aria-selected="false">Card info</a>
        <a class="nav-item nav-link" id="nav-Password-tab" data-toggle="tab" href="#nav-Password" role="tab" aria-controls="nav-Password" aria-selected="false">Password</a>
    </div>
</nav>
<div class="tab-content" id="nav-tabContent m-1">
    <div class="tab-pane fade show active m-2" id="nav-UserInfo" role="tabpanel" aria-labelledby="nav-UserInfo-tab">
        <div class="row">
            <div class="col ml-8 mr-8 mt-5" id="profile">
                <div class="row">
                    <div class="col">
                        <form action="/profiles/my/options" method="post" enctype="multipart/form-data" onsubmit="return isValidChangingForm()">
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
                            <div class="btn-group" role="group" aria-label="Basic example">
                                <button type="submit" class="btn btn-primary">Save</button>
                                <a href="/profiles/my" class="btn btn-secondary" >Back</a>
                            </div><br/>
                    <#if message??>${message}</#if>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="tab-pane fade show m-2" id="nav-CardInfo" role="tabpanel" aria-labelledby="nav-CardInfo-tab">
        <div class="row">
            <div class="col ml-8 mr-8 mt-5" id="profile">
                <div class="row">
                    <div class="col">
                        <h5>Card info</h5>
                        <div id="table-wrapper">
                            <div id="table-scroll">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th scope="col">Number</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Date</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody id="tBody">
                                    <tr class="rows">
                                        <td scope="row">ends at ${activeCard.getLastNumbers()}</td>
                                        <td>${activeCard.firstNameCard} ${activeCard.lastNameCard}</td>
                                        <td>${activeCard.month}/${activeCard.year}</td>
                                        <td>
                                            DEFAULT
                                        </td>
                                        <td>

                                        </td>
                                    </tr>
                                        <#list nonActiveCards as card>
                                            <tr class="rows">
                                                <td scope="row">ends at ${card.getLastNumbers()}</td>
                                                <td>${card.firstNameCard} ${card.lastNameCard}</td>
                                                <td>${card.month}/${card.year}</td>
                                                <td>
                                                    <button class="btn btn-secondary" onclick="makeDefault('${card.getId()}')">MAKE DEFAULT</button>
                                                </td>
                                                <td>
                                                    <button class="btn btn-danger" onclick="deleteBankCard('${card.getId()}')" >DELETE</button>
                                                </td>
                                            </tr>
                                        </#list>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <h5>Add new card</h5>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label id="labelInputFNCard" for="inputFirstNameCard">First name</label>
                                    <input type="text" name="firstNameCard" maxlength="20" class="form-control form-control-sm" id="inputFirstNameCard" >
                                </div>
                                <div class="form-group col-md-6">
                                    <label id="labelInputLNCard" for="inputLastNameCard">Last name</label>
                                    <input type="text" name="lastNameCard" maxlength="20" class="form-control form-control-sm" id="inputLastNameCard" >
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label id="labelInputNumber" for="inputNumber">Number</label>
                                    <input type="text" name="numberCard" maxlength="16" class="form-control form-control-sm" id="inputNumber" >
                                </div>
                                <div class="form-group col-md-3">
                                    <label id="labelInputMonth" for="inputMonth">Month</label>
                                    <input type="text" name="monthCard" maxlength="2" class="form-control form-control-sm" id="inputMonth" >
                                </div>
                                <div class="form-group col-md-3">
                                    <label id="labelInputYear" for="inputYear">Year</label>
                                    <input type="text" name="yearCard" maxlength="2" class="form-control form-control-sm" id="inputYear" >
                                </div>
                            </div>
                            <div class="btn-group" role="group" aria-label="Basic example">
                                <button onclick="createBankCard()" class="btn btn-primary">Add</button>
                                <a href="/profiles/my" class="btn btn-secondary" >Back</a>
                            </div><br/>
                    <#if message??>${message}</#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="tab-pane fade show m-2" id="nav-Password" role="tabpanel" aria-labelledby="nav-Password-tab">
        <div class="row">
            <div class="col ml-8 mr-8 mt-5" id="profile">
                <div class="row">
                    <div class="col">
                            <h5>Password changing</h5>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label id="labelCurrentPass" for="inputCurrentPass">Current password</label>
                                    <input type="password" name="currentPassword" maxlength="20" class="form-control form-control-sm" id="inputCurrentPass" >
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label id="labelPass" for="inputPass">New password</label>
                                    <input type="password" name="password" maxlength="20" class="form-control form-control-sm" id="inputPass" placeholder="6-20">
                                    <small id="hint"></small>
                                </div>
                                <div class="form-group col-md-6">
                                    <label id="labelConfirmPass" for="inputConfirmPass">Confirm new password</label>
                                    <input type="password" name="confirmPassword" maxlength="20" class="form-control form-control-sm" id="inputConfirmPass" >
                                </div>
                            </div>
                            <div class="btn-group" role="group" aria-label="Basic example">
                                <button onclick="newPassword()" class="btn btn-primary">Save</button>
                                <a href="/profiles/my" class="btn btn-secondary" >Back</a>
                            </div><br/>
                            <div id="passMessage"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</@c.page>
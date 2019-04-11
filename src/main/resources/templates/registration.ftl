<#import "parts/login.ftl" as l>
<#import "parts/bootstrapLoad.ftl" as bs>
<#import "parts/common.ftl" as c>
<@c.page "InterShop">
<script src="/js/registration.js" type="text/javascript" xmlns="http://www.w3.org/1999/html"></script>
<div class="row mt-3 ml-3">
    <div class="col col-md-6">
        <form action="/registration" method="post" enctype="multipart/form-data" onsubmit="return isValidForm()">
            <h5>User info</h5>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label id="labelFirstName" for="inputFirstName4">First name</label>
                    <input type="text" name="firstName" maxlength="20" class="form-control form-control-sm" id="inputFirstName4" placeholder="First name">
                </div>
                <div class="form-group col-md-6">
                    <label id="labelLastName" for="inputLastName4">Last name</label>
                    <input type="text" name="lastName" maxlength="20" class="form-control form-control-sm" id="inputLastName4" placeholder="Last name">
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
                    <input type="email" name="email" class="form-control form-control-sm" id="inputEmail4" maxlength="50" placeholder="Email">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label id="labelInputPass" for="inputPassword4">Password</label>
                    <input type="password" name="password" maxlength="20" class="form-control form-control-sm" id="inputPassword4" placeholder="Password">
                </div>
                <div class="form-group col-md-6">
                    <label id="labelInputConfirmPass" for="inputPasswordConfirm4">Confirm password</label>
                    <input type="password" name="confirmPassword" maxlength="20" class="form-control form-control-sm" id="inputPasswordConfirm4" placeholder="Confirm password">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label id="labelInputRole" for="inputRole">Role</label>
                    <select id="inputRole" name="role" class="custom-select custom-select-sm">
                        <option value="EMPTY" selected>Choose...</option>
                        <option value="USER">USER</option>
                        <option value="SELLER">SELLER</option>
                    </select>
                </div>
            </div>
            <h5>Card info</h5>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label id="labelInputFNCard" for="inputFirstNameCard">First name</label>
                    <input type="text" name="firstNameCard" maxlength="20" class="form-control form-control-sm" id="inputFirstNameCard" >
                </div>
                <div class="form-group col-md-6">
                    <label id="labelInputLNCard" for="inputLastNameCard">Last name</label>
                    <input type="text" name="lastNameCard" maxlength="20" class="form-control form-control-sm" id="inputLastNameCard">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label id="labelInputNumber" for="inputNumber">Number</label>
                    <input type="text" name="numberCard" maxlength="16" class="form-control form-control-sm" id="inputNumber" placeholder="0000000000000000">
                </div>
                <div class="form-group col-md-3">
                    <label id="labelInputMonth" for="inputMonth">Month</label>
                    <input type="text" name="monthCard" maxlength="2" class="form-control form-control-sm" id="inputMonth" placeholder="00">
                </div>
                <div class="form-group col-md-3">
                    <label id="labelInputYear" for="inputYear">Year</label>
                    <input type="text" name="yearCard" maxlength="2" class="form-control form-control-sm" id="inputYear" placeholder="00">
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Create</button>
        </form>
    </div>
</div>
</@c.page>
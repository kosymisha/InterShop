function isValidRolForm () {
    return document.getElementById("inputRole").options[document.getElementById("inputRole").selectedIndex].value !== "EMPTY";
}

function isValidChangingForm () {
    var isValidFlag = true;
    if(!/^[a-zA-Z]+$/.test(document.getElementById("inputFirstName4").value)) {
        document.getElementById("labelFirstName").style.color = "red";
        isValidFlag = false;
    } else { document.getElementById("labelFirstName").style.color = "#495057"; }

    if(!/^[a-zA-Z]+$/.test(document.getElementById("inputLastName4").value)) {
        document.getElementById("labelLastName").style.color = "red";
        isValidFlag = false;
    } else { document.getElementById("labelLastName").style.color = "#495057"; }

    if (!/^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$/.test(document.getElementById("inputEmail4").value)) {
        document.getElementById("labelInputEmail").style.color = "red";
        isValidFlag = false;
    } else { document.getElementById("labelInputEmail").style.color = "#495057"; }

    if (document.getElementById("inputPassword4").value !== document.getElementById("inputPasswordConfirm4").value) {
        document.getElementById("labelInputConfirmPass").style.color = "red";
        isValidFlag = false;
    } else { document.getElementById("labelInputConfirmPass").style.color = "#495057"; }

    if (document.getElementById("inputPassword4").value.length < 6 || document.getElementById("inputPassword4").value.length > 20) {
        document.getElementById("labelInputPass").style.color = "red";
        isValidFlag = false;
    } else { document.getElementById("labelInputPass").style.color = "#495057"; }

    if (document.getElementById("inputPasswordConfirm4").value.length < 6 || document.getElementById("inputPasswordConfirm4").value.length > 20) {
        document.getElementById("labelInputConfirmPass").style.color = "red";
        isValidFlag = false;
    } else { document.getElementById("labelInputConfirmPass").style.color = "#495057"; }

    if (document.getElementById("inputRole").options[document.getElementById("inputRole").selectedIndex].value === "EMPTY") {
        document.getElementById("labelInputRole").style.color = "red";
        isValidFlag = false;
    } else { document.getElementById("labelInputRole").style.color = "#495057"; }

    if(!/^[a-zA-Z]+$/.test(document.getElementById("inputFirstNameCard").value)) {
        document.getElementById("labelInputFNCard").style.color = "red";
        isValidFlag = false;
    } else { document.getElementById("labelInputFNCard").style.color = "#495057"; }

    if(!/^[a-zA-Z]+$/.test(document.getElementById("inputLastNameCard").value)) {
        document.getElementById("labelInputLNCard").style.color = "red";
        isValidFlag = false;
    } else { document.getElementById("labelInputLNCard").style.color = "#495057"; }

    if (!/^[0-9]+$/.test(document.getElementById("inputNumber").value)) {
        document.getElementById("labelInputNumber").style.color = "red";
        isValidFlag = false;
    } else { document.getElementById("labelInputNumber").style.color = "#495057"; }

    if (!/^[0-9]+$/.test(document.getElementById("inputMonth").value)) {
        document.getElementById("labelInputMonth").style.color = "red";
        isValidFlag = false;
    } else { document.getElementById("labelInputMonth").style.color = "#495057"; }

    if (!/^[0-9]+$/.test(document.getElementById("inputYear").value)) {
        document.getElementById("labelInputYear").style.color = "red";
        isValidFlag = false;
    } else { document.getElementById("labelInputYear").style.color = "#495057"; }

    return isValidFlag;
}

function isValidPasswordForm () {
    var isValidFlag = true;

    if (document.getElementById("inputPass").value !== document.getElementById("inputConfirmPass").value) {
        document.getElementById("labelConfirmPass").style.color = "red";
        document.getElementById("hint").innerText = "Passwords do not match";
        isValidFlag = false;
    } else { document.getElementById("labelConfirmPass").style.color = "#495057"; }

    if (document.getElementById("inputPass").value.length < 6) {
        document.getElementById("labelPass").style.color = "red";
        document.getElementById("hint").innerText = "Too small (size should be between 6 and 20 characters)";
        isValidFlag = false;
    } else { document.getElementById("labelPass").style.color = "#495057"; }

    return isValidFlag;
}

function newPassword () {
    if (isValidPasswordForm()) {
        var httpRequest = new XMLHttpRequest();
        httpRequest.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                document.getElementById("passMessage").innerText = this.responseText;
            }
        };
        httpRequest.open("POST", "/profiles/my/password", true);
        httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        httpRequest.send("currentPassword=" + document.getElementById("inputCurrentPass").value + "&" +
                         "password=" + document.getElementById("inputPass").value + "&" +
                         "confirmPassword=" + document.getElementById("inputConfirmPass").value);
    }
}

function deleteProfile (profileId) {
    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200) {
            window.location.replace('/logout');
        }
    };
    httpRequest.open("DELETE", "/profiles/" + profileId, true);
    httpRequest.send();
}

function setActiveProfile (profileId) {
    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200) {
            location.reload();
        }
    };
    httpRequest.open("PUT", "/profiles/" + profileId + "/active?value=true", true);
    httpRequest.send();
}

function setNonActiveProfile (profileId) {
    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200) {
            location.reload();
        }
    };
    httpRequest.open("PUT", "/profiles/" + profileId + "/active?value=false", true);
    httpRequest.send();
}
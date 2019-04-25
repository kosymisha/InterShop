function makeDefault (cardId) {
    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200) {
            document.getElementById("tBody").innerHTML = this.responseText;
        }
    };
    httpRequest.open("PUT", "/profiles/my/bankCards/" + cardId, true);
    httpRequest.send();
}

function deleteBankCard (cardId) {
    debugger;
    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200) {
            document.getElementById("tBody").innerHTML = this.responseText;
        }
    };
    httpRequest.open("DELETE", "/profiles/my/bankCards/" + cardId, true);
    httpRequest.send();
}

function createBankCard() {
    if (isValidBankCardForm()) {
        var httpRequest = new XMLHttpRequest();
        httpRequest.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                document.getElementById("tBody").innerHTML = this.responseText;
            }
        };
        httpRequest.open("POST", "/profiles/my/bankCards", true);
        httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        httpRequest.send("firstNameCard=" + document.getElementById("inputFirstNameCard").value + "&" +
                        "lastNameCard=" + document.getElementById("inputLastNameCard").value + "&" +
                        "numberCard=" + document.getElementById("inputNumber").value + "&" +
                        "monthCard=" + document.getElementById("inputMonth").value + "&" +
                        "yearCard=" + document.getElementById("inputYear").value);
    }
}

function isValidBankCardForm () {
    var isValidFlag = true;

    if(!/^[a-zA-Z]+$/.test(document.getElementById("inputFirstNameCard").value)) {
        document.getElementById("labelInputFNCard").style.color = "red";
        isValidFlag = false;
    } else { document.getElementById("labelInputFNCard").style.color = "#495057"; }

    if(!/^[a-zA-Z]+$/.test(document.getElementById("inputLastNameCard").value)) {
        document.getElementById("labelInputLNCard").style.color = "red";
        isValidFlag = false;
    } else { document.getElementById("labelInputLNCard").style.color = "#495057"; }

    if ((!/^[0-9]+$/.test(document.getElementById("inputNumber").value) ||
            (document.getElementById("inputNumber").value.length !== 16))) {
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
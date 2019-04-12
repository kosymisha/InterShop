function isValidForm() {
    var isValidFlag = true;
    if(!/^[a-zA-Z]+$/.test(document.getElementById("inputFirstName4").value)) {
        document.getElementById("labelFirstName").style.color = "red";
        isValidFlag = false;
    } else { document.getElementById("labelFirstName").style.color = "#495057"; }

    if(!/^[a-zA-Z]+$/.test(document.getElementById("inputLastName4").value)) {
        document.getElementById("labelLastName").style.color = "red";
        isValidFlag = false;
    } else { document.getElementById("labelLastName").style.color = "#495057"; }

    if (document.getElementById("inputPhoto4").value === null || document.getElementById("inputPhoto4").value === "") {
        document.getElementById("labelInputPhoto").style.color = "red";
        isValidFlag = false;
    } else { document.getElementById("labelInputPhoto").style.color = "#495057"}

    if (!/^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$/.test(document.getElementById("inputEmail4").value)) {
        document.getElementById("labelInputEmail").style.color = "red";
        isValidFlag = false;
    } else { document.getElementById("labelInputEmail").style.color = "#495057"; }

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
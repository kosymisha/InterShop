function isValidForm () {
    var isFormValid = true;

    if (document.getElementById("inputShopName").value.length <= 1) {
        document.getElementById("labelInputShopName").style.color = "red";
        isFormValid = false;
    } else { document.getElementById("labelInputShopName").style.color = "#495057"; }

    if (!/^(?:http(s)?:\/\/)?[\w.-]+(?:\.[\w\.-]+)+[\w\-\._~:/?#[\]@!\$&'\(\)\*\+,;=.]+$/.test(document.getElementById("inputShopUrl").value)) {
        document.getElementById("labelInputShopUrl").style.color = "red";
        isFormValid = false;
    } else { document.getElementById("labelInputShopUrl").style.color = "#495057"; }

    if (document.getElementById("inputPhoto").value === null || document.getElementById("inputPhoto").value === "") {
        document.getElementById("labelInputPhoto").style.color = "red";
        isFormValid = false;
    } else { document.getElementById("labelInputPhoto").style.color = "#495057"; }

    return isFormValid;
}
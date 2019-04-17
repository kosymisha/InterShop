function search() {
    document.getElementById("spinnerDiv").innerHTML = "<div  class=\"spinner-border text-secondary\" role=\"status\">" +
        "  <span class=\"sr-only\">Loading...</span>" +
        "</div>";
    debugger;
    var sort = document.getElementById("inputGroupSelect01").value;
    var category = document.querySelector('input[name="options"]:checked').value;
    var keyword = document.getElementById("keyword").value;
    var maxPrice = document.getElementById("maxPrice").value;
    var minPrice = document.getElementById("minPrice").value;
    var url = "http://localhost:8080/search";
    var isAvailableRequest = false;
    url = url + "?sort=" + sort;
    if (category !== "0") { url = url + "&categoryId=" + category.replace(/\s/g, ''); isAvailableRequest = true; }
    if (keyword !== "" && keyword != null) { url = url + "&keyword=" + keyword.replace(/\s/g, '-'); isAvailableRequest = true; }
    if (minPrice !== "" && minPrice != null && isValidPriceChecker(minPrice)) { url = url + "&minPrice=" + minPrice; }
    if (maxPrice !== "" && minPrice != null && isValidPriceChecker(maxPrice)) { url = url + "&maxPrice=" + maxPrice; }
    if(isAvailableRequest) {
        console.log(url); debugger;
        get(url);
    } else { alert('Input keywords or choose category.'); }
}

function isValidPrice(point) {
    if(!/^[1-9]\d{0,8}(\.\d{1,2})?$/.test(document.getElementById(point).value)){
        document.getElementById(point).style.color = "red";
    } else { document.getElementById(point).style.color = "#495057"; }
}

function isValidPriceChecker(value) {
    return /^[1-9]\d{0,8}(\.\d{1,2})?$/.test(value);
}

function get(url) {
    //alert(url);
    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200) {
            document.getElementById("spinnerDiv").innerHTML = "";
            document.getElementById("advs").innerHTML = this.responseText;
        }
    };
    httpRequest.open("GET", url, true);
    httpRequest.send();
}

function inputCategory() {
    var k = event.keyCode || event.charCode;
    if (k === 8) {
        var httpRequest = new XMLHttpRequest();
        httpRequest.onreadystatechange = function () {
            if(this.readyState === 4 && this.status === 200) {
                document.getElementById("toch").innerHTML = this.responseText;
                searchCategory();
            }
        };
        httpRequest.open("GET", "http://localhost:8080/categories", true);
        httpRequest.send();
    } else {
        searchCategory();
    }
}

function searchCategory() {
    var keyword = document.getElementById("keywordCategory").value;
    var categories = document.getElementsByName("objects");
    var source = document.getElementsByName("objects");
    console.log(categories);
    var isContainsKeyword = false;
    do {
        isContainsKeyword = false;
        categories.forEach(function(category){ if(category.innerText.toLowerCase().indexOf(keyword) === -1) {isContainsKeyword = true;} });
        console.log('s size: ' + source);
        console.log('c size: ' + categories);
        debugger;
        for (var l = 0; l < categories.length; l++) {
            if (categories.item(l).innerText.toLowerCase().indexOf(keyword) === -1) {
                categories.item(l).remove();
                break;
            }
        }
    } while (isContainsKeyword);
}

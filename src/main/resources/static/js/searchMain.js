function search() {
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
    //document.getElementById("advs").innerText = url;
    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200) {
            document.getElementById("advs").innerHTML = this.responseText;
        }
    };
    httpRequest.open("GET", url, true);
    httpRequest.send();
}

function searchCategory() {
    var keyword = document.getElementById("keywordCategory").value;
    var categories = document.getElementsByName("objects");
    var categoriesElement = document.getElementById("catlist");
    console.log(categories);

    //var regex = new RegExp(keyword);
    /*
    var filtered = Array.from(categories).filter(function(value, index, arr){
        //var a = new RegExp(categories.innerText);
        if (value.innerText.indexOf(keyword) !== 0){
            return value;
        }
    });
    console.log('keyword ' + keyword);
    console.log('filtered:');
    console.log(filtered);*/
    var isContainsKeyword = false;
    do {
        isContainsKeyword = false;
        categories.forEach(function(category){ if(category.innerText.toLowerCase().indexOf(keyword) === -1) {isContainsKeyword = true;} });
        console.log('isContKey ' + isContainsKeyword); debugger;
        for (var l = 0; l < categories.length; l++) {
            if (categories.item(l).innerText.toLowerCase().indexOf(keyword) === -1) {
                console.log('removed ' + categories.item(l).innerText);
                categories.item(l).remove();
                break;
            }
        }
    } while (isContainsKeyword);
    /*
    categories.forEach(function(category){
        if (category.innerText.toLowerCase().indexOf(keyword) === -1) {
            console.log(category);
            debugger;
            category.remove();
        } else { console.log(category.innerText); }
    });
    console.log(categories);
    debugger;
    */
    //categoriesElement.innerHTML = categories.values();
}

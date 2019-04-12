function inputProductTitle() {
    var k = event.keyCode || event.charCode;
    console.log('k ' + k); //debugger;
    if (k === 8) {
        var httpRequest = new XMLHttpRequest();
        httpRequest.onreadystatechange = function () {
            if(this.readyState === 4 && this.status === 200) {
                document.getElementById("prods").innerHTML = this.responseText;
                searchProduct();
            }
        };
        httpRequest.open("GET", "http://localhost:8080/products", true);
        httpRequest.send();
    } else {
        searchProduct();
    }
}

function searchProduct() {
    var keyword = document.getElementById("titleProd").value;
    var products = document.getElementsByName("objectsProd");
    console.log('keyword ' + keyword);
    console.log('products ' + products);
    //debugger;
    var isContainsKeyword = false;
    do {
        isContainsKeyword = false;
        products.forEach(function(product){
            //console.log(product.childNodes.item(1).childNodes.item(4).innerText); //debugger;
            if (product.childNodes.item(1).childNodes.item(4).innerText.toLowerCase().indexOf(keyword) === -1) { isContainsKeyword = true; } });
        for (var l = 0; l < products.length; l++) {
            console.log(products.item(l).childNodes.item(1).childNodes.item(4).innerText.toLowerCase().indexOf(keyword));
            if (products.item(l).childNodes.item(1).childNodes.item(4).innerText.toLowerCase().indexOf(keyword) === -1) {
                debugger;
                products.item(l).remove();
                break;
            }
        }
    } while (isContainsKeyword);
}

function chooseProduct (productId, userId) {
    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200) {
            document.getElementById("productForm").innerHTML = this.responseText;
        }
    };
    httpRequest.open("GET", "http://localhost:8080/products/" + productId + "/form?userId=" + userId, true);
    httpRequest.send();
}

function backToAllProducts () {
    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200) {
            document.getElementById("productForm").innerHTML = this.responseText;
        }
    };
    httpRequest.open("GET", "http://localhost:8080/products/form", true);
    httpRequest.send();
}

function isValidPrice(point) {
    if(!/^[1-9]\d{0,8}(\.\d{1,2})?$/.test(document.getElementById(point).value)){
        document.getElementById(point).style.color = "red";
    } else {
        document.getElementById(point).style.color = "#495057";
    }
}

function isValidPriceChecker(value) {
    return /^[1-9]\d{0,8}(\.\d{1,2})?$/.test(value);
}

function isValidFormCreateWithProd () {
    var isValidForm = true;
    var price = document.getElementById("priceInput").value;
    var title = document.getElementById("title").value;
    var category = document.querySelector('input[name="options"]:checked').value;
    var file = document.getElementById("file").value;
    console.log('price ' + price);
    console.log('title ' + title);
    console.log('category ' + category);
    console.log('file ' + file);
    debugger;
    if ( file === null || file === "") {
        document.getElementById("photoLabel").style.color = "red";
        isValidForm = false;
    } else { document.getElementById("photoLabel").style.color = "#495057"; }

    if (!isValidPriceChecker(price)) {
        document.getElementById("priceLabel").style.color = "red";
        isValidForm =  false;
    } else { document.getElementById("priceLabel").style.color = "#495057"; }

    if (title === null || title === "") {
        document.getElementById("titleLabel").style.color = "red";
        isValidForm =  false;
    } else { document.getElementById("titleLabel").style.color = "#495057"; }

    if (category === "0") {
        document.getElementById("categoryLabel").style.color = "red";
        isValidForm =  false;
    } else { document.getElementById("categoryLabel").style.color = "#495057"; }

    if (document.getElementById("shopInput").options[document.getElementById("shopInput").selectedIndex].value === "EMPTY") {
        document.getElementById("shopLabel").style.color = "red";
        isValidForm = false;
    } else { document.getElementById("shopLabel").style.color = "#495057"; }

    return isValidForm;
}

function isValidFormCreateNewProd () {
    var isValidForm = true;
    if (!/^[1-9]\d{0,8}(\.\d{1,2})?$/.test(document.getElementById("inputPrice2").value)) {
        document.getElementById("priceLabel2").style.color = "red";
        debugger;
        isValidForm = false;
    } else { document.getElementById("priceLabel2").style.color = "#495057"; }
    if (document.getElementById("shopInput2").options[document.getElementById("shopInput2").selectedIndex].value === "EMPTY") {
        document.getElementById("shopLabel2").style.color = "red";
        isValidForm = false;
    } else { document.getElementById("shopLabel2").style.color = "#495057"; }
    return isValidForm;
}
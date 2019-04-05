function inputProductTitle() {
    var k = event.keyCode || event.charCode;
    console.log('k ' + k); debugger;
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
    var keyword = document.getElementById("title").value;
    var products = document.getElementsByName("objectsProd");
    console.log('keyword ' + keyword);
    console.log('products ' + products);
    debugger;
    var isContainsKeyword = false;
    do {
        isContainsKeyword = false;
        products.forEach(function(product){
            if (product.childNodes.item(4).innerText.toLowerCase().indexOf(keyword) === -1) { isContainsKeyword = true; } });
        for (var l = 0; l < products.length; l++) {
            if (products.item(l).childNodes.item(4).innerText.toLowerCase().indexOf(keyword) === -1) {
                products.item(l).remove();
                break;
            }
        }
    } while (isContainsKeyword);
}
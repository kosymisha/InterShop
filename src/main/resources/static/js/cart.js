function addToCart (advertId) {
    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200) {
            document.getElementById("addCartBtn").innerHTML = 'NOT AVAILABLE';
        }
    };
    httpRequest.open("GET", "/orders/create?advertId=" + advertId, true);
    httpRequest.send();
}

function deleteFromCart (orderId, parThis) {
    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function () {
        deleteOrderFromList(parThis);
        if(this.readyState === 4 && this.status === 200) {
            //debugger;
        }
    };
    httpRequest.open("GET", "/orders/" + orderId + "/delete", true);
    httpRequest.send();
}

function deleteOrderFromList (parThis) {
    var p=parThis.parentNode.parentNode;
    console.log('p ' + p);
    p.parentNode.removeChild(p);
}

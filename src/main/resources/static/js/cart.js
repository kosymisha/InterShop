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

function confirmOrder (orderId, parThis) {
    var cardId = document.getElementById("inputBankCard").options[document.getElementById("inputBankCard").selectedIndex].value;
    console.log('cardid - - ' + cardId); debugger;
    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function () {
        console.log('respText - - ' + this.responseText);
        console.log('resp - - ' + this.response); debugger;
        if(this.readyState === 4 && this.status === 200) {
            if(this.responseText === "true"){
                var p=parThis.parentNode.parentNode;
                console.log('p ' + p);
                p.parentNode.removeChild(p);
                location.reload();
            } else {
                alert('Some problems. Try again please.');
            }
        }
    };
    httpRequest.open("GET", "/orders/" + orderId +"/confirm?cardId=" + cardId, true);
    httpRequest.send();
}

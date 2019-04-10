function deleteShopComment (shopId, comment) {
    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200) {
            document.getElementById("comm").innerHTML = this.responseText;
        }
    };
    httpRequest.open("GET", "http://localhost:8080/shops/" + shopId + "/comments/" + comment + "/delete", true);
    httpRequest.send();
}

function createShopComment(shopId) {
    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200) {
            document.getElementById("comm").innerHTML = this.responseText;
        }
    };
    httpRequest.open("POST", "http://localhost:8080/shops/" + shopId + "/comments/create", true);
    httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    httpRequest.send("commentBox=" + document.getElementById("comBox").value);
}
function deleteAdvertComment (advertId, comment) {
    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200) {
            document.getElementById("comm").innerHTML = this.responseText;
        }
    };
    httpRequest.open("GET", "http://localhost:8080/adverts/" + advertId + "/comments/" + comment + "/delete", true);
    httpRequest.send();
}

function createAdvertComment(advertId, csrfToken) {
    alert("advert");
    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200) {
            document.getElementById("comm").innerHTML = this.responseText;
        }
    };
    httpRequest.open("POST", "http://localhost:8080/adverts/" + advertId + "/comments/create", true);
    httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    httpRequest.setRequestHeader('X-CSRF-Token', csrfToken);
    httpRequest.send("commentBox=" + document.getElementById("advComBox").value);
}
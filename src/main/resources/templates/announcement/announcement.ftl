<#import "../parts/common.ftl" as c>
<@c.page "InterShop" "${announcement.id}">
<script>
    function loadDoc(announcement, comment) {
        var httpRequest = new XMLHttpRequest();
        httpRequest.onreadystatechange = function () {
            if(this.readyState === 4 && this.status === 200) {
                document.getElementById("comm").innerHTML = this.responseText;
            }
        };
        httpRequest.open("GET", "http://localhost:8080/announcements/" + announcement + "/comments/" + comment + "/delete", true);
        httpRequest.send();
    }

    function addCom(announcement, csrfToken) {
        var httpRequest = new XMLHttpRequest();
        httpRequest.onreadystatechange = function () {
            if(this.readyState === 4 && this.status === 200) {
                document.getElementById("comm").innerHTML = this.responseText;
            }
        };
        httpRequest.open("POST", "http://localhost:8080/announcements/" + announcement + "/comments/create", true);
        httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        httpRequest.setRequestHeader('X-CSRF-Token', csrfToken);
        httpRequest.send("commentBox=" + document.getElementById("comBox").value);
    }

    function onLoad(announcement) {
        var httpRequest = new XMLHttpRequest();
        httpRequest.onreadystatechange = function () {
            if(this.readyState === 4 && this.status === 200) {
                document.getElementById("comm").innerHTML = this.responseText;
            }
        };
        httpRequest.open("GET", "http://localhost:8080/announcements/" + announcement + "/comments", true);
        httpRequest.send();
    }
</script>
    <br/>
    <img width="100" height="100" src="${announcement.product.photoURL}" /> <br/>
    <a href="${announcement.productURL}">${announcement.product.title}</a> <br/>
    <i>${announcement.product.getCategory()}</i> <br/>
    <i>${announcement.currency} <b>${announcement.price}</b></i> <br/>
    <br/>
    <#list user.roles as role>
        <#if role == 'ADMIN'>
        <a href="/announcements/${announcement.id}/delete">delete</a>
        <#elseif announcement.shop.owner.id == user.id>
        <a href="/announcements/${announcement.id}/delete">delete</a>
        </#if>
    </#list>
    <br/>
    <div id="comm" name="commentsList"></div>
</@c.page>
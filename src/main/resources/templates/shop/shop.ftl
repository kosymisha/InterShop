<#import "../parts/common.ftl" as c>
<@c.page "InterShop" "${shop.id}">

<script>
    function loadDoc(shop, comment) {
        var httpRequest = new XMLHttpRequest();
        httpRequest.onreadystatechange = function () {
            if(this.readyState === 4 && this.status === 200) {
                document.getElementById("comm").innerHTML = this.responseText;
            }
        };
        httpRequest.open("GET", "http://localhost:8080/shops/" + shop + "/comments/" + comment + "/delete", true);
        httpRequest.send();
    }

    function addCom(shop, csrfToken) {
        var httpRequest = new XMLHttpRequest();
        httpRequest.onreadystatechange = function () {
            if(this.readyState === 4 && this.status === 200) {
                document.getElementById("comm").innerHTML = this.responseText;
            }
        };
        httpRequest.open("POST", "http://localhost:8080/shops/" + shop + "/comments/create", true);
        httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        httpRequest.setRequestHeader('X-CSRF-Token', csrfToken);
        httpRequest.send("commentBox=" + document.getElementById("comBox").value);
    }

    function onLoad(shop) {
        var httpRequest = new XMLHttpRequest();
        httpRequest.onreadystatechange = function () {
            if(this.readyState === 4 && this.status === 200) {
                document.getElementById("comm").innerHTML = this.responseText;
            }
        };
        httpRequest.open("GET", "http://localhost:8080/shops/" + shop + "/comments", true);
        httpRequest.send();
    }
</script>
    <div><img width="200" height="200" src="/img/${shop.photoURL}" /></div>
    <div><label>Name: </label>${shop.nameShop}</div>
    <div><label>URL: </label>${shop.url}</div>
    <div><label>Owner: </label>${shop.owner.firstName} ${shop.owner.lastName}</div>
    <div><label>Description: </label>${shop.description}</div>
    <br/>
    <#list user.roles as role>
        <#if role == 'ADMIN'>
        <a href="/shops/${shop.id}/delete">delete</a>
        <#elseif shop.owner.id == user.id>
        <a href="/shops/${shop.id}/delete">delete</a>
        </#if>
    </#list>
    <br/>
    <div id="comm" name="commentsList"></div>
</@c.page>

<#import "parts/login.ftl" as l>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>InterShop</title>
</head>
<body>
<#if currentUser??>
    <div>
        <p>Welcome, ${currentUser.getFirstName()} ${currentUser.getLastName()}</p>
    </div>
    <div>
        <@l.logout />
    </div>
<#else>
    <div>
        <a href="/login">Click to login.</a>
    </div>
</#if>


<br/><br/><br/><br/>
<form action="/main" method="get">
    <input type="text" name="keyword" placeholder="input name of product..." />
    <input type="submit" value="Search" />
</form>
<div>Announcements:</div>
    <#list announcements as announcement>
        <div>
            <br/>
            <img width="100" height="100" src="/img/${announcement.product.photoURL}" /> <br/>
            <a href="${announcement.productURL}">${announcement.product.title}</a> <br/>
            <i>${announcement.product.category.categoryName}</i> <br/>
            <i>${announcement.currency} <b>${announcement.price}</b></i> <br/>
            <br/>
        </div>
    <#else>
    No announcements.
    </#list>
<#list announcementsEbay as announcementEbay>
<div>
    <br/>
    <img width="100" height="100" src="${announcementEbay.product.photoURL}" /> <br/>
    <a href="${announcementEbay.productURL}">${announcementEbay.product.title}</a> <br/>
    <i>${announcementEbay.product.category.categoryName}</i> <br/>
    <i>${announcementEbay.currency} <b>${announcementEbay.price}</b></i> <br/>
    <br/>
</div>
    <#else>
No announcements from Ebay.
</#list>
</body>
</html>
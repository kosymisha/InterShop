<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page "Shop">
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
        <span>${announcement.id}</span>
        <i>${announcement.price}</i>
        <b>${announcement.views}</b>
        <i>${announcement.sneaker.getCorporation()}</i>
        <i>${announcement.sneaker.getModel()}</i>
        <i>${announcement.sneaker.getColor()}</i>
    </div>
<#else>
No announcements.
</#list>
Ebay results:
<#list announcementsEbay as announcementEbay>
<div>
    <br/>
    <img src="${announcementEbay.photoURL}" /> <br/>
    <a href="${announcementEbay.productURL}">${announcementEbay.title}</a> <br/>
    <i>${announcementEbay.categoryName}</i> <br/>
    <i>${announcementEbay.currency} <b>${announcementEbay.price}</b></i> <br/>
    <br/>
</div>
    <#else>
No announcements from Ebay.
</#list>
</@c.page>
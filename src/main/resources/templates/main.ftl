<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<div>
    <@l.logout />
</div>
<div>
    <a href="/login">Click to login.</a>
</div>
<br/><br/><br/><br/>
<form action="/main" method="get">
    <input type="text" placeholder="input name of product..." />
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
    <span>${announcementEbay.title}</span>
</div>
    <#else>
No announcements from Ebay.
</#list>
</@c.page>
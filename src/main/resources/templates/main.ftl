<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<div>
    <@l.logout />
</div>
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
</@c.page>
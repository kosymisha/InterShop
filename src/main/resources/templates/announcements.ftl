<#import "parts/common.ftl" as c>

<@c.page "Announcements">
    <#list announcements as announcement>
        <div>
            <br/>
            <img src="D://pholder.png" /> <br/>
            <a href="${announcement.product.productURL}">${announcement.product.title}</a> <br/>
            <i>${announcement.product.category.categoryName}</i> <br/>
            <i>${announcement.currency} <b>${announcement.price}</b></i> <br/>
            <br/>
        </div>
    <#else>
    No announcements.
    </#list>
</@c.page>
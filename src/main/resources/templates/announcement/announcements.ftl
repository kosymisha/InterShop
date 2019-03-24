<#import "../parts/common.ftl" as c>
<@c.page "InterShop"  "">
<a href="/announcements/create" >Create new announcement</a>
    <#list announcements as announcement>
        <div>
            <br/>
            <img width="100" height="100" src="/img/${announcement.product.photoURL}" /> <br/>
            <a href="${announcement.productURL}">${announcement.product.title}</a> <br/>
            <i>${announcement.product.category.categoryName}</i> <br/>
            <i>${announcement.currency} <b>${announcement.price}</b></i> <br/>
            <a href="/announcements/${announcement.id}">more</a>
            <br/>
        </div>
    <#else>
    No announcements.
    </#list>
</@c.page>
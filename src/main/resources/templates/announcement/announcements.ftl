<#import "../parts/common.ftl" as c>

<@c.page "Announcements">
    <#list announcements as announcement>
        <div>
            <br/>
            <img src="/img/${announcement.product.photoURL}" /> <br/>
            <a href="${announcement.productURL}">${announcement.product.title}</a> <br/>
            <i>${announcement.product.category.categoryName}</i> <br/>
            <i>${announcement.currency} <b>${announcement.price}</b></i> <br/>
            <#list user.roles as role>
                <#if role == 'ADMIN'>
                    <a href="/shops/${announcement.id}">more</a> <a href="">delete</a>

                <#elseif announcement.shop.owner.id == user.id>
                    <a href="/shops/${announcement.id}">more</a> <a href="">delete</a> <a href="">create</a>

                <#elseif role == 'USER' || role == 'SELLER'>
                    <a href="/shops/${announcement.id}">more</a>
                </#if>
            </#list>
            <br/>
        </div>
    <#else>
    No announcements.
    </#list>
</@c.page>
<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl" />
<#import "../parts/pager.ftl" as p>
<@c.page "Adverts">
<#if isSeller>
<a href="/adverts/create" class="btn btn-secondary mt-4 mb-4">Create new advert</a>
</#if>
    <#list adverts as advert>
        <div>
            <br/>
            <img width="100" height="100" src="${advert.photoURL}" /> <br/>
            <p>${advert.title}</p> <br/>
            <i>${advert.categoryName}</i> <br/>
            <a href="/adverts/${advert.id}">more</a>
            <br/>
        </div>
    <#else>
    No adverts.
    </#list>
</@c.page>
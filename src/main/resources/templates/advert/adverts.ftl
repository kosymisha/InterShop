<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl" />
<@c.page "Adverts">
<#if isSeller>
<a href="/adverts/create" class="btn btn-secondary">Create new advert</a>
</#if>
    <#list adverts as advert>
        <div>
            <br/>
            <img width="100" height="100" src="${advert.product.photoURL}" /> <br/>
            <p>${advert.product.title}</p> <br/>
            <i>${advert.product.category.categoryName}</i> <br/>
            <i>${advert.currency} <b>${advert.price}</b></i> <br/>
            <a href="/adverts/${advert.id}">more</a>
            <br/>
        </div>
    <#else>
    No adverts.
    </#list>
</@c.page>
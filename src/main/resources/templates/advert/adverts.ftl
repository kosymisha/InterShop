<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl" />
<@c.page "InterShop">
<#if isSeller>
<a href="/adverts/create" >Create new advert</a>
</#if>
    <#list adverts as advert>
        <div>
            <br/>
            <img width="100" height="100" src="${advert.product.photoURL}" /> <br/>
            <a href="${advert.productURL}">${advert.product.title}</a> <br/>
            <i>${advert.product.getCategory()}</i> <br/>
            <i>${advert.currency} <b>${advert.price}</b></i> <br/>
            <a href="/adverts/${advert.id}">more</a>
            <br/>
        </div>
    <#else>
    No adverts.
    </#list>
</@c.page>
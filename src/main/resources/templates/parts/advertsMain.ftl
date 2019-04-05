<div id="advs">
    <#if adverts??>
        <#list adverts as advert>
            <div>
                <br/>
                <img width="100" height="100" src="${advert.product.photoURL}" /> <br/>
                <p>${advert.product.title}</p><br/>
                <i>${advert.product.category.categoryName}</i> <br/>
                <i>${advert.currency} <b>${advert.price}</b></i> <br/>
                <a href="/adverts/${advert.id}">more</a>
                <br/>
            </div>
        </#list>
    <#else>
No adverts.
    </#if>
</div>
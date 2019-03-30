<div id="advs">
<#list adverts as advert>
            <div>
                <br/>
                <img width="100" height="100" src="${advert.product.photoURL}" /> <br/>
                <a href="${advert.productURL}">${advert.product.title}</a> <br/>
                <i>${advert.product.getCategory()}</i> <br/>
                <i>${advert.currency} <b>${advert.price}</b></i> <br/>
                <br/>
            </div>
<#else>
No adverts.
</#list>
</div>
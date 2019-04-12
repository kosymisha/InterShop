
<#import "../parts/login.ftl" as l>
<#import "../parts/common.ftl" as c>
<@c.page "Sales">
<script src="/js/cart.js" type="text/javascript" xmlns="http://www.w3.org/1999/html"></script>
<link rel="stylesheet" type="text/css"  href="/css/orders.css" media="all">
        <div id="table-wrapper">
            <div id="table-scroll">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Shop</th>
                        <th scope="col">Photo</th>
                        <th scope="col" width="300">Title</th>
                        <th scope="col">Price</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list sales as sale>
                        <tr class="rows">
                            <td scope="row"><a href="/shops/${sale.advert.shop.id}">${sale.advert.shop.nameShop}</a></td>
                            <td><img height="100" src="${sale.advert.product.photoURL}" /></td>
                            <td><a href="/adverts/${sale.advert.id}">${sale.advert.product.title}</a></td>
                            <td>
                                ${sale.advert.price} ${sale.advert.currency}
                            </td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
        </div>
        <p>${itemCount} item(s). Total price: <b>${totalUsd.price}</b><sup>${totalUsd.currency}</sup> <b id="currencyHelp">${totalEur.price}</b><sup id="currencyHelp">${totalEur.currency}</sup> <b id="currencyHelp">${totalByn.price}</b><sup id="currencyHelp">${totalByn.currency}</sup></p>

</@c.page>
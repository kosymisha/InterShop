<#import "../parts/common.ftl" as c>

<@c.page "asd">
<br/>
            <br/>
            <img width="100" height="100" src="/img/${announcement.product.photoURL}" /> <br/>
            <a href="${announcement.productURL}">${announcement.product.title}</a> <br/>
            <i>${announcement.product.category.categoryName}</i> <br/>
            <i>${announcement.currency} <b>${announcement.price}</b></i> <br/>
            <br/>
</@c.page>
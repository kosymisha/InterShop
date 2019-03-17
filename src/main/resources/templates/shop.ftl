<#import "parts/common.ftl" as c>

<@c.page "${shop.nameShop}">
<div><label>Name: </label>${shop.nameShop}</div>
<div><label>URL: </label>${shop.url}</div>
<div><label>Owner: </label>${shop.owner.firstName} ${shop.owner.lastName}</div>
</@c.page>
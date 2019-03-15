<#import "parts/common.ftl" as c>

<@c.page "My profile">

<div><label>First Name: </label>${user.firstName}</div>
<div><label>Last Name: </label>${user.lastName}</div>
<div><label>Email: </label>${user.email}</div>

</@c.page>
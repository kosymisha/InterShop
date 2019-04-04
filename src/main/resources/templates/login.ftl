
<#import "parts/login.ftl" as l>
<#import "parts/common.ftl" as c>
<@c.page "InterShop">
Welcome
<@l.login "/login" />
<a href="/registration">Create new user.</a>
</@c.page>
<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page "Login">
Welcome
<@l.login "/login" />
<a href="/registration">Create new user.</a>
</@c.page>
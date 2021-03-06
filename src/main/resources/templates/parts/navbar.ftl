<#include "security.ftl" />
<#import "login.ftl" as l>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">InterShop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/shops">Shops</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/adverts">Adverts</a>
            </li>
            <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/profiles">Profiles</a>
            </li>
            </#if>
            <#if isUser>
                <li class="nav-item">
                    <a class="nav-link" href="/orders/my">Orders</a>
                </li>
            </#if>
            <#if isSeller>
                <li class="nav-item">
                    <a class="nav-link" href="/sales">Sales</a>
                </li>
            </#if>
        </ul>
        <#if firstname == "guest">
            <div><a class="nav-link" href="/login">Sign in</a></div>
        <#else>
            <a class="nav-link" href="/profiles/my">${firstname} ${lastname}</a>
        </#if>
    </div>
</nav>

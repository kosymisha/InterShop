
<#import "parts/login.ftl" as l>
<#import "parts/common.ftl" as c>
<@c.page "Welcome">
<div class="row mt-3 ml-3">
    <div class="col col-md-3">
        <@l.login "/login" />
    </div>
</div>
</@c.page>
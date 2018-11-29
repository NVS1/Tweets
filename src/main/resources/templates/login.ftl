<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<div class="mb-2">Sign In</div>
    <#if message??>
        ${message}
    </#if>
<@l.login/>
</@c.page>
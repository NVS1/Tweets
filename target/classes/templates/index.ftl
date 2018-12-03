<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
<#--<div class="form-row">-->
    <#--<div class="form-group col-md-6">-->
        <#--<form method="get" action="/" class="form-inline">-->
            <#--<input type="text" name="filter" placeholder="Search by Tag" class="form-control">-->
            <#--<button type="submit" class="btn btn-primary ml-2">Search</button>-->
        <#--</form>-->
    <#--</div>-->
<#--</div>-->
<#include "parts/addTweet.ftl"/>
<#include "parts/tweetList.ftl">
</@c.page>


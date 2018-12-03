<#import "parts/common.ftl" as c>
<@c.page>
<#if isCurrentUser>
    <#include "parts/addTweet.ftl">
</#if>
<#include "parts/tweetList.ftl">
</@c.page>
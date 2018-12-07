<#import "parts/common.ftl" as c>
<#import "parts/searchTweet.ftl" as s>
<#include "parts/security.ftl">
<@c.page>
<@s.search "/"/>
<#include "parts/addTweet.ftl"/>
<#include "parts/tweetList.ftl">
</@c.page>


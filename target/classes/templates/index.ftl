<#import "parts/common.ftl" as c>
<#import "parts/searchTweet.ftl" as s>
<#import "parts/pager.ftl" as p>
<#include "parts/security.ftl">
<@c.page>
<ul class="nav nav-tabs my-3">
    <li class="nav-item">
        <a class="nav-link active" href="/">Follow</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/all">All</a>
    </li>
</ul>
    <@s.search "/"/>
    <#include "parts/addTweet.ftl"/>
    <#include "parts/tweetList.ftl">
</@c.page>


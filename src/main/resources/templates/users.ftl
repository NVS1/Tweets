<#import "parts/common.ftl" as c>

<@c.page>
<div class="card-columns">
<#list users as user>
     <div class="card text-center border-info mb-3" style="width: 18rem;">
         <#if user.avatarId??>
             <img class="card-img-top" src="/image/${user.avatarId}" alt="Card image cap">
         <#else >
             <img class="card-img-top" src="/static/avatar.png" alt="Card image cap">
         </#if>
         <div class="card-body">
             <h5 class="card-title">${user.name}</h5>
             <#if !user.currentUser>
                 <#if isSubscribers??>
                 <#else >
                     <#if user.subscriber>
                     <a href="/user/unsubscribe/${user.id}" class="btn btn-danger">Unsubscribe</a>
                     <#else>
                     <a href="/user/subscribe/${user.id}" class="btn btn-success">Subscribe</a>
                     </#if>
                 </#if>
             </#if>
             <a href="/tweets/${user.id}" class="btn btn-primary"><i class="fas fa-eye"></i></a>
             <div class="my-2"><p class="card-text"><small class="text-muted">Online</small></p></div>
         </div>
     </div>
<#else >
  <h3>Not found!</h3>
</#list>
</div>
</@c.page>
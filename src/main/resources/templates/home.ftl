<#import "parts/common.ftl" as c>
<@c.page>
<ul class="nav nav-tabs" id="myTab" role="tablist">
    <li class="nav-item">
        <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Tweets</a>
    </li>
    <li class="nav-item">
        <a class="nav-link active" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="true">Profile</a>
    </li>
</ul>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
        <div class="form-group mt-3">
            <div class="form-group">
             <#if isCurrentUser>
              <#include "parts/addTweet.ftl">
             </#if>
            </div>
            <div class="form-group">
            <#include "parts/tweetList.ftl">
            </div>
        </div>
    </div>
    <div class="tab-pane fade show active" id="contact" role="tabpanel" aria-labelledby="contact-tab">

        <div class="container my-4">
            <div class="row">
                <div class="col">
                    <div class="card text-center" style="width: 18rem;">
                        <#if profile.avatar??>
                           <img class="card-img-top" src="/image/${profile.avatarId}" alt="Card image cap">
                        <#else>
                            <#if isCurrentUser>
                                <form method="post" action="/avatar" enctype="multipart/form-data">
                                    <div class="custom-file">
                                        <input type="file" name="file" id="customFile${profile.id}">
                                        <label class="custom-file-label" for="customFile${profile.id}">Choose file</label>
                                    </div>
                                    <input type="hidden" name="url" value="${springMacroRequestContext.getRequestUri()}">
                                    <button type="submit" class="btn btn-primary btn-sm"><i class="far fa-image"></i></button>
                                </form>
                            </#if>
                        </#if>
                        <div class="card-body">
                            <div class="form group mt-3">
                                <div class="form-group">
                                    <h5 class="card-title">${profile.name}</h5>
                                </div>
                                <#if !isCurrentUser>
                                    <#if isSubscriber>
                                    <div class="form-group">
                                        <a href="/user/unsubscribe/${profile.id}" class="btn btn-danger">Unsubscribe</a>
                                    </div>
                                    <#else>
                                    <div class="form-group">
                                        <a href="/user/subscribe/${profile.id}" class="btn btn-success">Subscribe</a>
                                    </div>
                                    </#if>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <div class="card-title">Subscriptions</div>
                            <h3 class="card-text">
                                <a href="/profile/subscriptions/${profile.id}">${subscriptionsCount}</a>
                            </h3>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <div class="card-title">Subscribers</div>
                            <h3 class="card-text">
                                <a href="/profile/subscriptions/${profile.id}">${subscribersCount}</a>
                            </h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</@c.page>
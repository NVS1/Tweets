<#import "parts/common.ftl" as c>
<#import "parts/searchTweet.ftl" as s>
<@c.page>
        <div class="container my-4">
            <div class="row">
                <div class="col">
                    <div class="card text-center" style="width: 18rem;">
                        <#if userDTO.avatarId??>
                           <img class="card-img-top" src="/image/${userDTO.avatarId}" alt="Card image cap">
                        <#else>
                            <#if userDTO.currentUser>
                                <form method="post" action="/avatar" enctype="multipart/form-data">
                                    <div class="custom-file">
                                        <input type="file" name="file" id="customFile${userDTO.id}">
                                        <label class="custom-file-label" for="customFile${userDTO.id}">Choose file</label>
                                    </div>
                                    <input type="hidden" name="url" value="${springMacroRequestContext.getRequestUri()}">
                                    <button type="submit" class="btn btn-primary btn-sm"><i class="far fa-image"></i></button>
                                </form>
                            <#else >
                                <img class="card-img-top" src="/static/avatar.png" alt="Card image cap">
                            </#if>
                        </#if>
                        <div class="card-body">
                            <div class="form group mt-3">
                                <div class="form-group">
                                    <h5 class="card-title">${userDTO.name}</h5>
                                </div>
                                <#if !userDTO.currentUser>
                                    <#if userDTO.subscriber>
                                    <div class="form-group">
                                        <a href="/user/unsubscribe/${userDTO.id}" class="btn btn-danger">Unsubscribe</a>
                                    </div>
                                    <#else>
                                    <div class="form-group">
                                        <a href="/user/subscribe/${userDTO.id}" class="btn btn-success">Subscribe</a>
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
                                <a href="/profile/subscriptions/${userDTO.id}">${userDTO.subscriptionsCount}</a>
                            </h3>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <div class="card-title">Subscribers</div>
                            <h3 class="card-text">
                                <a href="/profile/subscribers/${userDTO.id}">${userDTO.subscribersCount}</a>
                            </h3>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="form-group mt-3">
                        <div class="form-group">
                        <#if userDTO.currentUser>
                            <#include "parts/addTweet.ftl">
                        </#if>
                        <@s.search "/tweets/${userDTO.id}"/>
                        </div>
                        <div class="form-group">
                        <#include "parts/tweetList.ftl">
                        </div>
                    </div>
                </div>
            </div>
        </div>
</@c.page>
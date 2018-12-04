<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<#import "parts/editProfile.ftl" as ep>
<@c.page>
<#if msg??>
<div class="alert alert-info" role="alert">
    ${msg}
</div>
</#if>
<h5>${user.name}</h5>
    <form method="post" action="/profile">
        <div class="form-group">
            <label>${user.username}</label>
        </div>
        <input type="hidden" name="username" value="${user.username}">
        <@ep.editProfile/>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control ${(passwordError??)?string('is-invalid', '')}" name="password" id="password" placeholder="Password">
             <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
             </#if>
        </div>
        <button class="btn btn-primary" type="submit">Save</button>
    </form>
</@c.page>
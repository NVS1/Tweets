<#import "parts/common.ftl" as c>
<#import "parts/editProfile.ftl" as ep>
<@c.page>
<strong>User editor</strong>
<div class="form-group mt-3">
    <form method="post" action="/admin/user">
        <div class="form-group">
            <strong>Id: ${user.id}</strong><br>
        </div>
        <input type="hidden" name="id" value="${user.id}">
        <input type="hidden" name="password" value="${user.password}">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control ${(usernameError??)?string('is-invalid', '')}" id="username" name="username" placeholder="Username" value="<#if userEdit??>${userEdit.username}<#else>${user.username}</#if>">
             <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
             </#if>
        </div>
        <@ep.editProfile/>
        <#list roles as role>
        <div class="form-group">
            <div class="custom-control custom-checkbox">
                <input class="custom-control-input" id="${role}" type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>
                <label class="custom-control-label" for="${role}">${role}</label>
            </div>
        </div>
        </#list>
        <div class="form-group">
            <div class="custom-control custom-checkbox">
                <input class="custom-control-input" id="active" type="checkbox" name="active" value="true" ${user.active?string("checked", "")}>
                <label class="custom-control-label" for="active">User active</label>
            </div>
        </div>
        <div class="form-group">
            <button class="btn btn-primary" type="submit">Save</button>
        </div>
    </form>
</div>
</@c.page>
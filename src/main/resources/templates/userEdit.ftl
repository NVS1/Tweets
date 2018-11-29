<#import "parts/common.ftl" as c>
<@c.page>
<strong>User editor</strong>
<div class="form-group mt-3">
    <form method="post" action="/admin/user">
        <div class="form-group">
            <strong>Id: ${user.id}</strong><br>
        </div>
        <input type="hidden", name="id", value="${user.id}">
       <div class="form-group">
           <div class="input-group">
               <div class="input-group-prepend">
                   <span class="input-group-text">Username and name and email</span>
               </div>
               <input type="text" class="form-control" name="username" value="${user.username}" placeholder="username">
               <input type="text" class="form-control" name="name" value="${user.name}" placeholder="name">
               <input type="email" class="form-control" name="email" value="${user.email?if_exists}" placeholder="email">
           </div>
       </div>
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
<#import "parts/common.ftl" as c>
<@c.page>
<strong>List of users</strong>
<div class="mt-3">
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Username</th>
            <th scope="col">Name</th>
            <th scope="col">Role</th>
            <th scope="col">Active</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
        <tr>
            <th scope="row">${user.id}</th>
            <td>${user.username}</td>
            <td>${user.name}</td>
            <td>
            <#list user.roles as role>
                ${role}<#sep>,
            </#list>
            </td>
            <td>${user.active?string("yes", "no")}</td>
            <td><a class="btn btn-primary" role="button" href="/admin/user/${user.id}"><i class="fas fa-pen"></i></a> </td>
            <td><a class="btn btn-danger" role="button" href="/admin/delete/${user.id}"><i class="fas fa-ban"></i></a> </td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>
</@c.page>
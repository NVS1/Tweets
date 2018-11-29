<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<#if msg??>
    ${msg}
</#if>
<h5>${user.name}</h5>
    <form method="post" action="/profile">
        <div class="form-group">
            <label>${user.username}</label>
        </div>
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="Name" value="${user.name}">
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="Email" value="${user.email}">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" name="password" id="password" placeholder="Password">
        </div>
        <button class="btn btn-primary" type="submit">Save</button>
    </form>
</@c.page>
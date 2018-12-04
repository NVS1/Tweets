<#include "security.ftl">
<#import "login.ftl" as l>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="/">Tweets</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/tweets/${userId}">Home</a>
            </li>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/users">User List</a>
                </li>
            </#if>
        </ul>
        <form method="get" action="/" class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="text" name="filter" placeholder="Search by Tag">
            <button class="btn btn-success my-2 my-sm-0" type="submit"><i class="fas fa-search"></i></button>
        </form>
        <div class="btn-group" role="group">
            <button id="btnGroupDrop1" type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                ${name}
            </button>
            <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                <a class="dropdown-item" href="/profile">Edit profile</a>
                <#--<a class="dropdown-item" href="#">Dropdown link</a>-->
            </div>
        </div>
         <@l.logout/>
    </div>
</nav>
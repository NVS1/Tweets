<#macro login>
<form action="/login" method="post" xmlns="http://www.w3.org/1999/html">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Login:</label>
            <div class="col-sm-5">
                <input type="text" name="username" class="form-control" placeholder="Login"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password:</label>
            <div class="col-sm-5">
                <input type="password" name="password" class="form-control" placeholder="Password"/>
            </div>
        </div>
        <div class="mb-2"><a href="/registration">Registration</a></div>
        <div class="mb-1"><button class="btn btn-primary" type="submit">Sign In</button></div>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <button class="btn btn-primary" type="submit">Sign Out</button>
    </form>
</#macro>

<#macro register>
    <form action="/registration" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Login:</label>
            <div class="col-sm-5">
                <input type="text" name="username" class="form-control" placeholder="Login"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Name:</label>
            <div class="col-sm-5">
                <input type="text" name="name" class="form-control" placeholder="Name"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password:</label>
            <div class="col-sm-5">
                <input type="password" name="password" class="form-control" placeholder="Password"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Email:</label>
            <div class="col-sm-5">
                <input type="email" name="email" class="form-control" placeholder="Email"/>
            </div>
        </div>
        <button class="btn btn-primary" type="submit">Sign Up</button>
    </form>
</#macro>

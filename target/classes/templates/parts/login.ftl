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
                <input type="text" name="username" value="<#if user??>${user.username}</#if>" class="form-control ${(usernameError??)?string('is-invalid', '')}" placeholder="Login"/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Name:</label>
            <div class="col-sm-5">
                <input type="text" value="<#if user??>${user.name}</#if>" name="name" class="form-control ${(nameError??)?string('is-invalid', '')}" placeholder="Name"/>
                <#if nameError??>
                    <div class="invalid-feedback">
                        ${nameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password:</label>
            <div class="col-sm-5">
                <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid', '')}" placeholder="Password"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Email:</label>
            <div class="col-sm-5">
                <input type="email" value="<#if user??>${user.email}</#if>" name="email" class="form-control ${(emailError??)?string('is-invalid', '')}" placeholder="Email"/>
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
                </#if>
            </div>
        </div>
        <button class="btn btn-primary" type="submit">Sign Up</button>
    </form>
</#macro>

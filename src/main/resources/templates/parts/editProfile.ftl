<#macro editProfile>
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}" id="name" name="name" placeholder="Name" value="<#if userEdit??>${userEdit.name}<#else>${user.name}</#if>">
             <#if nameError??>
                    <div class="invalid-feedback">
                        ${nameError}
                    </div>
             </#if>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control ${(emailError??)?string('is-invalid', '')}" id="email" name="email" placeholder="Email" value="<#if userEdit??>${userEdit.email}<#else>${user.email}</#if>">
             <#if emaileError??>
                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
             </#if>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control ${(passwordError??)?string('is-invalid', '')}" name="password" id="password" placeholder="Password">
             <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
             </#if>
        </div>
</#macro>
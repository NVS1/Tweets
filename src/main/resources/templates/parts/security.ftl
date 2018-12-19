<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>
<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        fullName = user.getName()
        isAdmin = user.isAdmin()
        userId = user.getId()
    >
<#else>
    <#assign
        name = "Unknown"
        isAdmin = false
        userId = -1
    >
</#if>
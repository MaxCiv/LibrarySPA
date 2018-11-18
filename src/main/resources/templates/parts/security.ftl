<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    userAuth = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    userName = userAuth.getUsername()
    userFullName = userAuth.getName()
    isLibrarian = userAuth.isLibrarian()
    isReader = userAuth.isReader()
    isSupplier = userAuth.isSupplier()
    >
<#else>
    <#assign
    userName = "Guest"
    userFullName = "Sign in to continue"
    isLibrarian = false
    isReader = false
    isSupplier = false
    >
</#if>
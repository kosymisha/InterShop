<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>
<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        firstname = user.getFirstName()
        lastname = user.getLastName()
        isAdmin = user.isAdmin()
        isSeller = user.isSeller()
        isUser = user.isUser()
        currentUserId = user.getId()
        currentUser = user
    >
<#else>
    <#assign
        firstname = "guest"
        isAdmin = false
        isSeller = false
        isUser = false
        currentUserId = 0
    >
</#if>
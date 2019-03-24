<#macro login path>
    <form action="${path}" method="post">
        <div><label>Email: <input type="text" name="email" /></label></div>
        <div><label>Password: <input type="password" name="password" /></label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div><button class="btn btn-primary" type="submit">Sign in</button></div>
    </form>
</#macro>

<#macro logout>
        <form action="/logout" method="post">
            <button class="btn btn-primary" type="submit">Sign out</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
        </form>
</#macro>
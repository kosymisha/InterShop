<#macro login path>
    <form action="${path}" method="post">
        <div class="form-group">
            <label for="exampleInputEmail1">Email</label>
            <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
        </div>
        <button type="submit" class="btn btn-primary">Sign in</button>
        <a href="/registration" class="btn btn-secondary">Create new user</a>
    </form>
</#macro>

<#macro logout>
        <form action="/logout" method="post">
            <button class="btn btn-primary" type="submit">Sign out</button>
        </form>
</#macro>
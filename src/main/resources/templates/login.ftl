
<#import "parts/login.ftl" as l>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
Welcome
<@l.login "/login" />
<a href="/registration">Create new user.</a>
</body>
</html>
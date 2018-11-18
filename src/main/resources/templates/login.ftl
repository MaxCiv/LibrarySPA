<#include "parts/security.ftl">
<#import "parts/common.ftl" as page/>

<@page.head "styleLogin.css">
<div class="text-center mx-auto">
    <form class="form-signin" action="/login" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>

        <label for="inputUsername" class="sr-only">Username</label>
        <input type="text" id="inputUsername" class="form-control input-top" name="username" placeholder="Login"
               required autofocus>

        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control input-bottom" name="password"
               placeholder="Password" required>

        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <br>
        <a href="/registration">Registration</a>

        <p class="mt-5 mb-3 text-muted">&copy; 2018</p>
    </form>
</div>
</@page.head>

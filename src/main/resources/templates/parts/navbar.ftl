<#include "security.ftl">

<nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark header-bar px-5">
    <a class="navbar-brand" href="/">LibraryApp</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">

            <#if isLibrarian>
                <li class="nav-item">
                    <a class="nav-link" href="/librarian/tableUsers">Users</a>
                </li>
            </#if>

            <#if isLibrarian>
                <li class="nav-item">
                    <a class="nav-link" href="/registration">Add new user</a>
                </li>
            </#if>

        </ul>

        <div class="navbar-text px-3" title="${userFullName}">
            ${userName}
        </div>

        <#if userAuth??>
            <form class="form-inline my-2 my-lg-0" action="/logout" method="post">
                <button class="btn btn-outline-primary my-2 my-sm-0 text-white" type="submit">Sign out</button>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
            </form>
        <#else>
            <form class="form-inline my-2 my-lg-0" action="/login" method="get">
                <button class="btn btn-primary my-2 my-sm-0" type="submit">Sign in</button>
            </form>
        </#if>
    </div>
</nav>
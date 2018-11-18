<#include "parts/security.ftl">
<#import "parts/common.ftl" as page/>

<@page.head "styleLogin.css">
<div class="text-center mx-auto">
    <form class="form-signin" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <h1 class="h3 mb-3 font-weight-normal">Edit profile of ${userForEdit.username}</h1>
        <h1 class="h3 mb-3 font-weight-normal">(id: ${userForEdit.id})</h1>

        <label for="inputName" class="sr-only">Name</label>
        <input type="text" id="inputName" class="form-control input-top" name="name" placeholder="Name"
               value="${userForEdit.name}" required
               autofocus>

        <label for="inputUsername" class="sr-only">Username</label>
        <input type="text" id="inputUsername" class="form-control input-middle" name="username" placeholder="Login"
               value="${userForEdit.username}"
               required>

        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control input-middle" name="password"
               placeholder="Password" required>

        <label for="inputPassword2" class="sr-only">Repeat password</label>
        <input type="password" id="inputPassword2" class="form-control input-bottom" name="password2"
               placeholder="Repeat password" required>

        <div class="btn-group btn-group-toggle mx-auto pb-2" data-toggle="buttons">
            <label class="btn btn-light <#if userForEdit.isReader()>active</#if>">
                <input type="radio" name="role" value="Reader" id="Reader" autocomplete="off"
                       <#if userForEdit.isReader()>checked</#if>> Reader
            </label>
            <label class="btn btn-light <#if userForEdit.isSupplier()>active</#if>">
                <input type="radio" name="role" value="Supplier" id="Supplier"
                       autocomplete="off" <#if userForEdit.isSupplier()>checked</#if>> Supplier
            </label>
            <label class="btn btn-light <#if userForEdit.isLibrarian()>active</#if>">
                <input type="radio" name="role" value="Librarian" id="Librarian"
                       autocomplete="off" <#if userForEdit.isLibrarian()>checked</#if>> Librarian
            </label>
        </div>

        <#if message??>
            <p>${message}</p>
        </#if>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
        <br>

        <p class="mt-5 mb-3 text-muted">&copy; 2018</p>
    </form>
</div>
</@page.head>

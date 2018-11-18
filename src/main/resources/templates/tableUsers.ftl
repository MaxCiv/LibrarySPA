<#include "parts/security.ftl">
<#import "parts/common.ftl" as page/>

<@page.head "">
<div class="row">
    <div class="col">
        <table class="table table-striped table-bordered table-hover">
            <thead class="header-bar text-white">
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Login</th>
                <th scope="col">Name</th>
                <th scope="col">Roles</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <#list users as currentUser>
            <tr>
                <th scope="row">${currentUser.id}</th>
                <td>${currentUser.username}</td>
                <td>${currentUser.name}</td>
                <td><#list currentUser.roles as role>${role.roleName}<#sep>, </#list></td>
                <td>
                    <form method="get" action="/librarian/editUser/${currentUser.id}">
                        <button type="submit" class="btn btn-outline-info btn-sm">Edit</button>
                    </form>
                </td>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>
</@page.head>

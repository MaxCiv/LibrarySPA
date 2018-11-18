<#include "parts/security.ftl">
<#import "parts/common.ftl" as page/>

<@page.head "">
<div class="row">
    <div class="text-center mx-auto">
        <#if !userAuth??>
            <h1 class="h3 mb-3 font-weight-normal">Hello!</h1>
            You can <a href="/login">sign in</a> or <a href="/registration">register new account</a>.
        <#else>
            <h1 class="h3 mb-3 font-weight-normal">You can work now!</h1>
        </#if>
    </div>
</div>
</@page.head>

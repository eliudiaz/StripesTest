<%--
 Copyright (C) 2008-2012 Freddy Daoud

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>

<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout.jsp" title="Sistema RRHH">
    <s:layout-component name="body">
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/style.css">
        <header class="headerNew">
        </header>
        <div class="login-page">
            <div class="form">
                <s:errors action="/Login.htm"/>
                <s:form class="login-form"  action="/Login.htm" focus="username">
                    <!--<input type="text" placeholder="CUI"/>-->
                    <sdyn:text name="username" value="${user.username}" placeholder="Usuario"/>
                    <!--<input type="password" placeholder="password"/>-->
                    <sdyn:password name="password" placeholder="Contraseņa" />
                    <!--<p class="message"><a href="#">Recuperar Contraseņa</a></p>-->
                    <s:hidden name="targetUrl" />
                    <!--<button type="submit" value="login">Ingresar</button>-->
                    <s:submit  name="login" value="Ingresar" />
                </s:form>
            </div>
        </div>
    </s:layout-component>
</s:layout-render>

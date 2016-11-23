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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<!doctype html>
<html>
    <head>
        <!--<meta http-equiv="refresh" content="0;url=/MS_RRHH/fuse">-->
    </head>
    <body>
    <stripes:useActionBean beanclass="org.ms.rrhh.action.HomeActionBean" var="bean"/>
    <jsp:useBean id="currentUser"   class="org.ms.rrhh.dao.dto.UsuarioDto" scope="session"></jsp:useBean>
        <script type="text/javascript">
            if (typeof (Storage) !== "undefined") {
                localStorage.setItem("sessionId", '<c:out value="${pageContext.session.id}"/>');
                localStorage.setItem("context",
                        '<c:out value="${sessionScope.applicationPath}"/>');
                localStorage.setItem("servicesPath",
                        '<c:out value="${sessionScope.servicesPath}"/>');
                localStorage.setItem("lectorPath",
                        '<c:out value="${sessionScope.lectorPath}"/>');
                localStorage.setItem("pushPath",
                        '<c:out value="${sessionScope.pushPath}"/>');
                localStorage.setItem("pullPath",
                        '<c:out value="${sessionScope.pullPath}"/>');
                localStorage.setItem("currentUser", JSON.stringify(<%=currentUser%>));
                setTimeout(function () {
                    window.location = "${sessionScope.applicationPath}/fuse";
                }, 300);
            } else {
                alert("Explorador no compatible con aplicacion!, intente con uno mas reciente.");
            }


    </script>
</body>
</html>
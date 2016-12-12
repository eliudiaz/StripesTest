<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<jsp:useBean id="error" class="gt.org.isis.api.misc.exceptions.ext.ValidationException"><jsp:useBean/>
    <div style="color:#b72222; font-weight: bold">Listado errores:</div><ol>
        <c:forEach var="e" items="error.errors" >
            <li style="color: #b72222;">
                <c:out value="e.message"></c:out>
                </li>
        </c:forEach>
    </ol>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<%-- For purposes of this task CSS/JS weren't used --%>

<!DOCTYPE html>
<html>
    <head>
        <title>List of users</title>
    </head>

    <body>
        <%@include file="kind_of_a_header.jsp"%>

        <h1>Users:</h1>

        <table>
            <thead>
                <tr style="border: 1px solid">
                    <th scope="col" style="border: 1px solid">Login</th>
                    <th scope="col" style="border: 1px solid">Name</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${not empty users}">
                    <c:forEach var="user" items="${users}">
                        <tr style="border: 1px solid">
                            <td style="border: 1px solid">${user.login}</td>
                            <td style="border: 1px solid">${user.name}</td>
                        </tr>
                    </c:forEach>
                </c:if>
            </tbody>
        </table>
    </body>
</html>
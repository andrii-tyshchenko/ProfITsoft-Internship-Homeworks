<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<%-- For purposes of this task CSS/JS weren't used --%>

<!DOCTYPE html>
<html>
    <head>
        <title>Login page</title>
    </head>

    <body>
        <c:if test="${not empty error}">
            <p style="color: red">
                <b>${error}</b>
            </p>
        </c:if>

        <form method="POST">
            <input type="text" id="login" name="login" placeholder="Login" />
            <label for="login">Login</label>

            <input type="password" id="password" name="password" placeholder="Password" />
            <label for="password">Password</label>

            <input type="submit" value="Login" />
        </form>
    </body>
</html>
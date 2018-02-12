<%--
  Created by IntelliJ IDEA.
  User: Taku
  Date: 12/02/2018
  Time: 1:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Home</title>
  </head>
  <body>
    <h1>Login</h1>
    <form action="users" method="post">
        <fieldset>
          <label for="username">Username</label>
          <input type="text" name="username" id="username" value="${user.username}"/>
          <label for="username">Password</label>
          <input type="text" name="password" id="password" value="${user.password}"/>
          <input type="submit">
        </fieldset>
    </form>
  </body>
</html>

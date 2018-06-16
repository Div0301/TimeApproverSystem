<%-- 
    Document   : index
    Created on : May 23, 2018, 11:57:48 PM
    Author     : Div
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Time Entry Management</title>
    </head>
    <body>
    <h1>Login User</h1>
        <hr>
    <form action="VerifyUser">
            <pre>
            Userid          <input type="text" name="userid" />
            Password        <input type="password" name="password"/>
            Usertype        <select name="usertype">
                            <option>employee</option>
                            <option>admin</option>
                            <option>approver</option>
                            </select>
          <!--  Save-Password   <input type="checkbox" name="save" value="yes"/>  -->
                            <input type="submit" value="Login"/>
            </pre>
        </form>
    </body>
</html>

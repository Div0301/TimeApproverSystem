<%-- 
    Document   : adminportal
    Created on : May 24, 2018, 1:35:55 AM
    Author     : Div
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <h1>Employee detail form</h1>
        <hr>
    <form action="EmployeeDetail">
            <pre>
            Userid          <input type="text" name="uid" />
            Username        <input type="text" name="uname" />
            Password        <input type="password" name="upass"/>
            Usertype        <select name="utype">
                            <option>employee</option>
                            <option>admin</option>
                            <option>approver</option>
                            </select>
          <!--  Save-Password   <input type="checkbox" name="save" value="yes"/>  -->
                            <input type="submit" value="Submit"/>
            </pre>
    </form>
    
        
    <h1>Employee Authority form</h1>
        <hr>
    <form action="EmployeeAuthority">
            <pre>
            Userid          <input type="text" name="uid" />
           
            Usertype        <select name="utype">
                            <option>employee</option>
                            <option>admin</option>
                            <option>approver</option>
                            </select>
          <!--  Save-Password   <input type="checkbox" name="save" value="yes"/>  -->
                            <input type="submit" value="Submit"/>
            </pre>
        </form>    
    </body>
</html>

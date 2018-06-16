<%-- 
    Document   : detail.jsp
    Created on : Jun 4, 2018, 11:29:48 AM
    Author     : Div
--%>
<%
    try{
String uname=(String)session.getAttribute("username");

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h1><%=uname%></h1>
<%
    String usid=(String)session.getAttribute("userid");
    Class.forName("com.mysql.jdbc.Driver");
    java.sql.Connection con=java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/timedb","root","1234567890");
    java.sql.Statement stmt=con.createStatement();
    java.sql.ResultSet rs=stmt.executeQuery("select * from detail where userid='"+usid+"'");
%>
<table>
            
                <tr>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Name</th>
                    <th>Type</th>
                    
                    <th>Comment</th>
                    
                   
                </tr>
           
            
<%
    rs.afterLast();
    
    while(rs.previous()){
        String s1=rs.getString(2);
        String s2=rs.getString(3);
        String s3=rs.getString(4);
        String s4=rs.getString(5);
        String s5=rs.getString(6);
        
%>               
                <tr>
                        
                    <td><%=s1%></td>
                    <td><%=s2%></td>
                    <td><%=s3%></td>
                    <td><%=s4%></td>
                                       
                    <td><%=s5%></td>
                    
                    
                    
                </tr>
<%
    }
    con.close();
%>  
            
        </table>
<button onclick="goBack()">Go Back</button>


    </body>
</html>
<%
    }catch(Exception e)
    {
        out.println(e);
    }
%>
<script>
function goBack() {
    window.history.back();
}
</script>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>

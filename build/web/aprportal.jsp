<%-- 
    Document   : aprportal
    Created on : May 26, 2018, 2:54:25 PM
    Author     : Div
--%>
<!-- get the details from table -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    try{
String uname=(String)session.getAttribute("username");

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hello Approver</title>
        
    </head>
    <body>
        <h1>Hello Approver <%=uname%></h1>
        <hr>
        <br>
<%
    Class.forName("com.mysql.jdbc.Driver");
    java.sql.Connection con=java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/timedb","root","1234567890");
    java.sql.Statement stmt=con.createStatement();
    java.sql.ResultSet rs=stmt.executeQuery("select * from etime");
%>
<table>
            
                <tr>
                    <th>User ID</th>
                    
                    
                    <th>Date</th>
                    <th>Status</th>
                </tr>
           
            
<%
    rs.afterLast();
    
    while(rs.previous()){
       // if((rs.getString(6).equals("Rejected") ||  rs.getString(6).equals("submit")))
        if(rs.getString(6).equals("submit"))
        {
        String s0=rs.getString(1);
       
        
        //String s5=rs.getString(6);
        String s7=rs.getString(7);
%>               
                <tr>
                <form action="TimeApp">
                    <td><input type="text" value="<%=s0%>" name="userid" readonly></td>
                    
                 
                   
                    
                    <td><input type="text" value="<%=s7%>" name="date" ></td>
                    <td><input type="submit" value="Approve" name="appbt"><input type="submit" value="Rejected" name="appbt"></td>
                </form>    
                </tr>
 
              <%
                  }
    }
    con.close();
%>            
</table>
<br>
<br>
      <%
    Class.forName("com.mysql.jdbc.Driver");
    java.sql.Connection conn=java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/timedb","root","1234567890");
    java.sql.Statement stmtt=conn.createStatement();
    java.sql.ResultSet rset=stmtt.executeQuery("select * from etime");
    String qr="select * from detail where userid=? and date=?";
    java.sql.PreparedStatement pr=conn.prepareStatement(qr);;
%>

           
            
<%
    rset.afterLast();
    
    while(rset.previous()){
        if((rset.getString(6).equals("Rejected") ||  rset.getString(6).equals("submit")))
        {
        String s8=rset.getString(1);
        String s9=rset.getString(4);
        String s13=rset.getString(6);
        String s14=rset.getString(7);
        pr.setString(1, s8);
        pr.setString(2, s14);
        java.sql.ResultSet rdetail=pr.executeQuery();
        
%>           
<table>
            
                <tr>
                    <th>User ID</th>
                 <!--   <th>Name</th>
                    <th>Type</th>
                    <th>Time</th>
                    <th>Comment</th>-->
                    
                 <th>Total time </th>
                    <th>Status</th>
                    <th>Date</th>
                </tr>
                <tr>
                
                    <td><%=s8%></td>
                    <td><%=s9%></td>
                   
                   
                    
                    <td><%=s13%></td>
                    <td><%=s14%></td>
                  
                </tr>
                 <table>
                     
                <tr>
                    <th>name</th>
                 <!--   <th>Name</th>
                    <th>Type</th>
                    <th>Time</th>
                    <th>Comment</th>-->
                    
                    <th>type</th>
                    <th>time</th>
                    <th>comment</th>
                </tr>
   <%
    rdetail.afterLast();
    
    while(rdetail.previous()){
    String name=rset.getString(2);
        
        String type=rset.getString(3);
        String time=rset.getString(4);
        String comment=rset.getString(5);
       
        
%>        
   
                <tr>
                
                    <td><%=name%></td>
                   
                   
                    
                    <td><%=type%></td>
                    <td><%=time%></td>
                    <td><%=comment%></td>
                  
                </tr>


   
                <%
                    }   %>
                     </table>
                     <br>
                    <%
}
    }
    conn.close();
%> 
</table>
    </body>
</html>
<%
    }catch(Exception e)
    {
        out.println(e);
    }
%>
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
   

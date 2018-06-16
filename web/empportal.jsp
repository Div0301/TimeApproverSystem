
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    try{
String uname=(String)session.getAttribute("username");

%>

<html>
        <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=uname%></title>
        </head>
    
     <body>
        <h1>Hello <%=uname%></h1>
        <hr>
        <form action="TaskEntry">
          <pre>
            Name of task          <input type="text" name="etask"  required/>
            Time                  <input type="text" name="etime" required/>
            Task type             <input type="text" name="ettype" required />
            Date                  <input type="date" name="edate"  required/>
            Comment(optional)     <input type="text" name="comm">
                                   
                            <input type="submit" value="Submit"/>
            </pre>
        </form>
        <br>
        <a href="detail.jsp">DETAIL</a>
        <br>
        <br>
<%
    String usid=(String)session.getAttribute("userid");
    Class.forName("com.mysql.jdbc.Driver");
    java.sql.Connection con=java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/timedb","root","1234567890");
    java.sql.Statement stmt=con.createStatement();
    java.sql.ResultSet rs=stmt.executeQuery("select * from etime where userid='"+usid+"'");
%>
<table>
            
                <tr>
                    
                    <th>Status</th>
                    <th>Date</th>
                </tr>
           
            
<%
    rs.afterLast();
    
    while(rs.previous()){
       
        String s5=rs.getString(6);
        String s7=rs.getString(7);
%>               
                <tr>
                        
                 
                    <td><%=s7%></td>                    
                    <td><%=s5%>
                    <% if(s5.equals("Rejected"))
                    {
                    %>
                    <form action="DeleteEntry">
                        <input type="hidden" value="<%=usid%>" name="dusid" readonly> 
                        <input type="hidden" value="<%=s7%>" name="ddate" readonly>
                    <input type="submit" value="DELETE">
                    </form>
                    <%
                    }
                    %>
                    </td>
                    
                    
                </tr>
<%
    }
    con.close();
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
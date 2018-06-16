/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Div
 */
public class VerifyUser extends HttpServlet {
        Connection con;PreparedStatement ps;ResultSet rs;
        @Override
        public void init(){
            try{
               String qr="select uname from usertype where userid=? and password=?"; 
               Class.forName("com.mysql.jdbc.Driver");
               con=DriverManager.getConnection("jdbc:mysql://localhost:3306/timedb","root","1234567890");
               ps=con.prepareStatement(qr);     
            }catch(Exception e){
                
            }
        }
   
        @Override
        public void destroy(){
            try{
                con.close();
            }catch(Exception e){
                
            }
        }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            /* TODO output your page here. You may use following sample code. */
            String s1=request.getParameter("userid");
            String s2=request.getParameter("password");
            String s3=request.getParameter("usertype");
            
            if(s3.equals("admin"))
            {
                if(s2.equals("admin") && s1.equals("admin"))
                {
                    response.sendRedirect("adminportal.jsp");
                }
                else
                {
                     out.println("<h2>INVALID ADMIN CREDENTIALS</h2>");
                     out.println("<a href=index.jsp>Try-Again</a>");
                }
            }
            if(s3.equals("employee"))
            {
                ps.setString(1,s1);
                ps.setString(2,s2);
                rs=ps.executeQuery();
                boolean found=rs.next();
                
                if(found==true)
                {
                    String name=rs.getString("uname");
                    HttpSession session=request.getSession();
                    session.setAttribute("username", name);
                    session.setAttribute("userid",s1);
                    response.sendRedirect("empportal.jsp");
                   // response.sendRedirect("TaskEntry");
                }
                else{
                    out.println("<h2>INVALID  CREDENTIALS</h2>");
                    out.println("<a href=index.jsp>Try-Again</a>");
                }
                
                
                
            }
            if(s3.equals("approver"))                              //for a employee whose a approver too, user id will be same therefore check on user type
                
            {
                ps.setString(1,s1);
                ps.setString(2,s2);
                rs=ps.executeQuery();
                boolean found=rs.next();
                
                if(found==true)
                {
                    String name=rs.getString("uname");
                    HttpSession session=request.getSession();
                    session.setAttribute("username", name);
                    session.setAttribute("userid",s1);
                    response.sendRedirect("aprportal.jsp");
                   // response.sendRedirect("TaskEntry");
                }
                else{
                    out.println("<h2>INVALID  CREDENTIALS</h2>");
                    out.println("<a href=index.jsp>Try-Again</a>");
                }
                
            }
        }   catch (Exception ex) {
                //Logger.getLogger(VerifyUser.class.getName()).log(Level.SEVERE, null, ex);
                out.println(ex);
            }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Div
 */
public class TimeApp extends HttpServlet {

    Connection con;PreparedStatement psupdate;ResultSet rs;
    @Override
    public void init()
    {        
    try{
           
          
            String qrupdate2="update etime set status=? where userid=? and edate=?";
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/timedb","root","1234567890");
           
            psupdate=con.prepareStatement(qrupdate2);
            
        }catch(Exception e)
        {
            
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
        String s1=request.getParameter("userid");
        String s2=request.getParameter("date");
        String s3=request.getParameter("appbt");
         try{  
             if(s3.equals("Approve"))
                 psupdate.setString(1,"Approved");
             else
                 psupdate.setString(1,"Rejected");
                 psupdate.setString(2,s1);
                 psupdate.setString(3,s2);
                 psupdate.executeUpdate();
                 response.sendRedirect("aprportal.jsp");
         }catch(Exception e)
        {
            out.println(e);
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

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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Div
 */
public class TaskEntry extends HttpServlet {

      
    Connection con;PreparedStatement psinsert,psupdate,psupdate2,ps;ResultSet rs;
    @Override
    public void init()
    {        
    try{
            String qrinsert="insert into etime values (?,?,?,?,?,?,?)";
            String qrupdate1="select * from etime where userid=? and edate=?";
            String qrupdate2="update etime set ptime=? where userid=? and edate=?";
            String qinsert="insert into detail values (?,?,?,?,?,?)";
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/timedb","root","1234567890");
            psinsert=con.prepareStatement(qrinsert);
            psupdate=con.prepareStatement(qrupdate1);
            psupdate2=con.prepareStatement(qrupdate2);
            ps=con.prepareStatement(qinsert);
            
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
           HttpSession session=request.getSession();
           
           String s1=request.getParameter("etask");
           String s2=request.getParameter("ettype");
           String s3=request.getParameter("etime");
           String s4=request.getParameter("comm");
           String s5=request.getParameter("edate");
           String uid=(String)session.getAttribute("userid");
           String s6="submit";
        try{
                    ps.setString(1, uid);   //to insert into detail tale
                    ps.setString(2, s5);
                    ps.setString(3, s3);
                    ps.setString(4, s1);
                    ps.setString(5, s2);
                    ps.setString(6,s4);
                    

                    ps.executeUpdate(); 
                    
                    
                 psupdate.setString(1,uid);
                 psupdate.setString(2,s5);
                 rs=psupdate.executeQuery();
                 boolean found=rs.next();
               
                
                if(found==true)
                {
                   int i; 
                   i = Integer.parseInt(s3);
                   i=i+rs.getInt(4);
                   String s=Integer.toString(i);
                   psupdate2.setString(1, s);
                   psupdate2.setString(2, uid);
                   psupdate2.setString(3, s5);
                   psupdate2.executeUpdate();
                   
                    
                   
                  
                }
                else
                {
            
            
            

                    psinsert.setString(1, uid);
                    psinsert.setString(2, s1);
                    psinsert.setString(3, s2);
                    psinsert.setString(4, s3);
                    psinsert.setString(5, s4);
                    psinsert.setString(6,s6);
                    psinsert.setString(7, s5);

                    psinsert.executeUpdate();
                }
            response.sendRedirect("empportal.jsp");
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

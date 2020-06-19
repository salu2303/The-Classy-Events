/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;

/**
 *
 * @author HENA
 */
public class Book extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String et=request.getParameter("eventtype");
        String ed=request.getParameter("EventDate");
        String eti=request.getParameter("EventTime");
        String ev=request.getParameter("EventVenue");
        String b=request.getParameter("Budget");
        Cookie cookie=null;
        Cookie[] c=null;
        int id = 0;
        c=request.getCookies();
        for(int i=0;i<c.length;i++)
        {
          cookie=c[i];
          if((cookie.getName()).equals("1"))
                  {
                       id=Integer.parseInt(cookie.getValue());
                  }
          
                  
        }
        try (PrintWriter out = response.getWriter()) {
            
            
            
            try
            {
                
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/se?[root on Default schema]","root","");
                   String q="insert into bookevent(eventtype,eventdate,eventtime,eventvenue,budget,c_id)"+"values(?,?,?,?,?,?)";
                    // st.executeUpdate(q); 
                    try{
                        PreparedStatement psmt=conn.prepareStatement(q,Statement.RETURN_GENERATED_KEYS);
                       
                        
                        psmt.setString(1,et);
                        psmt.setString(2,ed);
                        psmt.setString(3,eti);
                        psmt.setString(4,ev);
                        psmt.setString(5,b);
                        psmt.setInt(6,id);
                        
                         
                         
                         int row=psmt.executeUpdate();
                    out.println("U REGISTERED EVENT");
                    
                    RequestDispatcher rs=request.getRequestDispatcher("chome.html");
                    rs.include(request, response);
                    //response.sendRedirect("cust.html");
                    }
                    catch(SQLException e){
                        out.println(e);
                    }                      
                     //boolean rs=st.execute("create table retailer(username VARCHAR(15) PRIMARY KEY,name VARCHAR(20),address VARCHAR(60),mobileno INT(10),email VARCHAR(25),password VARCHAR(12))");
                /*out.println("after boolean");
                if(("True").equals(rs))
                    out.println("created");
                else
                    out.println("not created");*/
            }
            catch(Exception e){
            
                out.println(e); }    
            /* TODO output your page here. You may use following sample code. */
         
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

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
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dell
 */
public class Pros extends HttpServlet {

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
        String id=request.getParameter("id");
        try (PrintWriter out = response.getWriter()) {
             try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/se?[root on Default schema]", "root", "");
                    //  Statement st=conn.createStatement();
                    // st.executeUpdate("insert into customer(c_name,c_address,c_city,c_mono,c_email,c_password) values('uname','add','city',mob,'email','pass')");
                    //  out.println("after classdfdfdfdf");
                    String q = "insert into status(id)" + "values(?)";
                    // st.executeUpdate(q); 
                    try {
                        PreparedStatement psmt = conn.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
                        psmt.setString(1,id);
                        
                        int row = psmt.executeUpdate();
                       
                        javax.servlet.RequestDispatcher rs = request.getRequestDispatcher("ahome.html");
                        rs.include(request, response);
                        //response.sendRedirect("cust.html");
                    } catch (SQLException e) {
                        out.println(e);
                    }
                    
                } catch (Exception e) {

                    out.println(e);
                }
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

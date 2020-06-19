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

/**
 *
 * @author HENA
 */
public class Sadd extends HttpServlet {

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
        String uname = request.getParameter("c_name");
        String add = request.getParameter("add");
        String city = request.getParameter("city");
 long mob = Long.parseLong(request.getParameter("mob"));
              
// int mob = Integer.parseInt(request.getParameter("mob"));
        String email = request.getParameter("email");

        String pass = request.getParameter("password");
        String repass = request.getParameter("repass");
        if (pass.equals(repass)) {
            try (PrintWriter out = response.getWriter()) {

                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/se?[root on Default schema]", "root", "");
                    //  Statement st=conn.createStatement();
                    // st.executeUpdate("insert into customer(c_name,c_address,c_city,c_mono,c_email,c_password) values('uname','add','city',mob,'email','pass')");
                    //  out.println("after classdfdfdfdf");
                    String q = "insert into customer(c_name,c_address,c_city,c_mono,c_email,c_password)" + "values(?,?,?,?,?,?)";
                    // st.executeUpdate(q); 
                    try {
                        PreparedStatement psmt = conn.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);

                        psmt.setString(1, uname);
                        psmt.setString(2, add);
                        psmt.setString(3, city);
psmt.setLong(4, mob);
// psmt.setInt(4, mob);
                        psmt.setString(5, email);
                        psmt.setString(6, pass);

                        int row = psmt.executeUpdate();
                        out.println("U REGISTERED SUCESSFULLY NOW LOGIN..");

                        RequestDispatcher rs = request.getRequestDispatcher("index.html");
                        rs.include(request, response);
                        //response.sendRedirect("cust.html");
                    } catch (SQLException e) {
                        out.println(e);
                    }
                    
                } catch (Exception e) {

                    out.println(e);
                }
                /* TODO output your page here. You may use following sample code. */

            }
        } else {
            System.out.println("PASWORD MISMATCH");
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

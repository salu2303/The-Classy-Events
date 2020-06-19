/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;

/**
 *
 * @author HENA
 */
public class customerval extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            RequestDispatcher rd1 = request.getRequestDispatcher("chome.html");
            int flag = 0;
            String username = request.getParameter("cuname");
            String password = request.getParameter("cpass");
            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/se?[root on Default schema]", "root", "");
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select c_name,c_password,c_id from customer ");

                while (rs.next()) {
                    // out.println(request.getParameter("cpass"));

                    if ((rs.getString("c_name")).equals(username) && (rs.getString("c_password")).equals(request.getParameter("cpass"))) {
                        //  out.println("hello");
                        //out.println(rs.getString("c_id"));
                        String id = rs.getString("c_id");
                        Cookie c = new Cookie("1", id);
                        response.addCookie(c);
                        c.setMaxAge(5);
                        flag = 1;

                        rd1.forward(request, response);
                    }
                    
                }
                
                 if(flag!=1)
                {
                    out.println("PLEASE ENTER CORRECT CREDENTIALS!!");
                     
                    RequestDispatcher rd2=request.getRequestDispatcher("index.html?flag=1");
                    rd2.forward(request,response);
                }
                     
            } catch (Exception e) {
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

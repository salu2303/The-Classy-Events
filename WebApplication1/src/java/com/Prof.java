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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dell
 */
public class Prof extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            Cookie cookie = null;
            Cookie[] c = null;
            int id = 0;
            c = request.getCookies();
            for (Cookie c1 : c) {
                cookie = c1;
                if ((cookie.getName()).equals("1")) {
                    id = Integer.parseInt(cookie.getValue());

                }
            }
            //out.println(id);

            try {
           
                Class.forName("com.mysql.jdbc.Driver");

                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/se?[root on Default schema]", "root", "");
                Statement st = conn.createStatement();
            //      out.println(id);
                ResultSet rs = st.executeQuery("select * from customer where c_id =" + id);
                rs.next();
                javax.servlet.RequestDispatcher rd2=request.getRequestDispatcher("show.html");
                    rd2.include(request,response);
 
                out.println("<center>");

                out.println("customer-id: ");

                out.println(rs.getString("c_id"));
                out.println("</br>");
                out.println("Name:  ");

                out.println(rs.getString("c_name"));
                out.println("</br>");
                out.println("Address:  ");

                out.println(rs.getString("c_address"));
                out.println("</br>");
                out.println("City:  ");

                out.println(rs.getString("c_city"));
                out.println("</br>");
                   out.println("mobile-number:  ");

                out.println(rs.getString("c_mono"));
                out.println("</br>");
                out.println("email-id:  ");

                out.println(rs.getString("c_email"));
                out.println("</br>");
                out.println("Password:  ");

                out.println(rs.getString("c_password"));
               // out.println(("</center>"));
            out.println("</center>");

             
            }
                
            catch (ClassNotFoundException e) {
                out.println(e);
            } catch (SQLException e) {
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

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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dell
 */
public class Veve extends HttpServlet {

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
            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/se?[root on Default schema]", "root", "");
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from bookevent ");
                while (rs.next()) {

                    
                    out.println("customer-id:"+rs.getString(7));
                    out.println("</br>");
                    out.println("Event-type:"+rs.getString(2));
                    out.println("</br>");

                    out.println("Event-Date:"+rs.getString(3));
                    out.println("</br>");

                    out.println("Event-Time:"+rs.getString(4));
                    out.println("</br>");

                    out.println("Event-Venue:"+rs.getString(5));
                    out.println("</br>");

                    out.println("Budget:"+rs.getString(6));
                    out.println("</br>");
out.println("</br>");
  javax.servlet.RequestDispatcher rd2=request.getRequestDispatcher("conf.html");
                    rd2.include(request,response);
 

                    /*    int id = Integer.parseInt(rs.getString("c_id"));
                    ResultSet rs2 = st.executeQuery("select * from customer where c_id=" + id);
                    rs2.next();
                    out.println(rs2.getString("c_name"));
                    out.println(rs2.getString("c_mono"));
                   rs2.close();*/
                }
                out.println("<input type=\"button\" name=\"logout\" value=\"BACK\" onclick=\"window.location='ahome.html'\">\n" +
"     ");
            } catch (ClassNotFoundException e) {
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

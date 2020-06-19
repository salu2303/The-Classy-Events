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
public class Msg extends HttpServlet {

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
            Cookie cookie = null;
            Cookie[] c = null;
            String vid =null;
            c = request.getCookies();
            for (Cookie c1 : c) {
                cookie = c1;
                if ((cookie.getName()).equals("1")) {
                    vid = (cookie.getValue());

                }
            }
            int flag=0;
            //out.println(id);

            try {
           
                Class.forName("com.mysql.jdbc.Driver");

                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/se?[root on Default schema]", "root", "");
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select id from status where id ="+vid);
              //out.println(rs.getString("id"));
               // out.println(rs.next());   
               while(rs.next()) 
               {
                                    out.println("<h3>"+"...............................confirmation message..................................\n" +
"\n" +"</br>"+
"Dear Customer,\n" +"</br>"+
"Thank you for choosing us for managing your event.\n" +"</br>"+
"This is Saloni from EVENTO  .This is confirmation message that we will look forward for managing your events and we will contact soon for further formalities."+"</h3>");

                   javax.servlet.RequestDispatcher rs3 = request.getRequestDispatcher("show.html");
                        rs3.include(request, response);
                        
                 flag=1;
                
                }
                if(flag==0)
                {
                 out.println("<h3>"+"Dear Customer,\n" +
"Thank you for visiting our site.\n" +"</br>"+
"Your current status should one of this:\n"+"</br>" +
" 1.You have not registered any event.\n" +"</br>"+
"2.Your event registration may be in pending list.We will inform you soon with confirmation massage."+"</h3>");
                   javax.servlet.RequestDispatcher rs3 = request.getRequestDispatcher("show.html");
                        rs3.include(request, response);
                    
                }
             
             
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

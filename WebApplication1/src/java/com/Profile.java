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

public class Profile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
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

            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/se?[root on Default schema]", "root", "");
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from customer where (c_id=id)");
                out.println("he");
                out.println(rs.getString("c_address"));

            } catch (ClassNotFoundException e) {
                out.println(e);
            } catch (SQLException e) {
                System.out.println(e);

            }
        }
        

       
    }
}

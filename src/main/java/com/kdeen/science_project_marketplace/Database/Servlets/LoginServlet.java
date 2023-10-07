package com.kdeen.science_project_marketplace.Database.Servlets;

import com.kdeen.science_project_marketplace.Database.Helpers.PasswordHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "login", value = "/login")
public class LoginServlet  extends HttpServlet {
    private String url;
    private final Logger LOGGER = Logger.getLogger("CreateUser");

    public void init () {
        url = "jdbc:sqlite:c:\\Users\\kdeen\\IntellijProjects\\Science Project Marketplace\\Joint.db";
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt  = conn.createStatement();

            String sql = "SELECT email, password, salt from Accounts";
            ResultSet rs = stmt.executeQuery(sql);

            String email = request.getParameter("email");
            String passwordToHash = request.getParameter("password");

            String salt = PasswordHelper.getSalt();
            String password = PasswordHelper.HashPassword(passwordToHash, salt);

            boolean loggedIn = false;

            while (rs.next()){
                if (email.equals(rs.getString("email")) && password.equals(rs.getString("password"))){
                    loggedIn = true;
                }
            }

            request.setAttribute("loggedIn", loggedIn);
            request.getRequestDispatcher("loginResult.jsp").forward(request, response);

        } catch (SQLException | ServletException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

}

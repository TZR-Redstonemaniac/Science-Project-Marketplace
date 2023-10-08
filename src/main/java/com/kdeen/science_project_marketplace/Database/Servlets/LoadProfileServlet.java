package com.kdeen.science_project_marketplace.Database.Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "profile", value = "/profile")
public class LoadProfileServlet extends HttpServlet {
    private String url;
    private final Logger LOGGER = Logger.getLogger("Profile");

    public void init () {
        url = "jdbc:sqlite:c:\\Users\\kdeen\\IntellijProjects\\Science Project Marketplace\\Joint.db";
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt  = conn.createStatement();

            String sql = "SELECT userId from CurrentUser";
            int currentUser = stmt.executeQuery(sql).getInt("userId");

            sql = "SELECT id, email, username, password, dateOfBirth, tags from Accounts where id = " + currentUser;
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                request.setAttribute("email", rs.getString("email"));
                request.setAttribute("username", rs.getString("username"));
                request.setAttribute("dateOfBirth", rs.getString("dateOfBirth"));
                request.setAttribute("tags", rs.getString("tags"));
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            }

        } catch (SQLException | ServletException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

}

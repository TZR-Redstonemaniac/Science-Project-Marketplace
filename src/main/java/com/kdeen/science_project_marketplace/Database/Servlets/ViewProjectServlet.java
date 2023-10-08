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

@WebServlet(name = "viewProject", value = "/viewProject")
public class ViewProjectServlet  extends HttpServlet {
    private String url;
    private final Logger LOGGER = Logger.getLogger("CreateUser");

    public void init () {
        url = "jdbc:sqlite:c:\\Users\\kdeen\\IntellijProjects\\Science Project Marketplace\\Joint.db";
    }

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt  = conn.createStatement();
            int id = Integer.parseInt(request.getParameter("hiddenId")) + 1;

            String sql = "SELECT name, description, details FROM Projects WHERE id = " + id;
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                String detail = rs.getString("details");

                request.setAttribute("name", name);
                request.setAttribute("description", description);
                request.setAttribute("detail", detail);
            }

            conn.close();

            request.getRequestDispatcher("projectView.jsp").forward(request, response);

        } catch (SQLException | ServletException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

}

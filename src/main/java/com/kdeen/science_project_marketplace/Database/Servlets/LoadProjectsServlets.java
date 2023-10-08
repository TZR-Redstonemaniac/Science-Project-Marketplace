package com.kdeen.science_project_marketplace.Database.Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "loadProjects", value = "/loadProjects")
public class LoadProjectsServlets extends HttpServlet {
    private String url;
    private final Logger LOGGER = Logger.getLogger("LoadProjects");

    public void init () {
        url = "jdbc:sqlite:c:\\Users\\kdeen\\IntellijProjects\\Science Project Marketplace\\Joint.db";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        loadProjects(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        loadProjects(request, response);
    }

    public void loadProjects(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html");

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();

            String sql = "SELECT name, description, tags, details from Projects";
            ResultSet rs = stmt.executeQuery(sql);

            List<String> projectNames = new ArrayList<>();
            List<String> projectDescriptions = new ArrayList<>();
            List<String> projectTags = new ArrayList<>();
            List<String> projectDetails = new ArrayList<>();

            while (rs.next()){
                projectNames.add(rs.getString("name"));
                projectDescriptions.add(rs.getString("description"));
                projectTags.add(rs.getString("tags"));
                projectDetails.add(rs.getString("details"));
            }

            request.setAttribute("projectNames", projectNames);
            request.setAttribute("projectDescriptions", projectDescriptions);
            request.setAttribute("projectTags", projectTags);
            request.setAttribute("projectDetails", projectDetails);

            if (stmt.executeQuery("SELECT userId from CurrentUser").getInt("userId") > -1)
                request.setAttribute("loggedIn", true);

            conn.close();

            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (SQLException | ServletException | IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

}

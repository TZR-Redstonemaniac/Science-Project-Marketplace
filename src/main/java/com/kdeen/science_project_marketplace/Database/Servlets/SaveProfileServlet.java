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

@SuppressWarnings("ALL")
@WebServlet(name = "saveProfile", value = "/saveProfile")
public class SaveProfileServlet extends HttpServlet {
    private String url;
    private final Logger LOGGER = Logger.getLogger("Save Profile");

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

            PreparedStatement pstmt = null;

            if (request.getParameter("CurrentPasswordInput") != null
                    && request.getParameter("NewPasswordInput") != null){
                String salt = PasswordHelper.getSalt();

                String currentPasswordToHash = request.getParameter("CurrentPasswordInput");
                String currentPassword = PasswordHelper.HashPassword(currentPasswordToHash, salt);

                String userPasswordToHash =
                        stmt.executeQuery(("SELECT password from Accounts where id = " + currentUser)).getString(
                                "password");
                String userPassword = PasswordHelper.HashPassword(userPasswordToHash, salt);

                if (currentPassword == userPassword){
                    sql = "UPDATE Accounts SET email = ?, username = ?," +
                            "dateOfBirth = ?, tags = ?, password = ? WHERE id = " + currentUser;
                    pstmt = conn.prepareStatement(sql);

                    String newPasswordToHash = request.getParameter("NewPasswordInput");
                    String newPassword = PasswordHelper.HashPassword(currentPasswordToHash, salt);
                    pstmt.setString(5, newPassword);
                } else {
                    sql = "UPDATE Accounts SET email = ?, username = ?," +
                            "dateOfBirth = ?, tags = ? WHERE id = " + currentUser;
                    pstmt = conn.prepareStatement(sql);
                }
            } else {
                sql = "UPDATE Accounts SET email = ?, username = ?," +
                        "dateOfBirth = ?, tags = ? WHERE id = " + currentUser;
                pstmt = conn.prepareStatement(sql);
            }

            pstmt.setString(1, request.getParameter("email"));
            pstmt.setString(2, request.getParameter("username"));
            pstmt.setString(3, request.getParameter("dateOfBirth"));
            pstmt.setString(4, request.getParameter("tags"));

            pstmt.executeUpdate();

            request.setAttribute("loggedIn", true);
            request.getRequestDispatcher("loadProjects").forward(request, response);

        } catch (ServletException | SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }
}

package com.kdeen.science_project_marketplace.Database.Servlets;

import com.kdeen.science_project_marketplace.Database.Helpers.PasswordHelper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "createUser", value = "/createUser")
public class CreateUserServlet extends HttpServlet {
    private String url;
    private final Logger LOGGER = Logger.getLogger("CreateUser");

    public void init () {
        url = "jdbc:sqlite:c:\\Users\\kdeen\\IntellijProjects\\Science Project Marketplace\\Joint.db";
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");

        try {
            Class.forName("org.sqlite.JDBC");

            Connection conn = DriverManager.getConnection(url);
            String sql = "INSERT INTO Accounts(email, username, password, salt) VALUES(?, ?, ?, ?)";

            if (conn == null) {
                LOGGER.log(Level.WARNING, "Unable to connect to database");
                return;
            }

            PreparedStatement pstmt = conn.prepareStatement(sql);

            String email = request.getParameter("email");
            String name = request.getParameter("username");
            String passwordToHash = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");

            if (!passwordToHash.equals(confirmPassword)) return;

            String salt = PasswordHelper.getSalt();
            String password = PasswordHelper.HashPassword(passwordToHash, salt);

            pstmt.setString(1, email);
            pstmt.setString(2, name);
            pstmt.setString(3, password);
            pstmt.setString(4, salt);

            pstmt.executeUpdate();

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
package com.aware.servlet;

import com.aware.dao.UserDAO;
import com.aware.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class SignupServlet extends HttpServlet {

    // Show the signup page
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(req, resp);
    }

    // Handle signup form submission
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username").trim();
        String email    = req.getParameter("email").trim();
        String password = req.getParameter("password").trim();
        String confirm  = req.getParameter("confirmPassword").trim();

        // Basic validation
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "All fields are required.");
            req.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(req, resp);
            return;
        }

        if (!password.equals(confirm)) {
            req.setAttribute("error", "Passwords do not match.");
            req.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(req, resp);
            return;
        }

        if (password.length() < 6) {
            req.setAttribute("error", "Password must be at least 6 characters.");
            req.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(req, resp);
            return;
        }

        try {
            UserDAO userDAO = new UserDAO();

            if (userDAO.usernameExists(username)) {
                req.setAttribute("error", "Username already taken.");
                req.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(req, resp);
                return;
            }

            if (userDAO.emailExists(email)) {
                req.setAttribute("error", "Email already registered.");
                req.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(req, resp);
                return;
            }

            // Create new user (role = "user" by default)
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password); // plain text for now (college project level)
            user.setRole("user");

            userDAO.insertUser(user);

            // Redirect to login with success message
            resp.sendRedirect(req.getContextPath() + "/login?success=1");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Something went wrong. Try again.");
            req.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(req, resp);
        }
    }
}

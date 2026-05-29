package com.aware.servlet;

import com.aware.dao.UserDAO;
import com.aware.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    // Show the login page
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    // Handle login form submission
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();

        if (username.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "Please fill in all fields.");
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
            return;
        }

        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.login(username, password);

            if (user == null) {
                req.setAttribute("error", "Wrong username or password.");
                req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
                return;
            }

            // Save user info in session
            HttpSession session = req.getSession();
            session.setAttribute("user", user.getUsername());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("role", user.getRole());
            session.setAttribute("userId", user.getId());

            // Redirect based on role
            if (user.isAdmin()) {
                resp.sendRedirect(req.getContextPath() + "/admin");
            } else {
                resp.sendRedirect(req.getContextPath() + "/my-reports");
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Something went wrong. Try again.");
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        }
    }
}

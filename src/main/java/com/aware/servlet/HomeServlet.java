package com.aware.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

// Just redirects to login if not logged in, or to app if logged in
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            // Already logged in
            String role = (String) session.getAttribute("role");
            if ("admin".equals(role)) {
                resp.sendRedirect(req.getContextPath() + "/admin");
            } else {
                resp.sendRedirect(req.getContextPath() + "/my-reports");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}

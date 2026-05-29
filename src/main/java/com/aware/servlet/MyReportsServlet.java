package com.aware.servlet;

import com.aware.dao.ReportDAO;
import com.aware.model.Report;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class MyReportsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Must be logged in as a normal user
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String username = (String) session.getAttribute("user");

        try {
            ReportDAO reportDAO = new ReportDAO();
            List<Report> reports = reportDAO.getReportsByUser(username);
            req.setAttribute("reports", reports);
            req.getRequestDispatcher("/WEB-INF/jsp/myreports.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Could not load your reports.");
            req.getRequestDispatcher("/WEB-INF/jsp/myreports.jsp").forward(req, resp);
        }
    }
}

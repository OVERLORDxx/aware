package com.aware.servlet;

import com.aware.dao.ReportDAO;
import com.aware.model.Report;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class AdminServlet extends HttpServlet {

    // Show admin panel with all reports
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Must be logged in as admin
        HttpSession session = req.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("role"))) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        try {
            ReportDAO reportDAO = new ReportDAO();
            List<Report> reports = reportDAO.getAllReports();
            req.setAttribute("reports", reports);
            req.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Could not load reports.");
            req.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(req, resp);
        }
    }

    // Handle status update and delete actions
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("role"))) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String action   = req.getParameter("action");
        String idParam  = req.getParameter("id");

        try {
            ReportDAO reportDAO = new ReportDAO();
            int id = Integer.parseInt(idParam);

            if ("updateStatus".equals(action)) {
                String newStatus = req.getParameter("status");
                reportDAO.updateStatus(id, newStatus);
            } else if ("delete".equals(action)) {
                reportDAO.deleteReport(id);
            }

            resp.sendRedirect(req.getContextPath() + "/admin");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/admin?error=1");
        }
    }
}

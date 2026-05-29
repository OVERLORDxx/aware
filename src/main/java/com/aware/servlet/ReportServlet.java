package com.aware.servlet;

import com.aware.dao.ReportDAO;
import com.aware.model.Report;
import com.aware.util.HashUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;

// @MultipartConfig tells Tomcat this servlet handles file uploads
@MultipartConfig(maxFileSize = 5 * 1024 * 1024) // 5 MB max
public class ReportServlet extends HttpServlet {

    // Show the submit report form
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Must be logged in
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.getRequestDispatcher("/WEB-INF/jsp/report.jsp").forward(req, resp);
    }

    // Handle report form submission
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.setCharacterEncoding("UTF-8");

        String username  = (String) session.getAttribute("user");
        String email     = (String) session.getAttribute("email");

        String issueType   = req.getParameter("issueType");
        String description = req.getParameter("description");
        String location    = req.getParameter("location");

        // Validate required fields
        if (issueType == null || issueType.trim().isEmpty() ||
            description == null || description.trim().isEmpty()) {
            req.setAttribute("error", "Issue type and description are required.");
            req.getRequestDispatcher("/WEB-INF/jsp/report.jsp").forward(req, resp);
            return;
        }

        // Get image file (optional)
        byte[] imageBytes = null;
        Part imagePart = req.getPart("image");
        if (imagePart != null && imagePart.getSize() > 0) {
            try (InputStream is = imagePart.getInputStream()) {
                imageBytes = is.readAllBytes();
            }
        }

        try {
            ReportDAO reportDAO = new ReportDAO();
            String previousHash = reportDAO.getLastHash();

            Report report = new Report();
            report.setIssueType(issueType.trim());
            report.setDescription(description.trim());
            report.setLocation((location != null && !location.trim().isEmpty()) ? location.trim() : null);
            report.setSubmittedBy(username);
            report.setSubmitterEmail(email);
            report.setPreviousHash(previousHash);
            report.setHash(HashUtil.generateHash(issueType + description + previousHash));
            report.setStatus("PENDING");

            reportDAO.insertReport(report, imageBytes);

            resp.sendRedirect(req.getContextPath() + "/my-reports?success=1");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Failed to submit report. Please try again.");
            req.getRequestDispatcher("/WEB-INF/jsp/report.jsp").forward(req, resp);
        }
    }
}

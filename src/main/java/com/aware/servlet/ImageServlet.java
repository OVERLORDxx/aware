package com.aware.servlet;

import com.aware.dao.ReportDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

// Serves image bytes from the database so JSP can show them with <img src="/image?id=5">
public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");
        if (idParam == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            ReportDAO reportDAO = new ReportDAO();
            byte[] imageBytes = reportDAO.getImage(id);

            if (imageBytes == null || imageBytes.length == 0) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            resp.setContentType("image/jpeg"); // works for most images
            resp.getOutputStream().write(imageBytes);

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

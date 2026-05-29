package com.aware.dao;

import com.aware.model.Report;
import com.aware.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Handles all database operations for Report
public class ReportDAO {

    // Get the hash of the last report (needed for chaining)
    public String getLastHash() throws SQLException {
        String sql = "SELECT hash FROM reports ORDER BY id DESC LIMIT 1";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString("hash");
            return "0"; // first report has previousHash = "0"
        }
    }

    // Insert a new report
    public void insertReport(Report report, byte[] imageBytes) throws SQLException {
        String sql = "INSERT INTO reports (issue_type, description, location, status, hash, previous_hash, submitted_by, submitter_email, image) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, report.getIssueType());
            ps.setString(2, report.getDescription());
            ps.setString(3, report.getLocation());
            ps.setString(4, "PENDING");
            ps.setString(5, report.getHash());
            ps.setString(6, report.getPreviousHash());
            ps.setString(7, report.getSubmittedBy());
            ps.setString(8, report.getSubmitterEmail());
            if (imageBytes != null && imageBytes.length > 0) {
                ps.setBytes(9, imageBytes);
            } else {
                ps.setNull(9, Types.BLOB);
            }
            ps.executeUpdate();
        }
    }

    // Get all reports (for admin)
    public List<Report> getAllReports() throws SQLException {
        String sql = "SELECT id, issue_type, description, location, status, hash, previous_hash, submitted_by, submitter_email, (image IS NOT NULL) AS has_image FROM reports ORDER BY id DESC";
        List<Report> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    // Get reports by username (for user's "my reports" page)
    public List<Report> getReportsByUser(String username) throws SQLException {
        String sql = "SELECT id, issue_type, description, location, status, hash, previous_hash, submitted_by, submitter_email, (image IS NOT NULL) AS has_image FROM reports WHERE submitted_by = ? ORDER BY id DESC";
        List<Report> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    // Update status of a report
    public void updateStatus(int id, String status) throws SQLException {
        String sql = "UPDATE reports SET status = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    // Delete a single report
    public void deleteReport(int id) throws SQLException {
        String sql = "DELETE FROM reports WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // Get image bytes for a report (used by ImageServlet)
    public byte[] getImage(int id) throws SQLException {
        String sql = "SELECT image FROM reports WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getBytes("image");
            return null;
        }
    }

    // Map a ResultSet row to a Report object
    private Report mapRow(ResultSet rs) throws SQLException {
        Report r = new Report();
        r.setId(rs.getInt("id"));
        r.setIssueType(rs.getString("issue_type"));
        r.setDescription(rs.getString("description"));
        r.setLocation(rs.getString("location"));
        r.setStatus(rs.getString("status"));
        r.setHash(rs.getString("hash"));
        r.setPreviousHash(rs.getString("previous_hash"));
        r.setSubmittedBy(rs.getString("submitted_by"));
        r.setSubmitterEmail(rs.getString("submitter_email"));
        r.setHasImage(rs.getBoolean("has_image"));
        return r;
    }
}

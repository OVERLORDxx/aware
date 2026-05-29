<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AWARE - Submit Report</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%@ include file="navbar.jsp" %>

<div class="container">

    <div class="page-header">
        <h1>Submit a Civic Issue</h1>
        <p>Fill in the details below and submit your report. It will be reviewed by an admin.</p>
    </div>

    <!-- Error -->
    <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-error">${error}</div>
    <% } %>

    <!-- enctype is needed because we upload a file -->
    <form action="${pageContext.request.contextPath}/report" method="post" enctype="multipart/form-data" class="report-form">

        <div class="form-group">
            <label for="issueType">Issue Type *</label>
            <select id="issueType" name="issueType" required>
                <option value="">-- Select Issue Type --</option>
                <option value="Road Damage / Pothole">Road Damage / Pothole</option>
                <option value="Garbage / Waste Issue">Garbage / Waste Issue</option>
                <option value="Water Leakage / Flooding">Water Leakage / Flooding</option>
                <option value="Street Light Issue">Street Light Issue</option>
                <option value="Drainage Blockage">Drainage Blockage</option>
                <option value="Vegetation / Tree Issue">Vegetation / Tree Issue</option>
                <option value="Other">Other</option>
            </select>
        </div>

        <div class="form-group">
            <label for="description">Description *</label>
            <textarea id="description" name="description" rows="4"
                      placeholder="Describe the issue in detail..." required></textarea>
        </div>

        <div class="form-group">
            <label for="location">Location</label>
            <input type="text" id="location" name="location"
                   placeholder="e.g. Near Gandhi Chowk, Jaipur">
        </div>

        <div class="form-group">
            <label for="image">Upload Image (optional)</label>
            <input type="file" id="image" name="image" accept="image/*">
            <small>Attach a photo of the issue to help admins understand better.</small>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn-primary">Submit Report</button>
            <a href="${pageContext.request.contextPath}/my-reports" class="btn-secondary">Cancel</a>
        </div>

    </form>

</div>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AWARE - My Reports</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%@ include file="navbar.jsp" %>

<div class="container">

    <div class="page-header">
        <h1>My Reports</h1>
        <p>Here are all the civic issues you have reported.</p>
        <a href="${pageContext.request.contextPath}/report" class="btn-primary">+ Submit New Report</a>
    </div>

    <!-- Success message after submitting -->
    <% if ("1".equals(request.getParameter("success"))) { %>
        <div class="alert alert-success">Report submitted successfully! We will look into it.</div>
    <% } %>

    <!-- Error -->
    <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-error">${error}</div>
    <% } %>

    <!-- Reports list -->
    <c:choose>
        <c:when test="${empty reports}">
            <div class="empty-state">
                <p>&#128203; You haven't submitted any reports yet.</p>
                <a href="${pageContext.request.contextPath}/report" class="btn-primary">Submit your first report</a>
            </div>
        </c:when>
        <c:otherwise>
            <div class="reports-grid">
                <c:forEach var="r" items="${reports}">
                    <div class="report-card">

                        <!-- Status badge -->
                        <div class="card-header">
                            <span class="issue-type">${r.issueType}</span>
                            <span class="status-badge status-${r.status}">${r.status}</span>
                        </div>

                        <p class="report-desc">${r.description}</p>

                        <c:if test="${not empty r.location}">
                            <p class="report-meta">&#128205; ${r.location}</p>
                        </c:if>

                        <!-- Show image if exists -->
                        <c:if test="${r.hasImage}">
                            <div class="report-image-wrap">
                                <img src="${pageContext.request.contextPath}/image?id=${r.id}"
                                     alt="Report image" class="report-image">
                            </div>
                        </c:if>

                        <!-- Hash info -->
                        <div class="hash-info">
                            <small>&#128279; Hash: <code>${r.hash.substring(0, 16)}...</code></small>
                        </div>

                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>

</div>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AWARE - Admin Panel</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%@ include file="navbar.jsp" %>

<div class="container">

    <div class="page-header">
        <h1>Admin Panel</h1>
        <p>Manage all submitted civic issue reports.</p>
        <span class="total-badge">Total Reports: ${reports.size()}</span>
    </div>

    <!-- Error -->
    <% if (request.getParameter("error") != null) { %>
        <div class="alert alert-error">Something went wrong. Please try again.</div>
    <% } %>

    <!-- Reports table -->
    <c:choose>
        <c:when test="${empty reports}">
            <div class="empty-state">
                <p>&#128203; No reports submitted yet.</p>
            </div>
        </c:when>
        <c:otherwise>
            <div class="table-wrapper">
                <table class="admin-table">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Issue Type</th>
                            <th>Description</th>
                            <th>Location</th>
                            <th>Submitted By</th>
                            <th>Image</th>
                            <th>Hash</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="r" items="${reports}">
                            <tr>
                                <td>${r.id}</td>
                                <td><span class="issue-type issue-type-sm">${r.issueType}</span></td>
                                <td class="desc-cell">${r.description}</td>
                                <td>${not empty r.location ? r.location : '-'}</td>
                                <td>
                                    ${r.submittedBy}<br>
                                    <small>${r.submitterEmail}</small>
                                </td>
                                <td>
                                    <c:if test="${r.hasImage}">
                                        <a href="${pageContext.request.contextPath}/image?id=${r.id}"
                                           target="_blank" class="img-link">View</a>
                                    </c:if>
                                    <c:if test="${!r.hasImage}">-</c:if>
                                </td>
                                <td><code class="hash-code">${r.hash.substring(0, 12)}...</code></td>
                                <td><span class="status-badge status-${r.status}">${r.status}</span></td>
                                <td class="actions-cell">

                                    <!-- Update Status -->
                                    <form action="${pageContext.request.contextPath}/admin" method="post" style="display:inline;">
                                        <input type="hidden" name="action" value="updateStatus">
                                        <input type="hidden" name="id" value="${r.id}">
                                        <select name="status" onchange="this.form.submit()" class="status-select">
                                            <option value="PENDING"     ${r.status == 'PENDING'     ? 'selected' : ''}>Pending</option>
                                            <option value="IN_PROGRESS" ${r.status == 'IN_PROGRESS' ? 'selected' : ''}>In Progress</option>
                                            <option value="COMPLETED"   ${r.status == 'COMPLETED'   ? 'selected' : ''}>Completed</option>
                                            <option value="REJECTED"    ${r.status == 'REJECTED'    ? 'selected' : ''}>Rejected</option>
                                        </select>
                                    </form>

                                    <!-- Delete -->
                                    <form action="${pageContext.request.contextPath}/admin" method="post"
                                          style="display:inline;"
                                          onsubmit="return confirm('Are you sure you want to delete this report?');">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="id" value="${r.id}">
                                        <button type="submit" class="btn-delete">Delete</button>
                                    </form>

                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:otherwise>
    </c:choose>

</div>

</body>
</html>

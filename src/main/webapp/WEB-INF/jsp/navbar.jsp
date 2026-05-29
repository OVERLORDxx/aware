<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Shared navigation bar included in all pages after login -->
<nav class="navbar">
    <div class="nav-left">
        <span class="logo-icon">&#128279;</span>
        <span class="logo-text">AWARE</span>
        <span class="logo-sub">Civic Issue Reporter</span>
    </div>
    <div class="nav-right">
        <span class="nav-user">Hello, <strong>${sessionScope.user}</strong>
            &nbsp;<span class="role-badge ${sessionScope.role}">${sessionScope.role}</span>
        </span>

        <% if ("admin".equals(session.getAttribute("role"))) { %>
            <a href="${pageContext.request.contextPath}/admin" class="nav-link">Admin Panel</a>
        <% } else { %>
            <a href="${pageContext.request.contextPath}/my-reports" class="nav-link">My Reports</a>
            <a href="${pageContext.request.contextPath}/report" class="nav-link">Submit Report</a>
        <% } %>

        <a href="${pageContext.request.contextPath}/logout" class="nav-link nav-logout">Logout</a>
    </div>
</nav>

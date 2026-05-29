<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AWARE - Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="auth-wrapper">

    <div class="auth-box">
        <div class="auth-logo">
            <span class="logo-icon">&#128279;</span>
            <span class="logo-text">AWARE</span>
        </div>
        <p class="auth-subtitle">Advanced Web-based Application for Reporting Events</p>

        <h2 class="auth-heading">Login to your account</h2>

        <!-- Success message after signup -->
        <% if ("1".equals(request.getParameter("success"))) { %>
            <div class="alert alert-success">Account created! You can now login.</div>
        <% } %>

        <!-- Error message -->
        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-error">${error}</div>
        <% } %>

        <form action="${pageContext.request.contextPath}/login" method="post">

            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" placeholder="Enter your username" required>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required>
            </div>

            <button type="submit" class="btn-primary btn-full">Login</button>
        </form>

        <p class="auth-link">Don't have an account? <a href="${pageContext.request.contextPath}/signup">Sign Up</a></p>
    </div>

</div>

</body>
</html>

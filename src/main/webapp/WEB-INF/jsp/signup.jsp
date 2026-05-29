<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AWARE - Sign Up</title>
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

        <h2 class="auth-heading">Create an account</h2>

        <!-- Error message -->
        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-error">${error}</div>
        <% } %>

        <form action="${pageContext.request.contextPath}/signup" method="post">

            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" placeholder="Choose a username" required>
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Enter your email" required>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="At least 6 characters" required>
            </div>

            <div class="form-group">
                <label for="confirmPassword">Confirm Password</label>
                <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Repeat your password" required>
            </div>

            <button type="submit" class="btn-primary btn-full">Sign Up</button>
        </form>

        <p class="auth-link">Already have an account? <a href="${pageContext.request.contextPath}/login">Login</a></p>
    </div>

</div>

</body>
</html>

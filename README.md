# AWARE — Civic Issue Reporter

**Advanced Web-based Application for Reporting Events**

A college project built with **JSP + Servlet + JDBC + MySQL** deployed on **Apache Tomcat 10**.
Citizens can report local civic issues like potholes, garbage, water leakage etc.
Admins can manage and update the status of all reports.

---

## Features

- User Signup & Login
- Admin Login (separate role)
- Submit civic issue reports with image upload
- View your own submitted reports and their status
- Admin panel — update status, delete reports
- SHA-256 hash chaining on every report
- Session-based authentication

---

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Frontend | JSP, JSTL, HTML, CSS |
| Backend | Java Servlets (Jakarta EE) |
| Database | MySQL (raw JDBC) |
| Server | Apache Tomcat 10 |
| Build | Maven |

---

## Project Structure

```
aware/
├── src/
│   └── main/
│       ├── java/com/aware/
│       │   ├── dao/
│       │   │   ├── ReportDAO.java       # DB operations for reports
│       │   │   └── UserDAO.java         # DB operations for users
│       │   ├── model/
│       │   │   ├── Report.java          # Report model class
│       │   │   └── User.java            # User model class
│       │   ├── servlet/
│       │   │   ├── AdminServlet.java    # Admin panel
│       │   │   ├── HomeServlet.java     # Redirect based on role
│       │   │   ├── ImageServlet.java    # Serves images from DB
│       │   │   ├── LoginServlet.java    # Login
│       │   │   ├── LogoutServlet.java   # Logout
│       │   │   ├── MyReportsServlet.java# User's reports page
│       │   │   ├── ReportServlet.java   # Submit report
│       │   │   └── SignupServlet.java   # Signup
│       │   └── util/
│       │       ├── DBConnection.java    # JDBC connection
│       │       └── HashUtil.java        # SHA-256 hashing
│       └── webapp/
│           ├── WEB-INF/
│           │   ├── jsp/
│           │   │   ├── admin.jsp
│           │   │   ├── login.jsp
│           │   │   ├── myreports.jsp
│           │   │   ├── navbar.jsp
│           │   │   ├── report.jsp
│           │   │   └── signup.jsp
│           │   └── web.xml
│           ├── css/
│           │   └── style.css
│           └── index.jsp
├── database.sql                         # Run this first in MySQL
├── Dockerfile                           # For Render deployment
└── pom.xml
```

---

## How to Run Locally

### Step 1 — Requirements
- Java 17
- Apache Tomcat 10
- MySQL
- Maven

### Step 2 — Setup Database

Open MySQL and run:
```bash
mysql -u root -p < database.sql
```

This will create the `aware_db` database, both tables, and the default admin account.

### Step 3 — Update DB Password

Open `src/main/java/com/aware/util/DBConnection.java` and change:
```java
private static final String PASSWORD = "your_password_here";
```
to your actual MySQL password.

### Step 4 — Build the Project

```bash
mvn clean package
```

This creates `target/aware.war`

### Step 5 — Deploy to Tomcat

Copy `target/aware.war` to Tomcat's `webapps/` folder, then start Tomcat:
```bash
# On Windows
startup.bat

# On Linux/Mac
./startup.sh
```

### Step 6 — Open in Browser

```
http://localhost:8080/aware
```

---

## Default Admin Account

| Field | Value |
|-------|-------|
| Username | admin |
| Password | admin123 |

Normal users can register from the Signup page.

---

## Pages & URLs

| URL | Description | Access |
|-----|-------------|--------|
| `/aware/login` | Login page | Public |
| `/aware/signup` | Signup page | Public |
| `/aware/report` | Submit a report | Logged in users |
| `/aware/my-reports` | View your reports | Logged in users |
| `/aware/admin` | Admin panel | Admin only |
| `/aware/logout` | Logout | Logged in |

---

## Deploying on Render

See the **Deploy on Render** section below. Make sure to set environment variables for DB credentials on Render instead of hardcoding them.

### Environment Variables on Render

| Variable | Example Value |
|----------|--------------|
| `DB_URL` | `jdbc:mysql://your-host:3306/aware_db` |
| `DB_USER` | `root` |
| `DB_PASSWORD` | `yourpassword` |

Update `DBConnection.java` to read from environment variables:
```java
private static final String URL      = System.getenv("DB_URL");
private static final String USER     = System.getenv("DB_USER");
private static final String PASSWORD = System.getenv("DB_PASSWORD");
```

---

## Deploy on Render (Step by Step)

1. Push this project to a GitHub repository
2. Go to [render.com](https://render.com) and create a new **Web Service**
3. Connect your GitHub repo
4. Set **Environment** to `Docker`
5. Add the environment variables listed above
6. Click **Deploy**

Render will use the `Dockerfile` to build and run the app automatically.

---

## Course Info

- **Subject:** Advance Programming in Java (BCO 037 B)
- **Department Elective:** 3
- **Credits:** 3-0-0 [3]

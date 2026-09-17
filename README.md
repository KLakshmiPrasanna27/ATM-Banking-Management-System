# 🏦 ATM Banking Management System

A web-based ATM Banking Management System developed using **Java, Spring Boot, JDBC, MySQL, HTML, CSS, JavaScript, and Bootstrap**.

This project simulates common ATM banking operations through a simple and user-friendly web interface while demonstrating a layered backend architecture and direct JDBC database connectivity.

---

## 📌 Project Overview

The ATM Banking Management System allows customers to securely access their accounts and perform common banking operations such as:

* 🔐 Login using card number and PIN
* 💰 Check account balance
* 💵 Deposit cash
* 🏧 Withdraw cash
* 📄 View mini statement
* 🔑 Change PIN
* 📝 Register a new account
* 🔄 Forgot PIN and PIN reset
* 🚪 Logout
* 📋 Account activity tracking

The backend follows a layered architecture:

**Frontend → Controller → Service → DAO → JDBC → MySQL**

---

## ✨ Features

### 🔐 Authentication

* Card number and PIN based login
* Invalid PIN validation
* Account validation
* Login activity tracking

### 💰 Banking Operations

* Balance enquiry
* Cash deposit
* Cash withdrawal
* Insufficient balance validation
* Transaction amount validation

### 📄 Mini Statement

* Displays recent account transactions
* Shows transaction type
* Shows transaction amount
* Shows transaction date and time

### 🔑 PIN Management

* Change existing PIN
* Validate old PIN
* Forgot PIN functionality
* Security-question verification
* Reset PIN with confirmation

### 📝 Account Registration

* New customer registration
* Unique card number validation
* PIN confirmation
* Initial deposit
* Security question and answer

### 📋 Activity Tracking

The system records important account activities such as:

* Login success/failure
* Deposits
* Withdrawals
* PIN changes
* Registration activities

---

## 🛠️ Technologies Used

### Frontend

* HTML5
* CSS3
* JavaScript
* Bootstrap

### Backend

* Java 21
* Spring Boot
* Spring MVC
* REST APIs

### Database

* MySQL

### Database Connectivity

* JDBC
* DriverManager
* Connection
* PreparedStatement
* ResultSet
* SQL Transactions

### Development Tools

* Spring Tool Suite / Eclipse
* MySQL Workbench
* Postman
* Git
* GitHub

---

## 🏗️ System Architecture

```text
                ┌─────────────────────┐
                │      Frontend       │
                │ HTML/CSS/JS/Bootstrap│
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │   REST Controller   │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │       Service       │
                │   Business Logic    │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │         DAO         │
                │    JDBC Operations  │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │        MySQL        │
                │     atm_db          │
                └─────────────────────┘
```

---

## 📂 Project Structure

```text
ATM-Banking-Management-System
│
├── src
│   └── main
│       ├── java
│       │   └── com.atm_project
│       │       ├── atm
│       │       ├── controller
│       │       ├── service
│       │       ├── dao
│       │       ├── model
│       │       ├── util
│       │       └── exception
│       │
│       └── resources
│           ├── static
│           │   ├── index.html
│           │   ├── dashboard.html
│           │   ├── balance.html
│           │   ├── deposit.html
│           │   ├── withdraw.html
│           │   ├── statement.html
│           │   ├── change-pin.html
│           │   ├── register.html
│           │   ├── forgot-pin.html
│           │   ├── login.html
│           │   ├── style.css
│           │   └── script.js
│           │
│           └── application.properties
│
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
└── README.md
```

---

## 🔗 REST API Endpoints

| Method | Endpoint                            | Purpose                 |
| ------ | ----------------------------------- | ----------------------- |
| POST   | `/login`                            | Customer login          |
| GET    | `/balance/{accountId}`              | Check balance           |
| POST   | `/deposit`                          | Deposit money           |
| POST   | `/withdraw`                         | Withdraw money          |
| GET    | `/transactions/{accountId}`         | View mini statement     |
| POST   | `/change-pin`                       | Change PIN              |
| POST   | `/register`                         | Register account        |
| POST   | `/logout`                           | Logout                  |
| GET    | `/activities/{accountId}`           | View account activities |
| GET    | `/forgot-pin/question/{cardNumber}` | Get security question   |
| POST   | `/forgot-pin/verify-answer`         | Verify security answer  |
| POST   | `/forgot-pin/reset`                 | Reset PIN               |

---

## 🗄️ Database

The application uses a MySQL database named:

```text
atm_db
```

Main tables include:

```text
users
transactions
account_activity
```

### Users

Stores customer account information such as:

* Account ID
* Customer name
* Card number
* PIN
* Balance
* Security question
* Security answer

### Transactions

Stores banking transactions such as:

* Deposit
* Withdrawal
* Transaction amount
* Transaction date

### Account Activity

Stores important account activities and their status.

---

## 🔐 Security & Validation

The project implements several validation mechanisms:

* Card number validation
* PIN validation
* Confirm PIN validation
* Unique card number validation
* Insufficient balance validation
* Minimum and maximum transaction amount validation
* Old PIN validation
* Security-question verification
* PreparedStatement for database queries
* JDBC transaction handling for banking operations

> ⚠️ This project is intended as an educational/demo application and should not be used as a real banking system without additional production-grade security measures.

---

## ▶️ How to Run Locally

### 1. Clone the repository

```bash
git clone https://github.com/KLakshmiPrasanna27/ATM-Banking-Management-System.git
```

### 2. Open the project

Open the project in:

* Spring Tool Suite
* Eclipse
* IntelliJ IDEA

### 3. Create the MySQL database

Create:

```sql
CREATE DATABASE atm_db;
```

Create the required tables using the SQL script included in the project.

### 4. Configure database credentials

Set the following environment variables:

```text
DB_URL
DB_USER
DB_PASSWORD
```

For example, the local database URL is:

```text
jdbc:mysql://localhost:3306/atm_db
```

### 5. Run the Spring Boot application

Run:

```text
AtmApplication.java
```

The application runs on:

```text
http://localhost:8075
```

### 6. Open the application

Open the browser and visit:

```text
http://localhost:8075
```

---

## 🧪 API Testing

The REST APIs can be tested using **Postman**.

Example login request:

```text
POST /login
```

Request body:

```json
{
    "cardNumber": "1234567890123456",
    "pin": "1234"
}
```

---

## 📸 Screenshots

Screenshots of the application can be added here.

Example:

```text
screenshots/
├── login.png
├── dashboard.png
├── balance.png
├── deposit.png
├── withdraw.png
└── statement.png
```

---

## 🚀 Future Enhancements

Possible future improvements include:

* Email/SMS transaction notifications
* OTP-based authentication
* Role-based access
* Admin dashboard
* Account profile management
* Improved authentication and session management
* Deployment to a cloud platform
* Enhanced transaction reporting

---

## 🎯 Learning Outcomes

Through this project, I gained practical experience with:

* Core Java
* Object-Oriented Programming
* Spring Boot
* Spring MVC
* REST API development
* JDBC
* MySQL
* SQL queries
* Database transactions
* Exception handling
* Frontend-backend integration
* Git and GitHub
* API testing with Postman

---

## 👩‍💻 Author

**Kasarla Lakshmi Prasanna**

B.Tech – Computer Science and Engineering

Aspiring Java Developer | Java Full Stack Developer

---

## ⭐ Project

If you find this project useful for learning Java, Spring Boot, JDBC, and MySQL, feel free to explore the repository.

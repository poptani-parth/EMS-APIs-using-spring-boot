# 🧑‍💼 EMS APIs (Spring Boot)

Employee Management System **REST API** built with **Spring Boot 3** and **MongoDB**, featuring **JWT-based authentication**, **role-based access control**, and **Swagger/OpenAPI** documentation.

---

## ✨ Highlights

- ✅ **Authentication & Authorization** with JWT (Bearer token)
- ✅ **Role-based access** using method-level security (`@PreAuthorize`)
- ✅ **Employee & Department management**
- ✅ **Attendance tracking**
- ✅ **Leave requests & approvals**
- ✅ **Salary records & net salary calculation**
- ✅ **Advance payment requests**
- ✅ **Reports** (employees, attendance range, salary by month, leave by status, headcount by department)
- ✅ **Swagger UI** for interactive API testing

---

## 🧰 Tech Stack

- **Backend**: Spring Boot `3.3.7`, Spring Web, Spring Validation
- **Security**: Spring Security + JWT (`io.jsonwebtoken:jjwt 0.12.6`)
- **Database**: MongoDB (Spring Data MongoDB)
- **API Docs**: springdoc-openapi (`2.6.0`)
- **Language**: Java `17`
- **Build Tool**: Maven
- **Optional UI**: Thymeleaf dependency is included (no templates currently in repo)

---

## 🧩 Modules & API Routes

Base URL (default): `http://localhost:8080`

### 🔐 Auth
- `POST /api/auth/signup` — register a user and receive JWT
- `POST /api/auth/login` — login and receive JWT

### 👤 Employees
- `POST /api/employees` — create employee *(ADMIN, MANAGER)*
- `PUT /api/employees/{employeeId}` — update employee *(ADMIN, MANAGER)*
- `DELETE /api/employees/{employeeId}` — delete employee *(ADMIN)*
- `GET /api/employees/{employeeId}` — get employee *(ADMIN, MANAGER, EMPLOYEE)*
- `GET /api/employees?search=` — list/search employees *(ADMIN, MANAGER)*

### 🏢 Departments
- `POST /api/departments` — create *(ADMIN)*
- `PUT /api/departments/{departmentId}` — update *(ADMIN)*
- `DELETE /api/departments/{departmentId}` — delete *(ADMIN)*
- `GET /api/departments/{departmentId}` — get *(ADMIN, MANAGER)*
- `GET /api/departments` — list *(ADMIN, MANAGER)*

### 🕒 Attendance
- `POST /api/attendance` — record attendance *(ADMIN, MANAGER, EMPLOYEE)*
- `GET /api/attendance/employee/{employeeId}?start=YYYY-MM-DD&end=YYYY-MM-DD` *(ADMIN, MANAGER)*
- `GET /api/attendance/date?date=YYYY-MM-DD` *(ADMIN, MANAGER)*

### 🏖️ Leaves
- `POST /api/leaves` — apply leave *(EMPLOYEE)*
- `PUT /api/leaves/{leaveId}/status?status=...` — approve/reject *(ADMIN, MANAGER)*
- `GET /api/leaves/employee/{employeeId}` — list by employee *(ADMIN, MANAGER)*
- `GET /api/leaves/status?status=...` — list by status *(ADMIN, MANAGER)*

### 💰 Salaries
- `POST /api/salaries` — create salary record *(ADMIN, MANAGER)*
- `GET /api/salaries/{salaryId}/calculate` — calculate net salary *(ADMIN, MANAGER)*
- `GET /api/salaries/employee/{employeeId}` — list by employee *(ADMIN, MANAGER)*
- `GET /api/salaries/month?month=YYYY-MM` — list by month *(ADMIN, MANAGER)*

### 🧾 Advance Payments
- `POST /api/advance-payments` — request advance *(EMPLOYEE)*
- `PUT /api/advance-payments/{advanceId}/status?status=...` — update status *(ADMIN, MANAGER)*
- `GET /api/advance-payments/employee/{employeeId}` *(ADMIN, MANAGER)*
- `GET /api/advance-payments/status?status=...` *(ADMIN, MANAGER)*

### 📊 Reports
- `GET /api/reports/employees` *(ADMIN, MANAGER)*
- `GET /api/reports/attendance?start=YYYY-MM-DD&end=YYYY-MM-DD` *(ADMIN, MANAGER)*
- `GET /api/reports/salaries?month=YYYY-MM` *(ADMIN, MANAGER)*
- `GET /api/reports/leaves?status=...` *(ADMIN, MANAGER)*
- `GET /api/reports/headcount` *(ADMIN, MANAGER)*

---

## 🧪 Swagger / OpenAPI

- **Swagger UI**: `http://localhost:8080/swagger-ui/index.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

Swagger is configured with a **JWT Bearer** security scheme (`bearerAuth`), so you can paste a token in Swagger UI’s **Authorize** dialog.

---

## 🔐 Roles & Access Control

The project uses **method-level authorization** via `@PreAuthorize(...)` (roles like `ADMIN`, `MANAGER`, `EMPLOYEE`).

### Default admin user (auto-created on startup)

On application startup, a default admin is seeded if it doesn’t exist:

- **Username**: `admin`
- **Password**: `Admin123!`

> ⚠️ For portfolio/public repos, change these credentials and avoid using them outside local dev.

---

## ✅ Prerequisites

- **Java 17**
- **Maven**
- **MongoDB** (local or Atlas)

---

## ⚙️ Configuration

This repo currently contains database/JWT values under `src/main/resources/application.yml`.

> 🔒 **Security note**: Do not commit real credentials or JWT secrets to GitHub. Prefer environment variables or a local-only config file.

Recommended environment variables:

- `SPRING_DATA_MONGODB_URI`
- `SPRING_DATA_MONGODB_DATABASE`
- `APP_JWT_SECRET`
- `APP_JWT_EXPIRATION_MS` *(optional)*

Example (PowerShell):

```powershell
$env:SPRING_DATA_MONGODB_URI="mongodb://localhost:27017"
$env:SPRING_DATA_MONGODB_DATABASE="EMSDB"
$env:APP_JWT_SECRET="change-this-secret"
$env:APP_JWT_EXPIRATION_MS="86400000"
```

---

## ▶️ Run Locally

### 1) Clone

```bash
git clone <your-repo-url>
cd EMS-APIs-using-spring-boot
```

### 2) Build

```bash
mvn clean install
```

### 3) Run

```bash
mvn spring-boot:run
```

App starts on:

- `http://localhost:8080`

---

## 🧑‍💻 Sample API Usage (cURL)

### Login (get JWT)

```powershell
curl -X POST "http://localhost:8080/api/auth/login" `
  -H "Content-Type: application/json" `
  -d '{"username":"admin","password":"Admin123!"}'
```

### Call a secured endpoint

```powershell
curl "http://localhost:8080/api/departments" `
  -H "Authorization: Bearer <JWT_TOKEN>"
```

---

## 🗂️ Project Structure

```text
src/main/java/com/EMS/Practice_Project/
  Application.java
  config/        (OpenAPI config, data initializer)
  controller/    (REST controllers)
  dto/           (request/response DTOs)
  entity/        (MongoDB entities)
  exception/     (global exception handling)
  mapper/        (DTO <-> entity mappers)
  repository/    (Spring Data repositories)
  security/      (JWT filter, security config, user details)
  service/       (service interfaces + implementations)
src/main/resources/
  application.yml
```

---

## 🚀 Future Enhancements (ideas)

- 🔒 Enforce authentication at the HTTP layer (update `SecurityConfig` to require auth by default)
- 🧪 Add integration tests for controllers/services
- 📦 Add Docker support for app + MongoDB
- 📝 Add Postman collection

---

## 👤 Author

- **Name**: *(your name)*
- **GitHub**: *(add link)*
- **LinkedIn**: *(optional)*

# NovelBlog API

*[Lire en français (README.fr.md)](./README.fr.md)*

A secure RESTful API built with Spring Boot for managing a blog platform (articles, categories, comments, and role-based user management).

## 🛠️ Tech Stack

* **Language:** Java 17+
* **Framework:** Spring Boot 3
* **Security:** Spring Security (Stateless JWT Authentication)
* **Database:** Spring Data JPA / Hibernate, H2 (for `dev` profile)
* **Tools:** Maven, Git

## ✨ Features (V1)

* **Authentication & Authorization:** User registration, login with JWT generation, and Role-Based Access Control (`USER`, `ADMIN`).
* **Post Management:** Public read access, with create, update, and delete operations restricted to administrators.
* **Categories & Comments:** Topic-based organization and user-linked commenting system.
* **Automated Seeding:** Fail-fast admin account initialization on startup and automatic sample data seeding in `dev` mode.

## Getting Started

### 1. Prerequisites
* JDK 17 or higher
* Maven 3.8+

### 2. Required Environment Variables
The application enforces a fail-fast startup policy and requires the following environment variables to be set before running:

```bash
JWT_SECRET=your_base64_encoded_secret_key
ADMIN_USERNAME=adminTest
ADMIN_PASSWORD=your_secure_password_min_12_chars
ADMIN_EMAIL=admin@example.com
```

### 3. Run the Application (Dev Profile)
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## 📌 Key Endpoints

| Method | Endpoint | Access | Description |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/auth/login` | Public | Authenticate user and retrieve JWT token |
| `POST` | `/api/users` | Public | Register a new user account |
| `GET` | `/api/posts` | Public | Retrieve all blog posts |
| `POST` | `/api/posts` | `ADMIN` | Create a new blog post |
| `POST` | `/api/comments` | Authenticated | Add a comment to a post |

## 🔒 Architecture Decisions & Known Limitations (V1)

* **Token Lifecycle:** For V1, authentication relies on a single short-lived JWT without a refresh token mechanism or server-side token blacklisting in order to maintain a lightweight stateless architecture. Implementing refresh token rotation and token invalidation upon password change is planned for V2.
* **Database & H2 Console:** The H2 database and its web console are enabled for the `dev` profile only and must be replaced by a persistent relational database in production.
* **JPA Properties:** `spring.jpa.show-sql=true` and `spring.jpa.hibernate.ddl-auto=update` are configured for development convenience and must be reviewed before deploying to production.
* **CORS Configuration:** Currently configured for local frontend development (`http://localhost:3000`); it must be updated to allow only the actual production frontend URL.

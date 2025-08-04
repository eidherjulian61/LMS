# Mini Library Management System (LMS)

This is a RESTful web service for managing a mini–Library Management System (LMS). The system allows managing books, authors, users, and borrowing activities with full data persistence in a relational database.

## Core Functional Requirements

### Book Management
- Add, update, delete, and retrieve books.
- Each book has attributes: title, author (linked entity), ISBN, availability Status.

### User Management
- Add, update, delete, and retrieve users.
- Each user has: name, email, a unique libraryId.

### Borrowing System
- Users can borrow up to 2 books at a time.
- Books can only be borrowed if available.
- Track due dates and borrowing history.
- Mark books as unavailable when borrowed.

### Search
- Search books by: title, author, ISBN.

## Additional Requirements

### Authentication:
- Use JWT-based authentication with Admin and User roles.
- Admins manage books and users; Users can borrow/search books.

### API Design:
- Design clean, RESTful endpoints using standard HTTP verbs and status codes.

### Database Design:
- Use relational schema with proper relationships (Author to Book, User to Book via Borrowing Record).

## Technical Requirements

### Backend:
- Spring Boot
- Spring Data JPA
- Spring Security + JWT
- H2 Database (or another lightweight RDBMS for development/testing)

### Testing:
- Junit and Mockito
- REST endpoint tests via MockMvc

### Documentation:
- Swagger / OpenAPI
- README with setup, run instructions, architecture overview.

## Setup and Run Instructions

1.  **Clone the repository:**
    ```bash
    git clone <repository-url>
    ```
2.  **Navigate to the project directory:**
    ```bash
    cd LMS
    ```
3.  **Build the project:**
    ```bash
    mvn clean install
    ```
4.  **Run the application:**
    ```bash
    java -jar target/LMS-0.0.1-SNAPSHOT.jar
    ```
5.  **Access the application:**
    - The application will be running at `http://localhost:8080`.
    - Swagger UI is available at `http://localhost:8080/swagger-ui.html`.
    - H2 console is available at `http://localhost:8080/h2-console`.

## Architecture Overview

The application follows a standard layered architecture:

-   **Controller Layer:** Handles incoming HTTP requests and responses.
-   **Service Layer:** Contains the business logic of the application.
-   **Repository Layer:** Responsible for data access and persistence.
-   **Entity Layer:** Defines the domain objects and their relationships.
-   **Security Layer:** Implements JWT-based authentication and authorization.

## Example Endpoints

-   `POST /api/books` - Add a book
-   `GET /api/books/{id}` - Get book details
-   `GET /api/books/search` - Search books
-   `POST /api/users` - Add a user
-   `GET /api/users/{id}` - Get user details
-   `POST /api/users/{userId}/borrow/{bookId}` - Borrow a book
-   `PUT /api/users/{userId}/return/{bookId}` - Return a book
-   `GET /api/users/{userId}/books` - View borrowed books
-   `GET /api/users/{userId}/history` - View borrowing history

# Application README

This is a general README file for your application. It provides basic instructions to help you get started with the API.

---

### **Getting Started**

To begin using the application's API, you can follow the steps below.

---

### **1. Creating a User**

To create a new user with an `ADMIN` role, send a `POST` request to the following endpoint with the provided JSON payload.

**Endpoint:** `http://localhost:8080/api/auth/register`

**Request Body:**

```json
{
  "id": 102,
  "name": "Eidher.Cadavid",
  "email": "eidherjulian61@gmail.com",
  "libraryId": "lib_user_2024",
  "password": "SecurePassword123!",
  "role": "ADMIN"
}
```

---

### **2. Authenticate a User**

To authenticate a user, send a `POST` request to the following endpoint with the user's credentials.

**Endpoint:** `http://localhost:8080/api/auth/authenticate`

**Request Body:**

```json
{
  "id": 101,
  "name": "Eidher Cadavid",
  "email": "eidher.cadavid@example.com",
  "libraryId": "lib_user_2024",
  "password": "SecurePassword123!",
  "role": "ADMIN"
}
```

---

### **3. Invoking the API**

After authenticating, you can use the returned token to access other protected endpoints. For instance, to create a new user via the API, you would make a `POST` request with the following details.

**Endpoint:** `http://localhost:8080/api/users`

**Request Method:** `POST`

**Request Body:**

```json
{
  "id": 102,
  "name": "Eidher.Cadavid",
  "email": "eidherjulian61@msn.com",
  "libraryId": "lib_user_2027",
  "password": "SecurePassword123!",
  "role": "ADMIN"
}
```

**Request Headers:**

You must include the `Authorization` header with the token you received in the previous authentication step.

```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlaWRoZXJqdWxpYW42MUBnbWFpbC5jb20iLCJpYXQiOjE3NTQzMjQ5MTQsImV4cCI6MTc1NDQxMTMxNH0.-vtx-4JCxuYmttqqmO13BMEmQEliEtml8pdxdzSfplE
Content-Type: application/json
```

---

### **What's Next?**

Once the user is created and authenticated, you can use the returned token to access other protected API endpoints.

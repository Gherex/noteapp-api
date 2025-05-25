# 🗃️ NoteApp - Backend

This is the backend for **NoteApp**, a note-taking REST API built with **Spring Boot** and **MySQL**. It provides endpoints to manage notes, filter by category, archive/unarchive them, and more.

---

## ✅ Requirements

- Java 17
- Maven 3.9.x (wrapper included: `./mvnw`)
- MySQL 8.x

---

## ⚙️ Setup

1. Make sure MySQL is running and create the database:

```sql
CREATE DATABASE note_app;
```

2. Configure DB credentials in application.properties or with environment variables:

```properties
spring.datasource.username=${DB_USER:root}
spring.datasource.password=${DB_PASS:root}
```

3. Start the backend:

```bash
./mvnw spring-boot:run
```

---

## 🔌 Base URL

```bash
http://localhost:8080/api/v1/notes
```

---

## 📖 API Endpoints

### 🟢 GET

| Endpoint                                  | Description                             |
| ----------------------------------------- | --------------------------------------- |
| `/api/v1/notes`                           | Get all notes                           |
| `/api/v1/notes?category=Trabajo`          | Get all notes filtered by category      |
| `/api/v1/notes/active`                    | Get all active notes                    |
| `/api/v1/notes/active?category=Trabajo`   | Get active notes filtered by category   |
| `/api/v1/notes/archived`                  | Get all archived notes                  |
| `/api/v1/notes/archived?category=Trabajo` | Get archived notes filtered by category |
| `/api/v1/notes/{id}`                      | Get a single note by ID                 |
| `/api/v1/notes/categories`                | Get all available categories            |
| `/api/v1/notes/categories/{id}`           | Get a single category by ID             |

### 🟡 POST

| Endpoint                   | Description           |
| -------------------------- | --------------------- |
| `/api/v1/notes`            | Create a new note     |
| `/api/v1/notes/categories` | Create a new category |

- Body example:

```json
{
  "title": "New Note",
  "content": "This is the note content.",
  "category": {
    "id": 1,
    "name": "Work"
  }
}
```

### 🔵 PUT

| Endpoint             | Description         |
| -------------------- | ------------------- |
| `/api/v1/notes/{id}` | Update a note by ID |

- Body same as POST

### 🔴 DELETE

| Endpoint             | Description         |
| -------------------- | ------------------- |
| `/api/v1/notes/{id}` | Delete a note by ID |

### 🟣 PATCH

| Endpoint                       | Description      |
| ------------------------------ | ---------------- |
| `/api/v1/notes/{id}/archive`   | Archive a note   |
| `/api/v1/notes/{id}/unarchive` | Unarchive a note |

## 🗂️ Notes

- All responses are in JSON.
- createdAt and updatedAt fields are managed automatically.
- Category must exist (referenced by ID).
- Use proper HTTP headers (Content-Type: application/json).

## 📬 Contact

Maintained by [Germán Lagger](https://www.linkedin.com/in/germanlagger/)

# Task Tracker

A full-stack task management application built with Spring Boot and React. Organize your tasks into lists, set priorities, track status, and manage due dates.

## Features

- **Task Lists**: Create and manage multiple task lists
- **Tasks**: Add tasks to lists with the following attributes:
  - Title and description
  - Due date
  - Priority (High, Medium, Low)
  - Status (Open, Closed)
- **CRUD Operations**: Full create, read, update, and delete functionality for both task lists and tasks
- **Modern UI**: Built with React, TypeScript, NextUI, and Tailwind CSS for a beautiful and responsive interface

## Tech Stack

### Backend
- **Java 25**
- **Spring Boot 3.5.8**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Maven**

### Frontend
- **React 18**
- **TypeScript**
- **Vite**
- **NextUI**
- **Tailwind CSS**
- **React Router**
- **Axios**
- **Framer Motion**
- **Lucide React**

### Infrastructure
- **Docker Compose** (for database)

## Prerequisites

- Java 25 (or compatible JDK)
- Node.js (v18 or higher)
- npm or yarn
- Docker and Docker Compose
- Maven 3.6+

## Getting Started

### 1. Clone the Repository

```bash
git clone <repository-url>
cd task-tracker
```

### 2. Start the Database

Start the PostgreSQL database using Docker Compose:

```bash
docker-compose up -d
```

This will start a PostgreSQL container on port 5432 with the following credentials:
- Username: `your_username`
- Password: `your_password`
- Database: `postgres`

### 3. Start the Backend

Navigate to the backend directory and start the Spring Boot application:

```bash
cd backend
./mvnw spring-boot:run
```

Or on Windows:

```bash
cd backend
mvnw.cmd spring-boot:run
```

The backend API will be available at `http://localhost:8080` (default Spring Boot port).

### 4. Start the Frontend

In a new terminal, navigate to the frontend directory and install dependencies:

```bash
cd frontend
npm install
```

Then start the development server:

```bash
npm run dev
```

The frontend will be available at `http://localhost:5173` (default Vite port).

## Project Structure

```
task-tracker/
├── backend/                 # Spring Boot backend
│   ├── src/
│   │   └── main/
│   │       └── java/
│   │           └── com/
│   │               └── coder2client/
│   │                   ├── controllers/    # REST controllers
│   │                   ├── services/       # Business logic
│   │                   ├── repositories/   # Data access layer
│   │                   ├── entities/      # JPA entities
│   │                   ├── dto/            # Data transfer objects
│   │                   ├── mappers/        # Entity-DTO mappers
│   │                   ├── enums/          # Enumerations
│   │                   └── exceptions/     # Exception handling
│   └── pom.xml
├── frontend/               # React frontend
│   ├── src/
│   │   ├── components/     # React components
│   │   ├── domain/         # TypeScript domain models
│   │   └── App.tsx         # Main app component
│   └── package.json
└── docker-compose.yml      # Database configuration
```

## API Endpoints

### Task Lists
- `GET /api/task-lists` - Get all task lists
- `GET /api/task-lists/{id}` - Get a task list by ID
- `POST /api/task-lists` - Create a new task list
- `PUT /api/task-lists/{id}` - Update a task list
- `DELETE /api/task-lists/{id}` - Delete a task list

### Tasks
- `GET /api/task-lists/{listId}/tasks` - Get all tasks in a list
- `GET /api/tasks/{id}` - Get a task by ID
- `POST /api/task-lists/{listId}/tasks` - Create a new task in a list
- `PUT /api/tasks/{id}` - Update a task
- `DELETE /api/tasks/{id}` - Delete a task

## Development

### Backend Development

The backend uses Spring Boot with JPA. The database schema is automatically updated based on entity changes (configured with `spring.jpa.hibernate.ddl-auto=update`).

To run tests:
```bash
cd backend
./mvnw test
```

### Frontend Development

The frontend uses Vite for fast development. Hot module replacement is enabled by default.

To build for production:
```bash
cd frontend
npm run build
```

To preview the production build:
```bash
npm run preview
```

To lint the code:
```bash
npm run lint
```

## Configuration

### Backend Configuration

Database configuration can be modified in `backend/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=pastoral2u
```

### Frontend Configuration

The frontend API base URL can be configured in the Axios setup (typically in a service or configuration file).

## Database Schema

### TaskList
- `id` (UUID, Primary Key)
- `title` (String, Not Null)
- `description` (String, Not Null)
- `created_date` (LocalDateTime, Not Null)
- `updated_date` (LocalDateTime, Not Null)

### Task
- `id` (UUID, Primary Key)
- `title` (String, Not Null)
- `description` (String, Not Null)
- `due_date` (LocalDateTime, Nullable)
- `status` (TaskStatus: OPEN/CLOSED, Not Null)
- `priority` (TaskPriority: HIGH/MEDIUM/LOW, Not Null)
- `task_list_id` (UUID, Foreign Key to TaskList)
- `created_date` (LocalDateTime, Not Null)
- `updated_date` (LocalDateTime, Not Null)

## License

See the LICENSE file in the frontend directory for more information.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.


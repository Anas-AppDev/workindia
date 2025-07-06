# 🚆 IRCTC Railway Management System

A Spring Boot-based RESTful API simulating the core functionalities of the Indian Railway Catering and Tourism Corporation (IRCTC). This system allows users to register, log in, check train seat availability, book tickets, and manage bookings. Admin users can securely manage trains using an API key. The system is designed to support concurrent bookings and provide real-time seat updates.

---

## 🛠️ Tech Stack

- **Framework:** Spring Boot  
- **Database:** MySQL / PostgreSQL  
- **Build Tool:** Maven  
- **Security:** JWT Authentication, API Key for Admin

---

## ✅ Features

- User Registration & Authentication (JWT)
- Admin-controlled train management
- Real-time seat availability check
- Ticket booking with concurrency handling
- Booking history and ticket lookup
- API key protection for sensitive endpoints

---

## 🚀 Getting Started

### 🔧 Prerequisites

- Java 11 or higher
- MySQL/PostgreSQL
- Maven
- Git

### 📦 Setup Instructions

#### 1. Clone the Repository

```bash
git clone <your-repo-url>
cd irctc-railway-system
```

#### 2. Configure the Database

- Create a database (e.g., `irctc_db`)
- Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/irctc_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

> ⚠️ Ensure tables for `Roles`, `Users`, `Trains`, and `Bookings` exist based on entity definitions.

#### 3. Build the Project

```bash
mvn clean install
```

#### 4. Run the Application

```bash
mvn spring-boot:run
```

- The server will start at: `http://localhost:8080`

#### 5. Admin API Key Configuration

- Default key: `ADMIN_KEY_123`  
- Pass this in the request header as: `X-API-Key: ADMIN_KEY_123`  
- You can change it in the code or configure it via environment variables for better security.

---

## 📡 API Endpoints

### 👤 User Management

- `POST /api/users/register` – Register a new user  
- `POST /api/users/login` – Authenticate and receive JWT  
- `GET /api/users/{userId}` – Get user details  
- `PUT /api/users/{userId}` – Update user  
- `DELETE /api/users/{userId}` – Delete user  

### 🚉 Train Management (Admin Only)

- `POST /api/trains` – Add a new train 🔐  
- `GET /api/trains/{trainId}` – Get train details  
- `PUT /api/trains/{trainId}` – Update train  
- `DELETE /api/trains/{trainId}` – Delete train  

> 🔐 Requires `X-API-Key` header.

### 📊 Seat Availability

- `GET /api/trains/availability?source={source}&destination={destination}` – Check available seats

### 🎟️ Booking Management

- `POST /api/bookings` – Book a ticket  
- `GET /api/bookings/{ticketId}` – Get booking details  

---

## ⚙️ Assumptions & Notes

- Uses **JWT** for session-less authentication.
- Seat availability: `train.capacity - booked_seats`
- Concurrency handled using `@Transactional` with **pessimistic locking** to prevent overbooking.

---

## 🧪 Running Tests

Unit tests are located under `src/test/java`.

```bash
mvn test
```

---

## 🤝 Contributing

1. Fork the repository
2. Create a new branch: `git checkout -b feature-branch`
3. Make your changes and commit: `git commit -m "Add new feature"`
4. Push to GitHub: `git push origin feature-branch`
5. Open a Pull Request

---

## 📄 License

This project is licensed under the [MIT License](LICENSE.md).

---

## ✨ Acknowledgements

Inspired by the official IRCTC system, this project aims to simulate the core experience in a simplified backend service form.

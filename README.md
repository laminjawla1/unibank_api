# 🏦 UniBank API

A robust **banking REST API** built with **Java** and **Spring Boot**.  
This project serves as the backend for the UniBank platform, providing secure and scalable APIs for core banking operations including customer management, account operations, transactions, and user administration.

---

## 🚀 Features

- **Customer Management** - Create, read, update, and delete customer records
- **Account Operations** - Manage bank accounts with full CRUD capabilities
- **Transaction Processing** - Handle deposits, withdrawals, and transfers
- **User Administration** - Role-based access control and user management
- **RESTful Architecture** - Clean, well-structured API endpoints
- **Security** - Robust authentication and authorization mechanisms
- **Database Integration** - Persistent data storage with JPA/Hibernate

---

## 🛠️ Tech Stack

| Technology      | Purpose                           |
|-----------------|-----------------------------------|
| Java            | Core programming language         |
| Spring Boot     | Application framework             |
| Spring Data JPA | Database access and ORM           |
| Spring Security | Authentication & authorization    |
| Maven           | Dependency management & build     |
| MySQL/H2        | Database (production/development) |
| Hibernate       | ORM framework                     |

---

## 📁 Project Structure

```
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/unibank/api/
│   │   │       ├── controller/    # REST controllers
│   │   │       ├── service/       # Business logic
│   │   │       ├── repository/    # Data access layer
│   │   │       ├── model/         # Entity models
│   │   │       ├── dto/           # Data transfer objects
│   │   │       ├── config/        # Configuration classes
│   │   │       └── security/      # Security configurations
│   │   └── resources/
│   │       └── application.yml
│   └── test/
│       └── java/                  # Unit and integration tests
├── .mvn/
├── pom.xml
└── README.md
```

---

## ⚡ Getting Started

### Prerequisites

- **Java 17** or higher ([Download here](https://www.oracle.com/java/technologies/downloads/))
- **Maven 3.6+** (included via Maven Wrapper)
- **MySQL** (optional, H2 can be used for development)
- **Git**

### 1. Clone the Repository

```bash
git clone https://github.com/laminjawla1/unibank_api.git
cd unibank_api
```

### 2. Configure Database

Edit `src/main/resources/application.yml`:

```yaml
# MySQL Configuration (Production)
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/unibank_db
    username: your_username
    password: your_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

# H2 Configuration (Development)
# spring:
#   datasource:
#     url: jdbc:h2:mem:unibank_db
#   h2:
#     console:
#       enabled: true
```

### 3. Build the Project

Using Maven Wrapper (recommended):

```bash
./mvnw clean install
```

Or with Maven:

```bash
mvn clean install
```

### 4. Run the Application

Using Maven Wrapper:

```bash
./mvnw spring-boot:run
```

Or with Maven:

```bash
mvn spring-boot:run
```

The API will be available at:
➡️ `http://localhost:8080`

---

## 📚 API Endpoints

### Customers
- `GET /api/customers` - Get all customers
- `GET /api/customers/{id}` - Get customer by ID
- `POST /api/customers` - Create new customer
- `PUT /api/customers/{id}` - Update customer
- `DELETE /api/customers/{id}` - Delete customer

### Accounts
- `GET /api/accounts` - Get all accounts
- `GET /api/accounts/{id}` - Get account by ID
- `POST /api/accounts` - Create new account
- `PUT /api/accounts/{id}` - Update account
- `DELETE /api/accounts/{id}` - Delete account

### Transactions
- `GET /api/transactions` - Get all transactions
- `GET /api/transactions/{id}` - Get transaction by ID
- `POST /api/transactions/deposit` - Process deposit
- `POST /api/transactions/withdraw` - Process withdrawal
- `POST /api/transactions/transfer` - Process transfer

### Users
- `GET /api/users` - Get all users
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

### Reports
- `GET /api/reports/dashboard` - Get dashboard statistics

---

## 🔒 Security

This API implements:
- **Spring Security** for authentication and authorization
- **JWT tokens** for stateless authentication (if configured)
- **Role-based access control** (RBAC)
- **Password encryption** using BCrypt

---

## 🧪 Testing

Run tests with:

```bash
./mvnw test
```

Or:

```bash
mvn test
```

---

## 📦 Building for Production

Create a production-ready JAR:

```bash
./mvnw clean package -DskipTests
```

The JAR file will be in `target/` directory.

Run the JAR:

```bash
java -jar target/unibank-api-0.0.1-SNAPSHOT.jar
```

---

## 🐳 Docker Deployment (Optional)

### Create Dockerfile

```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Build and Run

```bash
docker build -t unibank-api .
docker run -p 8080:8080 unibank-api
```

---

## 🔧 Configuration

Key configuration options in `application.yml`:

```yaml
# Server Configuration
server:
  port: 8080

# Database
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/unibank_db
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

  # Security
  security:
    user:
      name: admin
      password: admin123

# Logging
logging:
  level:
    root: INFO
    com.unibank.api: DEBUG
```

---

## 🤝 Contributing

Contributions are welcome! To contribute:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## 📝 Development Guidelines

- Follow Java coding conventions
- Write unit tests for new features
- Update API documentation for new endpoints
- Use meaningful commit messages
- Keep dependencies up to date

---

## 🐛 Troubleshooting

### Common Issues

**Port already in use:**
```yaml
# Change port in application.yml
server:
  port: 8081
```

**Database connection error:**
- Verify MySQL is running
- Check database credentials
- Ensure database exists

**Maven build fails:**
```bash
./mvnw clean
./mvnw install
```

---

## 📄 License

This project is open source and available under the MIT License.

---

## 👥 Authors

**Development Team:**
- **Lamin Jawla** - [@laminjawla1](https://github.com/laminjawla1)
- **Mahamadou Jabbie**
- **Alimatou Njie**
- **Yankuba Suso**
- **Abubacarr Touray**

---

## 🔗 Related Projects

- [UniBank Client](https://github.com/laminjawla1/unibank_client) - Frontend React application

---

## 📞 Support

For issues and questions:
- Open an issue on [GitHub Issues](https://github.com/laminjawla1/unibank_api/issues)
- Contact the maintainer

---

## ⭐ Acknowledgments

Built with Spring Boot framework and the Java ecosystem.
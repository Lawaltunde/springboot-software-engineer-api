# springboot-software-engineer-api
A Spring Boot REST API for managing software engineer records, including creating and retrieving engineers from a database.

## Prerequisites
- Java 17 or higher
- Maven 3.6+
- Docker and Docker Compose

## Environment Setup

### 1. Create Environment File
Copy the `.env.example` file to `.env`:
```bash
cp .env.example .env
```

### 2. Configure Environment Variables
Edit the `.env` file and set your actual credentials:
```properties
# Database Configuration
POSTGRES_USER=your_postgres_user
POSTGRES_PASSWORD=your_secure_password
POSTGRES_DB=engineer
POSTGRES_PORT=5332

# Spring Application Configuration
DB_HOST=localhost
DB_PORT=5332
DB_NAME=engineer
DB_USERNAME=your_db_username
DB_PASSWORD=your_secure_password
```

**⚠️ IMPORTANT**: Never commit the `.env` file to Git! It's already added to `.gitignore`.

## Running the Application

### 1. Start the Database
```bash
docker-compose up -d
```

### 2. Set Environment Variables (for Spring Boot)
**Option A: Using IDE (IntelliJ/VS Code)**
- Set environment variables in your run configuration

**Option B: Using Terminal (macOS/Linux)**
```bash
export DB_HOST=localhost
export DB_PORT=5332
export DB_NAME=engineer
export DB_USERNAME=your_db_username
export DB_PASSWORD=your_secure_password
```

**Option C: Using Terminal (Windows)**
```cmd
set DB_HOST=localhost
set DB_PORT=5332
set DB_NAME=engineer
set DB_USERNAME=your_db_username
set DB_PASSWORD=your_secure_password
```

### 3. Run the Spring Boot Application
```bash
./mvnw spring-boot:run
```

## API Endpoints

### Get All Software Engineers
```http
GET http://localhost:8080/api/v1/softwaare-engineer
```

### Add a Software Engineer
```http
POST http://localhost:8080/api/v1/softwaare-engineer
Content-Type: application/json

{
  "name": "John Doe",
  "stack": "Java, Spring Boot, React"
}
```

## Production Best Practices

### For Production Deployment:
1. **Use a secrets management service** (AWS Secrets Manager, Azure Key Vault, HashiCorp Vault)
2. **Use Spring Profiles** for different environments (dev, staging, prod)
3. **Never log sensitive data**
4. **Use strong, unique passwords**
5. **Rotate credentials regularly**
6. **Use HTTPS in production**

### Spring Profiles Example
Create separate property files:
- `application-dev.properties`
- `application-prod.properties`

Run with specific profile:
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
```

## Security Notes
- The `.env` file contains sensitive information and is excluded from version control
- Share `.env.example` with your team as a template
- Each developer should create their own `.env` file locally
- Use different credentials for development, staging, and production environments

# springboot-software-engineer-api
A Spring Boot REST API for managing software engineer records with AI-powered learning path recommendations. The application uses OpenAI to generate personalized learning paths based on the engineer's tech stack.

## Features
- ✨ **AI-Powered Learning Paths**: Automatically generates personalized learning recommendations using OpenAI
- 📊 **CRUD Operations**: Create, Read, Update, and Delete software engineer records
- 🗄️ **PostgreSQL Database**: Persistent storage with JPA/Hibernate
- 🔒 **Secure Configuration**: Environment-based credential management
- 🚀 **RESTful API**: Clean and intuitive API endpoints

## Prerequisites
- Java 17 or higher
- Maven 3.6+
- Docker and Docker Compose
- OpenAI API Key (for AI-powered features)

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

# AI Configuration
API_KEY=your_openai_api_key
```

**⚠️ IMPORTANT**: 
- Never commit the `.env` file to Git! It's already added to `.gitignore`.
- Get your OpenAI API key from [OpenAI Platform](https://platform.openai.com/api-keys)

## Running the Application

### 1. Start the Database
```bash
docker-compose up -d
```

### 2. Set Environment Variables (for Spring Boot)
**Option A: Using IDE (IntelliJ/VS Code)**
- Set environment variables in your run configuration

**Option B: Using Helper Script (Recommended)**
```bash
./run.sh
```
This script automatically loads environment variables from `.env` and starts the application.

**Option C: Using Terminal (macOS/Linux)**
```bash
export DB_HOST=localhost
export DB_PORT=5332
export DB_NAME=engineer
export DB_USERNAME=your_db_username
export DB_PASSWORD=your_secure_password
export API_KEY=your_openai_api_key
```

**Option D: Using Terminal (Windows)**
```cmd
set DB_HOST=localhost
set DB_PORT=5332
set DB_NAME=engineer
set DB_USERNAME=your_db_username
set DB_PASSWORD=your_secure_password
set API_KEY=your_openai_api_key
```

### 3. Run the Spring Boot Application
```bash
# With Maven
./mvnw spring-boot:run

# Or using the helper script (loads .env automatically)
./run.sh

# Run with specific profile
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
```

## API Endpoints

### 1. Get All Software Engineers
```http
GET http://localhost:8080/api/v1/software-engineer
```

**Response Example:**
```json
[
  {
    "id": 1,
    "name": "Lawal",
    "stack": "Java, Spring Boot, React",
    "recommendedLearningPath": "Based on your tech stack focusing on Java, Spring Boot, and React..."
  }
]
```

### 2. Add a Software Engineer (with AI Learning Path Generation)
```http
POST http://localhost:8080/api/v1/software-engineer
Content-Type: application/json

{
  "name": "John Doe",
  "stack": "Java, Spring Boot, React"
}
```

**What Happens:**
1. The API receives the engineer's name and tech stack
2. The `AiService` sends a prompt to OpenAI asking for a personalized learning path
3. The AI generates a well-structured learning path based on the provided stack
4. The engineer record is saved with the AI-generated `recommendedLearningPath`

**Response:** 200 OK (The record is created with AI-generated learning path)

### 3. Update a Software Engineer
```http
PUT http://localhost:8080/api/v1/software-engineer/{id}
Content-Type: application/json

{
  "name": "John Doe Updated",
  "stack": "Java, Spring Boot, React, Docker"
}
```

### 4. Delete a Software Engineer
```http
DELETE http://localhost:8080/api/v1/software-engineer/{id}
```

## AI Integration Details

### How It Works

The application integrates **Spring AI** with **OpenAI** to provide intelligent learning path recommendations:

1. **AiService Class**: Handles all AI-related operations
   ```java
   @Service
   public class AiService {
       private final ChatClient chatClient;
       
       public String chat(String prompt) {
           return chatClient.prompt(prompt).call().content();
       }
   }
   ```

2. **Integration in Service Layer**: When a new engineer is added, the service:
   - Constructs a prompt with the engineer's name and tech stack
   - Calls the AI service to generate a learning path
   - Stores the response in the `recommendedLearningPath` field

3. **Example AI Prompt**:
   ```
   Based on the provided tech stack Java, Spring Boot, React by John Doe, 
   please provide a well-structured and summarized learning path for the individual.
   Ensure it is well formatted and written in clear, human-sounding language.
   ```

### Configuration

The AI integration uses OpenAI's API, configured in `application-dev.properties`:
```properties
spring.ai.openai.api-key=${API_KEY}
```

**Dependencies Used:**
- `spring-ai-bom` (version 2.0.0-M2)
- `spring-ai-starter-model-openai`

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

## Architecture

### Project Structure
```
src/main/java/com/devlawal/engineer/
├── Application.java                    # Main Spring Boot application
├── SoftwareEngineer.java              # Entity model with JPA annotations
├── SoftwareEngineerRepository.java    # JPA repository interface
├── SoftwareEngineerService.java       # Business logic with AI integration
├── SoftwareEngineerController.java    # REST API endpoints
└── AiService.java                     # AI/OpenAI integration service
```

### Key Components

1. **Entity Layer** (`SoftwareEngineer.java`)
   - Fields: `id`, `name`, `stack`, `recommendedLearningPath`
   - The `recommendedLearningPath` field stores AI-generated content

2. **Repository Layer** (`SoftwareEngineerRepository.java`)
   - Extends `JpaRepository` for database operations

3. **Service Layer** (`SoftwareEngineerService.java`)
   - Integrates `AiService` for generating learning paths
   - Handles CRUD business logic

4. **AI Service** (`AiService.java`)
   - Wraps Spring AI's `ChatClient` for OpenAI integration
   - Provides simple `chat(prompt)` method for AI interactions

5. **Controller Layer** (`SoftwareEngineerController.java`)
   - Exposes RESTful endpoints
   - Handles HTTP requests and responses

## Technologies Used

- **Spring Boot 4.0.2** - Application framework
- **Spring Data JPA** - Database persistence
- **Spring AI 2.0.0-M2** - AI integration framework
- **OpenAI API** - AI-powered learning path generation
- **PostgreSQL** - Relational database
- **Hibernate** - ORM implementation
- **Maven** - Dependency management
- **Docker** - Database containerization

## Security Notes
- The `.env` file contains sensitive information and is excluded from version control
- **Never commit API keys** - Keep your OpenAI API key secure
- Share `.env.example` with your team as a template
- Each developer should create their own `.env` file locally
- Use different credentials for development, staging, and production environments
- Monitor your OpenAI API usage to avoid unexpected charges

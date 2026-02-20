# Security Checklist for Production

## ✅ Pre-Deployment Checklist

### Environment Variables
- [ ] All sensitive data moved to environment variables
- [ ] `.env` file is in `.gitignore`
- [ ] `.env.example` provided for team reference (without real credentials)
- [ ] Different credentials for dev, staging, and production
- [ ] Passwords are strong and unique (minimum 16 characters)

### Database Security
- [ ] Database user has minimum required privileges
- [ ] Database is not exposed to the public internet
- [ ] Connection pool properly configured
- [ ] SSL/TLS enabled for database connections in production
- [ ] Regular backups configured

### Application Security
- [ ] `spring.jpa.hibernate.ddl-auto` set to `validate` or `none` in production (NEVER `create-drop`)
- [ ] Error messages don't expose sensitive information
- [ ] Stack traces disabled in production
- [ ] SQL queries not logged in production (`spring.jpa.show-sql=false`)
- [ ] HTTPS/TLS enabled
- [ ] CORS properly configured
- [ ] Input validation implemented
- [ ] SQL injection protection (JPA handles this)
- [ ] Authentication and authorization implemented (if required)

### Logging
- [ ] Sensitive data not logged (passwords, tokens, etc.)
- [ ] Appropriate log levels set (WARN or ERROR in production)
- [ ] Log files properly rotated
- [ ] Centralized logging configured (optional but recommended)

### Secrets Management (Production)
Choose one:
- [ ] AWS Secrets Manager
- [ ] Azure Key Vault
- [ ] HashiCorp Vault
- [ ] Google Cloud Secret Manager
- [ ] Kubernetes Secrets (if using K8s)

### Docker Security
- [ ] Don't use `latest` tag in production (use specific versions)
- [ ] Docker images scanned for vulnerabilities
- [ ] Non-root user in Dockerfile
- [ ] Minimal base images used
- [ ] Secrets not baked into images

### Code Review
- [ ] No hardcoded credentials in code
- [ ] No sensitive data in comments
- [ ] `.env` file not committed (double-check git history)
- [ ] Dependencies up to date (no known vulnerabilities)

### Monitoring
- [ ] Health check endpoint configured
- [ ] Metrics collection enabled
- [ ] Alerting configured for critical errors
- [ ] Database connection pool monitoring

## 🔒 Current Implementation Status

### ✅ Completed
- Environment variables configured for sensitive data
- `.env` file excluded from Git
- Separate profiles for dev and production
- Production profile with secure defaults
- Error details hidden in production

### 🚧 Recommended Next Steps
1. Add Spring Security for authentication/authorization
2. Implement input validation with `@Valid` annotations
3. Add API rate limiting
4. Configure HTTPS/TLS certificates
5. Set up a secrets management service for production
6. Add health check endpoint (`spring-boot-starter-actuator`)
7. Implement comprehensive logging strategy
8. Add API documentation (Swagger/OpenAPI)

## 📝 Additional Resources

### Spring Security
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

### Validation
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

### Actuator (Health Checks)
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

## 🚨 Emergency Response

If credentials are accidentally committed:
1. **Immediately rotate all exposed credentials**
2. Remove sensitive data from Git history:
   ```bash
   git filter-branch --force --index-filter \
   "git rm --cached --ignore-unmatch .env" \
   --prune-empty --tag-name-filter cat -- --all
   ```
3. Force push (use with caution):
   ```bash
   git push origin --force --all
   ```
4. Inform your team
5. Check logs for unauthorized access

@echo off
REM Script to load environment variables from .env file and run Spring Boot application

REM Check if .env file exists
if not exist .env (
    echo Error: .env file not found!
    echo Please copy .env.example to .env and configure your credentials.
    echo Run: copy .env.example .env
    exit /b 1
)

echo Loading environment variables from .env file...

REM Load environment variables from .env file
for /f "tokens=*" %%a in ('type .env ^| findstr /v "^#"') do set %%a

REM Determine which profile to use (default to dev)
if "%SPRING_PROFILE%"=="" set SPRING_PROFILE=dev

echo Starting Spring Boot application with profile: %SPRING_PROFILE%
call mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=%SPRING_PROFILE%

#!/bin/bash
# Script to load environment variables from .env file and run Spring Boot application

# Check if .env file exists
if [ ! -f .env ]; then
    echo "Error: .env file not found!"
    echo "Please copy .env.example to .env and configure your credentials."
    echo "Run: cp .env.example .env"
    exit 1
fi

# Load environment variables from .env file
echo "Loading environment variables from .env file..."
export $(cat .env | grep -v '^#' | xargs)

# Determine which profile to use (default to dev)
PROFILE=${SPRING_PROFILE:-dev}

echo "Starting Spring Boot application with profile: $PROFILE"
./mvnw spring-boot:run -Dspring-boot.run.profiles=$PROFILE

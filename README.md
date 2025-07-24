# JournalApp – Spring Boot REST API with MongoDB & Basic Auth

A simple and secure backend application built using **Spring Boot**, designed for managing user data through RESTful APIs. This project demonstrates how to structure, secure, and deploy a Spring Boot application in a real-world environment.

## 🔧 Tech Stack

- **Spring Boot** – Java-based backend framework  
- **Spring Security (Basic Auth)** – For securing endpoints  
- **MongoDB Atlas** – Cloud NoSQL database  
- **Maven** – Project and dependency management  
- **AWS EC2** – Deployment on a virtual cloud server

## 🚀 Features

This Spring Boot backend project includes the following features:

### 🔐 Authentication & Security
- Basic authentication using Spring Security
- Role-based access control (e.g., `/admin/*` endpoints secured for admin users)

### 👥 User Management
- Register new users
- Basic role assignment
- Retrieve all users (admin access only)

### 📓 Journal Entry System
- Create and manage journal entries
- Associate entries with specific users
- Fetch entries based on user ID

### 💾 MongoDB Integration
- Connected to MongoDB Atlas (cloud database)
- Repository layer built with Spring Data MongoDB

### 🌐 Health Check
- Simple `/health` endpoint to check service availability

### 🌍 Environment Profiles
- `dev` and `prod` profiles supported via YAML
- Easy switching using Maven profile flags during build

## 📂 Project Structure

- `Configuration`: Security configuration
- `Controller`: REST APIs for User, Admin, Journal, and Health Check
- `Entity`: MongoDB document models (`UserEntity`, `JournalEntity`)
- `Repository`: Interface-based data access layer
- `Services`: Business logic layer

## 🚀 Getting Started

To run the app locally:

### Run with dev profile

```bash

mvn clean spring-boot:run -Dspring-boot.run.profiles=dev

```


### To Run on AWS EC2 Create jar with prod profile

```bash
mvn clean package -Dspring.profiles.active=prod
```

## ✅ Deployment
- Deployed and tested on AWS EC2 (Amazon Linux instance).
- MongoDB Atlas access is restricted only to the EC2 instance’s public IP for improved security.
- The app is packaged using the prod profile and ran on port 8080, which was opened via EC2’s security group inbound rules.

## 🔄 Upcoming Improvements

### 🛠️ CI/CD with GitHub Actions

I’m currently working on setting up CI/CD pipelines using GitHub Actions to streamline the deployment process. The idea is to:

- Automatically build the Spring Boot app whenever changes are pushed to the `main` branch  
- Run all tests to make sure everything is stable  
- Package the app using the `prod` profile  
- Connect to the EC2 instance over SSH and deploy the new `.jar`  
- Restart the app on the server — no manual steps needed

This will help reduce deployment errors, save time, and bring the project one step closer to a production-ready setup.

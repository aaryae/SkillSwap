# 🧠 SkillSwap – Microservices-Based Skill Exchange Platform

A learning-oriented **Skill Exchange Platform** where users can offer and request skills — enabling a barter-like system powered by **Spring Boot Microservices**, **React**, and **PostgreSQL**.

---

## 🚀 Project Overview

SkillSwap is a microservices-based web application that connects users who want to **learn a skill** with those who can **teach or offer it**.  
It is designed to demonstrate **industry-level backend architecture** with Spring Cloud components, clean coding principles, and service orchestration.

---

## 🏗️ Architecture Overview

### 🧩 Services
| Service | Description |
|----------|--------------|
| **Eureka Server** | Service registry for discovering microservices |
| **API Gateway** | Central entry point for routing requests |
| **Auth Service** | Handles user registration, login, and JWT authentication |
| **User Service** | Manages user profiles, offered/requested skills |
| **Skill Service** | CRUD operations for skills and categories |
| **Matchmaking Service** | Pairs users based on compatible skill sets |
| **Common Library** | Shared DTOs, exceptions, and utility classes |

---

## ⚙️ Tech Stack

### 🖥️ Backend
- **Java 21**
- **Spring Boot 3.2.5**
- **Spring Cloud Netflix Eureka**
- **Spring Cloud Gateway**
- **Spring Security (JWT)**
- **Spring Data JPA (Hibernate) and JDBC Template**
- **PostgreSQL**
- **Gradle (Build Tool)**
- **Docker & Docker Compose**

---

## 🧱 Project Structure
SkillSwap/ <br/>
│ <br/>
├── eureka-server/ # Service registry <br/>
├── api-gateway/ # API routing layer<br/>
│<br/>
├── auth-service/ # Authentication & JWT<br/>
├── user-service/ # User management<br/>
├── skill-service/ # Skill CRUD<br/>
├── matchmaking-service/ # Skill pairing logic<br/>
│<br/>
├── common-library/ # Shared utilities and DTOs<br/>
│<br/>
├── docker-compose.yml<br/>
├── README.md<br/>
└── .gitignore<br/>


---

## 🔧 Setup Instructions

 ### Prerequisites
- **Java 21**
- **Gradle**
- **PostgresSQL**
- **Docker** (optional but recommended)
- **IntelliJ IDEA** or VS Code

---

### 🧩 STEP 1 — Create and Configure the **Eureka Server**

### 📘 Purpose

Eureka Server acts as a **service registry**.  
All other microservices (auth, user, skill, etc.) will register themselves here and discover each other dynamically.

---

### 🧱 1. Create Eureka Server Folder

Inside your project root:<br/>
SkillSwap/<br/>
└── eureka-server/

---

### 🧰 2. Create a New Spring Boot Project

Use **Spring Initializr** (https://start.spring.io/)  
Choose these settings:

| Setting | Value |
|----------|--------|
| Project | Gradle |
| Language | Java |
| Spring Boot | 3.2.5 |
| Group | `com.skillswap` |
| Artifact | `eureka-server` |
| Java | 21 |

**Dependencies:**
- Spring Cloud Netflix Eureka Server
- Spring Boot Actuator
- Spring Web

Download → Extract → Place inside `SkillSwap/eureka-server`

---

### ⚙️ 3. Configure `build.gradle`

Make sure your file looks like this 👇

```gradle
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.5'
    id 'io.spring.dependency-management' version '1.1.4'
}

group = 'com.skillswap'
version = '0.0.1-SNAPSHOT'
java {
    sourceCompatibility = '21'
}

repositories {
    mavenCentral()
}

ext {
    set('springCloudVersion', "2023.0.3")
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-server'
    implementation 'org.springframework.boot:spring-boot-starter-actuator'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

dependencyManagement {
    imports {
        mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"
    }
}

tasks.named('test') {
    useJUnitPlatform()
}
// ./gradlew bootRun to run  the server of Eureka
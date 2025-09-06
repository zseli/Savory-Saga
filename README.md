# 🍴 Savory Saga

Savory Saga is a **food blogging web application** built with a **Spring Boot backend** and an **Angular frontend**.  
The platform allows users to explore, share, and interact with food blog posts while ensuring secure access through JWT authentication.

---

## 🚀 Tech Stack

### Backend
- **Spring Boot** 
- **JWT Token** based authentication & authorization
- **MongoDB** as the database
- **Microservice Architecture** 
- **Maven** for build and dependency management

### Frontend
- **Angular** for building a responsive single-page application
- **TypeScript, HTML, SCSS**

---

## 🔑 Features
- User authentication & authorization with JWT
- Post and manage food blogs
- View and bookmark blogs from other users
- Microservices for scalable and modular design
- MongoDB for flexible document-based storage

---

## 📂 Project Structure
savory-saga/

├── backend/ # Spring Boot microservices

└── frontend/ # Angular application

## ⚙️ Getting Started

### 1. Clone the repository
git clone https://github.com/zseli/Savory-Saga.git
cd savory-saga

1. Backend Setup
cd backend
./mvnw spring-boot:run

2. Frontend Setup
cd frontend
npm install
ng serve

**The Angular app will run by default on http://localhost:4200**

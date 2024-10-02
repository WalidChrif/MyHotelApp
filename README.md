# My-Hotel-App - Hotel Management System

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Screenshots](#screenshots)
- [Contributing](#contributing)
- [License](#license)

## Introduction
**My-Hotel-App** is a sophisticated hotel management system built using a microservices architecture with **Spring Cloud**. This application enables efficient management of hotel operations, including property listings, bookings, and customer interactions. By utilizing various modern technologies, My-Hotel-App ensures scalability and high performance.

## Features
- **Property Management**: Easy listing and management of hotel properties.
- **Booking System**: Users can book properties and manage reservations seamlessly.
- **User Management**: Secure user authentication and role-based access.
- **Admin Dashboard**: A comprehensive interface for administrators to manage properties and bookings.
- **Messaging System**: Integration of RabbitMQ and Kafka for efficient event handling.

## Technologies Used
- **Backend**:
  - **Spring Boot**: Framework for building RESTful APIs.
  - **Spring Cloud**: For microservices architecture.
  - **Eureka**: Service discovery.
  - **API Gateway**: For routing requests.
  - **RabbitMQ & Kafka**: For messaging and event streaming.
- **Database**: 
  - **MySQL**: Relational database for data persistence.
  - **MongoDB**: NoSQL database for flexible data storage.
- **Other**: 
  - **JPA**: For data access and manipulation.
  - **Feign Client**: For making HTTP requests to other services.
  - **RestTemplate**: For interacting with RESTful services.
  - **Zipkin**: For distributed tracing and monitoring.

## Installation

### Prerequisites
- **Java JDK 11+**: Install from [Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- **MySQL**: Install and configure MySQL for the backend.
- **MongoDB**: Install and configure MongoDB if used.
- **Node.js** and **npm**: Install from [Node.js](https://nodejs.org) (if applicable).

### Frontend Setup (if applicable)
1. Clone the repository:
   ```bash
   git clone https://github.com/walidchrif/myhotelapp.git
   cd my-hotel-app/frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the Angular development server:
   ```bash
   ng serve
   ```

### Backend Setup
1. Navigate to the backend directory:
   ```bash
   cd my-hotel-app/backend
   ```
2. Configure your database settings in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/hotel_db
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   ```
3. Build and run the Spring Boot application:
   ```bash
   ./mvnw spring-boot:run
   ```

## Usage
- Access the application at `http://localhost:4200` for the frontend.

## Screenshots
(Include screenshots of the application showcasing key features)

## Contributing
1. Fork the project
2. Create your feature branch: `git checkout -b feature/my-feature`
3. Commit your changes: `git commit -m 'Add my feature'`
4. Push to the branch: `git push origin feature/my-feature`
5. Open a pull request

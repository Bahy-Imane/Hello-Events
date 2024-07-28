# Hello-Events

Hello-Events is a web application for event ticket booking developed using Spring Boot 3.3.2.

## Project Overview

This application allows users to browse events, book tickets, and manage their accounts. It also provides an admin interface for managing events and user accounts.

### Features

#### For Clients:
- View events on the homepage
- User registration and authentication
- User profile management
- Event search and filtering
- Ticket booking
- View team and company values
- Contact form submission

#### For Administrators:
- Dashboard with client activities and event data
- User account management
- Event management (CRUD operations)
- Handle contact form submissions

## Technologies Used

- Spring Boot 3.3.2
- Spring MVC
- Spring Security 6.3.1 with JWT
- Spring Data JPA
- JUnit for testing
- DTO pattern
- Lombok for reducing boilerplate code
- MySQL 8.3.0 as the database
- Docker for containerization

## Getting Started

### Prerequisites

- JDK 21 or later
- Maven 3.6.3 or later
- MySQL 8.3.0
- Docker (for containerization)

### Setup and Installation

1. Clone the repository: https://github.com/Bahy-Imane/Hello-Events.git
2. Navigate to the project directory: cd Hello-Events
3. Configure the database connection in `src/main/resources/application.properties`.
4. Build the project: mvn clean install

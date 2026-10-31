# Vehicle Rental and Car Booking System Backend

## Project Information

**Project Name:** Vehicle Rental and Car Booking System Backend

**Student Name:** Lama Suleiman

**Student ID:** 27610

## Project Description

The Vehicle Rental and Car Booking System Backend is a RESTful API developed using Java and Spring Boot. It provides backend functionality for managing vehicles, bookings, and related rental operations.

The application uses Spring Data JPA for database operations and exposes REST API endpoints that can be tested using Postman.

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven
- Relational Database (configured through application properties)
- Postman
- Git and GitHub

## Project Structure

The project follows a layered architecture:

- **Controller:** Handles HTTP requests and API responses.
- **Service:** Contains application business logic.
- **Repository:** Handles database operations using Spring Data JPA.
- **Domain/Entity:** Defines the application's database entities.

## Prerequisites

Before running the application, make sure you have installed:

- JDK 17 or later
- Maven
- IntelliJ IDEA or Visual Studio Code
- A configured database
- Postman (for API testing)

## How to Run the API

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR_GITHUB_USERNAME/vehicle-rentaland-car-booking-system-backend.git
```

### 2. Open the Project

Navigate to the project directory:

```bash
cd vehicle-rentaland-car-booking-system-backend
```

Open the project in IntelliJ IDEA or Visual Studio Code.

### 3. Configure the Database

Open `src/main/resources/application.properties`.

Configure your database connection using your database name, username, password, and database URL.

Make sure your database server is running before starting the application.

**Note:** Database credentials should be kept private and must not be committed to GitHub.

### 4. Install Dependencies

Run:

```bash
mvn clean install
```

### 5. Run the Application

Start the Spring Boot application using:

```bash
mvn spring-boot:run
```

Alternatively, run the main application class from your IDE.

When the application starts successfully, the API will be available at:

```text
http://localhost:8080
```

## API Testing

The API endpoints can be tested using Postman.

The Postman collection is included in this repository and contains requests for testing the available API functionalities.

Import the collection into Postman, start the Spring Boot application, and execute the requests.

## Author

**Lama Suleiman**

Student ID: 27610
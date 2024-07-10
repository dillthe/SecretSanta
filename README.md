# SecretSanta

SecretSanta is a web application designed to facilitate the organization and management of Secret Santa events. Users can create events, manage participants, and draw names while ensuring that certain conditions (e.g., couples not drawing each other) are met.

## Features

- **Event Management**: Create and manage Secret Santa events.
- **Participant Management**: Add, edit, and remove participants from events.
- **Name Drawing**: Conduct the Secret Santa draw, ensuring that couples do not draw each other.
- **Email Notifications**: (In progress) Send email notifications with draw results to participants.

## Tech Stack

### Backend
- **Java**: A robust and widely-used programming language suitable for building scalable backend services.
- **Spring Boot**: A Java-based framework that simplifies the development of RESTful web services and microservices.
- **MySQL**: A reliable and efficient relational database management system for storing application data.

## Installation and Running

### Prerequisites

- Java Development Kit (JDK) installed
- MySQL installed and running

## Usage

1. **Create Event**: Create a new Secret Santa event.
2. **Manage Participants**: Add participants to the event, specifying any couples.
3. **Conduct Draw**: Run the Secret Santa draw, ensuring that couples do not draw each other.
4. **Email Notifications**: (In progress) Automatically send out emails to participants with their Secret Santa assignments.

## Project Structure
  - `src/main/java/`: Java source files
    - `com/secretsanta/`: Main package
      - `web/`: REST controllers, DTO
      - `service/`: Business logic, Mappers, Exceptions
      - `repository/`: Database access, Entity definitions
  - `src/main/resources/`: Application configuration files

## Why These Technologies?

- **Java**: Chosen for its robustness and scalability, making it ideal for backend services.
- **Spring Boot**: Selected for its ability to simplify the development of RESTful web services and microservices, providing a robust and scalable backend.
- **MySQL**: Used for its reliability and efficiency in managing relational data, making it suitable for handling the application’s data storage needs.

## Challenges Faced

1. **Excluding Couples from Draw**: Implementing the logic to ensure that couples do not draw each other was challenging. This required careful handling of participant relationships and draw logic.
2. **Scalability with Event Schema**: To make the system scalable, an `Event` schema (parent entity) was added later. This required significant code refactoring and reworking the mapping between entities, which was complex and time-consuming.
3. **Email Notifications**: Implementing a system to send email notifications with the draw results to participants was challenging. This feature is currently in progress and involves setting up email servers and integrating email services with the application.

## Future Features

- **Advanced Event Management**: Adding more customization options for events, such as budget limits and custom messages.
- **Enhanced Participant Management**: Allowing participants to join events via invitation links.

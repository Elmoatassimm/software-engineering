# Library Management System

A simple console-based library management system implemented in Java using the MVC (Model-View-Controller) architecture pattern.

## Features

- User Authentication
  - Login system
  - User registration with roles (ADMIN/USER)
  - Role-based access control

- Admin Features
  - Add new books to the library
  - View all books in the library
  - Logout functionality

- User Features
  - View all available books
  - Borrow books
  - Return books
  - Logout functionality

## Project Structure

src/
├── App.java
├── controller/
│ └── LibraryController.java
├── model/
│ ├── Book.java
│ └── User.java
└── view/
└── LibraryView.java

## Usage

### First Time Setup
1. Register a new user (you can choose ADMIN or USER role)
2. Login with your credentials

### Admin Operations
- Add new books to the library system
- View all books in the library
- Logout from the system

### User Operations
- View all available books
- Borrow books using book ID
- Return borrowed books using book ID
- Logout from the system

## Technical Details

- Built using Java
- Implements MVC architecture pattern
- Uses enums for Status (AVAILABLE/BORROWED) and Role (ADMIN/USER)
- Implements auto-generation of IDs for books
- Console-based user interface
- In-memory data storage using ArrayLists


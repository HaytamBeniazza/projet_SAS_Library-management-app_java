# projet_SAS_Library-management-app_java


Jira Link: https://sassproject.atlassian.net/jira/software/projects/KAN/boards/1

google docs link specification:
https://docs.google.com/document/d/1LzP2nVhvoe8XptMi1p2RKkrvqHHrt0KI1w_sxBCH25k/edit?usp=sharing

user guide:
https://docs.google.com/document/d/1PUnUfW2mQxYmgOSzwNBLuSGFgpnVkWAp3XSxgtYDlQQ/edit?usp=sharing


Library Management App
The Library Management App is a simple Java-based application designed to help manage a library's book inventory. It provides features such as adding books to the library, searching for books, borrowing and returning books, displaying statistics, and more.

Table of Contents
Features
Getting Started
Prerequisites
Installation
Usage
Contributing
License
Features
Add a Book: Easily add new books to the library's inventory, providing details like title, author, ISBN, and quantity.

Search Books: Search for books by title, author, or ISBN to quickly find the information you need.

Borrow and Return: Keep track of borrowed books by recording borrower information and return dates.

Display Statistics: Get insights into your library's inventory with statistics on total books, borrowed books, lost books, and more.

Getting Started
Prerequisites
Before running the Library Management App, make sure you have the following prerequisites installed:

Java Development Kit (JDK) - Download and install JDK
PostgreSQL Database - Download and install PostgreSQL
Installation
Clone the repository to your local machine:

bash
Copy code
git clone https://github.com/your-username/library-management-app.git
Create a PostgreSQL database for the application. You can use the provided SQL scripts to set up the database schema and sample data:

bash
Copy code
psql -U your-username -d your-database -a -f database/schema.sql
psql -U your-username -d your-database -a -f database/sample-data.sql
Open the project in your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse).

Configure the database connection in the DatabaseConnection.java file with your PostgreSQL database credentials.

Build and run the application.

Usage
Start the Library Management App by running the Main class.

Use the menu options to perform various tasks:

Add a Book: Choose option 1 to add a new book to the library.
Search Books: Choose option 2 to search for books by title, author, or ISBN.
Borrow a Book: Choose option 3 to record a book loan.
Return a Book: Choose option 4 to return a borrowed book.
Show Statistics: Choose option 5 to display statistics about the library's inventory.
Exit: Choose option 6 to exit the application.
Follow the on-screen prompts to complete each task.

Contributing
Contributions are welcome! If you'd like to contribute to this project, please follow these guidelines:

Fork the repository and create a new branch.
Make your changes and test them thoroughly.
Create a pull request with a clear description of your changes.
License
This project is licensed under the MIT License - see the LICENSE file for details.

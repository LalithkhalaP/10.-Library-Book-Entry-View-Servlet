# 10.-Library-Book-Entry-View-Servlet
Objective:
To build a servlet-based library system where admin can add books and view all available books using JDBC.
Description:
The servlet initializes DB connection inside init(), handles requests inside service(), and closes the connection in destroy().
Admin enters book title, author, and price → servlet inserts into books table.
When "View Books" is clicked, servlet fetches all rows and displays them in a table.
Requirements:
HTML:
Form for adding books
Button/link to view books
Servlet:
init() → connection setup
service() → insert/select logic
destroy() → close connection
JDBC queries (INSERT, SELECT)
 connect jar and connect server and run it.It go to localhost page

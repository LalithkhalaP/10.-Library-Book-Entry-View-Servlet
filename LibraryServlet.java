package library;

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/LibraryServlet")
public class LibraryServlet extends HttpServlet {

    Connection con;
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/librarydb", "root", "lalli@2007"  );
        } catch (Exception e) {
            throw new ServletException("DB Connection error: " + e.getMessage());
        }
    }
    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String action = req.getParameter("action");

        out.println("<html><body>");

        try {
            if ("add".equals(action)) {
                String title = req.getParameter("title");
                String author = req.getParameter("author");
                double price = Double.parseDouble(req.getParameter("price"));

                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO books(title, author, price) VALUES (?, ?, ?)"
                );
                ps.setString(1, title);
                ps.setString(2, author);
                ps.setDouble(3, price);
                ps.executeUpdate();

                out.println("Book Added Successfully!<br>");
                out.println("Title: " + title + "<br>");
                out.println("Author: " + author + "<br>");
                out.println("Price: " + price + "<br><br>");
                out.println("<a href='addbook.html'>Add Another Book</a><br>");
                out.println("<a href='LibraryServlet?action=view'>View All Books</a><br>");
      
            }

            else if ("view".equals(action)) {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM books");
                ResultSet rs = ps.executeQuery();

                out.println("All Books in Library<br>");
                out.println("<table border='1'>");
                out.println("<tr><th>ID</th><th>Title</th><th>Author</th><th>Price</th></tr>");
                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("id") + "</td>");
                    out.println("<td>" + rs.getString("title") + "</td>");
                    out.println("<td>" + rs.getString("author") + "</td>");
                    out.println("<td>" + rs.getDouble("price") + "</td>");
                    out.println("</tr>");
                }
                out.println("</table><br>");
                out.println("<a href='addbook.html'>Add New Book</a><br>");
              
            }

        } catch (Exception e) {
            out.println("Error: " + e.getMessage() + "<br>");
        }

        out.println("</body></html>");
    }

    public void destroy() {
        try {
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DemoJDBC extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        // Initialize JDBC connection
        Connection connection = null;
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Configure JDBC connection parameters
            String url = "jdbc:mysql://localhost:3306/mydatabase";
            String username = "root";
            String password = "password";

            // Establish the JDBC connection
            connection = DriverManager.getConnection(url, username, password);

            out.println("<h2>JDBC connection successfully established!</h2>");
        } catch (ClassNotFoundException | SQLException e) {
            out.println("<h2>Error establishing JDBC connection: " + e.getMessage() + "</h2>");
        } finally {
            // Close JDBC connection
            if (connection != null) {
                try {
                    connection.close();
                    out.println("<h2>JDBC connection closed successfully!</h2>");
                } catch (SQLException e) {
                    out.println("<h2>Error closing JDBC connection: " + e.getMessage() + "</h2>");
                }
            }
        }

        out.println("</body></html>");
    }
}

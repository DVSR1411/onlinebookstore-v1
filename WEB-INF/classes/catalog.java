import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class catalog extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println("<html><body bgcolor=\"pink\">");
        String title = req.getParameter("title");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Sathwik123");
            Statement stmt = con.createStatement();
            String sqlStmt = "SELECT title, author, version, publisher, cost FROM books WHERE title = '" + title + "'";
            ResultSet rs = stmt.executeQuery(sqlStmt);
            int flag = 0;
            while (rs.next()) {
                pw.println("<div align=\"center\">");
                pw.println("TITLE: " + rs.getString(1) + "<br>");
                pw.println("AUTHOR: " + rs.getString(2) + "<br>");
                pw.println("VERSION: " + rs.getString(3) + "<br>");
                pw.println("PUBLISHER: " + rs.getString(4) + "<br>");
                pw.println("COST: " + rs.getString(5) + "<br>");
                pw.println("</div>");
                flag = 1;
            }
            if (flag == 0) {
                pw.println("Sorry, invalid title. Please try again.<br><br>");
                pw.println("<a href=\"catalog.html\">Press here to retry</a>");
            }
            pw.println("</body></html>");
        } catch (Exception e) {
            resp.sendError(500, e.toString());
        }
    }
}
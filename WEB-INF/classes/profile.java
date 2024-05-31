import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class profile extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println("<html><body bgcolor=\"pink\">");
        String id = req.getParameter("id");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Sathwik123");
            Statement stmt = con.createStatement();
            String sqlStmt = "SELECT * FROM login WHERE id='" + id + "'";
            ResultSet rs = stmt.executeQuery(sqlStmt);
            int flag = 0;
            pw.println("<br><br><br>");
            while (rs.next()) {
                pw.println("<div align=\"center\">");
                pw.println("NAME: " + rs.getString(1) + "<br>");
                pw.println("ADDRESS: " + rs.getString(2) + "<br>");
                pw.println("PHONENO: " + rs.getString(3) + "<br>");
                pw.println("</div>");
                flag = 1;
            }
            if (flag == 0) {
                pw.println("Sorry, invalid ID. Please try again.<br><br>");
                pw.println("<a href=\"profile.html\">Press here to retry</a>");
            }
            pw.println("</body></html>");
        } catch (Exception e) {
            resp.sendError(500, e.toString());
        }
    }
}
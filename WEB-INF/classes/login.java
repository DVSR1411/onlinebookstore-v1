import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class login extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println("<html><body bgcolor=\"pink\">");
        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Sathwik123");
            Statement stmt = con.createStatement();
            String sqlStmt = "SELECT id, pwd FROM login";
            ResultSet rs = stmt.executeQuery(sqlStmt);
            int flag = 0;
            while (rs.next()) {
                if (id.equals(rs.getString(1)) && pwd.equals(rs.getString(2))) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                pw.println("Sorry, invalid ID. Please try again.<br><br>");
                pw.println("<a href=\"login.html\">Click here to retry login</a>");
            } else {
                pw.println("Valid login ID<br><br>");
                pw.println("<h3><ul>");
                pw.println("<li><a href=\"profile.html\"><font color=\"black\">User Profile</font></a></li><br><br>");
                pw.println("<li><a href=\"catalog.html\"><font color=\"black\">Books Catalog</font></a></li><br><br>");
                pw.println("<li><a href=\"order.html\"><font color=\"black\">Order Confirmation</font></a></li><br><br>");
            }
            pw.println("</body></html>");
        } catch (Exception e) {
            resp.sendError(500, e.toString());
        }
    }
}

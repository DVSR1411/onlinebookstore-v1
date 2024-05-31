import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class order extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println("<html><body bgcolor=\"pink\">");
        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");
        String title = req.getParameter("title");
        String count1 = req.getParameter("no");
        String date = req.getParameter("date");
        String cno = req.getParameter("cno");
        int count = Integer.parseInt(count1);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Sathwik123");
            Statement stmt = con.createStatement();
            String sqlstmt = "SELECT id,pwd FROM login";
            ResultSet rs = stmt.executeQuery(sqlstmt);
            int flag = 0;
            while (rs.next()) {
                if (id.equals(rs.getString(1)) && pwd.equals(rs.getString(2))) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                pw.println("SORRY INVALID ID TRY AGAIN ID<br><br>");
                pw.println("<a href=\"order.html\">Press HERE to RETRY</a>");
            } else {
                Statement stmt2 = con.createStatement();
                String s = "SELECT cost FROM book WHERE title='" + title + "'";
                ResultSet rs1 = stmt2.executeQuery(s);
                int flag1 = 0;
                while (rs1.next()) {
                    flag1 = 1;
                    int x = Integer.parseInt(rs1.getString(1));
                    int amount = count * x;
                    pw.println("AMOUNT: " + amount + "<br><br><br><br>");
                    Statement stmt1 = con.createStatement();
                    stmt1.executeUpdate("INSERT INTO details VALUES('" + id + "','" + title + "'," + amount + ",'" + cno + "')");
                    pw.println("YOUR ORDER has been taken<br>");
                }
                if (flag1 == 0) {
                    pw.println("SORRY INVALID TITLE TRY AGAIN<br><br>");
                    pw.println("<a href=\"order.html\">Press HERE to RETRY</a>");
                }
            }
            pw.println("</body></html>");
            con.close();
        } catch (Exception e) {
            resp.sendError(500, e.toString());
        }
    }
}
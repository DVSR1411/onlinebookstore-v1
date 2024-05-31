import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class reg extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println("<html><body bgcolor=\"pink\">");
        String name = req.getParameter("name");
        String addr = req.getParameter("addr");
        String phno = req.getParameter("phno");
        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");
        int no = Integer.parseInt(phno);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Sathwik123");
            Statement stmt = con.createStatement();
            String sqlStmt = "SELECT id,password FROM login";
            ResultSet rs = stmt.executeQuery(sqlStmt);
            int flag = 0;
            while (rs.next()) {
                if (id.equals(rs.getString(1)) && pwd.equals(rs.getString(2))) {
                    flag = 1;
                }
            }
            if (flag == 1) {
                pw.println("Sorry, invalid ID already exists. Please try again with a new ID.<br><br>");
                pw.println("<a href=\"reg.html\">Click here to retry registration</a>");
            } else {
                Statement stmt1 = con.createStatement();
                stmt1.executeUpdate("INSERT INTO login VALUES('" + name + "','" + addr + "'," + no + ",'" + id + "','" + pwd + "')");
                pw.println("Your details are entered successfully.<br><br>");
                pw.println("<a href=\"login.html\">Click here to login</a>");
            }
            pw.println("</body></html>");
            con.close();
        } catch (Exception e) {
            resp.sendError(500, e.toString());
        }
    }
}

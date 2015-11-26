package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowDateServlet
 */
@WebServlet(name = "ShowDate", urlPatterns = { "/ShowDate" })
public class ShowDateServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		String url = "jdbc:postgresql://localhost/sample";
		Properties props = new Properties();
		props.setProperty("user","postgres");
		props.setProperty("password","root");
//		props.setProperty("ssl","true");
		try {
			Connection conn = DriverManager.getConnection(url, props);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM showfile");
			while (rs.next()) {
			    System.out.print("Column 1 returned ");
			    System.out.println(rs.getString(1));
			}
			rs.close();
			st.close();

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("");
		out.println("");
		out.println(new java.util.Date());
		out.println("");
		out.println("");
	}
}
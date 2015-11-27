package pack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowRegisteredFileServlet
 */
@WebServlet(name = "ShowRegisteredFile", urlPatterns = { "/ShowRegisteredFile" })
public class ShowRegisteredFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowRegisteredFileServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("Windows-31J");
		String filename = request.getParameter("registeredFile");
		String err =" ";
		String contents = null;

		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost/sample";
			Properties props = new Properties();
			props.setProperty("user","postgres");
			props.setProperty("password","root");
			Connection conn = DriverManager.getConnection(url, props);
			String sql = "select file_contents from showfile where file_name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, filename);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
			    contents = rs.getString("file_contents");
			}
			rs.close();
			pstmt.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			err = "DBÇ…ê⁄ë±Ç≈Ç´Ç‹ÇπÇÒ";
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("contents", contents);
		RequestDispatcher rd = request.getRequestDispatcher("./ShowRegisteredFile.jsp");
		rd.forward(request, response);
	}
}

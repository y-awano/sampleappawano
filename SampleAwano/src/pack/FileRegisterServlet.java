package pack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FileRegisterServlet
 */
@WebServlet(name = "FileRegister", urlPatterns = { "/FileRegister" })
public class FileRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("Windows-31J");
		String filename = request.getParameter("registerFile");
		LinkedList<ConnectBean> list = new LinkedList<ConnectBean>();
		String line = "tes";
		String err =" ";
		StringBuilder val = new StringBuilder("");


		try{
			BufferedReader reader = new BufferedReader(new FileReader(filename));
//			test.toString();

			while((line = reader.readLine()) != null){
				ConnectBean cbean = new ConnectBean();
				cbean.setFile(line);
				val.append(line);
				val.append("\n");
				list.add(cbean);
			}
			reader.close();
			err = "保存に成功しました";
		}catch(FileNotFoundException e){
			e.printStackTrace();
			err = "ファイルが見つかりません";
			System.out.println(err);
		}catch(IOException e){
			System.out.println(e);
			err = "エラーです";
		}

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			err = "DBに接続できません";
		}


		String url = "jdbc:postgresql://localhost/sample";
		Properties props = new Properties();
		props.setProperty("user","postgres");
		props.setProperty("password","root");

		try {
			Connection conn = DriverManager.getConnection(url, props);
			String sql = "insert into showfile(file_name, file_contents) values(?,?);";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, filename);
			pstmt.setString(2, val.toString());
			pstmt.executeUpdate();

			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
			    System.out.print("Column 1 returned ");
			    System.out.println(rs.getString(1));
			}
			rs.close();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println(val.toString());
		request.setAttribute("error", err);
		RequestDispatcher rd = request.getRequestDispatcher("./FileRegistered.jsp");
		rd.forward(request, response);

	}

}

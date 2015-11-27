package pack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ShowFileServlet
 */
@WebServlet(name = "ShowFile", urlPatterns = { "/ShowFile" })
public class ShowFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowFileServlet() {
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
		String filename = request.getParameter("showFile");
		LinkedList<ConnectBean> list = new LinkedList<ConnectBean>();
		String line = "tes";

		try{
			BufferedReader reader = new BufferedReader(new FileReader(filename));

			while((line = reader.readLine()) != null){
				ConnectBean cbean = new ConnectBean();
				cbean.setFile(line);
				list.add(cbean);
			}
			reader.close();

		}catch(FileNotFoundException e){
			ConnectBean cbean = new ConnectBean();
			e.printStackTrace();
			line = filename + "Ç™å©Ç¬Ç©ÇËÇ‹ÇπÇÒÅB";
			cbean.setFile(line);
			list.add(cbean);
		}catch(IOException e){
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		session.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("./ShowFile2.jsp");
		rd.forward(request, response);
	}
}

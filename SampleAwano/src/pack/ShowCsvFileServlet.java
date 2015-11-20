package pack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ShowCsvFileServlet
 */
@WebServlet(name = "ShowCsvFile", urlPatterns = { "/ShowCsvFile" })
public class ShowCsvFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCsvFileServlet() {
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("Windows-31J");
		String csvfilename = request.getParameter("showCsvFile");
		LinkedList<ConnectBean> list = new LinkedList<ConnectBean>();
		String line = "tes";
		File filename = new File(csvfilename);

		try{
			FileInputStream fis = new FileInputStream(filename);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);


			while((line = br.readLine()) != null){
				String[] cols = line.split(",");
				ConnectBean cbean = new ConnectBean();
				cbean.setId(cols[0]);
				cbean.setName(cols[1]);
				cbean.setNumber(cols[2]);
				list.add(cbean);
				System.out.println(cols[0]);
			}
			br.close();
		}catch(FileNotFoundException e){
			ConnectBean cbean = new ConnectBean();
			line = csvfilename + "(csvファイル)が見つかりません。";
			cbean.setFile(line);
			list.add(cbean);
			System.out.println(csvfilename + "(csvファイル)が見つかりません。");
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println(e);
		}catch(IOException e){
			System.out.println(e);
		}
		System.out.println(list);
		HttpSession session = request.getSession();
		session.setAttribute("list", list);
		 RequestDispatcher rd = request.getRequestDispatcher("./ShowFile3.jsp");
		  rd.forward(request, response);
	}

}

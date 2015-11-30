package pack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
 * 読み込んだcsvファイルを整形して表示するクラスです。
 * @author USER0223 awano
 */
@WebServlet(name = "ShowCsvFile", urlPatterns = { "/ShowCsvFile" })
public class ShowCsvFileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCsvFileServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * 読み込んだcsvファイルを整形して表示するメソッドです。
     * splitを使用して『,』を省いたcsvファイルの内容を表示します。
     * @param filename 入力されたcsvファイルのパスが入っています。(showCsvFile)
     * @return list 読み込んだcsvファイルの内容を整形した結果が入っているリストです。
     * @throws ServletException 実行時に起こり得る例外
     * @throws IOException ファイル入出力時に起こり得る例外
     * @throws FileNotFoundException 指定されたファイルが見つからなかった時に起こる例外
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("Windows-31J");
        String csvfilename = request.getParameter("showCsvFile");
        LinkedList<ConnectBean> list = new LinkedList<ConnectBean>();
        String line = "tes";
        File filename = new File(csvfilename);

        try {
            FileInputStream fis = new FileInputStream(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            while ((line = br.readLine()) != null) {
                String[] cols = line.split(",");
                ConnectBean cbean = new ConnectBean();
                cbean.setId(cols[0]);
                cbean.setName(cols[1]);
                cbean.setNumber(cols[2]);
                list.add(cbean);
            }
            br.close();

        } catch(FileNotFoundException e) {
            e.printStackTrace();
            ConnectBean cbean = new ConnectBean();
            line = csvfilename + "(csvファイル)が見つかりません。";
            cbean.setFile(line);
            list.add(cbean);
        } catch(ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            ConnectBean cbean = new ConnectBean();
            line = csvfilename + "3項目のcsvファイルを指定してください。";
            cbean.setFile(line);
            list.add(cbean);
        } catch(IOException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        session.setAttribute("list", list);
        RequestDispatcher rd = request.getRequestDispatcher("./ShowFile3.jsp");
        rd.forward(request, response);
    }
}

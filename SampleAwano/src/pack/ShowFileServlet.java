package pack;

import static pack.Const.*;
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
 * 読み込んだファイルを表示するクラスです。
 * @author USER0223 AWANO
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
     * 読み込んだファイルを表示するメソッドです。
     * @param request　リクエスト情報
     * @param response レスポンス情報
     * @throws ServletException 実行時に起こり得る例外
     * @throws IOException ファイル入出力時に起こり得る例外
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("Windows-31J");
        //入力フォームの値をセット
        String fileName = request.getParameter("showFile");
        //ファイルを読み込んだ結果
        String line = null;
        //ファイルを読み込んだ結果を格納するリストの作成
        LinkedList<ConnectBean> list = new LinkedList<ConnectBean>();

        try {
            //ファイルの読み込み
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            //読み込んだ内容をセット
            while ((line = reader.readLine()) != null) {
                ConnectBean cbean = new ConnectBean();
                cbean.setFile(line);
                list.add(cbean);
            }

            reader.close();

        } catch(FileNotFoundException e) {
            ConnectBean cbean = new ConnectBean();
            e.printStackTrace();
            line = fileName + FILE_NOT_FOUND_ERROR_MESSAGE;
            cbean.setFile(line);
            list.add(cbean);
        } catch(IOException e) {
            e.printStackTrace();
            line = IO_ERROR_MESSAGE;
        }

        //値を渡してJSP画面に遷移
        HttpSession session = request.getSession();
        session.setAttribute("list", list);
        RequestDispatcher rd = request.getRequestDispatcher("./ShowFile2.jsp");
        rd.forward(request, response);
    }
}

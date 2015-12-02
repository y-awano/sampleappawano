package pack;

import static pack.Const.*;
import java.io.BufferedReader;
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
 * 読み込んだCSVファイルを整形して表示するクラスです。
 * @author USER0223 AWANO
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
     * 読み込んだCSVファイルを整形して表示するメソッドです。
     * 『,』を省いたCSVファイルの内容を表示します。
     * @param request リクエスト情報
     * @param response　レスポンス情報
     * @throws ServletException 実行時に起こり得る例外
     * @throws IOException ファイル入出力時に起こり得る例外
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("Windows-31J");
        //入力フォームの値をセット
        String csvFileName = request.getParameter("showCsvFile");
        //ファイルを読み込んだ結果
        String line = null;
        ///ファイルを読み込んだ結果を格納するリストの作成
        LinkedList<ConnectBean> list = new LinkedList<ConnectBean>();

        try {
            //ファイルの読み込み
            FileInputStream fis = new FileInputStream(csvFileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            //『,』を省いた値をセット
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
            line = csvFileName + FILE_NOT_FOUND_ERROR_MESSAGE;
            cbean.setFile(line);
            list.add(cbean);
        } catch(ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            ConnectBean cbean = new ConnectBean();
            line = csvFileName + ARRAY_INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE;
            cbean.setFile(line);
            list.add(cbean);
        } catch(IOException e) {
            e.printStackTrace();
        }

        //値を渡してJSP画面に遷移
        HttpSession session = request.getSession();
        session.setAttribute("list", list);
        RequestDispatcher rd = request.getRequestDispatcher("./ShowFile3.jsp");
        rd.forward(request, response);
    }
}

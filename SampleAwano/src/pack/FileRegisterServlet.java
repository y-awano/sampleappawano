package pack;

import static pack.Const.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 読み込んだファイル内容をDBに登録するクラスです。
 * @author USER0223 AWANO
 */
@WebServlet(name = "FileRegister", urlPatterns = { "/FileRegister" })
public class FileRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileRegisterServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * 読み込んだファイル内容をDBに登録するメソッドです。
     * @param request リクエスト情報
     * @param response レスポンス情報
     * @throws ServletException 実行時に起こり得る例外
     * @throws IOException ファイル入出力時に起こり得る例外
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("Windows-31J");
        //入力フォームの値をセット
        String fileName = request.getParameter("registerFile");
        //ファイルを読み込んだ結果
        String line = null;
        //遷移後の画面に表示する文言
        String msg = null;
        //DBに登録するための整形
        StringBuilder val = new StringBuilder("");

        try {
            //ファイルの読み込みと値のセット
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while ((line = reader.readLine()) != null) {
                val.append(line);
                val.append("\n");
            }
            reader.close();

            //データベースに接続
            Connection conn = DBConnection.getConnection();

            //DBに値を登録
            String sql = INSERT_FILE_SQL;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, fileName);
            pstmt.setString(2, val.toString());
            pstmt.executeUpdate();
            msg = INSERT_SUCCESS_MESSAGE;

            pstmt.close();
            conn.close();

        } catch(ClassNotFoundException e) {
            e.printStackTrace();
            msg = CLASS_NOT_FOUND_ERROR_MESSAGE;
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            msg = FILE_NOT_FOUND_ERROR_MESSAGE;
        } catch(IOException e) {
            e.printStackTrace();
            msg = IO_ERROR_MESSAGE;
        } catch(SQLException e) {
            e.printStackTrace();
            msg = SQL_ERROR_MESSAGE;
        } catch(Exception e) {
            e.printStackTrace();
        }

        //値を渡してJSP画面に遷移
        request.setAttribute("message", msg);
        RequestDispatcher rd = request.getRequestDispatcher("./FileRegistered.jsp");
        rd.forward(request, response);
    }
}

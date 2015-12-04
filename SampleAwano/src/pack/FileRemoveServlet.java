package pack;

import static pack.Const.*;
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
 * DBに登録した内容を論理削除するクラスです。
 * @author USER0223 AWANO
 */
@WebServlet(name = "FileRemove", urlPatterns = { "/FileRemove" })
public class FileRemoveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * DBに登録した内容を論理削除するメソッドです。
     * @param request リクエスト情報
     * @param response レスポンス情報
     * @throws ServletException 実行時に起こり得る例外
     * @throws IOException ファイル入出力時に起こり得る例外
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("Windows-31J");
        //入力フォームの値をセット
        String fileName = request.getParameter("removeFile");
        //遷移後の画面に表示する値
        String contents = null;

        try {
            //データベースに接続
            Connection conn = DBConnection.getConnection();

            //DBの値を論理削除
            String sql = ROGICAL_DELETE_SQL;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, fileName);
            pstmt.executeUpdate();
            contents = fileName + DELETE_SUCCESS_MESSAGE;

            pstmt.close();
            conn.close();

        } catch(ClassNotFoundException e) {
            e.printStackTrace();
            contents = CLASS_NOT_FOUND_ERROR_MESSAGE;
        } catch(SQLException e) {
            e.printStackTrace();
            contents = SQL_ERROR_MESSAGE;
        } catch(Exception e) {
            e.printStackTrace();
        }

        //値を渡してJSP画面に遷移
        request.setAttribute("contents", contents);
        RequestDispatcher rd = request.getRequestDispatcher("./ShowRegisteredFile.jsp");
        rd.forward(request, response);
    }
}


package pack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
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
     * @see HttpServlet#HttpServlet()
     */
    public FileRemoveServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * DBに登録した内容を論理削除するメソッドです。
     * @param request　DBから論理削除するファイルの絶対パス
     * @param response
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
            //DBコネクション処理
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/sample";

            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "root");
            Connection conn = DriverManager.getConnection(url, props);

            //DBの値を論理削除
            String sql = "update showfile set remove_flag = false where file_name = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, fileName);
            pstmt.executeUpdate();
            contents = fileName + "　を削除しました";

            pstmt.close();
            conn.close();

        } catch(ClassNotFoundException e) {
            e.printStackTrace();
            contents = "DBに接続できません";
        } catch(SQLException e) {
            e.printStackTrace();
            contents = "miss";
        }

        //値を渡してJSP画面に遷移
        request.setAttribute("contents", contents);
        RequestDispatcher rd = request.getRequestDispatcher("./ShowRegisteredFile.jsp");
        rd.forward(request, response);
    }
}


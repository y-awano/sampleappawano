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
 * DBに登録した内容を論理削除するクラスです。
 * @author USER0223 awano
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
     * @param filename 選択したファイルのパスが入っています。(registeredFile)
     * @return
     * @throws ServletException 実行時に起こり得る例外
     * @throws IOException ファイル入出力時に起こり得る例外
     * @throws ClassNotFoundException クラスが見つからなかった時に起こる例外
     * @throws SQLException SQL実行時に起こり得る例外
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("Windows-31J");
        String filename = request.getParameter("removeFile");
        String err =" ";
        String contents = null;


        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/sample";
            Properties props = new Properties();
            props.setProperty("user","postgres");
            props.setProperty("password","root");
            Connection conn = DriverManager.getConnection(url, props);
            String sql = "update showfile set remove_flag = false where file_name = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, filename);
            ResultSet rs = pstmt.executeQuery();
            contents = filename + "　を削除しました";
            rs.close();
            pstmt.close();
            conn.close();

        } catch(ClassNotFoundException e) {
            e.printStackTrace();
            contents = "DBに接続できません";
        } catch(SQLException e) {
            e.printStackTrace();
            contents = "miss";
        }

        request.setAttribute("contents", contents);
        RequestDispatcher rd = request.getRequestDispatcher("./ShowRegisteredFile.jsp");
        rd.forward(request, response);
    }
}


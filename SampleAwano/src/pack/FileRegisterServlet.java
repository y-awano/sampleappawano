package pack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
 * 読み込んだファイル内容をDBに登録するクラスです。
 * @author USER0223 awano
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
     * @param filename 入力されたファイルのパスが入っています。(registerFile)
     * @return list 読み込んだファイルの内容が入っているリストです。
     * @throws ServletException 実行時に起こり得る例外
     * @throws IOException ファイル入出力時に起こり得る例外
     * @throws ClassNotFoundException クラスが見つからなかった時に起こる例外
     * @throws FileNotFoundException 指定されたファイルが見つからなかった時に起こる例外
     * @throws SQLException SQL実行時に起こり得る例外
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("Windows-31J");
        String filename = request.getParameter("registerFile");
        String line = "tes";
        String err =" ";
        StringBuilder val = new StringBuilder("");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            while ((line = reader.readLine()) != null) {
                val.append(line);
                val.append("\n");
            }
            reader.close();
            err = "保存に成功しました";

            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/sample";
            Properties props = new Properties();
            props.setProperty("user","postgres");
            props.setProperty("password","root");
            Connection conn = DriverManager.getConnection(url, props);
            String sql = "insert into showfile(file_name, file_contents) values(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, filename);
            pstmt.setString(2, val.toString());
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();

            } catch(ClassNotFoundException e) {
                e.printStackTrace();
                err = "DBに接続できません";
            } catch(FileNotFoundException e) {
                e.printStackTrace();
                err = "ファイルが見つかりません";
            } catch(IOException e) {
                e.printStackTrace();
                err = "ファイル入出力エラーです";
            } catch(SQLException e) {
                e.printStackTrace();
                err = "SQLエラーです";
            }

        request.setAttribute("error", err);
        RequestDispatcher rd = request.getRequestDispatcher("./FileRegistered.jsp");
        rd.forward(request, response);
    }
}

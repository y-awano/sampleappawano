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
 * DB�ɓo�^�������e��_���폜����N���X�ł��B
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
     * DB�ɓo�^�������e��_���폜���郁�\�b�h�ł��B
     * @param filename �I�������t�@�C���̃p�X�������Ă��܂��B(registeredFile)
     * @return
     * @throws ServletException ���s���ɋN���蓾���O
     * @throws IOException �t�@�C�����o�͎��ɋN���蓾���O
     * @throws ClassNotFoundException �N���X��������Ȃ��������ɋN�����O
     * @throws SQLException SQL���s���ɋN���蓾���O
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
            contents = filename + "�@���폜���܂���";
            rs.close();
            pstmt.close();
            conn.close();

        } catch(ClassNotFoundException e) {
            e.printStackTrace();
            contents = "DB�ɐڑ��ł��܂���";
        } catch(SQLException e) {
            e.printStackTrace();
            contents = "miss";
        }

        request.setAttribute("contents", contents);
        RequestDispatcher rd = request.getRequestDispatcher("./ShowRegisteredFile.jsp");
        rd.forward(request, response);
    }
}


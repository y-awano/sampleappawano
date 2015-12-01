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
 * DB�ɓo�^�����t�@�C���̓��e��\������N���X�ł��B
 * @author USER0223 AWANO
 */
@WebServlet(name = "ShowRegisteredFile", urlPatterns = { "/ShowRegisteredFile" })
public class ShowRegisteredFileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowRegisteredFileServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * DB�ɓo�^�����t�@�C���̓��e��\������N���X�ł��B
     * @param request ���N�G�X�g���
     * @param response ���X�|���X���
     * @throws ServletException ���s���ɋN���蓾���O
     * @throws IOException �t�@�C�����o�͎��ɋN���蓾���O
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("Windows-31J");
        //���̓t�H�[���̒l���Z�b�g
        String fileName = request.getParameter("registeredFile");
        //�J�ڌ�̉�ʂɕ\������l
        String contents = null;

        try {
            //DB�R�l�N�V��������
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/sample";

            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "root");
            Connection conn = DriverManager.getConnection(url, props);

            //DB�̒l���Q��
            String sql = "select file_contents from showfile where file_name = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, fileName);
            ResultSet rs = pstmt.executeQuery();

            //�l�̃Z�b�g
            while (rs.next()) {
              contents = rs.getString("file_contents");
            }

            rs.close();
            pstmt.close();
            conn.close();

        } catch(ClassNotFoundException e) {
            e.printStackTrace();
            contents = "DB�ɐڑ��ł��܂���";
        } catch(SQLException e) {
            e.printStackTrace();
            contents = "error";
        }

        //�l��n����JSP��ʂɑJ��
        request.setAttribute("contents", contents);
        RequestDispatcher rd = request.getRequestDispatcher("./ShowRegisteredFile.jsp");
        rd.forward(request, response);
    }
}

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
 * DB�ɓo�^�������e��_���폜����N���X�ł��B
 * @author USER0223 AWANO
 */
@WebServlet(name = "FileRemove", urlPatterns = { "/FileRemove" })
public class FileRemoveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * DB�ɓo�^�������e��_���폜���郁�\�b�h�ł��B
     * @param request ���N�G�X�g���
     * @param response ���X�|���X���
     * @throws ServletException ���s���ɋN���蓾���O
     * @throws IOException �t�@�C�����o�͎��ɋN���蓾���O
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("Windows-31J");
        //���̓t�H�[���̒l���Z�b�g
        String fileName = request.getParameter("removeFile");
        //�J�ڌ�̉�ʂɕ\������l
        String contents = null;

        try {
            //�f�[�^�x�[�X�ɐڑ�
            Connection conn = DBConnection.getConnection();

            //DB�̒l��_���폜
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

        //�l��n����JSP��ʂɑJ��
        request.setAttribute("contents", contents);
        RequestDispatcher rd = request.getRequestDispatcher("./ShowRegisteredFile.jsp");
        rd.forward(request, response);
    }
}


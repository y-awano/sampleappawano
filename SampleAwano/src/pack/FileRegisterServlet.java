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
 * �ǂݍ��񂾃t�@�C�����e��DB�ɓo�^����N���X�ł��B
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
     * �ǂݍ��񂾃t�@�C�����e��DB�ɓo�^���郁�\�b�h�ł��B
     * @param request ���N�G�X�g���
     * @param response ���X�|���X���
     * @throws ServletException ���s���ɋN���蓾���O
     * @throws IOException �t�@�C�����o�͎��ɋN���蓾���O
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("Windows-31J");
        //���̓t�H�[���̒l���Z�b�g
        String fileName = request.getParameter("registerFile");
        //�t�@�C����ǂݍ��񂾌���
        String line = null;
        //�J�ڌ�̉�ʂɕ\�����镶��
        String msg = null;
        //DB�ɓo�^���邽�߂̐��`
        StringBuilder val = new StringBuilder("");

        try {
            //�t�@�C���̓ǂݍ��݂ƒl�̃Z�b�g
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while ((line = reader.readLine()) != null) {
                val.append(line);
                val.append("\n");
            }
            reader.close();

            //�f�[�^�x�[�X�ɐڑ�
            Connection conn = DBConnection.getConnection();

            //DB�ɒl��o�^
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

        //�l��n����JSP��ʂɑJ��
        request.setAttribute("message", msg);
        RequestDispatcher rd = request.getRequestDispatcher("./FileRegistered.jsp");
        rd.forward(request, response);
    }
}

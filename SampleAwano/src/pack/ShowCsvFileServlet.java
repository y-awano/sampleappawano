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
 * �ǂݍ���CSV�t�@�C���𐮌`���ĕ\������N���X�ł��B
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
     * �ǂݍ���CSV�t�@�C���𐮌`���ĕ\�����郁�\�b�h�ł��B
     * �w,�x���Ȃ���CSV�t�@�C���̓��e��\�����܂��B
     * @param request ���N�G�X�g���
     * @param response�@���X�|���X���
     * @throws ServletException ���s���ɋN���蓾���O
     * @throws IOException �t�@�C�����o�͎��ɋN���蓾���O
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("Windows-31J");
        //���̓t�H�[���̒l���Z�b�g
        String csvFileName = request.getParameter("showCsvFile");
        //�t�@�C����ǂݍ��񂾌���
        String line = null;
        ///�t�@�C����ǂݍ��񂾌��ʂ��i�[���郊�X�g�̍쐬
        LinkedList<ConnectBean> list = new LinkedList<ConnectBean>();

        try {
            //�t�@�C���̓ǂݍ���
            FileInputStream fis = new FileInputStream(csvFileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            //�w,�x���Ȃ����l���Z�b�g
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

        //�l��n����JSP��ʂɑJ��
        HttpSession session = request.getSession();
        session.setAttribute("list", list);
        RequestDispatcher rd = request.getRequestDispatcher("./ShowFile3.jsp");
        rd.forward(request, response);
    }
}

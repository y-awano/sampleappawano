package pack;

import java.io.BufferedReader;
import java.io.File;
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
 * �ǂݍ���csv�t�@�C���𐮌`���ĕ\������N���X�ł��B
 * @author USER0223 awano
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
     * �ǂݍ���csv�t�@�C���𐮌`���ĕ\�����郁�\�b�h�ł��B
     * split���g�p���āw,�x���Ȃ���csv�t�@�C���̓��e��\�����܂��B
     * @param filename ���͂��ꂽcsv�t�@�C���̃p�X�������Ă��܂��B(showCsvFile)
     * @return list �ǂݍ���csv�t�@�C���̓��e�𐮌`�������ʂ������Ă��郊�X�g�ł��B
     * @throws ServletException ���s���ɋN���蓾���O
     * @throws IOException �t�@�C�����o�͎��ɋN���蓾���O
     * @throws FileNotFoundException �w�肳�ꂽ�t�@�C����������Ȃ��������ɋN�����O
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("Windows-31J");
        String csvfilename = request.getParameter("showCsvFile");
        LinkedList<ConnectBean> list = new LinkedList<ConnectBean>();
        String line = "tes";
        File filename = new File(csvfilename);

        try {
            FileInputStream fis = new FileInputStream(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

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
            line = csvfilename + "(csv�t�@�C��)��������܂���B";
            cbean.setFile(line);
            list.add(cbean);
        } catch(ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            ConnectBean cbean = new ConnectBean();
            line = csvfilename + "3���ڂ�csv�t�@�C�����w�肵�Ă��������B";
            cbean.setFile(line);
            list.add(cbean);
        } catch(IOException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        session.setAttribute("list", list);
        RequestDispatcher rd = request.getRequestDispatcher("./ShowFile3.jsp");
        rd.forward(request, response);
    }
}

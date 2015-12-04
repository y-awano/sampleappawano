<%@ page language="java" contentType="text/html; charset=Windows-31J"%>
<%@ page import="pack.DBConnection"%>
<%@ page import="static pack.Const.*"%>
<%@ page import="java.util.*"%>
<%@ page import="pack.*"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>���̓t�H�[��</title>
</head>
<body>

  <p>�t�@�C���̃p�X����͂��Ă�������</p>
  <form method="post" action="ShowFile">
    <p>
      �p�X: <input type="text" name="showFile" />
    </p>
    <p>
      <input type="submit" value="���s">
      <input type="reset" value="�L�����Z��">
    </p>
  </form>

  <p>CSV�t�@�C���̃p�X����͂��Ă�������</p>
  <form method="post" action="ShowCsvFile">
    <p>
      �p�X: <input type="text" name="showCsvFile" />
    </p>
    <p>
      <input type="submit" value="���s">
      <input type="reset" value="�L�����Z��">
    </p>
  </form>

  <p>DB�ɓo�^����t�@�C���̃p�X����͂��Ă�������</p>
  <form method="post" action="FileRegister">
    <p>
      �p�X: <input type="text" name="registerFile" />
    </p>
    <p>
      <input type="submit" value="���s">
      <input type="reset" value="�L�����Z��">
    </p>
  </form>

  <p>�\��������t�@�C����I�����Ă�������</p>
  <form method="post" action="ShowRegisteredFile">
    <select name="registeredFile">
      <%
        Connection conn = null;
        Statement st = null;
        try {
          //�f�[�^�x�[�X�ɐڑ�
          conn = DBConnection.getConnection();

          //�v���_�E���ɕ\������l�̎Q��
          st = conn.createStatement();
          String sql = SELECT_FILE_NAME_SQL;
          ResultSet rs = st.executeQuery(sql);

          // <option> �̌���sql�̌��ʂ��o��
          while (rs.next()) {
            String s0 = rs.getString("file_name");
            out.println("<option>" + s0);
          }

          rs.close();
          st.close();
          conn.close();

        } catch(ClassNotFoundException e) {
          e.printStackTrace();
        } catch(SQLException e) {
          e.printStackTrace();
        } finally {
          // �ی��p
          if (st != null && !st.isClosed()) {
            st.close();
          }
          if (conn != null && !conn.isClosed()) {
            conn.close();
          }
        }
      %>
    </select> <input type="submit" value="���s">
  </form>

  <p>�폜����t�@�C����I�����Ă�������</p>
  <form method="post" action="FileRemove">
    <select name="removeFile">
      <%
        try {
          //�f�[�^�x�[�X�ɐڑ�
          conn = DBConnection.getConnection();

          //�v���_�E���ɕ\������l�̎Q��
          st = conn.createStatement();
          String sql = SELECT_FILE_NAME_SQL;
          ResultSet rs = st.executeQuery(sql);

          // <option> �̌���sql�̌��ʂ��o��
          while (rs.next()) {
            String s1 = rs.getString("file_name");
            out.println("<option>" + s1);
          }

          rs.close();
          st.close();
          conn.close();

        } catch(ClassNotFoundException e) {
          e.printStackTrace();
        } catch(SQLException e) {
          e.printStackTrace();
        } finally {
          // �ی��p
          if (st != null && !st.isClosed()) {
            st.close();
          }
          if (conn != null && !conn.isClosed()) {
            conn.close();
          }
        }
      %>
    </select> <input type="submit" value="���s">
  </form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=Windows-31J"%>
<%@ page import="java.util.*"%>
<%@ page import="pack.*"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.sql.*"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>�p�X���̓t�H�[��</title>
</head>
<body>

<p>�t�@�C���̃p�X����͂��Ă�������:</p>
<form method="post" action="ShowFile">
<p>�p�X: <input type="text" name="showFile" /></p>
<p>
<input type="submit" value="���s" >
<input type="reset" value="�L�����Z��">
</p>
</form>

<p>CSV�t�@�C���̃p�X����͂��Ă�������:</p>
<form method="post" action="ShowCsvFile">
<p>�p�X: <input type="text" name="showCsvFile" /></p>
<p>
<input type="submit" value="���s" >
<input type="reset" value="�L�����Z��">
</p>
</form>

<p>DB�ɓo�^����t�@�C���̃p�X����͂��Ă�������:</p>
<form method="post" action="FileRegister">
<p>�p�X: <input type="text" name="registerFile" /></p>
<p>
<input type="submit" value="���s" >
<input type="reset" value="�L�����Z��">
</p>
</form>

<p>�\��������t�@�C����I�����Ă�������</p>
<form method="post" action="ShowRegisteredFile">
<select name="registeredFile">
 <%
	try {
		Class.forName("org.postgresql.Driver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}

	//DB�R�l�N�V������sql
 	String url = "jdbc:postgresql://localhost/sample";
	Properties props = new Properties();
	props.setProperty("user","postgres");
	props.setProperty("password","root");

	try {
		Connection conn = DriverManager.getConnection(url, props);
		String sql = "select file_name from showfile;";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
		       String s0 = rs.getString("file_name");
		    // <option> �̌���sql�̌��ʂ��o��
		       out.println("<option>" + s0);
		       System.out.println(s0);
		}
		rs.close();
		st.close();

	} catch (SQLException e) {
		e.printStackTrace();
	}
%>
</select>
<input type="submit" value="���s"><p>
</form>
</body>
</html>
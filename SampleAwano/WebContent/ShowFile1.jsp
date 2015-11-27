<%@ page language="java" contentType="text/html; charset=Windows-31J"%>
<%@ page import="java.util.*"%>
<%@ page import="pack.*"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>パス入力フォーム</title>
</head>
<body>

<p>ファイルのパスを入力してください:</p>
<form method="post" action="ShowFile">
<p>パス: <input type="text" name="showFile" /></p>
<p>
<input type="submit" value="実行" >
<input type="reset" value="キャンセル">
</p>
</form>

<p>CSVファイルのパスを入力してください:</p>
<form method="post" action="ShowCsvFile">
<p>パス: <input type="text" name="showCsvFile" /></p>
<p>
<input type="submit" value="実行" >
<input type="reset" value="キャンセル">
</p>
</form>

<p>DBに登録するファイルのパスを入力してください:</p>
<form method="post" action="FileRegister">
<p>パス: <input type="text" name="registerFile" /></p>
<p>
<input type="submit" value="実行" >
<input type="reset" value="キャンセル">
</p>
</form>

<p>表示させるファイルを選択してください</p>
<form method="post" action="ShowRegisteredFile">
<select name="registeredFile">
 <%
 Connection conn = null;
 Statement st = null;
	try {
		Class.forName("org.postgresql.Driver");
 		String url = "jdbc:postgresql://localhost/sample";
		Properties props = new Properties();
		props.setProperty("user","postgres");
		props.setProperty("password","root");
		conn = DriverManager.getConnection(url, props);
		st = conn.createStatement();
		String sql = "select file_name from showfile";
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
		       String s0 = rs.getString("file_name");
		    // <option> の後ろにsqlの結果を出力
		       out.println("<option>" + s0);
			   System.out.println(s0);
		}
		rs.close();
		st.close();
		conn.close();

	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		  // 保険用
		  if(st !=null && !st.isClosed()) {
		    st.close();
		  }
		  if(conn !=null && !conn.isClosed()) {
		    conn.close();
		  }
	}
%>
</select>
<input type="submit" value="実行"><p>
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=Windows-31J"%>
<%@ page import="java.util.*"%>
<%@ page import="pack.*"%>
<%
  request.setCharacterEncoding("Shift_JIS");
  LinkedList<ConnectBean> msgList = (LinkedList<ConnectBean>) session.getAttribute("list");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>ファイル表示画面</title>
</head>
<body>
  <table>
    <%
      for (ConnectBean temp : msgList) {
    %>
    <tr>
      <td>
        <%
          out.println(temp.getFile());
        %>
      </td>
    </tr>
    <%
      }
    %>
  </table>
  <a href="ShowFile1.jsp"> back </a>
</body>
</html>
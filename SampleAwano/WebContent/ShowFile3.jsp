<%@ page language="java" contentType="text/html; charset=Windows-31J"%>
<%@ page import="java.util.*" %>
<%@ page import="pack.*"%>
<%
request.setCharacterEncoding("Shift_JIS");
LinkedList<ConnectBean> msgList =  (LinkedList<ConnectBean>)session.getAttribute("list");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>CSVファイル表示画面</title>
</head>
<body>
<br>
<table border="1" width="50%">
<tr>
<td>id</td>
<td>name</td>
<td>number</td>
</tr>
<%
for (ConnectBean temp: msgList) {
%>
  <tr>
    <td>
      <% out.println(temp.getId());%>
    </td>
    <td>
      <% out.println(temp.getName());%>
    </td>
    <td>
      <% out.println(temp.getNumber());%>
    </td>
  </tr>
<%
}
%>
</table>
</body>
</html>
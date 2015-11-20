<%@ page language="java" contentType="text/html; charset=Windows-31J"%>
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


</body>
</html>
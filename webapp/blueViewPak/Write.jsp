<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작성 화면</title>
</head>
<body>
		<form method="post" action="../albumBoard/write.do" enctype="multipart/form-data">
	<table>
		<tr>
			<td>제목:</td>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<td>파일:</td>
			<td><input type="file" name="file" multiple></td>
		</tr>
		<tr>
			<td ><input type="submit" value="등록"></td>
			<td><button type="reset">초기화</button></td>
			<td><button type="button" onclick="location.href='../albumBoard/list.do';">
						목록 바로가기
				</button>
			</td>
		</tr>
	</table>
</form>
</body>
</html>
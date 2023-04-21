<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>비밀번호 검증</h2>
	<form action="../albumBoard/pass.do" method="post" name ="writeForm">
		<input type="hidden" name="alnum"	value="${param.alnum}"/>
		<input type="hidden" name="mode" value="${param.mode}" />
		<table border="1" width="90%">
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="number" name="pass" style="width:100px" required/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">검증하기</button>
					<button type="reset">초기화</button>
					<button type="button" onclick="location.href='../albumBoard/list.do';">
						목록 돌아가기
					</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
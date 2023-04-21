<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>안쪽 게시판</title>
</head>
<body>
	<h2>게시판 상세보기</h2>
	<table border ="1" width="90%">
		<colgroup>
			<col width="15%"/><col width="35%"/>
			<col width="15%"/><col width="*"/>
		</colgroup>
		<tr>
			<td>번호</td><td>${dto.alnum}</td>
			<td>작성자</td><td>${dto.teacherId}</td>
		</tr>
		<tr>
			<td>작성일</td><td>${dto.writeDate}</td>
			<td>조회수</td><td>${dto.count}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td colspan="3" align="center">${dto.title}</td>
		</tr>
		<tr>
			<td>사진</td>
			<td><img src="<%=request.getContextPath()%>/Storage/${dto.nfile}" width="300px" height="300px"/></td>
		</tr>
		<tr>
			<td colspan ="4" align="center">
			<button type="button" onclick="location.href='../albumBoard/pass.do?mode=edit&alnum=${param.alnum}';">수정하기</button>
				<button type="button" onclick="location.href='../albumBoard/pass.do?mode=delete&alnum=${param.alnum}';">삭제하기</button>
				<button type="button" onclick="location.href='../albumBoard/list.do';">목록 바로가기</button>
			</td>
	</table>
</body>
</html>
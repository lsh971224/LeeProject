<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정 화면</title>
</head>
<body>
<h2>수정하기</h2>
<form action="../albumBoard/edit.do" method="post" name="writeForm" enctype="multipart/form-data">
<input type="hidden" name="alnum" value="${dto.alnum}"/>
<input type="hidden" name="preOfile" value="${dto.imgName}"/>
<input type="hidden" name="preNfile" value="${dto.nfile}"/>
<table border="1" width="90%">
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
			<td><img src="<%=request.getContextPath()%>/Storage/${dto.nfile}" width="100px" height="200px"/></td>
		</tr>
		<tr>
         <td>첨부 파일</td>
         <td>
            <input type="file" name="ofile"/>
         </td>
      </tr>
      <tr>
         <td colspan="2" align="center">
            <button type="submit" >작성완료</button>
            <button type="reset">초기화</button>
            <button type="button" onclick="location.href='../albumBoard/list.do';">목록바로가기</button>
         </td>
      </tr>
   </table>
  </form>
</body>
</html>
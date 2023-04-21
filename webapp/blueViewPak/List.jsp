<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>앨범 게시판</title>
<style>
  /* 헤더 스타일링 */
  .header {
    background-color: #f8f9fa;
    /* 배경색 지정 */
    padding: 20px;
    /* 패딩 지정 */
    display: flex;
    /* 플렉스 박스 사용 */
    justify-content: space-between;
    /* 헤더 내부 요소들 간격 조정 */
    align-items: center;
    /* 수직 가운데 정렬 추가 */
  }

  /* 제목 스타일링 */
  .header h1 {
    font-size: 24px;
    /* 폰트 사이즈 지정 */
    margin: 0;
    
  }
 .header h1 a{
    text-decoration: none;
    color: black;
  }
  /* 글쓰기 버튼 스타일링 */
  .header .btn-write {
    background-color: #007bff;
    /* 배경색 지정 */
    color: #fff;
    /* 글자색 지정 */
    padding: 8px 16px;
    /* 패딩 지정 */
    border: none;
    /* 테두리 없앰 */
    font-size: 16px;
    /* 폰트 사이즈 지정 */
    cursor: pointer;
    /* 커서 스타일 변경 */
  }

 table {
    border-collapse: collapse; /* 셀 간의 간격 없애기 */
    width: 100%; /* 테이블 전체 크기 100%로 지정 */
    margin-bottom: 20px; /* 하단 여백 추가 */
  }

  th {
    background-color: #007bff; /* 제목 열 배경색 지정 */
    color: #fff; /* 제목 열 글자색 지정 */
    text-align: center; /* 제목 열 가운데 정렬 */
    padding: 12px; /* 제목 열 패딩 지정 */
    font-size: 16px; /* 제목 열 폰트 사이즈 지정 */
  }

  td {
    border: 1px solid #ddd; /* 테두리 지정 */
    padding: 8px; /* 패딩 지정 */
    font-size: 14px; /* 폰트 사이즈 지정 */
    text-align: center; /* 셀 가운데 정렬 */
  }

 

 

  footer h3 {
    font-size: 15px;
    /* 폰트 사이즈 지정 */
    margin: 0;
    text-align: center;
    /* 가운데 정렬 추가 */
  }
</style>	
</head>
<body>
<!-- 헤더 부분 -->
<div class="header">
  <h1><a href="../albumBoard/list.do">앨범 게시판</a></h1>
  <button class="btn-write" onclick="location.href='../albumBoard/write.do';">글쓰기</button>
</div>
 <!-- 검색 -->
   <form method="get">
      <table border="1" width="90%">
         <tr>
            <td align="center">
               <select name="searchType">
                  <option value="title" 
                     <c:if test="${map.searchType=='title'}">selected</c:if>>제목
                  </option>
               </select>
               <input type="search" name="searchStr" value="${map.searchStr}"/>
               <input type="submit" value="검색"/>
               
            </td>
         </tr>      
      </table>   
   </form>
 
 <table>

<c:choose>
    <c:when test="${empty boardList}">
        <tr>
            <td colspan="6" align="center">등록된 게시물이 없습니다.</td>
        </tr>
    </c:when>
    <c:otherwise>
         <c:forEach items="${boardList}" var="list" varStatus="stat">
<c:if test="${stat.index % 3 == 0}">
<tr>
</c:if>
<td align="center">
<table>
<tr>
<th>작성 날짜</th>
<th>글 번호</th>
<th>작성자</th>
<th>조회수</th>
</tr>
<tr>
<td>${list.writeDate}</td>
<td>${list.alnum}</td>
<td>${list.teacherId}</td>
<td>${list.count}</td>
</tr>
<tr>
<td colspan="4"> <a href="../albumBoard/view.do?alnum=${list.alnum}">${list.title} </a></td>
</tr>
<tr>
<td colspan="4"> <img src="<%=request.getContextPath()%>/Storage/${list.nfile}" width="100px" height="200px"/></td>
</tr>
</table>
</td>
<c:if test="${stat.index % 3 == 2 or stat.last}">
</tr>
</c:if>
</c:forEach>
    </c:otherwise>
</c:choose>
<footer>
  <table border="1" width="90%">
      <tr align="center">
         <td>
         ${map.pagingStr}
         </td>
      </tr>
   </table>
</footer>
</body>
</html>
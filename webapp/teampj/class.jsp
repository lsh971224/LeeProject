<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>원생 관리 프로그램</title>
<style>
	.container{
		display: flex;
		min-height: 100vh; /* 최소 높이를 100vh로 설정 */
		flex-wrap: nowrap; /* 줄 바꿈 방지 */
	}

	body {
		font-family: Arial, sans-serif;
		margin: 0;
		padding: 0;
		height: 100%; /* 높이를 100%로 설정 */
	}

	html {
		height: 100%; /* 높이를 100%로 설정 */
	}

	header {
		background-color: #428bca;
		color: #fff;
		padding: 20px;
		display: flex; /* 헤더를 flex container로 설정 */
		justify-content: space-between; /* 오른쪽으로 정렬 */
		flex-shrink: 0; /* 크기 축소 방지 */
	}

	header a {
		color: #fff;
		text-decoration: none;
	}

	

	nav {
		background-color: #f8f8f8;
		padding: 10px;
		width: 10%;
		display: flex;
		flex-direction: column;
		min-height: 100%; /* 최소 높이를 100%로 설정 */
		box-sizing: border-box; /* padding, border를 포함하여 요소의 크기를 조절하기 위해 box-sizing 속성 추가 */
		flex-shrink: 0; /* 크기 축소 방지 */
	}

	

	ul {
		display: flex;
		list-style-type: none;
		padding: 0;
		flex-direction: column;
	}

	li {	
		margin-bottom: 10px;
	}

	li a {
		color: #000;
		text-decoration: none;
	}

	.content {
		flex: 1; /* 남은 공간을 차지하도록 설정 */
		padding: 20px; /* 내용 영역에 padding 추가 */
	}

	footer {
		background-color: #f8f8f8;
		padding: 10px;
		text-align: center;
		color: #666;
		position: absolute;
		width: 100%;
	}
</style>
</head>
<body>
	<header>
		<a href=""><h1>원생 관리 프로그램</h1></a>
		<div>
			<p>로그인 회원 이름</p> <!-- 로그인 회원 이름 출력 -->
			<a href="">로그아웃</a> <!-- 로그아웃 링크 -->
		</div>
	</header>
	<div class="container">
	<nav>
		<h2>카테고리</h2>
		<ul class="">
			<li><a href="">공지사항</a></li>
			<li><a href="">알림장</a></li>
			<li><a href="">앨범</a></li>
			<li><a href="">일정표</a></li>
			<li><a href="class.jsp">반 정보</a></li>
		</ul>
	</nav>
	<div class="content">
		<!-- 내용 영역에 원하는 내용을 추가하세요 -->
		클래스영역
	</div>
	</div>
	<footer>
		<p>원생 관리 프로그램 &copy; 2023. All rights reserved.</p>
	</footer>
</body>
</html>
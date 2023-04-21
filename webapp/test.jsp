<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
	   .type {
            display: flex;
            align-items: center;
            justify-content: center; /* 가로 중앙 정렬 */
            gap: 10px;
            margin-bottom: 10px;
            max-width: 10%; /* div 창을 절반으로 줄임 */
            margin: 0 auto; /* 화면 세로 중앙 정렬 */
        }
        .type select {
            padding: 5px;
            border: none;
            background-color: #f5f5f5;
            font-size: 16px;
            color: #333;
            border-radius: 5px;
        }
        .type input[type="text"] {
            flex-grow: 1;
            padding: 5px;
            border: none;
            border-bottom: 1px solid #999;
            font-size: 16px;
            color: #333;
            border-radius: 5px;
        }
        .type button {
            padding: 10px 10px;
            border: none;
            background-color: #007bff;
            color: #fff;
            font-size: 15px;
            border-radius: 1px;
            cursor: pointer;
        }
    </style>
    <script src="search.js"></script> <!-- 검색 기능을 담당할 JavaScript 파일 링크 -->
    <script>
        // 옵션 선택에 따라 입력 필드 활성화/비활성화 함수
        function handleOptionChange() {
            var choice = document.getElementById("choice").value;
            var input = document.getElementById("input");
            if (choice === "title" || choice === "content" || choice === "writer") {
                input.disabled = false;
            } else {
                input.disabled = true;
            }
        }
    </script>
</head>
<body>
    <h1>글 검색</h1>
    <div class="type">
        <select id="choice" name="choice" onchange="handleOptionChange()"> <!-- 옵션 선택 시 handleOptionChange() 함수 호출 -->
            <option value="title">제목</option>
            <option value="content">내용</option>
            <option value="writer">작성자</option>
        </select>
        <input type="text" id="input" name="input">
        <button onclick="search()">검색</button> <!-- 검색 버튼 클릭 시 search() 함수 호출 -->
    </div>
    <div id="result">
    	
    </div> <!-- 검색 결과를 표시할 div 영역 -->
</body>
</html>
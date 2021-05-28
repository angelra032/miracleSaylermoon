<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- CSS -->
<link rel="stylesheet" href="resources/css/adminpage/listBoardNavi.css">
<!-- JS -->
<script src="resources/js/adminPage/menu-select.js"></script>
</head>

<body>
	<div class="top-board-menu-area">
         <ul id="top-board-menu">
             <li><a href="#" class='board-menu-btn' onclick="selectBoardMenu(1)">맛집후기</a></li>
             <li><a href="#" class='board-menu-btn' onclick="selectBoardMenu(2)">감사후기</a></li>
             <li><a href="#" class='board-menu-btn' onclick="selectBoardMenu(3)">가게추천</a></li>
             <li><a href="#" class='board-menu-btn' onclick="selectBoardMenu(4)">문의사항</a></li>
             <li><a href="#" class='board-menu-btn' onclick="selectBoardMenu(5)">공지사항</a></li>
         </ul>
	 </div>
</body>
</html>
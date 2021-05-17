<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- css -->
<link rel="stylesheet" href="resources/css/board/mainList.css">
<!-- JS -->
<script src="resources/js/board/menu-select.js"></script>

<title>게시판 네비게이션</title>
</head>
<body>
	<div class="top-board-menu-area">
                <ul id="top-board-menu">
                    <li><a href="#" class='menu-btn' onclick="selectMenu(1)">맛집후기</a></li>
                    <li><a href="#" class='menu-btn' onclick="selectMenu(2)">감사후기</a></li>
                    <li><a href="#" class='menu-btn' onclick="selectMenu(3)">가게추천</a></li>
                    <li><a href="#" class='menu-btn' onclick="selectMenu(4)">문의 및 공지</a></li>
                </ul>
     </div>
</body>
</html>
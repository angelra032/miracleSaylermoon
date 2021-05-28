<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- css -->
<link rel="stylesheet" href="resources/css/adminpage/listNavi.css">
<!-- JS -->
<script src="resources/js/adminPage/menu-select.js"></script>
</head>
<body>
	<div class="top-list-menu-area">
           <ul id="top-list-menu">
               <li><a href="#" class='menu-btn' onclick="selectMenu(1)">회원관리</a></li>
               <li><a href="#" class='menu-btn' onclick="selectMenu(2)">사업자관리</a></li>
               <li><a href="#" class='menu-btn' onclick="selectMenu(3)">게시판관리</a></li>
               <li><a href="#" class='menu-btn' onclick="selectMenu(4)">포인트관리</a></li>
           </ul>
	 </div>
</body>
</html>
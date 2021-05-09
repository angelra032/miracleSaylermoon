<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <!-- css -->
    <link rel="stylesheet" href="resources/css/header.css"> 
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <!-- JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title>헤더</title>
</head>
<body>
    <header>
        <div class="fixed-header-navi">
            <div class="header-logo-area" onclick="location.href='#'">
                <!-- visual에서 작업함 경로수정필요!! -->
                <img src="resources/images/logo.png" alt="로고">
            </div>
            <div class="header-menu-area">
                <ul>
                    <li><a href="#">돈쭐소개</a></li>
                    <li><a href="#">지도조회</a></li>
                    <li><a href="#">가게검색</a></li>
                    <li><a href="#">커뮤니티</a></li>
                </ul>
            </div>
            <div class="header-submenu-area">
                <a href="#">로그인</a>
                <a href="#">회원가입</a>
            </div>
        </div>
    </header>
    <div class="header-background-area">
        <img src="resources/images/mapListMain.png" alt="뒷배경이미지">
    </div>
    <main>
        <div id="main-title">지도조회</div>

    </main>
</body>
<script>
    $(document).ready(function(){
        $(window).scroll(function(){
            var scroll = $(window).scrollTop();
            if (scroll > 1) {
                scrollDownEvent();
                
            }
            else{
                scrollUpEvent();
            }
        });
        
        // 스크롤아래로
        function scrollDownEvent() {
            $('.fixed-header-navi').css("background" , "#ffffff");
            $('.fixed-header-navi').css("position" , "fixed");
            $('.header-menu-area a').css('color', '#333333');
            $('.header-submenu-area a').css('color', '#333333');
            $(".header-logo-area img").attr("src", 'resources/images/logo-color.png');
        }
        // 스크롤 위로
        function scrollUpEvent() {
            $(".fixed-header-navi").css("background" , "");
            $('.header-menu-area a').css('color', '#ffffff');
            $('.header-submenu-area a').css('color', '#ffffff');
            $(".header-logo-area img").attr("src", 'resources/images/logo.png');
        }

    });
</script>
</body>
</html>
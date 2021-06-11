<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/story/story.css"> 
<link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
<title>돈쭐스토리</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	<div class="header-background-area">
       	<img src="/resources/images/story/story-main.png" alt="뒷배경이미지">
  		</div>	
	<div class="main-title">
		돈쭐 스토리
		<p>우리의 소중했던 시절을<br>너희에게도 느끼게 해주고 싶어서 기획한<br>프로 돈쭐러들의 스토리</p>
	</div>
	<div class="title-bar"></div>
	<div class="black-title"  data-aos="fade-up" data-aos-duration="1000">
		고-집;
		<p>'선한 영향력은 무조건 성공한다.'<br>우리의 단단한 고집으로,<br>모두가 웃을 수 있는 세상을 만들어 드리겠습니다.</p>
	</div>
	<div class="frame">
		<div class="black-title left-title" data-aos="fade-right">
			이유있는 고집
			<p>가치있는 소비를 현실화시킵니다.<br>잔액부족, 홍보부족, 정보부족을 한번에 해결합니다.<br>돈이 없어도 되는 세상을 꿈꾸는 돈쭐이라서 가능한 이야기 입니다.</p>
			<img alt="협력" src="/resources/images/story/story-hand1.png">
		</div>
		<div class="black-title right-title"  data-aos="fade-left">
			모든 사람이&nbsp;<br>든든한 세상을 꿈꿉니다.
			<p>	돈쭐을 버팀목으로 삼아 무럭무럭 성장하는 꿈나무와<br>
				돈쭐로 인해 매출걱정없이 마음껏 한끼를 나눌 수 있는 소상공인의 미소,<br>
				가치있는 소비를 통해 성숙한 마인드를 가진 여러분이<br>
				돈쭐의 주 고객입니다.</p>
			<img alt="협력" src="/resources/images/story/story-hand2.png">
		</div>
		<div class="black-title last-title">
			돈쭐은 지치지 않습니다.
			<p>	단 하나의 공통된 목적을 가진 여러분들을 더 많이 모아<br>
				대한민국 최고의 돈쭐 커뮤니티로 거듭나는것이 목표입니다.</p>
			<img class="small-img" alt="집" src="/resources/images/story/small-img.png">
		</div>
	</div>
	
	
	
	
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
<script>
AOS.init();
</script>
</html>
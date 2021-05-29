<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/payment/snsPhoto.css"> 
<!-- <link href="/static/bootstrap.min.css" rel="stylesheet"> -->
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

<!-- 페이스북 공유 메타태그 -->
<meta property="og:url"                content="http://localhost:8521/snsPhotoView.dz/" />
<meta property="og:type"               content="article" />
<meta property="og:title"              content="누구나 마음편히 밥먹는 세상을 위해, 돈쭐내기에 참여하세요!" />
<!-- <meta property="og:description"        content="누구나 마음편히 밥먹는 세상을 위해, 돈쭐내기에 참여하세요!" /> -->
<meta property="og:image"              content="/resources/images/snsPhoto.png" />
<!-- 트위터 공유 메타태그 -->
<meta name="twitter:card" content="summary">
<meta name="twitter:url" content="http://localhost:8521/snsPhotoView.dz/" />
<meta name="twitter:description" content="누구나 마음편히 밥먹는 세상을 위해, 돈쭐내기에 참여하세요!" />
<meta name="twitter:image" content="/resources/images/snsPhoto.png" />

<title>돈쭐내기</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	
	<main>
		<div class="header-background-area">
	        <img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
	    </div>
		
		<div id="main-title">인증샷&nbsp;&nbsp;<span id="main-title-shop">${shopName }</span></div>
		<div class="frame">
		
			<div class="sns">
				
				<div>
					<img src="/resources/images/snsPhoto.png" id="sns-Photo"> 
					
				</div>
				
			</div>
			
			<div class="result">
				<div class="lay-title">
					<span class="title-span">당첨 결과</span>
				</div>
				<div class="lay-content">
					<div>
						<div id="h2">${loginUser.userName }님,<br> 호되게 돈쭐내셨군요!</div>
						<h4>인증샷을 SNS에 자랑해주세요.<br>많은 공유가 많은 돈쭐을 부릅니다.</h4>
					</div>
					<button id="sns-link-btn">SNS에 인증하기</button>
					
					<div class="sns-share" style="display:none">
						<a id="btnTwitter" class="link-icon twitter" href="javascript:shareTwitter();">트위터</a>
						<a id="btnFacebook" class="link-icon facebook" href="javascript:shareFacebook();">페이스북</a>    
						<a id="btnKakao" class="link-icon kakao" href="javascript:shareKakao();">카카오톡</a>   
						<a id="btnKakao" class="link-icon naver" href="javascript:shareNaver();">네이버</a>   
					</div>
					
				</div>
			</div>
			
			
			
		</div>
	</main>
	
	<script>
		
		$("#sns-link-btn").click(function(){
			$(".sns-share").show();
		});
	
		/* SNS 공유 */
		// 트위터
		function shareTwitter() {
		    var sendText = $('meta[property="og:title"]').attr("content"); // 전달할 텍스트
		    var sendUrl = encodeURIComponent( $('meta[name="twitter:url"]').attr("content") );
		    window.open("https://twitter.com/intent/tweet?text=" + sendText + "&url=" + sendUrl );
		}
		// 페이스북
		function shareFacebook() {
		    var sendUrl = "http://localhost:8521/snsPhotoView.dz/"; // 전달할 URL
		    var sendText = $('meta[property="og:title"]').attr("content"); // 전달할 텍스트
		    window.open("http://www.facebook.com/sharer/sharer.php?u=" + sendUrl);
		}
		// 카카오톡
		function shareKakao() {
		  // 사용할 앱의 JavaScript 키 설정
		  Kakao.init('572485776d8fac500f635301f3febf88');
		  // 카카오링크 버튼 생성
		  Kakao.Link.createDefaultButton({
		    container: '#btnKakao', // 카카오공유버튼ID
		    objectType: 'feed',
		    content: {
		      title: "돈쭐 인증!", // 보여질 제목
		      description: "누구나 마음편히 밥먹는 세상을 위해, 돈쭐내기에 참여하세요!", // 보여질 설명
		      imageUrl: "http://localhost:8521/snsPhotoView.dz/", // 콘텐츠 URL
		      link: {
		         mobileWebUrl: "http://localhost:8521/snsPhotoView.dz/",
		         webUrl: "http://localhost:8521/snsPhotoView.dz/"
		      }
		    }
		  });
		}
		// 네이버
		function shareNaver() {
	      var url = encodeURI(encodeURIComponent('http://localhost:8521/snsPhotoView.dz/'));
	      var title = encodeURI('돈쭐 인증!');
	      var shareURL = "https://share.naver.com/web/shareView?url=" + url + "&title=" + title;
		  window.open(shareURL);
	    }

		
	</script>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>
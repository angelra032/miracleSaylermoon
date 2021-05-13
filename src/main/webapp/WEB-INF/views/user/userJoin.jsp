<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/user/join.css"> 
<link href="/static/bootstrap.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>돈쭐 회원가입</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	<main>
		<div class="header-background-area">
        	<img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
   		</div>	
		<div id="main-title">회원가입</div>
		<div class="frame">
			<h4>회원타입을 선택하세요</h4>
			<ul class="tab">
				<li data-tab="dreamEnrollView" class="tabmenu" id="default">
					꿈나무회원
				</li>
				<li data-tab="mzEnrollView" class="tabmenu">
					일반회원
				</li>
				<li data-tab="partnerEnrollView" class="tabmenu">
					사업자회원
				</li>
			</ul>
			<div class="tabcontent current">
				<div class="content-body">
					<div class="form-head">
						아이디&nbsp;
						<span class="required">*</span>&nbsp;&nbsp;
						<div class="form-noti userid-noti-0">아이디를 입력해 주세요.</div>
						<div class="form-noti userid-noti-1">영문소문자로 시작, 영문소문자, 숫자 포함 6~20자</div>
						<div class="form-noti userid-noti-2">이미 사용중인 아이디입니다.</div>
						<div class="form-noti userid_suc">사용가능한 아이디입니다.</div>
					</div>
					<div class="form-body">
						<input name="user-id" class="form-elem" type="text" maxlength="20" placeholder="영문소문자, 숫자 포함 6~20자">
					</div>
					<div class="form-head form-head2">
						비밀번호&nbsp;
						<span class="required">*</span>&nbsp;&nbsp;
						<div class="form-pwdnoti password_noti_0">비밀번호를 입력해 주세요.</div>
						<div class="form-pwdnoti password_noti_1">영문소문자, 숫자 포함 6~20자</div>
						<div class="form-pwdnoti password_noti_2">비밀번호가 일치하지 않습니다.</div>
					</div>
					<div class="form-body">
						<input name="user-pwd" class="form-pwdelem" type="password" maxlength="20" placeholder="영문, 숫자 또는 혼합 6~20자">
						<input name="re_pwd" class="form-pwdelem re_pwd" type="password" maxlength="20" placeholder="비밀번호 재입력">
					</div>
					<div class="form-head form-head2">
						이름&nbsp;
						<span class="required">*</span>&nbsp;&nbsp;
						<div class="form-namenoti name_noti_0">이름을 입력해 주세요.</div>
						<div class="form-namenoti name_noti_1">한글 2자 이상 입력</div>
					</div>
					<div class="form-body">
						<input name="user-name" class="form-nameelem" type="text" maxlength="20" placeholder="한글 2자 이상 입력">
					</div>
					<div class="form-head form-head2">
						카드번호&nbsp;
						<span class="required">*</span>&nbsp;&nbsp;
						<div class="form-namenoti name_noti_0">이름을 입력해 주세요.</div>
						<div class="form-namenoti name_noti_1">한글 2자 이상 입력</div>
					</div>
					<div class="form-body">
						<input name="user-name" class="form-nameelem" type="text" maxlength="20" placeholder="한글 2자 이상 입력">
					</div>
					<div class="form-head form-head2">
						닉네임&nbsp;
						<span class="required">*</span>&nbsp;&nbsp;
						<div class="form-namenoti name_noti_0">이름을 입력해 주세요.</div>
						<div class="form-namenoti name_noti_1">한글 2자 이상 입력</div>
					</div>
					<div class="form-body">
						<input name="user-name" class="form-nameelem" type="text" maxlength="20" placeholder="한글 2자 이상 입력">
					</div>
					<div class="form-head form-head2">
						휴대폰번호&nbsp;
						<span class="required">*</span>&nbsp;&nbsp;
						<div class="form-namenoti name_noti_0">이름을 입력해 주세요.</div>
						<div class="form-namenoti name_noti_1">한글 2자 이상 입력</div>
					</div>
					<div class="form-body">
						<input name="user-name" class="form-nameelem" type="text" maxlength="20" placeholder="한글 2자 이상 입력">
					</div>
					<div class="form-head form-head2">
						이메일&nbsp;
						<span class="required">*</span>&nbsp;&nbsp;
						<div class="form-namenoti name_noti_0">이름을 입력해 주세요.</div>
						<div class="form-namenoti name_noti_1">한글 2자 이상 입력</div>
					</div>
					<div class="form-body">
						<input name="user-name" class="form-nameelem" type="text" maxlength="20" placeholder="한글 2자 이상 입력">
					</div>
				</div>
			</div>
		</div>
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
<script>
	$(function() {
		// 탭별로 양식 다르게 출력
		$('.tabmenu').click(function() {
			var activeTab = $(this).attr('data-tab');
			$('.tabmenu').css('background-color', '#f4f4f4');
			$('.tabmenu').css('color', '#333');
			$(this).css('background-color', '#0160ff');
			$(this).css('color', '#fff');
		});
		$('#default').click();   
		// 유효성 검사
		userId = $(".form-elem");
		var regExp = /^[a-z][a-z0-9]{5,11}$/;
		$('.form-elem').keyup(function() {
			$('.form-noti').css('display', 'none');
			if (!regExp.test(userId.val())) {
				$('.userid-noti-1').css('color', '#ff5442');
				$('.userid-noti-1').css('display', 'block');
				$('.form-elem').css('border', '1px solid #ff5442');
			}else{
				$('.form-noti').css('display', 'none');
				$('.userid_suc').css('display', 'block');
				$('.userid_suc').css('color', '#0160ff');
				$('.form-elem').css('border', '0');
			}
			$.ajax({
				url : "dupId.kh",
				data : { "userId" : userId },
				success : function(result) {
					if(result != 0){
						$('.userid-noti-2').css('color', '#ff5442');
						$('.userid-noti-2').css('display', 'block');
						$('.form-elem').css('border', '1px solid #ff5442');
					}else{
						$('.form-noti').css('display', 'none');
						$('.form-elem').css('border', '0');
					}
				},
				error : function() {
					console.log("전송실패");
				}
				
			});
		});
		$(".form-elem").on("blur", function() {
			$('.form-noti').css('display', 'none');
			if (userId.val() =="") {
				$('.userid-noti-0').css('color', '#ff5442');
				$('.userid-noti-0').css('display', 'block');
				$('.form-elem').css('border', '1px solid #ff5442');
			}else if(!regExp.test(userId.val())){
				$('.form-noti').css('display', 'none');
				$('.userid-noti-1').css('color', '#ff5442');
				$('.userid-noti-1').css('display', 'block');
				$('.form-elem').css('border', '1px solid #ff5442');
			}else{
				$('.form-noti').css('display', 'none');
				$('.form-elem').css('border', '0');
				$('.userid_suc').css('display', 'block');
				$('.userid_suc').css('color', '#0160ff');
			}
		});
		
		///비밀번호 찾기 유효성검사
		userPwd = $(".form-pwdelem");
		var regExpPw = /^[a-z0-9][a-z0-9]{5,11}$/;
		userPwd.keyup(function() {
			$('.form-pwdnoti').css('display', 'none');
			if (!regExpPw.test(userPwd.val())) {
				$('.password_noti_1').css('color', '#ff5442');
				$('.password_noti_1').css('display', 'block');
				$('.form-pwdelem').css('border', '1px solid #ff5442');
			}else{
				$('.form-pwdnoti').css('display', 'none');
				$('.form-pwdelem').css('border', '0');
			}
		});
		$(".re_pwd").keyup(function() {
			$('.form-pwdnoti').css('display', 'none');
			if($(".re_pwd").val()!=userPwd.val()){
				$('.form-pwdnoti').css('display', 'none');
				$('.password_noti_2').css('color', '#ff5442');
				$('.password_noti_2').css('display', 'block');
				$('.re_pwd').css('border', '1px solid #ff5442');
			}else{
				$('.form-pwdnoti').css('display', 'none');
				$('.form-pwdelem').css('border', '0');
			}
		});
		/////비밀번호 입력해주세요
		userPwd.on("blur", function() {
			$('.form-pwdnoti').css('display', 'none');
			if (userPwd.val() =="") {
				$('.password_noti_0').css('color', '#ff5442');
				$('.password_noti_0').css('display', 'block');
				$('.form-pwdelem').css('border', '1px solid #ff5442');
			}else if(!regExpPw.test(userPwd.val())){
				$('.form-pwdnoti').css('display', 'none');
				$('.password_noti_1').css('color', '#ff5442');
				$('.password_noti_1').css('display', 'block');
				$('.form-pwdelem').css('border', '1px solid #ff5442');
			}else if($(".re_pwd").val()==""){
				$('.re_pwd').css('border', '1px solid #ff5442');
			}else if($(".re_pwd").val()!=userPwd.val()){
				$('.form-pwdnoti').css('display', 'none');
				$('.password_noti_2').css('color', '#ff5442');
				$('.password_noti_2').css('display', 'block');
				$('.re_pwd').css('border', '1px solid #ff5442');
			}else{
				$('.form-pwdnoti').css('display', 'none');
				$('.form-pwdelem').css('border', '0');
			}
		});
		
		//이름 유효성 검사
		userName = $(".form-nameelem");
		var regExpName = /^[가-힣]{2,11}$/;
		userName.keyup(function() {
			$('.form-namenoti').css('display', 'none');
			if (!regExpName.test(userName.val())) {
				$('.name_noti_1').css('color', '#ff5442');
				$('.name_noti_1').css('display', 'block');
				$('.form-nameelem').css('border', '1px solid #ff5442');
			}else{
				$('.form-namenoti').css('display', 'none');
				$('.form-nameelem').css('border', '0');
			}
		});
		$(".form-nameelem").on("blur", function() {
			$('.form-namenoti').css('display', 'none');
			if (userName.val() =="") {
				$('.name_noti_0').css('color', '#ff5442');
				$('.name_noti_0').css('display', 'block');
				$('.form-nameelem').css('border', '1px solid #ff5442');
			}else if(!regExpName.test(userName.val())){
				$('.form-namenoti').css('display', 'none');
				$('.name_noti_1').css('color', '#ff5442');
				$('.name_noti_1').css('display', 'block');
				$('.form-nameelem').css('border', '1px solid #ff5442');
			}else{
				$('.form-namenoti').css('display', 'none');
				$('.form-nameelem').css('border', '0');
			}
		});
		
	});
</script>
</html>
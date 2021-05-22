<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/user/findid.css"> 
<title>ID 찾기</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	
	<main>
		<div class="header-background-area">
        	<img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
   		</div>	
		<div id="main-title">아이디 찾기</div>
		<div class="frame">
			<h4>이름, 이메일을 입력하세요</h4>
				<div class="tabcontent">
					<div class="content-body">
						<form action="findId.dz" method="post">
							<div class="form-head">
								이름&nbsp;
								<span class="required">*</span>&nbsp;&nbsp;
							</div>
							<div class="form-body">
								<input name="userName" class="form-elem nameelem" type="text" maxlength="20" placeholder="한글 2자 이상 입력">
							</div>
							<div class="form-head form-head2">
								이메일&nbsp;
								<span class="required">*</span>&nbsp;&nbsp;
							</div>
							<div class="form-body">
								<input name="userEmail" class="form-elem emailelem" type="text" maxlength="50" placeholder="아이디@도메인으로 입력">
							</div>
							<button class="submit-btn" type="submit">아이디 찾기</button>
						</form>
					</div>
				</div>
		</div>
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
<script>
	$(function() {
		$('.submit-btn').click(function() {
			var rtn = true;
			var userName = $(".nameelem");
			var userEmail = $(".emailelem");
			$.ajax({
				url : "dupFindId.dz",
				data : { "userName" : userName.val(), "userEmail" : userEmail.val() },
				async: false,
				success : function(result) {
					if(userName.val() == "") {
						alert("이름을 입력해주세요")
						rtn = false;
					}else if(userEmail.val() == ""){
						alert("이메일을 입력해주세요")
						rtn = false;
					}else if(result == 0){
						alert("이름 또는 이메일이 일치하지 않습니다. 다시 입력해주세요.");
						rtn = false;
					}
				},
				error : function() {
					console.log("전송실패");
				}
			});
			return rtn;
		});
	});
</script>
</html>
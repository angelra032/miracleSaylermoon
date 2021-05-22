<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/user/findpw.css"> 
<title>비밀번호 찾기</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	
	<main>
		<div class="header-background-area">
        	<img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
   		</div>	
		<div id="main-title">비밀번호 찾기</div>
		<div class="frame">
			<h4>아이디, 이메일을 입력하세요</h4>
			<div class="tabcontent">
				<div class="underline">
					<div class="content-body">
						<form action="sendEmail.dz" method="post">
							<c:if test="${ !empty userId }">
								<div class="form-head">
									아이디&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
								</div>
								<div class="form-body">
									<input name="userId" class="form-elem idelem" type="text" maxlength="20" placeholder="영문소문자, 숫자 포함 6~20자" value="${ userId }">
								</div>
								<div class="form-head form-head2">
									이메일&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
								</div>
								<div class="form-body">
									<input name="userEmail" class="form-elem emailelem" type="text" maxlength="50" placeholder="아이디@도메인으로 입력" value="${ userEmail }">
								</div>
							</c:if>
							<c:if test="${ empty userId }">
								<div class="form-head">
									아이디&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
								</div>
								<div class="form-body">
									<input name="userId" class="form-elem idelem" type="text" maxlength="20" placeholder="영문소문자, 숫자 포함 6~20자">
								</div>
								<div class="form-head form-head2">
									이메일&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
								</div>
								<div class="form-body">
									<input name="userEmail" class="form-elem emailelem" type="text" maxlength="50" placeholder="아이디@도메인으로 입력">
								</div>
							</c:if>
								<button class="submit-btn" type="submit">인증코드 보내기</button>
						</form>
					</div>
				</div>
					<c:if test="${ !empty randomCode }">
						<form action="resetPwView.dz" method="post">
							<div class="content-body">
								<input name="userCode" class="form-elem codeelem" type="text" maxlength="50" placeholder="인증 코드 입력">
								<input type="hidden" class="check-code" value="${ randomCode }">
								<input type="hidden" class="userId" name="userId" value="${ userId }">
								<input type="hidden" class="userEmail" name="userEmail" value="${ userEmail }">
								<button class="code-submit-btn click-submit-btn" type="submit">새 비밀번호 입력</button>
							</div>
						</form>
					</c:if>
					<c:if test="${ empty randomCode }">
						<div class="content-body">
							<input name="userCode" class="form-elem codeelem" type="text" maxlength="50" placeholder="인증 코드 입력" readonly>
							<button class="code-submit-btn nonclick-btn" type="submit">새 비밀번호 입력</button>
						</div>
					</c:if>
				</div>
		</div>
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
<script>
	$(function() {
		$('.submit-btn').click(function() {
			var rtn = true;
			var userId = $(".idelem");
			var userEmail = $(".emailelem");
			$.ajax({
				url : "dupFindPw.dz",
				data : { "userId" : userId.val(), "userEmail" : userEmail.val() },
				async: false,
				success : function(result) {
					if(userId.val() == "") {
						alert("아이디를 입력해주세요")
						rtn = false;
					}else if(userEmail.val() == ""){
						alert("이메일을 입력해주세요")
						rtn = false;
					}else if(result == 0){
						alert("아이디 또는 이메일이 일치하지 않습니다. 다시 입력해주세요.");
						rtn = false;
					}else {
						alert("인증코드를 발송하였습니다. 이메일을 확인해주세요.");
						rtn = true;
					}
				},
				error : function() {
					console.log("전송실패");
				}
			});
			return rtn;
		});
		$('.click-submit-btn').click(function() {
			var checkCode = $(".check-code");
			var inputCode = $(".codeelem");
			if(inputCode.val() =="") {
				alert("코드를 입력해주세요")
				return false;
			}else if(checkCode.val() != inputCode.val()) {
				alert("코드가 불일치합니다. 다시 입력해주세요.")
				return false;
			}
		});
	});
</script>
</html>
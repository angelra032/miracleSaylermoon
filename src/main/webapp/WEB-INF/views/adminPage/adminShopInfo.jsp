<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/user/myinfo.css"> 
<!-- <link href="/static/bootstrap.min.css" rel="stylesheet"> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>내 정보 수정</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	<main>
		<div class="header-background-area">
        	<img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
   		</div>	
		<div id="main-title">내 정보 수정</div>
		<div class="frame">
			<div class="tabcontent">
				<div class="content-body">
					<c:if test="${ !empty user && user.userType == '1'}">
						<div id="dreamEnrollView" class="tab-content current">
								<div class="form-head">
									아이디&nbsp;
								</div>
								<div class="form-body">
									<input name="userId" class="form-elem idelem" type="text" maxlength="20" value="${ user.userId }" readonly>
								</div>
								
								<div class="form-head form-head2">
									이름&nbsp;
								</div>
								<div class="form-body">
									<input name="userName" class="form-elem nameelem" type="text" maxlength="20" value="${ user.userName }" readonly>
								</div>
								
								<div class="form-head form-head2">
									카드번호&nbsp;
								</div>
								<div class="form-body">
									<input name="dreamCardno" id="cardelem" class="form-elem cardelem" type="text" maxlength="20" value="${ user.dreamCardno }" readonly>
								</div>
								
								<div class="form-head form-head2">
									닉네임&nbsp;
								</div>
								<div class="form-body">
									<input name="userNick" class="form-elem nickelem" type="text" maxlength="20" value="${ user.userNick }" readonly>
								</div>
								
								<div class="form-head form-head2">
									휴대폰번호&nbsp;
									<div class="form-noti phonenoti phone_noti_0">휴대폰번호를 입력해 주세요.</div>
									<div class="form-noti phonenoti phone_noti_2">이미 등록된 번호입니다.</div>
								</div>
								<div class="form-body">
									<input name="userPhone" id="phoneelem" class="form-elem phoneelem" type="text" maxlength="13" placeholder="숫자만 입력" value="${ user.userPhone }">
								</div>
								
								<div class="form-head form-head2">
									이메일&nbsp;
									<div class="form-noti emailnoti email_noti_0">이메일을 입력해 주세요.</div>
									<div class="form-noti emailnoti email_noti_1">이메일 형식이 올바르지 않습니다.</div>
									<div class="form-noti emailnoti email_noti_2">이미 사용중인 이메일입니다.</div>
								</div>
								<div class="form-body">
									<input name="userEmail" class="form-elem emailelem" type="text" maxlength="50" placeholder="아이디@도메인으로 입력" value="${ user.userEmail }">
								</div>
								<button class="submit-btn" onclick="location.href='adminUserList.dz'">목록보기</button>
								<input type="hidden" name="userNo" class="userNo" value="${ user.userNo }">
								<input type="hidden" name="userType" value="${ user.userType }">
						</div>
					</c:if>
					<!-- 사업자회원 -->
					<c:if test="${ !empty user && user.userType == '3'}">
						<div id="partnerEnrollView" class="tab-content">
								<div class="form-head">
									아이디&nbsp;
								</div>
								<div class="form-body">
									<input name="userId" class="form-elem idelem" type="text" maxlength="20" value="${ user.userId }" readonly>
								</div>
								
								
								<div class="form-head form-head2">
									사업자명&nbsp;
								</div>
								<div class="form-body">
									<input name="userName" class="form-elem nameelem" type="text" maxlength="20" value="${ user.userName }" readonly>
								</div>
								
								<div class="form-head form-head2">
									간이사업자명&nbsp;
									<div class="form-noti simplenamenoti simplename_noti_0">간이사업자명을 입력해 주세요.</div>
									<div class="form-noti simplenamenoti simplename_noti_1">한글 2자 이상 입력</div>
								</div>
								<div class="form-body">
									<input name="partnerName" class="form-elem simplenameelem" type="text" maxlength="20" placeholder="한글 2자 이상 입력" value="${ user.partnerName }">
								</div>
								
								<div class="form-head form-head2">
									연락처&nbsp;
									<div class="form-noti pphonenoti phone_noti_0">연락처를 입력해 주세요.</div>
									<div class="form-noti pphonenoti phone_noti_2">이미 등록된 번호입니다.</div>
								</div>
								<div class="form-body">
									<input name="userPhone" id="pphoneelem" class="form-elem pphoneelem" type="tel" maxlength="12" placeholder="숫자만 입력" value="${ user.userPhone }">
								</div>
								
								<div class="form-head form-head2">
									사업자등록번호&nbsp;
								</div>
								<div class="form-body">
									<input name="partnerVerify" id="pverielem" class="form-elem pverielem" type="text" maxlength="12" value="${ user.partnerVerify }" readonly>
								</div>
								
								<div class="form-head form-head2">
									이메일&nbsp;
									<div class="form-noti emailnoti email_noti_0">이메일을 입력해 주세요.</div>
									<div class="form-noti emailnoti email_noti_1">이메일 형식이 올바르지 않습니다.</div>
									<div class="form-noti emailnoti email_noti_2">이미 사용중인 이메일입니다.</div>
								</div>
								<div class="form-body">
									<input name="userEmail" class="form-elem emailelem" type="text" maxlength="50" placeholder="아이디@도메인으로 입력" value="${ user.userEmail }">
								</div>
								<button class="submit-btn" onclick="location.href='adminUserList.dz'">목록보기</button>
						</div>
				</c:if>
				</div>
			</div>
		</div>
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
<script>
	
			
			
</script>
</html>
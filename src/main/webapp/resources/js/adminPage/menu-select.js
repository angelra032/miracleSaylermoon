$(document).ready(function(){
	var link = document.location.href.split("/");
	if(link[3] == "adminUserList.dz") {
		$('.menu-btn').eq(0).css('background','#fdb504').css('color','white');
	} else if(link[3] == "adminShopList.dz") {
		$('.menu-btn').eq(1).css('background','#fdb504').css('color','white');
	} else if(link[3] == "adminMReviewList.dz" || link[3] == "adminDrmReviewList.dz" || link[3] == "adminRecommendList.dz" || link[3] == "adminQnaList.dz" || link[3] == "adminNoticeList.dz") {
		$('.menu-btn').eq(2).css('background','#fdb504').css('color','white');
	} else if(link[3] == "adminPointList.dz") {
		$('.menu-btn').eq(3).css('background','#fdb504').css('color','white');
	}
});

function selectMenu(data) {
	if(data == 1) {
		location.href="adminUserList.dz";
	} else if(data == 2) {
		location.href="adminShopList.dz";
	} else if(data == 3) {
		location.href="adminMReviewList.dz";
	} else if(data == 4) {
		location.href="adminPointList.dz";
	} else {
		alert('잘못된 접근입니다.');
		location.href="adminUserList.dz"; // 임시
	}
}

function selectBoardMenu(data) {
	if(data == 1) {
		location.href="adminMReviewList.dz";
	} else if(data == 2) {
		location.href="adminDrmReviewList.dz";
	} else if(data == 3) {
		location.href="adminRecommendList.dz";
	} else if(data == 4) {
		location.href="adminQnaList.dz";
	} else if(data == 5) {
		location.href="adminNoticeList.dz";
	} else {
		alert('잘못된 접근입니다.');
		location.href="adminNoticeList.dz"; // 임시
	}
}


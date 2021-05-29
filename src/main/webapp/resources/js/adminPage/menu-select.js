function selectMenu(data) {
	if(data == 1) {
		location.href="adminUserList.dz";
	} else if(data == 2) {
		location.href="adminShopList.dz";
	} else if(data == 3) {
		location.href="adminMReviewList.dz";
	} else if(data == 4) {
		alert('포인트관리');
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
		alert('가게추천');
	} else if(data == 4) {
		location.href="adminQnaList.dz";
	} else if(data == 5) {
		location.href="adminNoticeList.dz";
	} else {
		alert('잘못된 접근입니다.');
		location.href="adminNoticeList.dz"; // 임시
	}

}
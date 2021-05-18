function selectMenu(num) {
	if(num == 1) {
		location.href='mReviewMain.dz';
	} else if(num == 2) {
		location.href='dReviewMain.dz';
	} else if(num == 3) {
		location.href='recommendMain.dz';
	} else if(num == 4) {
		location.href='notiQnaMain.dz';
	} else {
		alert('잘못된 접근입니다.');
		location.href='mReviewMain.dz';
	}
}
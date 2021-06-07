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

$(document).ready(function(){
	var link = document.location.href.split("/");
	if(link[3] == "mReviewMain.dz") {
		$('.menu-btn').eq(0).css('background','#fdb504').css('color','white');
	} else if(link[3] == "dReviewMain.dz") {
		$('.menu-btn').eq(1).css('background','#fdb504').css('color','white');
	} else if(link[3] == "recommendMain.dz") {
		$('.menu-btn').eq(2).css('background','#fdb504').css('color','white');
	} else if(link[3] == "notiQnaMain.dz") {
		$('.menu-btn').eq(3).css('background','#fdb504').css('color','white');
	}
});
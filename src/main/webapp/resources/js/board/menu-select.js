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
	var test = link[3].indexOf('?');
	var splitLink = link[3].substring(0, test);
	if(link[3] == "mReviewMain.dz" || splitLink == "mReviewMain.dz") {
		$('.menu-btn').eq(0).css('background','#fdb504').css('color','white');
	} else if(link[3] == "dReviewMain.dz" || splitLink == "dReviewMain.dz") {
		$('.menu-btn').eq(1).css('background','#fdb504').css('color','white');
	} else if(link[3] == "recommendMain.dz" || splitLink == "recommendMain.dz") {
		$('.menu-btn').eq(2).css('background','#fdb504').css('color','white');
	} else if(link[3] == "notiQnaMain.dz" || splitLink == "notiQnaMain.dz") {
		$('.menu-btn').eq(3).css('background','#fdb504').css('color','white');
	}
});
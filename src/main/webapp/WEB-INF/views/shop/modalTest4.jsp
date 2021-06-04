<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 모달 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />

<style>
#img-modal {
	display: none;
	position: fixed;
	z-index: 20;
	padding-top: 100px;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgb(0, 0, 0);
	background-color: rgba(0, 0, 0, 0.9);
}

#img-modal>img {
	margin: auto;
	display: block;
	width: 80%;
}

#img-modal>span {
	position: absolute;
	top: 15px;
	right: 35px;
	color: #f1f1f1;
	font-size: 40px;
	font-weight: bold;
}

#img-modal>span:hover {
	color: #bbb;
	text-decoration: none;
	cursor: pointer;
}

article img:hover {
	opacity: 0.7;
}
</style>
</head>
<body>

	<article>
		<img src="/resources/images/snsPhoto.png" alt="텍스트 예제 1" width="300">
	</article>

	<div id="img-modal">
	   <span onclick="imgModalClose();">X</span>
	    <img id="img-modal-content" src="/resources/images/snsPhoto.png" alt="텍스트 예제 1" width="300">
	</div>

</body>
<script>



	/* 이미지 클릭 시 */
	var imageTagList = document.querySelectorAll('article img');

	for (var i = 0; i < imageTagList.length; i++) {
		imageTagList[i].addEventListener('click', function() {
			var modal = document.getElementById('img-modal');
			var content = document.getElementById('img-modal-content');
			modal.style.display = 'block';
			content.src = this.src;
		});
	}

	/* close 버튼 클릭시*/
	function imgModalClose() {
		var modal = document.getElementById('img-modal');
		var content = document.getElementById('img-modal-content');
		modal.style.display = "none";
		content.src = '';
		//content.src = '/resources/images/snsPhoto.png';
		
	}
</script>
</html>
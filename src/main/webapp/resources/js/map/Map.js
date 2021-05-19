window.onload = function(){
	
	 var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(37.55021, 126.92327), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };
	
	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();

	// 주소로 좌표를 검색합니다
	geocoder.addressSearch('서울특별시 마포구 와우산로 64 전원빌딩 2층 진짜파스타', function(result, status) {

	
	    // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {
	
			var imageSrc = '/resources/images/map_marker_blue.png', // 마커이미지의 주소입니다    
			    imageSize = new kakao.maps.Size(27, 35); // 마커이미지의 크기입니다
			      
			// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
			var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize),
			    markerPosition = new kakao.maps.LatLng(result[0].y, result[0].x); // 마커가 표시될 위치입니다
			console.log(markerPosition);
			
			// 마커를 생성합니다
			var marker = new kakao.maps.Marker({
			    position: markerPosition, 
			    image: markerImage // 마커이미지 설정 
			});
			
			// 마커가 지도 위에 표시되도록 설정합니다
			marker.setMap(map);  
	
			/// 커스텀 오버레이에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
			var content = '<div class="customoverlay">' +
			    '  <a href="javascript:void(0);" onclick="showShortInfo()">' +
			    '    <span class="title">진짜파스타</span>' +
			    '  </a>' +
			    '</div>';
			
			// 커스텀 오버레이가 표시될 위치입니다 
			var position = new kakao.maps.LatLng(result[0].y, result[0].x); 
			
			// 커스텀 오버레이를 생성합니다
			var customOverlay = new kakao.maps.CustomOverlay({
			    map: map,
			    position: position,
			    content: content,
			    yAnchor: 1 
			});
			
			
			// 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
			var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
			    iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
			
			// 인포윈도우를 생성합니다
			var infowindow = new kakao.maps.InfoWindow({
			    content : iwContent,
			    removable : iwRemoveable
			});
			
			// 마커에 클릭이벤트를 등록합니다
			function showShortInfo() {
			      // 마커 위에 인포윈도우를 표시합니다
			      infowindow.open(map, marker);  
			}
	
		}
	});
	
}
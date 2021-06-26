/**
 * 
 */
function button_a(){   // 의료기관찾기 버튼 클릭시 윈도우 창으로 띄우기
	window.open('./vCenter.jsp','win','width=517px,height=720px');
}

function load(){    // 처음 로드할때 시/도 가져오기, 오늘 날짜 지정
		var date = document.getElementById('date')
		date.valueAsDate = new Date();                // 오늘 날짜 지정
		
		$.ajax({                                    // 시/도 가져오기
				type : 'post',
				url : "sido.vCenter",
				success: function(resp){
					var resp = resp.trim().split(',');
					for(var i=0; i<resp.length-1;i++){	
						$('#sido').append('<option value="'+resp[i]+'">'+resp[i]+'</option>');
					}
			 	 }
		  		})
	}
	
function initMap()	{
	      var seoul = {lat: 37.5642135 ,lng: 127.0016985 };       // 처음 map 서울 중심으로 띄우기.
	      var map = new google.maps.Map(document.getElementById('center_map'), {
	          zoom: 17,
		      center: seoul
	        });
	}	


var sido = document.getElementById('sido');

sido.onchange = function(){	// 시도 변경시 시군구 가져오기
		param = $(frm_center).serialize();
		$('#sigungu').children('option:not(:first)').remove();  // 변경될때 시/군/구 리셋
		$('#dong').children('option:not(:first)').remove();     // 변경될때 읍/면/동 리셋
		
		$.ajax({
			type : 'post',
			url : "sigungu.vCenter",
			data : param,
			success: function(resp){
				var a = resp.trim().split(',');
				for(var i=0; i<a.length-1;i++){
					$('#sigungu').append('<option value="'+a[i]+'">'+a[i]+'</option>');
				}
		 	 }
	  		})
}
	
var sigungu = document.getElementById('sigungu');

sigungu.onchange = function(){    // 시군구 변경시 읍/면/동 가져오기
		param = $(frm_center).serialize();
		$('#dong').children('option:not(:first)').remove();
		
		$.ajax({
			type : 'post',
			url : "dong.vCenter",
			data : param,
			success: function(resp){
				var a = resp.trim().split(',');
				for(var i=0; i<a.length-1;i++){	
					$('#dong').append('<option value="'+a[i]+'">'+a[i]+'</option>');
				}
		 	 }
	  		})
	}
	
var btn_center_search = document.getElementById('btn_center_search');	

btn_center_search.onclick = function(){	
	var sido = document.getElementById('sido').value;
	param = $(frm_center).serialize();
	
	if(sido=='none'){
		alert('시/도를 선택해주세요')		
	}else{
		$('#center_div').load('search.vCenter', param);
	}	
}


function ct_info(num){	  // 의료기관 div 클릭시 정보 띄우기
/*$('#center').on("click",function(){*/

	var rg = document.getElementsByClassName('rg')[num].innerHTML
	var cn = document.getElementsByClassName('cn')[num].innerHTML

	var param = 'rg='+rg+'&cn='+cn;

	$.ajax({
			type : 'post',
			url : "information.vCenter",
			data : param,
			success: function(resp){
			var a = resp.trim().split(',');

			document.getElementById('center_n').innerHTML = a[0];  // 병원이름
			document.getElementById('center_f').innerHTML = a[1];  // 시설명
			document.getElementById('center_p').innerHTML = a[2];  // 연락처
			document.getElementById('center_a').innerHTML = a[3];  // 주소
			
			var center = { lat: Number(a[4]) ,lng: Number(a[5]) };  // 위도 / 경도
     		var map = new google.maps.Map(document.getElementById('center_map'),{
          			zoom: 15,
	      			center: center
    				});
			var marker = new google.maps.Marker({position: center, map: map});
			
		 	 }
	  		})
}
# 돈쭐

#### 팀원 : 김민진, 박다인, 박민애, 이주연, 조효주

## 수행기간
2021년 4월 26일 ~ 6월 15일

## Contetns

1. [개요](#개요)
2. [설계의 주안점](#설계의-주안점)
3. [사용기술 및 개발환경](#사용기술-및-개발환경)
4. [프로젝트 기능 구현](#프로젝트-기능-구현)
5. [주요기능](#주요-기능)
6. [Document](#Document)
------------
## 개요
보호자의 식사 제공이 어려워 결식 우려가 있는 아동, 청소년들에게 꿈나무카드 소지 시 결제 없이 무상으로 식사를 제공하는 착한 가게를 소개하고 간편한 접근, 기부할 수 사이트를 구현하고자 하였습니다.


## 설계의 주안점
1. 가게별 위치를 나타내는 지도 마커와 가게 상세정보 구현
2. 메인페이지, 가게검색에서 가게이름 검색으로 편리한 접근성 제공
3. 리뷰를 한눈에 모아볼 수 있는 게시판 형태의 커뮤니티(리뷰, 추천글, 공지, 문의)
4. 웹소켓을 활용한 관리자에게 문의할 수 있는 실시간 1대 다 채팅 기능 구현
5. 회원의 정보를 보호하기 위한 스프링 시큐리티 구현
6. 포인트 기능, 기부 시에 포인트 사용 가능
7. 아임포트 API를 사용한 결제기능과, 결제 후 플러스 포인트 룰렛 구현
8. 풀캘린더를 활용하여 예약할 수 있는 예약기능 구현
9. 가게 찜 기능

## 사용기술 및 개발환경
Category | Detail
---- | ----
FrontEnd | HTML5, JS, CSS3, JQuery
BackEnd | JAVA(JDK 1.8), Servlet, Mybatis, Spring(4.3.13)
OS | Windows10, MacOS
Library & API | Spring Security, kakao, naver, google, FullCalendar API, chart.js, GSON, summernote, Iamport
IDE | Eclipse, Visual Studio Code
Server | Tomcat(v8.5)
Document | Google Drive, draw.io, ERDCloud, zeplin, notion
CI | Github
DataBase | Oracle


## 프로젝트 기능 구현
+ 김민진 
  + 꿈나무회원 마이페이지 개발
    + 가고싶다 목록 조회
    + 내가 쓴 후기 조회
    + 내가 쓴 문의글 조회
    + 예약 취소 / 사업자가 방문완료 컨펌 시 후기작성 활성화
    + 예약완료시 후기쓰기 버튼 활성화
    + ajax 이용, 게시글 상태변경시 화면 이동 없음 
  + 예약관리(풀캘린더(API사용)
    + 가게별 영업시간에 따른 동적인 영업시간 셀렉트 박스
    + 모든 옵션 선택시에만 예약 완료
    + 시간별 예약상태에 따른 동적인 인원 수 셀렉트 박스
    + 오늘 이전 날짜 선택 불가능
    + 트랜잭션 처리
    + 포인트 사용 최소 최대 금액제한 및 숫자 외 입력 방지
    + 해당 가게 예약 목록 조회
  + 포인트 관리
    + 예약별 포인트 관리
    + 예약 확정 / 취소시에 포인트 상태 변경
    + 사업자번호 유효성 검사 
    + 실제 사업자 번호 입력시에만 가능
  + 사업자마이페이지 예약관리
    + 승인 버튼 클릭시 방문완료 버튼 활성화
    + 예약 기본 상태인 셀렉트박스  대기버튼 비활성화
    + 예약상태에 따른 풀캘린더 화면 변경
    + 툴팁을 이용한 예약 상세보기
    + ajax이용, 예약 상태 변경시 화면 이동 없음
  + Github 관리
    + 전체 Git 관리
  + 채팅
    + 모달창 이용하여 채팅 화면 구현
    + 웹소켓을 기반으로 실시간 1대 다 채팅 구현
    + 연결상태 확인하여 웹소켓 연결 상태 화면에 표시
    + 채팅 내용 미리보기는 토스트 라이브러리를 이용해서 구현
    + 채팅방 내용은 세션 스토리지를 사용해서 저장
    + 채팅방 목록은 컨트롤러 리스트에 저장

+ 박민애 
  + 스프링 시큐리티
    + 기존 세션과 연결
    + 회원별 권한설정 및 권한별 사이트 차단
    + 비밀번호 암호화 및 복호화
    + 중복로그인 막기
  + 인터셉터
    + 잘못된 경로로 접근시 차단페이지로 이동
  + 회원관련 기능
    + 로그인 
    + 회원가입 - 유효성 검사, 회원별 다른 회원가입탭 출력 및 한페이지 내 모든 ajax 충돌수정
    + 아이디 찾기/ 비밀번호 찾기
    + 이메일 인증
    + 카카오로그인
    + 구글로그인
    + 회원정보 수정
    + 회원탈퇴
    + 트랜잭션 처리
    + 예약 취소시 포인트 상환부분
  + 일반회원 마이페이지
    + 포인트조회
    + 예약목록 조회 및 상태 변경, 예약 취소, 후기작성
    + 예약상태변경시 ajax 처리로 인한 페이지 이동 없음
    + 가고싶다 목록 조회 및 삭제
    + 돈쭐 목록 조회
    + 내가 쓴 후기, 내가 쓴 추천, 내가 쓴 문의글 목록조회/수정/삭제
  + 메인, 스토리페이지
    + 검색창
    + 최신 후기 조회
    + 기부금 총액
  + Front 퀄리티 관리
    + 디자인, 전체적인 UI 관리 및 프론트엔드 수정 

+ 박다인 
  + 지도조회(카카오맵 api 사용)
    + 전국 단위 지역별 가게 전체 조회
    + 가게 위치 마커 표시
    + 마커 클릭 시 가게별 약식 정보 확인 및 상세페이지 이동 가능한 인포 윈도우 구현
    + 가게 목록 페이징 처리
    + 가게명 검색 기능
    + 지역별 검색 기능 
    + ajax 이용 리스트 조회 시 화면 이동 없음
    + 검색 결과 페이지 이동 시 지도 중심 좌표 이동
  + 메인
    + 식당명 검색 기능
    + 음식 종류별 가게 검색 기능
  + 가게검색
    + 식당명 검색 기능
    + 음식 종류별 가게 검색 기능
    + 가게 목록 페이징 처리
    + 테마 키워드별 해당 가게 리스트 조회
    + 후기순 가게 랭킹 조회
    + 신규 등록순 가게 랭킹 조회
  + 가게상세
    + 세션 체크하여 유저별 예약하기 버튼 활성화
    + 세션 체크하여 유저별 돈쭐내기 버튼 활성화
    + 가게 상세 정보 출력
  + 찜 등록, 해제
    + 찜 상태 조회하여 버튼 활성화
    + 카카오맵 api 이용한 가게 위치 구현
    + 예약 완료 시 해당 가게 상세 페이지 이동
  + 후기작성
    + 사진 후기 작성 시 유저 포인트 추가 트랜잭션

+ 이주연(팀장)
  + 발표
  + 관리자페이지 개발
    + 월별 기부금 총액, 첫달~최근까지의 기부금 총액을 합쳐 chartJS로 시각적 제공 (더보기 클릭시 총액과 수수료, 회원과 사업자에게 각각 돌아가는 포인트를 보여준다)
    + 사업자회원 승인버튼과 탈퇴신청 시 활성화되는 탈퇴기능 구현
    + 회원타입에 따라 구분해 볼 수 있으며 탈퇴기능 구현
    + 맛집후기, 감사후기, 가게추천, 문의사항, 공지사항 게시글 삭제 및 상세페이지 이동
    + 감사후기, 문의사항과 같은 비공개 게시글 조회 가능
    + 문의사항 답글기능 구현
    + 전체 회원 조회 / 탈퇴 및 회원 상세페이지
  + 사업자 마이페이지
    + 사업자회원 탈퇴 요청하면 관리자 페이지에서 탈퇴시키기 버튼 활성화
    + 사업자회원 포인트 환급요청 후, 관리자가 회원의 환급금액 확인과 환급 기능
  + 돈쭐기부
    + 이니시스(Iamport API)
    + 다날(Iamport API)
    + 경우에 따른 유효성 기능 추가
    + 결제방법(카카오페이, 이니시스, 다날) 선택을 위한 모달창 생성과 약관에 따른 유효성체크
  + 커뮤니티 게시판
    + 감사후기 등록/ 삭제/ 수정 구현
    + 감사후기 게시글 공개 비공개 설정 구현
    + 맛집후기 등록/ 삭제/ 수정 구현
    + 맛집후기 사진 업로드 구현
    + 가게추천 등록/ 삭제/ 수정 구현
    + 가게추천 사진 업로드 구현
    + 문의사항 등록/ 삭제/ 수정 구현
    + 문의사항 게시글 비공개 설정 구현
    + 문의 관리자답글 등록/삭제/수정 구현
    + 문의 관리자답글 원글 작성자, 관리자 조회 구현
    + 세션 체크하여 회원별 게시판 글쓰기 활성 비활성화
    + 쿠키에 따른 조회수 구현
    + 공지사항 등록/ 삭제/ 수정 구현
    + 에디터 summernote API 사용
    + 게시글 디테일, 리스트 구현
  + 오른쪽 마우스 및 개발자모드 금지하는 기능

+ 조효주 
  + 돈쭐 기부(Iamport API 사용) 
    + 카카오페이 연동
    + 모든 옵션 선택시에만 돈쭐 완료
    + 가용 포인트 제한 유효성 검사 구현
    + ajax를 통해 데이터 넘기고 돈쭐 내역 저장
    + 유저의 세션을 통한  회원별 접근 제한
    + 가게 등록 시 등록했던 메뉴 조회
    + 포인트 입력칸에 숫자만 허용하는 유효성 검사
  + 룰렛 개발 
    + jQueryRotate.js 라이브러리 사용
    + 룰렛 결과에 따른 돈쭐 금액의 1~10퍼센트 포인트 당첨
    + 룰렛 실행 시 룰렛 사용 가능 여부 제한
  + 인증 페이지 
    + sns(카카오톡, 트위터, 페이스북, 네이버)로 공유 가능
    + 카카오는 api 이용
    + 트위터, 페이스북, 네이버는 스크립트를 이용해 구현
  + 사업자 회원 마이페이지 
    + 가게 등록 페이지 유효성 검사
    + 가게 수정 페이지 유효성 검사
    + 가게 등록시 메뉴 및 가격 정규 표현식
    + 다중파일 업로드
    + 메뉴 및 파일 입력 추가 및 삭제 가능  
    + 입력 개수 추가 제한으로 추가 버튼 비활성화
    + 가게정보 수정 시 ajax 및 배열을 이용한 파일 선택 삭제 가능
    + 내가 쓴 문의글 조회
    + ajax로 이용 문의글 상태 변경시 페이지 이동 없음
  + 가게 상세 페이지
    + 모달창을 이용해 메뉴 사진 띄우기 구현
    + ajax이용 후기 더보기 기능 구현
    + 불러올 후기 없을 시 더보기 버튼 비활성화
    + 가게 상세페이지 후기 클릭시 해당 상세페이지로 이동
  + 포인트 관리
    + 돈쭐  결제 완료시, 사용 포인트 업데이트
    + 룰렛 당첨시  포인트 업데이트
    + 사업자 포인트 환급신청 시 포인트 리셋 및 업데이트


## 주요 기능
### 1.회원가입
![회원가입](https://user-images.githubusercontent.com/82349358/122237121-08388600-cefa-11eb-8221-f69671629cda.png)


![회원가입](https://user-images.githubusercontent.com/82349358/122237180-17b7cf00-cefa-11eb-8577-0196314d51e5.gif)


![회원가입2](https://user-images.githubusercontent.com/82349358/122237206-1c7c8300-cefa-11eb-97b5-15d4b475eed1.gif)


![회원가입3](https://user-images.githubusercontent.com/82349358/122237223-20100a00-cefa-11eb-8754-51086a5ba3a0.gif)


![회원가입4](https://user-images.githubusercontent.com/82349358/122237239-230afa80-cefa-11eb-8c8d-d2878145928f.gif)



### 2.로그인
![로그인](https://user-images.githubusercontent.com/82349358/122237604-6a918680-cefa-11eb-93db-91146a4c4c62.png)


![로그인](https://user-images.githubusercontent.com/82349358/122237650-73825800-cefa-11eb-8ba4-783aea2b4439.gif)


![로그인2](https://user-images.githubusercontent.com/82349358/122237670-78470c00-cefa-11eb-929d-621b3237f2e1.gif)



### 3.아이디찾기
![아이디찾기](https://user-images.githubusercontent.com/82349358/122237710-81d07400-cefa-11eb-9969-5eacd4b00cc1.png)



### 4.비밀번호찾기
![바밀번호찾기](https://user-images.githubusercontent.com/82349358/122237745-88f78200-cefa-11eb-8b4a-95fef96709e9.png)


![아이디 비밀번호 찾기](https://user-images.githubusercontent.com/82349358/122237769-8e54cc80-cefa-11eb-9a31-db5e865e18da.gif)



### 5.메인
![메인](https://user-images.githubusercontent.com/82349358/122247569-69645780-cf02-11eb-8dda-ef3068740332.png)



### 6.채팅
![채팅](https://user-images.githubusercontent.com/82349358/122249947-391db880-cf04-11eb-8ed7-71c67a317f25.png)


![채팅](https://user-images.githubusercontent.com/82349358/122249980-3fac3000-cf04-11eb-9c09-319c6efef930.gif)



### 7.가게(검색 결과)
![가게검색-테마](https://user-images.githubusercontent.com/70650493/122252352-3a4fe500-cf06-11eb-968f-3732952ccd19.png)


![가게검색결과](https://user-images.githubusercontent.com/82349358/122232467-37e58f00-cef6-11eb-8def-3ec349b3bcfa.png)


![가게검색](https://user-images.githubusercontent.com/82349358/122233213-d70a8680-cef6-11eb-9ca5-c97c8b2363f7.gif)



### 8.가게(상세)
![가게 상세](https://user-images.githubusercontent.com/82349358/122247403-4639a800-cf02-11eb-9541-7627a3e7ea9e.png)



### 9.지도조회
![지도조회](https://user-images.githubusercontent.com/82349358/122232927-97dc3580-cef6-11eb-942c-63cf41cb4393.png)


![지도](https://user-images.githubusercontent.com/82349358/122233615-2bae0180-cef7-11eb-8d94-74b66930a954.gif)



![지도2-1](https://user-images.githubusercontent.com/82349358/122236537-919b8880-cef9-11eb-9e5e-5cd5cc8779de.gif)



![지도3](https://user-images.githubusercontent.com/82349358/122233672-38caf080-cef7-11eb-8816-fed28407b3df.gif)



### 10.돈쭐내기(결제)
![돈쭐내기-결제](https://user-images.githubusercontent.com/82349358/122232680-62cfe300-cef6-11eb-905a-2fb0ebf518f2.png)


![돈쭐](https://user-images.githubusercontent.com/82349358/122234807-22716480-cef8-11eb-8fcf-dae9150b4a9a.jpg)


![MZ_다날(전화번호노출)](https://user-images.githubusercontent.com/82349358/122234872-2ef5bd00-cef8-11eb-9add-ad1c5f2b2412.gif)


![돈쭐_카카오페이](https://user-images.githubusercontent.com/82349358/122234910-374df800-cef8-11eb-8ba8-9487189a5d0a.gif)



### 11.돈쭐내기(룰렛)
![룰렛](https://user-images.githubusercontent.com/82349358/122236713-b4c63800-cef9-11eb-8822-47f047bf7c82.png)


![포인트 룰렛](https://user-images.githubusercontent.com/82349358/122236736-babc1900-cef9-11eb-8379-863953e721ee.gif)



### 12.돈쭐내기(인증샷)
![인증샷](https://user-images.githubusercontent.com/82349358/122236789-c8719e80-cef9-11eb-9abf-43bf34bf1b5f.png)


![인증샷_카카오인증_과정(전화번호노출)](https://user-images.githubusercontent.com/82349358/122236835-d1627000-cef9-11eb-8937-e5620a55cbae.gif)


![인증샷_카카오인증](https://user-images.githubusercontent.com/82349358/122236855-d4f5f700-cef9-11eb-81c7-bc6010a4b61f.gif)



### 13.예약하기
![예약하기](https://user-images.githubusercontent.com/82349358/122236898-dde6c880-cef9-11eb-984f-46d704f4de1e.png)


![예약](https://user-images.githubusercontent.com/82349358/122236944-e6d79a00-cef9-11eb-9e64-13b2c2c66e80.gif)


![예약2](https://user-images.githubusercontent.com/82349358/122243286-f6a5ad00-cefe-11eb-8a72-30eb5b0f970d.gif)


![예약3](https://user-images.githubusercontent.com/82349358/122243311-f9a09d80-cefe-11eb-98a5-f0657c9f63d7.gif)


![예약4](https://user-images.githubusercontent.com/82349358/122243392-0c1ad700-ceff-11eb-8d4d-de8c52a7e13e.gif)


![예약5](https://user-images.githubusercontent.com/82349358/122243407-10df8b00-ceff-11eb-884c-3eed85ab401c.gif)



### 14.글 작성(조회)
![글 작성-조회](https://user-images.githubusercontent.com/82349358/122232633-58154e00-cef6-11eb-99ec-01e1d9dba1a0.png)


![커뮤니티](https://user-images.githubusercontent.com/82349358/122234763-1a192980-cef8-11eb-8d71-33de73e2f706.gif)



### 15.후기리스트조회
![후기리스트조회](https://user-images.githubusercontent.com/82349358/122232973-a32f6100-cef6-11eb-9b00-68ac950a1b9d.png)



### 16.드림회원 마이페이지
![드림 마이페이지](https://user-images.githubusercontent.com/82349358/122242415-45067c00-cefe-11eb-91a9-f0640ecb7f97.png)


![dream_가고싶다](https://user-images.githubusercontent.com/82349358/122242510-58b1e280-cefe-11eb-8d50-8d613604c88c.gif)


![dream_감사후기](https://user-images.githubusercontent.com/82349358/122242529-5c456980-cefe-11eb-82a8-1486cd8b7d10.gif)


![dream_문의글](https://user-images.githubusercontent.com/82349358/122242545-5ea7c380-cefe-11eb-996e-10c4b2ed6c8c.gif)



### 17.MZ회원 마이페이지
![MZ 마이페이지](https://user-images.githubusercontent.com/82349358/122242298-2b653480-cefe-11eb-94f1-02cae4a9a3cd.png)


![MZ_가고싶다_1](https://user-images.githubusercontent.com/82349358/122238096-d247d180-cefa-11eb-8134-edd3b9166073.gif)


![MZ_돈쭐목록](https://user-images.githubusercontent.com/82349358/122238114-d70c8580-cefa-11eb-850e-031031ddc759.gif)


![MZ_돈쭐목록_small](https://user-images.githubusercontent.com/82349358/122238127-da077600-cefa-11eb-8995-5300f473d743.gif)


![MZ_문의글](https://user-images.githubusercontent.com/82349358/122238142-dd026680-cefa-11eb-9283-df887bc05b82.gif)


![MZ_추천 수정](https://user-images.githubusercontent.com/82349358/122238161-df64c080-cefa-11eb-90a0-07e1aa21ad05.gif)


![MZ_후기 수정](https://user-images.githubusercontent.com/82349358/122238167-e12e8400-cefa-11eb-9874-079bf34168bb.gif)



### 18.마이페이지(회원정보수정)
![회원정보수정](https://user-images.githubusercontent.com/82349358/122242770-8d259e80-cefe-11eb-997e-251db8675a7e.png)



### 19.마이페이지(회원탈퇴)
![회원탈퇴](https://user-images.githubusercontent.com/82349358/122250058-505ca600-cf04-11eb-8121-ca9c974fba61.png)


### 20.사업자 마이페이지
![사업자 마이페이지](https://user-images.githubusercontent.com/82349358/122247725-8731bc80-cf02-11eb-9ee9-b1456ff55760.png)


![예약6](https://user-images.githubusercontent.com/82349358/122243593-35d3fe00-ceff-11eb-8c4a-11d108b614db.gif)


![사업자_문의글](https://user-images.githubusercontent.com/82349358/122247798-96186f00-cf02-11eb-887f-da06187231d6.gif)



### 21.사업자 마이페이지(가게수정)
![가게수정](https://user-images.githubusercontent.com/82349358/122242850-9adb2400-cefe-11eb-9b22-6d5df8319f87.png)



### 22.관리자 마이페이지
![관리자 마이페이지](https://user-images.githubusercontent.com/82349358/122242621-6ff0d000-cefe-11eb-8ef7-149739c1ef33.png)



![우리의 노고](https://user-images.githubusercontent.com/82349358/122247840-9e70aa00-cf02-11eb-96d1-1f32229e2ee4.gif)


## Document

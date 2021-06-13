package com.donzzul.spring.partnermypage.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.common.Pagination;
import com.donzzul.spring.notiqna.domain.Qna;
import com.donzzul.spring.notiqna.service.QnaService;
import com.donzzul.spring.payment.service.PaymentService;
import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.reservation.service.ReservationService;
import com.donzzul.spring.shop.domain.MainMenu;
import com.donzzul.spring.shop.domain.MenuPhoto;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.shop.service.ShopService;
import com.donzzul.spring.user.domain.User;
import com.donzzul.spring.user.service.UserService;

@Controller
public class PartnerMyPageController {

	@Autowired
	private ReservationService rService;

	@Autowired
	private PaymentService pService;

	@Autowired
	private QnaService qService;

	@Autowired
	private UserService uService;

	@Autowired
	private ShopService sService;

	// 사업자 마이페이지 출력
	@RequestMapping(value = "partnerMyPage.dz", method = RequestMethod.GET)
	public String partnerMyPageView(HttpSession session, Model model) { // @RequestParam("userNo") int userNo, Model
																		// model, requestParam("userNo") int userNo

		User loginUser = (User) session.getAttribute("loginUser"); // 로그인세션(사업자)
		int userNo = loginUser.getUserNo();

		ArrayList<Reservation> rList = null;
		ArrayList<Qna> qList = null;
		Shop myShop = pService.selectMyShop(userNo);
		// 예약 목록 3개
		if (myShop != null) {
			rList = rService.listByShopToThree(myShop.getShopNo());
			qList = qService.shopQnaUpToThree(myShop.getShopNo());
		} else {
			qList = qService.dreamQnaUpToThree(userNo);
		}
		try {
			model.addAttribute("rList", rList);
			model.addAttribute("Rmsg", "데이터가 없습니다.");
			// 문의글 3개
			model.addAttribute("qList", qList);
			model.addAttribute("Qmsg", "문의 데이터가 없습니다.");

			model.addAttribute("shop", myShop);

			return "partnerMyPage/partnerMyPage";

		} catch (Exception e) {
			model.addAttribute("Rmsg", "데이터가 없습니다.");
			model.addAttribute("Qmsg", "문의 데이터가 없습니다.");
			return "partnerMyPage/partnerMyPage";
		}

	}

	// 예약 상태(업데이트 - 대기, 승인, 거부)
	@RequestMapping(value = "updateReservation.dz", method = RequestMethod.POST)
	public String updateReservationState(@RequestParam("reservationNo") int reservationNo,
			@RequestParam("rState") String rState, @RequestParam("shopNo") int shopNo, Model model) {
//		예약기본상태 O(default)
//		예약승인 Y(comfirm)
//		예약취소 X(cancle)
//		예약완료 C(complete)
		Reservation reservation = new Reservation();
		reservation.setrState(rState);
		reservation.setReservationNo(reservationNo);
		reservation.setShopNo(shopNo);

		int result = rService.updateRstate(reservation);
		return "redirect:partnerMyPage.dz";

	}

	// 방문완료
	@RequestMapping(value = "completeReservation.dz", method = RequestMethod.GET)
	public String completeReservation(@RequestParam("reservationNo") int reservationNo,
			@RequestParam("rState") String rState, @RequestParam(value = "mainPage", required = false) String mainPage,
			@RequestParam("shopNo") int shopNo, Model model) {
		// required =false 들어오는 변수가 꼭 필요하지않아도 된다는 뜻이다!
		System.out.println("mainPage" + mainPage);
		if (rState.equals("Y")) {
			Reservation reservation = new Reservation();
			reservation.setrState("C");
			reservation.setReservationNo(reservationNo);
			reservation.setShopNo(shopNo);
			System.out.println("shopNo" + shopNo);
			int result = rService.updateRstate(reservation); // rState 변경
			if (result > 0 && mainPage.equals("Y")) {
				rService.updateShopPoint(reservation);
				return "redirect:partnerMyPage.dz";
			} else if (result > 0 && mainPage.equals("N")) {
				return "redirect:partnerReserveList.dz";
			}
		} else {
			model.addAttribute("msg", "예약 상태 변경에 실패했습니다.");
			return "redirect:partnerMyPage.dz";
		}
		model.addAttribute("msg", "예약 상태 변경에 실패했습니다.");
		return "redirect:partnerMyPage.dz";
	}

	// 예약 더보기(풀 리스트)
	@RequestMapping(value = "partnerReserveList.dz", method = RequestMethod.GET)
	public ModelAndView partnerReserveAllList(ModelAndView mv, HttpSession session,
			@RequestParam(value = "page", required = false) Integer page) {
		// loginUser, shop 가져오기
		User loginUser = (User) session.getAttribute("loginUser");
		Shop myShop = pService.selectMyShop(loginUser.getUserNo());
		ArrayList<Reservation> rList = null;
		PageInfo pi = null;
		if (myShop != null) {
			// 페이징 pi
			int currentPage = (page != null) ? page : 1;
			int listCount = rService.selectShopListCount(myShop.getShopNo());
			pi = Pagination.getPageInfo(currentPage, listCount);
			rList = rService.reservaionListByShop(myShop.getShopNo(), pi);
		}

		// 예약 전체 리스트 가져오기
		try {
			mv.addObject("rList", rList);
			mv.addObject("pi", pi);
			mv.addObject("msg", "불러올 데이터가 없습니다.");
			mv.setViewName("partnerMyPage/partnerReservationDetail"); // 전체 예약페이지로 가기!!!(바꾸기)

		} catch (Exception e) {
			mv.addObject("msg", "불러올 데이터가 없습니다.");
			mv.setViewName("partnerMyPage/partnerReservationDetail"); // 전체 예약페이지로 가기!!!(바꾸기)
		}
		return mv;
	}

	// 사업자 qna 전체 불러오기
	@RequestMapping(value = "allQnaListByPartner.dz", method = RequestMethod.GET)
	public ModelAndView allQnaListByPartner(HttpSession session, ModelAndView mv,
			@RequestParam(value = "page", required = false) Integer page) {
		User loginUser = (User) session.getAttribute("loginUser"); // 로그인세션(사업자)
		int userNo = loginUser.getUserNo();

		Shop myShop = pService.selectMyShop(userNo);

		int currentPage = (page != null) ? page : 1;
		int listCount = qService.dreamListCount(userNo);
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		ArrayList<Qna> qList = null;
		if (myShop != null) {
			qList = qService.qnaListByPartner(myShop.getShopNo(), pi);
		} else {
			qList = qService.qnaListBydream(userNo, pi);
		}
		try {
			mv.addObject("shop", myShop);
			mv.addObject("qList", qList);
			mv.addObject("pi", pi);
			mv.setViewName("partnerMyPage/partnerQnaDetail");
		} catch (Exception e) {
			mv.addObject("msg", "불러올 문의 데이터가 없습니다.");
			mv.setViewName("partnerMyPage/partnerQnaDetail");
		}
		return mv;
	}

	// 사업자 포인트 환급신청
	@RequestMapping(value = "refundsPartnerPoint.dz", method = RequestMethod.GET)
	public void refundsPoint(HttpServletResponse response, HttpSession session, @ModelAttribute User user, Model model)
			throws Exception {
		// 환급신청(전체)
		// userNo랑 shopNo 통해서 shop의 shopPoint랑 shopPointYN 변경(update)

		// 내 가게 조회
		User loginUser = (User) session.getAttribute("loginUser");
		Shop myShop = pService.selectMyShop(loginUser.getUserNo());

		// 유저 포인트 업데이트(환급금액 옮김)
		User changePointUser = new User();
		changePointUser.setUserNo(loginUser.getUserNo());
		changePointUser.setUserPoint(myShop.getShopPoint());

		// 포인트 환급신청
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		if (myShop != null) {
			// 환급할 포인트가 0보다 크면
			if (myShop.getShopPoint() > 0) {
				System.out.println("가게 포인트:" + myShop.toString());
				int shopPointandYN = pService.applyRefundsShopPoint(myShop.getShopNo(), changePointUser);
				if (shopPointandYN > 0) {
					System.out.println("환급신청 YN 업데이트");
					// alert창으로 2-3일 내에 포인트가 환급됩니다 띄우기 - model
					out.println(
							"<script>alert('환급신청이 완료되었습니다. \\n2-3일 내에 포인트가 환급됩니다.');location.href='partnerMyPage.dz';</script>");
					out.flush();
				} else {
					out.println("<script>alert('포인트 환급신청에 실패하였습니다.');location.href='/';</script>");
					out.flush();
				}
			} else {
				// 환급할 포인트가 0보다 작거나 같으면
				System.out.println("가게 포인트:" + myShop.toString());
				out.println("<script>alert('신청할 포인트가 존재하지 않습니다.'); location.href='partnerMyPage.dz';</script>");
				out.flush();
			}
		} else {
			out.println("<script>alert('내 가게 조회에 실패하였습니다.');location.href='/';</script>");
			out.flush();
		}
	}

	// 가게정보 등록 화면(view)
	@RequestMapping(value = "shopRegisterView.dz", method = RequestMethod.GET)
	public String shopRegisterView(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		int userNo = user.getUserNo();
		String partnerName = user.getPartnerName();
		String userPhone = user.getUserPhone();
		model.addAttribute("partnerName", partnerName).addAttribute("userPhone", userPhone).addAttribute("userNo",
				userNo);
		return "partnerMyPage/partnerShopJoin";
	}

	// 가게정보 등록
	@RequestMapping(value = "shopRegister.dz", method = RequestMethod.POST)
	public String shopRegister(@ModelAttribute Shop shop, @RequestParam String[] mainMenuName,
			@RequestParam int[] mainMenuPrice, @RequestParam("zip") String zip, HttpServletRequest request,
			@RequestParam("addr1") String addr1, @RequestParam("addr2") String addr2,
			@RequestParam("shopTypeNum") int shopTypeNum,
			@RequestParam(value = "businessNum", required = false) String[] businessnum,
			@RequestParam(value = "shopPhoto", required = false) MultipartFile shopPhoto,
			@RequestParam(value = "mainMenuPhoto", required = false) MultipartFile[] mainMenuPhoto, Model model,
			@RequestParam("userNo") int userNo) {

		///////////// INSERT - 샵 정보 저장, 메뉴 저장, 메뉴 사진 저장

		/////// 샵 정보 저장 먼저 insert
		// 영업일
		String businessday = "";
		for (int i = 0; i < businessnum.length; i++) {
			if (i == businessnum.length - 1) {
				businessday += businessnum[i];
			} else {
				businessday += businessnum[i] + ",";
			}
		}
		System.out.println(businessnum);
		// 가게 SET
		String shopType = switchShopType(shopTypeNum);
		shop.setShopType(shopType);
		shop.setShopLat(null);
		shop.setShopLng(null);
		shop.setShopAddr(addr1 + " " + addr2);
		shop.setShopLongAddr(zip + "/" + addr1 + "/" + addr2);
		shop.setBusinessDay(businessday);
		shop.setShowShopYN("N");
		// 가게 파일 저장(서버, 디비)
		Shop fileShop = saveFile(shopPhoto, request);
		if (fileShop != null) {
			shop.setShopFileName(fileShop.getShopFileName());
			shop.setShopFilePath(fileShop.getShopFilePath());
			shop.setShopFileSize(fileShop.getShopFileSize());
			shop.setShopUploadTime(fileShop.getShopUploadTime());
		}
		/////// 샵 정보, 메뉴, 메뉴 사진 인서트 or 업데이트
		int result = sService.insertPartnerShop(shop);

		System.out.println("샵 넘버 가져오기" + shop.getShopNo());

		/////// 메뉴 사진 insert
		MenuPhoto menuPhoto = new MenuPhoto();
		// 서버에 파일 저장하는 작업
		for (int i = 0; i < mainMenuPhoto.length; i++) {
			if (!mainMenuPhoto[i].getOriginalFilename().equals("")) { // 비어있지 않으면(!)
				MenuPhoto photoFile = saveMultiFile(mainMenuPhoto[i], request);
				System.out.println("set 전" + photoFile.toString());
				if (photoFile != null) {
					menuPhoto.setMenuFileName(photoFile.getMenuFileName());
					menuPhoto.setMenuFilePath(photoFile.getMenuFilePath());
					menuPhoto.setMenuFileSize(photoFile.getMenuFileSize());
					menuPhoto.setMenuUploadTime(photoFile.getMenuUploadTime());
					menuPhoto.setShopNo(shop.getShopNo());
					System.out.println("set 후" + menuPhoto.toString());
					int insertMenuPhoto = sService.insertMenuPhoto(menuPhoto);
					if (insertMenuPhoto < 0) { // insert 가 실패하면 for문 빠져나오기
						break;
					}
				}
			}
		}

		/////// 메인메뉴 등록 insert
//		System.out.println("메뉴 정보 받아오나?"+mainMenu.toString());
//		for(int i=0; i<mainMenu.length; i++) {
//			mainMenu[i].setShopNo(shop.getShopNo());
//			int insertMainMenu = sService.insertMainMenu(mainMenu[i]);
//			System.out.println("메뉴"+mainMenu[i].toString());
//			if(insertMainMenu > 0) {
//				return  "redirect:partnerMyPage.dz";
//			} else {
//				model.addAttribute("msg", "Shop등록실패");
//				return "common/errorPage";
//			}
//		}
		// 메뉴 이름과 가격을 배열로 받아서 객체에 set해주고 insert 해주는 모든 과정을 for문으로 돌림
		// (그 수만큼 메뉴 값 들어감)
		for (int i = 0; i < mainMenuName.length; i++) {
			MainMenu main = new MainMenu();
			main.setMainMenuName(mainMenuName[i]);
			main.setMainMenuPrice(mainMenuPrice[i]);
			main.setShopNo(shop.getShopNo());
			// 메뉴정보 저장 insert문
			int insertMainmenu = sService.insertMainMenu(main);
		}

		return "redirect:partnerMyPage.dz";
	}

	// 가게정보 수정 화면(view)
	@RequestMapping(value = "shopUpdateView.dz", method = RequestMethod.GET)
	public String shopUpdateView(HttpServletRequest request, Model model) {
		// 유저 조회
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		int userNo = user.getUserNo();
		// 샵 조회
		Shop shop = sService.selectShopOneUserNo(userNo);
		String partnerName = user.getPartnerName();
		String userPhone = user.getUserPhone();

		// long 주소 자르기
		String longAddr = shop.getShopLongAddr();
		System.out.println(longAddr);
		String addr[] = longAddr.split("/");
		for (int i = 0; i < addr.length; i++) {
			System.out.println(addr[i]);
		}

		// 영업일 자르기
		String businessDay = shop.getBusinessDay();
		System.out.println(businessDay);
		String day[] = businessDay.split(",");
		for (int i = 0; i < day.length; i++) {
			System.out.println(day[i]);
		}

		// 메뉴 사진 조회
		ArrayList<MenuPhoto> photo = sService.selectMenuPhoto(shop.getShopNo());
		// 메뉴 조회
		ArrayList<MainMenu> menu = sService.selectMainMenu(shop.getShopNo());

		model.addAttribute("partnerName", partnerName).addAttribute("userPhone", userPhone).addAttribute("userNo",
				userNo);
		model.addAttribute("shop", shop);
		model.addAttribute("photo", photo);
		model.addAttribute("mainMenu", menu);
		return "partnerMyPage/partnerShopInfo";
	}

	// 가게정보 수정
	@RequestMapping(value = "shopUpdate.dz", method = RequestMethod.POST)
	public String shopUpdate(@ModelAttribute Shop shop, @RequestParam("zip") String zip, HttpServletRequest request,
			@RequestParam("addr1") String addr1, @RequestParam("addr2") String addr2,
			@RequestParam("shopTypeNum") int shopTypeNum,
			@RequestParam(value = "businessNum", required = false) String[] businessnum,
			@RequestParam(value = "shopPhoto", required = false) MultipartFile shopPhoto,
			@RequestParam(value = "mainMenuPhoto", required = false) MultipartFile[] mainMenuPhoto, Model model,
			@RequestParam("userNo") int userNo, @RequestParam("mainMenuName") String[] mainMenuName,
			@RequestParam("mainMenuPrice") int[] mainMenuPrice

	) {

		////////// jsp 데이터 받아서 인서트(업데이트)
		// - shop(update), menuPhoto(delete-insert), mainMenu(delete - insert)

		/////// shop update
		// 영업일 저장
		String businessday = "";
		for (int i = 0; i < businessnum.length; i++) {
			if (i == businessnum.length - 1) {
				businessday += businessnum[i];
			} else {
				businessday += businessnum[i] + ",";
			}
		}
		String shopType = switchShopType(shopTypeNum);
		shop.setShopType(shopType);
		shop.setShopLat(null);
		shop.setShopLng(null);
		shop.setShopAddr(addr1 + " " + addr2);
		shop.setShopLongAddr(zip + "/" + addr1 + "/" + addr2);
		shop.setBusinessDay(businessday);
		shop.setShowShopYN("N");

		// 가게 파일 저장(서버, 디비)
		Shop fileShop = saveFile(shopPhoto, request);
		if (fileShop != null) {
			shop.setShopFileName(fileShop.getShopFileName());
			shop.setShopFilePath(fileShop.getShopFilePath());
			shop.setShopFileSize(fileShop.getShopFileSize());
			shop.setShopUploadTime(fileShop.getShopUploadTime());
		}
		// shop UPDATE
		int result = sService.updatePartnerShop(shop);

		/////// 메뉴 사진 delete-insert
		// delete
//		ArrayList<MenuPhoto> deletePhotoList = sService.selectMenuPhoto(shop.getShopNo());
//		System.out.println("삭제할 사진리스트 조회"+deletePhotoList.toString());
//		if(deletePhotoList != null) { // 삭제할 사진이 있으면 사진 전부 삭제
//			int deletePhoto = sService.deleteMenuPhoto(shop.getShopNo());
//		}
		// insert - 다시 insert
		MenuPhoto menuPhoto = new MenuPhoto();
		for (int i = 0; i < mainMenuPhoto.length; i++) {
			if (!mainMenuPhoto[i].getOriginalFilename().equals("")) {
				MenuPhoto photoFile = saveMultiFile(mainMenuPhoto[i], request);
				if (photoFile != null) {
					menuPhoto.setMenuFileName(photoFile.getMenuFileName());
					menuPhoto.setMenuFilePath(photoFile.getMenuFilePath());
					menuPhoto.setMenuFileSize(photoFile.getMenuFileSize());
					menuPhoto.setMenuUploadTime(photoFile.getMenuUploadTime());
					menuPhoto.setShopNo(shop.getShopNo());

					// menuPhoto(delete-insert)
					int insertMenuPhoto = sService.insertMenuPhoto(menuPhoto);
					if (insertMenuPhoto < 0) {
						break;
					}
				}
			}
		}

		for (int i = 0; i < mainMenuName.length; i++) { // 메뉴이름과 가격 수 똑같으므로
			System.out.println("값" + mainMenuName[i]);
			System.out.println("값" + mainMenuName[i]);
		}

		/////// 메인메뉴 delete-insert
		// delete
		ArrayList<MainMenu> menuList = sService.selectMainMenu(shop.getShopNo());
		System.out.println("삭제할 메인메뉴 리스트: " + menuList.toString());
		if (menuList != null) { // 메뉴가 있으면 전부 삭제
			int delelteMenu = sService.deleteMainMenu(shop.getShopNo());
		}
		// insert - 다시 인서트
		for (int i = 0; i < mainMenuName.length; i++) { // 메뉴이름과 가격 수 똑같으므로
			MainMenu menu = new MainMenu();
			menu.setMainMenuName(mainMenuName[i]);
			menu.setMainMenuPrice(mainMenuPrice[i]);
			menu.setShopNo(shop.getShopNo());
			System.out.println("값" + mainMenuName[i]);
			// mainMenu(delete - insert) 오똫게 . . . . . .ㅠ_ㅠ
			int insertMainMenu = sService.insertMainMenu(menu);
//			if(insertMainMenu > 0) {
//				return  "redirect:partnerMyPage.dz";
//			} else {
//				model.addAttribute("msg", "Shop 수정 실패");
//				return "common/errorPage";
//			}
		}

		return "redirect:partnerMyPage.dz";
	}

	// 가게 수정 - 메뉴 사진 삭제하기
	@ResponseBody
	@RequestMapping(value = "deleteMenuPhoto.dz", method = RequestMethod.POST)
	public String deleteMenuPhoto(@RequestParam("deletePhotoList") ArrayList<String> deletePhotoList) {

		System.out.println("ajax로 받아온 배열 삭제할 파일 이름: " + deletePhotoList.toString());


		for (int i = 0; i < deletePhotoList.size(); i++) {
			String deletePhotoName = deletePhotoList.get(i);
			System.out.println("하나씩 꺼낸 사진 이름" + deletePhotoName);
			
			// 서버에서 삭제하기 위해(파일패스)
			ArrayList<MenuPhoto> selectPhotoList = sService.selectMenuPhotoPath(deletePhotoName);
			System.out.println("디비에서 불러온 서버용 삭제할 사진리스트 조회"+selectPhotoList.toString());
			
			// 디비에서 이름으로 삭제
			int deletePhoto = sService.deleteMenuPhoto(deletePhotoName);
			if (deletePhoto > 0) { // 삭제 완료 후 (돌아가기)
				// 파일 이름이랑 경로랑 뽑아서 서버에서 파일 삭제
				String filePath = selectPhotoList.get(i).getMenuFilePath();
				String fileName = deletePhotoName;
				fileDelete(fileName, filePath);
			}
		}
		return "success";
	}

	// 서버에서 사진파일 삭제하기
	public void fileDelete(String fileName, String filePath) {
		// 존재하면 삭제
		File deleteFile = new File(filePath+fileName);
//		deleteFile.setExecutable(true); // 파일 권한 주기
		if(deleteFile.exists()) {
			deleteFile.delete();
			System.out.println("서버에서 파일 삭제 완료");
		}else {
			System.out.println("서버에 파일 없음");
		}
	}
	
	

	// 버튼별 예약 현황 다르게 보여주기
	@ResponseBody
	@RequestMapping(value = "reservationStatue.dz", method = { RequestMethod.GET, RequestMethod.POST })
	public ArrayList<HashMap<String, String>> reservationStatue(@RequestParam("shopNo") int shopNo,
			@RequestParam("rState") String rState) {
		Reservation reservation = new Reservation();
		reservation.setrState(rState);
		reservation.setShopNo(shopNo);
		ArrayList<Reservation> rList = rService.reservationState(reservation);

		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < rList.size(); i++) {
			String orderDate = rList.get(i).getOrderDate();
			String dateResult = "20" + orderDate.replace('/', '-');
			rList.get(i).setOrderDate(dateResult);

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("title", rList.get(i).getDescription());
			map.put("start", rList.get(i).getReserveDate());

			map.put("userNick", rList.get(i).getUserNick());
			map.put("reserveDate", rList.get(i).getReserveDate());
			map.put("reserveTime", String.valueOf(rList.get(i).getReserveTime()));
			map.put("reserveCount", String.valueOf(rList.get(i).getReserveCount()));
			map.put("orderDate", rList.get(i).getOrderDate());
			list.add(map);
		}
		return list;
	}

	// 회원탈퇴 요청전 비밀번호 입력뷰
	@RequestMapping(value = "partnerWritePwView.dz", method = RequestMethod.GET)
	public String partnerWritePwView() {
		return "partnerMyPage/partnerWritePw";
	}

// 비밀번호 유효성 검사
	@ResponseBody
	@RequestMapping(value = "partnerdupPw.dz", method = RequestMethod.GET)
	public String pwDuplicateCheck(@RequestParam("userNo") String userNo, @RequestParam("userPw") String userPw) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userNo", userNo);
		map.put("userPw", userPw);
		int duplicateCheck = uService.checkPwDup(map);
		if (duplicateCheck == 0) {
			return 0 + "";
		} else {
			return 1 + "";
		}
	}

	// 회원탈퇴
	@RequestMapping(value = "partnerDelete.dz", method = RequestMethod.GET)
	public String partnerDelete(@RequestParam("userNo") int userNo, Model model) {
		// 탈퇴신청하고 user 상태 바꿔줌
//		int result = uService.updatePartnerWithdraw(userNo); // 수정하기
		int result = -2;
		if (result > 0) {
			return "redirect:logout.dz";
		} else {
			model.addAttribute("msg", "회원탈퇴 실패");
			return "common/errorPage";
		}
	}

	// 다중 파일저장 (메뉴 사진)
	public MenuPhoto saveMultiFile(MultipartFile multiFile, HttpServletRequest request) {

//		ArrayList<MenuPhoto> fileList = multiFile

		// 파일 저장 경로 설정
		String root = request.getSession().getServletContext().getRealPath("resources");
		// String root = "\\resources";
		// String root = request.getContextPath(); // return 프로젝트 Path
		// System.out.println("root"+root);
		String savePath = root + "\\partnerUploadFiles\\menuPhoto";
		// 저장 폴더 선택
		File folder = new File(savePath);

		// 폴더 없으면 자동생성
		if (!folder.exists()) {
			folder.mkdir();
		}

		// 파일명 변경하기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
		String originalFileName = multiFile.getOriginalFilename();
		String renameFileName = sdf.format(new Date(System.currentTimeMillis())) + "."
				+ originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
//		String renameFileName = UUID.randomUUID() + "." + originalFileName.substring(originalFileName.lastIndexOf(".")+1);

		// 파일저장
		String filePath = folder + "\\" + renameFileName;
		int fileSize = 0;

		// 파일명 변경하기
//		originalFileName = multiFile.getOriginalFilename();
//		renameFileName = sdf.format(new Date(System.currentTimeMillis())) + "." + originalFileName.substring(originalFileName.lastIndexOf(".")+1);

//		String originalName = multiFile.getOriginalFilename();
//			String original
//			String saveFileName = 

		// 파일저장
		try {
			multiFile.transferTo(new File(filePath));
			fileSize = (int) multiFile.getSize();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		MenuPhoto filePhoto = new MenuPhoto();
		filePhoto.setMenuFileName(renameFileName);
		filePhoto.setMenuFilePath(savePath);
		filePhoto.setMenuFileSize(fileSize);
		filePhoto.setMenuUploadTime(timestamp);

		// 리턴
		return filePhoto;

	}

	// 파일저장 (메인 사진)
	public Shop saveFile(MultipartFile file, HttpServletRequest request) {
		// 파일 저장 경로 설정
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\partnerUploadFiles\\shopPhoto";
		// 저장 폴더 선택
		File folder = new File(savePath);

		// 폴더 없으면 자동생성
		if (!folder.exists()) {
			folder.mkdir();
		}

		// 파일명 변경하기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
		String originalFileName = file.getOriginalFilename();
		String renameFileName = sdf.format(new Date(System.currentTimeMillis())) + "."
				+ originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
		// abc.jpg 중의 확장자명을 가져오기위해
		// a.bc.jpg의 경우 오른쪽만 가져오게하기위해
		String filePath = folder + "\\" + renameFileName;
		int fileSize = 0;
		// 파일저장
		try {
			file.transferTo(new File(filePath));
			fileSize = (int) file.getSize();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Shop fileShop = new Shop();
		fileShop.setShopFileName(renameFileName);
		fileShop.setShopFilePath(savePath);
		fileShop.setShopFileSize(fileSize);
		fileShop.setShopUploadTime(timestamp);

		// 리턴
		return fileShop;
	}

	public String switchShopType(int shopTypeNum) {
		switch (shopTypeNum) {
		case 1:
			return "한식";
		case 2:
			return "분식";
		case 3:
			return "일식";
		case 4:
			return "중식";
		case 5:
			return "양식";
		case 6:
			return "기타";
		default:
			return "기타";
		}
	}

}

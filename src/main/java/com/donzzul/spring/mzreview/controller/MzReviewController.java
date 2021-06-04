package com.donzzul.spring.mzreview.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.common.Pagination;
import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.mzreview.domain.MzReviewPhoto;
import com.donzzul.spring.mzreview.service.MzReviewService;
import com.donzzul.spring.pick.domain.Pick;
import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.shop.service.ShopService;
import com.donzzul.spring.user.domain.User;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

@Controller
public class MzReviewController {

	@Autowired
	private MzReviewService mService;
	@Autowired
	private ShopService sService;
	
	// 맛집후기 주소 selectAll
	@RequestMapping(value="mReviewMain.dz", method=RequestMethod.GET)
	public ModelAndView mReviewMainView(ModelAndView mv,  @RequestParam(value="page", required=false) Integer page) {
		int currentPage = (page != null) ? page : 1;
		int listCount = mService.getListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		ArrayList<MzReview> mList = mService.selectAllReview(pi);
		System.out.println(mList.toString());
		if(!mList.isEmpty()) {
			mv.addObject("mList", mList).addObject("pi", pi);
		} else {
			mv.addObject("msg", "게시글이 없습니다");
		}
		mv.setViewName("board/mzReview/mReviewListView");
		return mv;
	}
	
	// 디테일 selectOne
	@RequestMapping(value="mReviewDetail.dz", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView mReviewDetailView(ModelAndView mv, @RequestParam("mzReviewNo") int mzReviewNo, HttpServletResponse response, HttpServletRequest request) {
		MzReview mReview = mService.selectOneReview(mzReviewNo);
		if(mReview != null) {
			updateMzHit(response, request, mzReviewNo);
			mv.addObject("mReview", mReview).setViewName("board/mzReview/mReviewDetailView");
		} else {
			mv.addObject("msg", "맛집후기 게시글 상세 조회 실패").setViewName("common/errorPage");
		}
		return mv;
	}
	
	public void updateMzHit(HttpServletResponse response, HttpServletRequest request, int mzReviewNo) {
		User user = (User)request.getAttribute("loginUser");
		Cookie[] reqCookie = request.getCookies(); // 기존존재 쿠키가져옴
		Cookie nullCookie = null; // null 비교쿠키
		
		if(reqCookie != null && reqCookie.length > 0  && user != null) { // 로그인 되어있는 경우
			for(int i = 0; i < reqCookie.length; i++) {
				if(reqCookie[i].getName().equals("mReview" + user.getUserNo() + mzReviewNo)) {
					nullCookie = reqCookie[i];
				}
			}
		}
		if(reqCookie != null && reqCookie.length > 0 && user == null) { // 비로그인
			for(int i = 0; i < reqCookie.length; i++) {
				if(reqCookie[i].getName().equals("mReview"+mzReviewNo)) {
					nullCookie = reqCookie[i];
				}
			}
		}
		if(user != null && nullCookie == null) { // 로그인되어있는데 쿠키가 비어있음
			Cookie cookie = new Cookie("mReview"+user.getUserNo() + mzReviewNo, "mReview"+user.getUserNo() + mzReviewNo);
			cookie.setMaxAge(60*60*24*365);
			response.addCookie(cookie);
			
			int result = mService.updateHit(mzReviewNo);
			
			if(result > 0 ) {
				System.out.println("조회수 증가 성공");
			} else if(result <= 0) {
				System.out.println("조회수 증가 실패");
			}
		}
		
		if(user == null && nullCookie == null) { // 로그인X
			Cookie cookie = new Cookie("mReview" + mzReviewNo, "mReview" + mzReviewNo);
			cookie.setMaxAge(60*60*24*365);
			response.addCookie(cookie);
			int result = mService.updateHit(mzReviewNo);
			
			if(result > 0 ) {
				System.out.println("조회수 증가 성공");
			} else if(result <= 0) {
				System.out.println("조회수 증가 실패");
			}
		}
	}
	
	// 맛집후기 작성페이지 - 글쓰기버튼으로 들어옴 *****
	@RequestMapping(value="mReviewWriteView.dz", method=RequestMethod.GET)
	public String mReviewWriteView(@RequestParam("shopNo") int shopNo, @RequestParam("reservationNo") int reservationNo, Model model ) {
		Shop shop = sService.selectShopOne(shopNo);
		model.addAttribute("shopNo", shopNo);
		model.addAttribute("reservationNo", reservationNo);
		model.addAttribute("shop", shop);
		return "board/mzReview/mReviewInsertForm";
	}
	
	// 글쓰기 올림 (사진파일추가) insert
	@ResponseBody
	@RequestMapping(value="mReviewInsertForm.dz", method=RequestMethod.POST)
	public String mReviewRegister(@ModelAttribute MzReview mzReview, 
										@RequestParam("reservationNo") int reservationNo, 
										HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		mzReview.setmReviewWriter(user.getUserNick());
		mzReview.setUserType(user.getUserType());
		mzReview.setUserNo(user.getUserNo());
		ArrayList<MzReviewPhoto> mzPhotoList = null;
		
		if(session.getAttribute("mzPhotoList") != null) {
			mzPhotoList = (ArrayList<MzReviewPhoto>)session.getAttribute("mzPhotoList");
			System.out.println("에디터 사진 모음 : " + mzPhotoList.toString());
		}
		
		String target = mzReview.getmReviewContent();
		Pattern pattern = Pattern.compile("<img[^>]*src=[\\\"']?([^>\\\"']+)[\\\"']?[^>]*>");
		Matcher matcher = pattern.matcher(target);
		String rtn = "false";
		ArrayList<String> realList = new ArrayList<String>();
		
		
		// rState update 예약상태변경
		Reservation reservation = new Reservation();
		reservation.setReservationNo(reservationNo);
		reservation.setrState("H");
		
		int result = 0;
		result = mService.insertMzReview(mzReview, reservation);
		if(result > 0) {
			while(matcher.find()) {
				String realName = matcher.group(1).substring(matcher.group(1).lastIndexOf("=") + 1);
				realList.add(realName);
			}
			for(int i = 0; i < mzPhotoList.size(); i++) {
				String mzPhotoRename = mzPhotoList.get(i).getMzReviewRenameFileName();
				if(!realList.contains(mzPhotoRename)) {
					fileDelete(mzPhotoRename, mzPhotoList.get(i).getMzReviewFilePath());
				}
			}
			rtn = "success";
			//mv.setViewName("home");
		} else {
			rtn = "fail";
			//mv.addObject("msg", "맛집후기 글쓰기 실패").setViewName("common/errorPage");
		}
		return rtn;
	}
	
	
	
	// 삭제 delete
	// @ResponseBody // 스프링에서 ajax를 사용하는데, 그 값을 받아서 쓰고싶을때 반드시 필요함
	@RequestMapping(value="mReviewDelete.dz", method=RequestMethod.GET)
	public String mReviewDelete(@RequestParam("mReviewNo") int mReviewNo, Model model) {
		ArrayList<MzReviewPhoto> photoList = mService.selectPhoto(mReviewNo);
		int result = mService.deleteMzReview(mReviewNo);
		if(result > 0) {
			for(int i = 0; i < photoList.size(); i++) {
				String mzPhotoRename = photoList.get(i).getMzReviewRenameFileName();
				String mzPhotoFilePath = photoList.get(i).getMzReviewFilePath();
				fileDelete(mzPhotoRename, mzPhotoFilePath);
			}
			return "redirect:mReviewMain.dz";
		} else {
			model.addAttribute("msg", "게시글 삭제에 실패했습니다.");
			return "common/errorPage";
		}
	}
	
	// 파일삭제
	public void fileDelete(String fileName, String filePath) {
		// 존재하면 삭제
		File deleteFile = new File(filePath+fileName);
		deleteFile.setExecutable(true);
		if(deleteFile.exists()) {
			deleteFile.delete();
			System.out.println("삭제");
		} else {
			System.out.println("파일 존재안함");
		}
	}
	
	// 파일불러오기
	ArrayList<MzReviewPhoto> mzPhotoList = new ArrayList<MzReviewPhoto>();
	@RequestMapping(value="/uploadMReviewImg", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request )  {
		
		HttpSession session = request.getSession(); // 세션
		JsonObject jsonObject = new JsonObject();
		// 내부경로로 저장
		String contextRoot = request.getSession().getServletContext().getRealPath("resources");
		String fileRoot = contextRoot+"\\boardImg\\MzReview\\";
		
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
		String savedFileName = "M" + UUID.randomUUID() + extension;	//저장될 파일 명
		MzReviewPhoto mzReviewPhoto = new MzReviewPhoto();
		session.removeAttribute("mzReviewPhoto");
		
		File targetFile = new File(fileRoot + savedFileName);	
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			jsonObject.addProperty("url", "/Mupload/imageView.dz?imgName="+savedFileName); // contextroot + resources + 저장할 내부 폴더명
			jsonObject.addProperty("responseCode", "success");
			mzReviewPhoto.setMzReviewOriginalFileName(originalFileName); // 사진원본이름
			mzReviewPhoto.setMzReviewRenameFileName(savedFileName); // 사진 바뀐이름
			mzReviewPhoto.setMzReviewFileSize(targetFile.length()); // 사진 사이즈
			mzReviewPhoto.setMzReviewFilePath(fileRoot);
			mzReviewPhoto.setMzReviewFileTime(timestamp); // 사진 타임스탬프
			
			mzPhotoList.add(mzReviewPhoto);
	        session.setAttribute("mzPhotoList", mzPhotoList);
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		String a = jsonObject.toString();
		return a;
	}
	
	@RequestMapping(value="/Mupload/imageView.dz")
	public void summerNoteImageView(@RequestParam("imgName") String imgName, HttpServletResponse response, HttpServletRequest request) throws Exception {
		OutputStream out = response.getOutputStream();
        FileInputStream fis = null;
        
        String contextRoot = request.getSession().getServletContext().getRealPath("resources");
		String fileRoot = contextRoot+"/boardImg/MzReview/";
        try {
            fis = new FileInputStream(fileRoot+imgName);
            FileCopyUtils.copy(fis, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            out.flush();
            
        }
	}
	
	// 수정 주소
	@RequestMapping(value="mReviewUpdateView.dz", method=RequestMethod.GET)
	public ModelAndView mReviewUpdateView(@RequestParam("mReviewNo") int mReviewNo, ModelAndView mv, HttpServletResponse response) throws IOException {
		MzReview mzReview = mService.selectOneReview(mReviewNo);
		if(mzReview != null) {
			mv.addObject("mzReview", mzReview);
			mv.setViewName("board/mzReview/mReviewUpdateForm");
		} else {
			mv.addObject("msg", "수정페이지 불러오기 실패함");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 수정함 update
	@ResponseBody
	@RequestMapping(value="mReviewModify.dz", method=RequestMethod.POST)
	public String mReviewUpdate(@ModelAttribute MzReview mzReview, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int mzReviewNo = mzReview.getmReviewNo();
		ArrayList<MzReviewPhoto> mzPhotoList = null;
		ArrayList<MzReviewPhoto> beforemzPhotoList = mService.selectPhoto(mzReviewNo);
		int beforePhotoResult = 0;
		
		// 사진을 업로드하면 기존 테이블에 있던 이미지 삭제
		if(session.getAttribute("mzPhotoList") != null) {
			mzPhotoList = (ArrayList<MzReviewPhoto>)session.getAttribute("mzPhotoList");
			System.out.println("에디터 사진 : " + mzPhotoList.toString());
			beforePhotoResult = mService.deleteBeforePhoto(mzReviewNo);
			if(beforePhotoResult > 0) {
				System.out.println("사진 삭제 성공");
			} else {
				System.out.println("사진 삭제 실패");
			}
		}
		
		String target = mzReview.getmReviewContent();
		Pattern pattern = Pattern.compile("<img[^>]*src=[\\\"']?([^>\\\"']+)[\\\"']?[^>]*>"); // 이미지 잘라내기
		Matcher matcher = pattern.matcher(target);
		String rtn = "false";
		
		mzPhotoList.addAll(beforemzPhotoList); // 세션에서 받은 사진, 이전에 있던 사진 합침
		ArrayList<String> realList = new ArrayList<String>();
		int result = mService.updateMzReview(mzReview);
		if(result > 0) {
			while(matcher.find()) {
				String realName = matcher.group(1).substring(matcher.group(1).lastIndexOf("=") + 1); 
				realList.add(realName); // 게시글내용 코드에서 잘라온 img태그 이름들
			}
			for(int i = 0; i < mzPhotoList.size(); i++) {
				String mzRename = mzPhotoList.get(i).getMzReviewRenameFileName();
				if(!realList.contains(mzRename)) {
					fileDelete(mzRename, mzPhotoList.get(i).getMzReviewFilePath());
					beforePhotoResult = mService.deleteBeforePhoto(mzReviewNo);
					if (beforePhotoResult > 0) {
						System.out.println("사진 삭제 성공");
					} else {
						System.out.println("사진 삭제 실패");
					}
					continue;
				}
				
				mzPhotoList.get(i).setMzReviewNo(mzReviewNo);
				int photoResult = mService.insertPhoto(mzPhotoList.get(i));
				if(photoResult > 0) {
					rtn = "success";
				}
			}
			rtn = "success";
		} else {
			rtn = "fail";
		}
		return rtn;
	}
	
	//D 맛집후기 가져오기
	@RequestMapping(value="mzReviewShop.dz", method=RequestMethod.GET)
	public void selectMzReview(@RequestParam("shopNo") int shopNo, HttpServletResponse response) throws Exception {
		ArrayList<MzReview> mzList = mService.selectAllMzReview(shopNo);
	
		response.setContentType("application/json"); // json 객체로 전달시 파라미터 값 다름("text/html;charset=utf-8")
		response.setCharacterEncoding("utf-8"); // 데이터 한글 변환 위해 필수 작성!!
		
		Gson gson = new Gson();
		gson.toJson(mzList, response.getWriter());
	}
	
	// mz마이페이지에서 후기 삭제하고 새로 3개 리스트 뽑아오기
	@ResponseBody // 스프링에서 ajax를 사용하는데, 그 값을 받아서 쓰고싶을때 반드시 필요함
	@RequestMapping(value="mReviewDeleteAndSelectThree.dz", method=RequestMethod.GET)
	public ArrayList<MzReview> mReviewDeleteAndSelectThree(@RequestParam("mReviewNo") int mReviewNo, Model model, HttpSession session) {
		ArrayList<MzReviewPhoto> photoList = mService.selectPhoto(mReviewNo);
		int result = mService.deleteMzReview(mReviewNo);
		if(result > 0) {
			for(int i = 0; i < photoList.size(); i++) {
				String mzPhotoRename = photoList.get(i).getMzReviewRenameFileName();
				String mzPhotoFilePath = photoList.get(i).getMzReviewFilePath();
				fileDelete(mzPhotoRename, mzPhotoFilePath);
			}
			User user = (User)session.getAttribute("loginUser");
			ArrayList<MzReview> mList = mService.selectThreeReviewToMyPage(user.getUserNo());
			
			return mList;
		} else {
			return null;
		}
	}
}

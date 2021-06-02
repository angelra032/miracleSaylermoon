package com.donzzul.spring.recommendboard.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.CookieGenerator;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.common.Pagination;
import com.donzzul.spring.recommendboard.domain.RecommendBoard;
import com.donzzul.spring.recommendboard.domain.RecommendPhoto;
import com.donzzul.spring.recommendboard.service.RecommendBoardService;
import com.donzzul.spring.user.domain.User;
import com.google.gson.JsonObject;

@Controller
public class RecommendBoardController {
	
	@Autowired
	private RecommendBoardService reService;
	
	// 주소로 들어옴 (리스트출력할곳) selectAll
	@RequestMapping(value="recommendMain.dz", method=RequestMethod.GET)
	public ModelAndView recommendMainView(ModelAndView mv, @RequestParam(value="page", required=false) Integer page, HttpServletResponse response, HttpServletRequest request) {
		
		int currentPage = (page != null) ? page : 1;
		int listCount = reService.getListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		ArrayList<RecommendBoard> reList = reService.selectAllRecommend(pi);
		if(!reList.isEmpty()) {
			mv.addObject("rList", reList).addObject("pi", pi);
		} else {
			mv.addObject("msg", "가게추천 게시글이 없습니다");
		}
		mv.setViewName("board/recommend/recommendListView");
		return mv;
	}
	
	
	// 디테일 selectOne
	@RequestMapping(value="recommendDetail.dz", method=RequestMethod.GET)
	public ModelAndView recommendDetailView(ModelAndView mv, 
											@RequestParam("recommendNo") int recommendNo, 
											HttpServletResponse response,
											HttpServletRequest request) {
		
		// 쿠키
		User user = (User)request.getAttribute("loginUser");
		Cookie[] reqCookie = request.getCookies(); // 기존존재 쿠키가져옴
		Cookie nullCookie = null; // null 비교쿠키
		
		if(reqCookie != null && reqCookie.length > 0  && user != null) { // 로그인 되어있는 경우
			for(int i = 0; i < reqCookie.length; i++) {
				if(reqCookie[i].getName().equals("cookie" + user.getUserNo() + recommendNo)) {
					nullCookie = reqCookie[i];
				}
			}
		}
		if(reqCookie != null && reqCookie.length > 0 && user == null) { // 비로그인
			for(int i = 0; i < reqCookie.length; i++) {
				if(reqCookie[i].getName().equals("cookie"+recommendNo)) {
					nullCookie = reqCookie[i];
				}
			}
		}
		if(user != null && nullCookie == null) { // 로그인되어있는데 쿠키가 비어있음
			Cookie cookie = new Cookie("cookie"+user.getUserNo() + recommendNo, "cookie"+user.getUserNo() + recommendNo);
			cookie.setMaxAge(60*60*24*365);
			response.setHeader("Set-Cookie", "Test1=TestCookieValue1; Secure; SameSite=None");
			response.addHeader("Set-Cookie", "Test2=TestCookieValue1; Secure; SameSite=None");
			response.addCookie(cookie);
			
			int result = reService.updateHit(recommendNo);
			
			if(result > 0 ) {
				System.out.println("조회수 증가 성공");
			} else if(result <= 0) {
				System.out.println("조회수 증가 실패");
			}
		}
		
		if(user == null && nullCookie == null) { // 로그인X
			Cookie cookie = new Cookie("cookie" + recommendNo, "cookie" + recommendNo);
			cookie.setMaxAge(60*60*24*365);
			response.addCookie(cookie);
			int result = reService.updateHit(recommendNo);
			
			if(result > 0 ) {
				System.out.println("조회수 증가 성공");
			} else if(result <= 0) {
				System.out.println("조회수 증가 실패");
			}
		}
		
		
		
		RecommendBoard recommendBoard = reService.selectOneRecommend(recommendNo);
		if(recommendBoard != null) {
			mv.addObject("recommendBoard", recommendBoard).setViewName("board/recommend/recommendDetailView");
		} else {
			mv.addObject("msg", "게시글 상세 조회 실패").setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 감사후기 글쓰기버튼으로 들어옴 
	@RequestMapping(value="recommendWriteView.dz", method=RequestMethod.GET)
	public String recommendWriteView() {
		return "board/recommend/recommendInsertForm";
	}
	
	
	// 글쓰기 올림 (사진파일추가) insert
	@ResponseBody
	@RequestMapping(value="recommendInsertForm.dz", method= RequestMethod.POST)
	public String recommendRegister(@ModelAttribute RecommendBoard recommendBoard,
									HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		recommendBoard.setUserType(user.getUserType());
		recommendBoard.setRecommendWriter(user.getUserNick());
		recommendBoard.setUserNo(user.getUserNo()); // recommendBoard 데이터 저장
		ArrayList<RecommendPhoto> rPhotoList = null;
		
		if(session.getAttribute("rPhotoList") != null) {
			rPhotoList = (ArrayList<RecommendPhoto>)session.getAttribute("rPhotoList"); // 데이터 담아줄 ArrayList
			System.out.println("에디터 사진 모음" + rPhotoList.toString());
		}
		String target = recommendBoard.getRecommendContent();
		Pattern pattern = Pattern.compile("<img[^>]*src=[\\\"']?([^>\\\"']+)[\\\"']?[^>]*>"); // 이미지 잘라내기
		Matcher matcher = pattern.matcher(target);
		String rtn = "false";
		ArrayList<String> realList = new ArrayList<String>();
		int result = reService.insertRecommend(recommendBoard);
		if(result > 0) {
			while(matcher.find()) {
				String realName = matcher.group(1).substring(matcher.group(1).lastIndexOf("=") + 1);
				realList.add(realName);
			}
			for(int i = 0; i < rPhotoList.size(); i++) {
				String recommendRename = rPhotoList.get(i).getRecommendRenameFileName();
				//System.out.println("비교 값 : " + realList.contains(recommendRename));
				if(!realList.contains(recommendRename)) {
					fileDelete(recommendRename, rPhotoList.get(i).getRecommendFilePath());
				}
			}
			rtn = "success";
		}else {
			rtn = "fail";
		}
		return rtn;
//		while(matcher.find()) {
//			//String path = matcher.group(1).substring(0, matcher.group(1).lastIndexOf("/") + 1);
//			//String savedName = matcher.group(1).substring(matcher.group(1).lastIndexOf("/") + 1);
//			//System.out.println("패스 : " + path);
//			//System.out.println("세입네임 : " + savedName);
//			String realName =  matcher.group(1).substring(matcher.group(1).lastIndexOf("=") + 1);
//			System.out.println("리얼네임 : " + realName);
//			
//			if(rPhotoList.size() != 0) {
//				for(RecommendPhoto recoPhoto : rPhotoList) {
//					System.out.println(recoPhoto.getRecommendRenameFileName());
//					if(recoPhoto.getRecommendRenameFileName().equals(realName)) { // 저장되는 이름과 업로드된 파일의 이름이 같은 경우
//						recoPhoto.setRecommendNo(recommendBoard.getRecommendNo());
//						int photoResult = reService.insertPhoto(recoPhoto);
//						if(photoResult > 0) {
//							copyFile(recoPhoto, request);
//						}
//					} else { // 저장되는 이름에 업로드된 파일이 없는 경우
//						System.out.println("실패테스트");
//						// fileDelete(recoPhoto.getRecommendRenameFileName(), recoPhoto.getRecommendFilePath());
//						// 파일삭제
//					}
//				}
//				
//			} else {
//				// 파일삭제
//				System.out.println("삭제실패테스트");
//			}
//		}
		
//			session.removeAttribute("rPhotoList");
//			count = 0;
//			return "success";
//		} else {
//			session.removeAttribute("rPhotoList");
//			count = 0;
//			return "fail";
//		}
	}
	
	// 파일 넣기
//	public void fileInsert(HttpServletRequest request, RecommendBoard recommendBoard) {
//		HttpSession session = request.getSession();
//		ArrayList<RecommendPhoto> rPhotoList = (ArrayList<RecommendPhoto>)session.getAttribute("rPhotoList"); // 데이터 담아줄 ArrayList
//		if(rPhotoList != null) { // 업로드 된 사진이 있으면
//			System.out.println("에디터 사진 모음" + rPhotoList.toString());
//			String target = recommendBoard.getRecommendContent();
//			Pattern pattern = Pattern.compile("<img[^>]*src=[\\\"']?([^>\\\"']+)[\\\"']?[^>]*>"); // 이미지 잘라내기
//			Matcher matcher = pattern.matcher(target);
//			
//			while(matcher.find()) {
//				String path = matcher.group(1).substring(0, matcher.group(1).lastIndexOf("/") + 1);
//				String savedName = matcher.group(1).substring(matcher.group(1).lastIndexOf("/") + 1);
//				System.out.println("패스 : " + path);
//				System.out.println("세입네임 : " + savedName);
//				String realName =  matcher.group(1).substring(matcher.group(1).lastIndexOf("=") + 1);
//				System.out.println("리얼네임 : " + realName);
//				
//				if(rPhotoList.size() != 0) {
//					System.out.println("이프테스트1" + rPhotoList.toString());
//					for(RecommendPhoto recoPhoto : rPhotoList) {
//						System.out.println(" 뭥미 ? : " + recoPhoto.getRecommendRenameFileName() + " 뭥미 ? : " + realName);
//						if(recoPhoto.getRecommendRenameFileName().equals(realName)) { // 저장되는 이름과 업로드된 파일의 이름이 같은 경우
//							recoPhoto.setRecommendNo(recommendBoard.getRecommendNo());
//							int photoResult = reService.insertPhoto(recoPhoto);
//						} else { // 저장되는 이름에 업로드된 파일이 없는 경우
//							System.out.println("실패테스트");
//							fileDelete(recoPhoto.getRecommendRenameFileName(), recoPhoto.getRecommendFilePath());
//							// 파일삭제
//						}
//					}
//					
//				} else {
//					// 파일삭제
//)					System.out.println("삭제실패테스트");
//				}
//			}
//			
//		} else { // 업로드 된 사진 없으면
//			return;
//		}
//	}
	
	
	// 파일삭제
	public void fileDelete(String fileName, String filePath) {
//		File file = new File(filePath); // 파일 패스 조회
//		file.setWritable(true);
//		String fileList[] = file.list();
////		System.out.println("****파일패스 : " + filePath);
//		for(int i = 0; i < fileList.length; i++) { // 전체파일
//			String fName = fileList[i]; // 파일명 찾기
//			System.out.println("****파일이름 : " + fName);
//			
//			if(fName.contains(fileName)) {
				// 존재하면 삭제
				File deleteFile = new File(filePath+fileName);
				deleteFile.setExecutable(true);
				if(deleteFile.exists()) {
					deleteFile.delete();
					System.out.println("삭제");
				} else {
					System.out.println("파일 존재안함");
				}
//				if(deleteFile.delete()) {
//					System.out.println("파일을 삭제");
//				} else {
//					System.out.println("실패");
//				}
//			}
			
//		}
	}
	
	// 파일
//	@ResponseBody
//	@RequestMapping(value="/uploadSummernoteImageFile",produces = "application/json; charset=utf-8", method= {RequestMethod.POST, RequestMethod.GET})
//	public JsonObject uploadSummernoteImageFile(MultipartFile multipartFile) {
//		
//		JsonObject jsonObject = new JsonObject();
//		
//		String fileRoot = "C:\\summernote_image\\";	//저장될 외부 파일 경로
//		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
//		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
//				 한
//		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
//		
//		File targetFile = new File(fileRoot + savedFileName);	
//		
//		try {
//			InputStream fileStream = multipartFile.getInputStream();
//			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
//			jsonObject.addProperty("url", "/summernoteImage/"+savedFileName);
//			jsonObject.addProperty("responseCode", "success");
//				
//		} catch (IOException e) {
//			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
//			jsonObject.addProperty("responseCode", "error");
//			e.printStackTrace();
//		}
//		
//		return jsonObject;
//	}
	
	//
	int count = 0;
	@RequestMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request )  {
		// 세션 attribute에 저장해서 옮기는 방법도 있음.
		JsonObject jsonObject = new JsonObject();
		// 내부경로로 저장
		String contextRoot = request.getSession().getServletContext().getRealPath("resources");
		String fileRoot = contextRoot+"\\fileupload\\";
//		String fileRoot = "C:/Users/dlwnd/git/donjjul/src/main/webapp/resources/fileupload/";	//저장될 외부 파일 경로
		
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
		String savedFileName = "R" + UUID.randomUUID() + extension;	//저장될 파일 명
		RecommendPhoto recommendPhoto = new RecommendPhoto();
		HttpSession session = request.getSession(); // 세션
		session.removeAttribute("rPhotoList");
		
		File targetFile = new File(fileRoot + savedFileName);	
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
			jsonObject.addProperty("url", "/summernote/imageView.dz?imgName="+savedFileName); // contextroot + resources + 저장할 내부 폴더명
			jsonObject.addProperty("responseCode", "success");
			recommendPhoto.setRecommendOriginalFileName(originalFileName); // 사진원본이름
			recommendPhoto.setRecommendRenameFileName(savedFileName); // 사진 바뀐이름
			recommendPhoto.setRecommendFileSize(targetFile.length()); // 사진 사이즈
			recommendPhoto.setRecommendFilePath(fileRoot);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			recommendPhoto.setRecommendFileTime(timestamp); // 사진 타임스탬프
			session.setAttribute("recommendPhoto", recommendPhoto); // 세션에 저장...?
			
			System.out.println("어쩌냐.. : " + recommendPhoto.toString());
			rPhotoList.add(recommendPhoto);
			count++;
			System.out.println("1차 어레이리스트 체크 :  " + rPhotoList.toString());
	        session.setAttribute("rPhotoList", rPhotoList);
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		String a = jsonObject.toString();
		return a;
	}
	
	
	ArrayList<RecommendPhoto> rPhotoList = new ArrayList<RecommendPhoto>();
	@RequestMapping(value="/summernote/imageView.dz")
	public void summerNoteImageView(@RequestParam("imgName") String imgName, HttpServletResponse response, HttpServletRequest request) throws Exception {
		OutputStream out = response.getOutputStream();
        FileInputStream fis = null;
        
        String contextRoot = request.getSession().getServletContext().getRealPath("resources");
		String fileRoot = contextRoot+"/fileupload/";
//		RecommendPhoto recommendPhoto = (RecommendPhoto) session.getAttribute("recommendPhoto");
//		recommendPhoto.setRecommendFilePath(contextRoot);
		
		
		// 세션에 arrayList넣을수있음.
		// 차곡차곡 저장 후 ... 나중에 삭제하기
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

	// 삭제 delete
	// @ResponseBody // 스프링에서 ajax를 사용하는데, 그 값을 받아서 쓰고싶을때 반드시 필요함
	@RequestMapping(value="recommendDelete.dz", method=RequestMethod.GET)
	public String recommendDelete(@RequestParam("recommendNo") int recommendNo, Model model) {
		int result = reService.deleteRecommend(recommendNo);
		if(result > 0 ) {
			return "redirect:recommendMain.dz";
		} else {
			model.addAttribute("msg", "게시글 삭제에 실패했습니다.");
			return "common/errorPage";
		}
	}
	
	
	// 수정버튼누름
	@RequestMapping(value="recommendUpdateForm.dz", method=RequestMethod.GET)
	public ModelAndView recommendUpdateView(ModelAndView mv, @RequestParam("recommendNo") int recommendNo) {
		RecommendBoard recommendBoard = reService.selectOneRecommend(recommendNo);
		if(recommendBoard != null) {
			mv.addObject("recommendBoard", recommendBoard).setViewName("board/recommend/recommendUpdateForm");
		} else {
			mv.setViewName("redirect:recommendMain.dz:");
		}
		return mv;
	}
	
	// 수정함 update
//	@ResponseBody
//	@RequestMapping(value="recommendModify.dz", method= {RequestMethod.GET, RequestMethod.POST})
//	public String recommendUpdate(@ModelAttribute RecommendBoard recommendBoard, HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		int recommendNo = recommendBoard.getRecommendNo();
//		ArrayList<RecommendPhoto> beforePhotoList = reService.selectPhoto(recommendNo); // 이전데이터값
//		ArrayList<RecommendPhoto> rPhotoList = (ArrayList<RecommendPhoto>)session.getAttribute("rPhotoList"); // 올린 사진 데이터 담아준 ArrayList
//		
//		int result = reService.updateRecommend(recommendBoard);
//		if(result > 0) { // board 수정 성공하면
//		// 필요한 경우의 수 (사진추가수정X/ 업데이트, 사진추가업로드O 삭제X 업데이트/ 사진추가업로드O 삭제O /사진추가업로드X 삭제O)
//			if(rPhotoList != null) { // 세션에서 불러온 리스트가 있다면
//				String target = recommendBoard.getRecommendContent();
//				Pattern pattern = Pattern.compile("<img[^>]*src=[\\\"']?([^>\\\"']+)[\\\"']?[^>]*>"); // 이미지 잘라내기
//				Matcher matcher = pattern.matcher(target);
//				
//				while(matcher.find()) {
//					String path = matcher.group(1).substring(0, matcher.group(1).lastIndexOf("/") + 1);
//					String savedName = matcher.group(1).substring(matcher.group(1).lastIndexOf("/") + 1);
//					String realName =  matcher.group(1).substring(matcher.group(1).lastIndexOf("=") + 1);
//					for(RecommendPhoto recoPhoto : rPhotoList) { // 세션에서 가져온값 for
//						for(RecommendPhoto beforePhoto : beforePhotoList) { // DB에 저장된값
//							String beforeDBname = beforePhoto.getRecommendRenameFileName(); // 이전에올려진놈
//							String newInsertName = recoPhoto.getRecommendRenameFileName(); // 이번세션에 올라온놈
//							
//							if(recoPhoto.getRecommendRenameFileName().equals(realName)) { // 업로드한 사진과 실제올린 사진 비교
//								recoPhoto.setRecommendNo(recommendBoard.getRecommendNo());
//								if(beforeDBname.equals(newInsertName)) {
//									System.out.println("같은 파일을 그대로 업데이트 하셨습니다");
////									fileDelete(beforePhoto.getRecommendRenameFileName(), beforePhoto.getRecommendFilePath());
////									int pResult = reService.insertPhoto(recoPhoto);
//								} else if(!beforeDBname.equals(newInsertName)) {
//									System.out.println("같은 파일이 없습니다");
//									int pResult = reService.insertPhoto(recoPhoto);
////									fileDelete(beforePhoto.getRecommendRenameFileName(), beforePhoto.getRecommendFilePath());
//									System.out.println("포토올라옴");
//								} 
//							} else {
////								fileDelete(beforePhoto.getRecommendRenameFileName(), beforePhoto.getRecommendFilePath());
////								fileDelete(recoPhoto.getRecommendRenameFileName(), recoPhoto.getRecommendFilePath());
//							}
//							
//						}
//					}
//				}
//				session.removeAttribute("rPhotoList");
//				return "success";
//			} else {
//				session.removeAttribute("rPhotoList");
//				return "success";
//			}
//		} else { // 게시글 업데이트 실패
//			session.removeAttribute("rPhotoList");
//			return "fail";
//		}
////			int photoResult = reService.deleteBeforePhoto(recommendNo);
//	}
	
	@ResponseBody
	@RequestMapping(value="recommendModify.dz", method= {RequestMethod.GET, RequestMethod.POST})
	public String recommendUpdate(@ModelAttribute RecommendBoard recommendBoard, HttpServletRequest request) {
		HttpSession session = request.getSession();
		int recommendNo = recommendBoard.getRecommendNo();
		ArrayList<RecommendPhoto> beforePhotoList = reService.selectPhoto(recommendNo); // 이전데이터값
		ArrayList<RecommendPhoto> rPhotoList = (ArrayList<RecommendPhoto>)session.getAttribute("rPhotoList"); // 올린 사진 데이터 담아준 ArrayList
		ArrayList<String> realList = new ArrayList<String>();
		
		String target = recommendBoard.getRecommendContent();
		Pattern pattern = Pattern.compile("<img[^>]*src=[\\\"']?([^>\\\"']+)[\\\"']?[^>]*>"); // 이미지 잘라내기
		Matcher matcher = pattern.matcher(target);
		
		while(matcher.find()) {
			String realName = matcher.group(1).substring(matcher.group(1).lastIndexOf("=") + 1);
			realList.add(realName);
		}
		
		int result = reService.updateRecommend(recommendBoard);
		if(result > 0) { // 글수정 성공하면
			for(RecommendPhoto beforePhoto : beforePhotoList) {
				fileDelete(beforePhoto.getRecommendRenameFileName(), beforePhoto.getRecommendFilePath()); // 파일삭제
			}
			int pResult = reService.deleteBeforePhoto(recommendNo); // 테이블에서 삭제
			if(pResult > 0) { // 사진삭제 성공하면
				System.out.println("삭제성공");
				for(int i = 0; i < beforePhotoList.size(); i++) {
					String recommendRename = beforePhotoList.get(i).getRecommendRenameFileName();
					//System.out.println("비교 값 : " + realList.contains(recommendRename));
					if(realList.contains(recommendRename)) {
						//reService.insertPhoto(recoPhoto);
						System.out.println("잇는 것만 디비에 저장");
					}
				}
				for(RecommendPhoto recoPhoto : rPhotoList) {
					System.out.println("모조리 저장");
				}
			}
			return "success";
		} else { // 게시글 업데이트 실패
			System.out.println("게시글업데이트 실패");
			session.removeAttribute("rPhotoList");
			count = 0;
			return "fail";
		}
	}
	
	// 파일이동
	public void copyFile(RecommendPhoto recoPhoto, HttpServletRequest request ) {
		// 원본위치
		String contextRoot = request.getSession().getServletContext().getRealPath("resources");
		String fileRoot = contextRoot+"\\\\board\\recommend\\"; // 카피위치
		
		String targetPath = recoPhoto.getRecommendFilePath();
		String targetFileName = recoPhoto.getRecommendRenameFileName();
		
		File target = new File(targetPath+targetFileName);
		File dst = new File(fileRoot+targetFileName);
		
		try {
			FileUtils.moveFile(target, dst);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	// 수정함 update
//		@ResponseBody
//		@RequestMapping(value="recommendModify.dz", method= {RequestMethod.GET, RequestMethod.POST})
//		public String recommendUpdate(@ModelAttribute RecommendBoard recommendBoard, HttpServletRequest request) {
//			HttpSession session = request.getSession();
//			int recommendNo = recommendBoard.getRecommendNo();
//			int photoResult = reService.deleteBeforePhoto(recommendNo);
//			
//			ArrayList<RecommendPhoto> rPhotoList = (ArrayList<RecommendPhoto>)session.getAttribute("rPhotoList"); // 데이터 담아줄 ArrayList
//			if(!rPhotoList.isEmpty()) { // 세션에서 불러온 리스트가 null 아니라면
//				String target = recommendBoard.getRecommendContent();
//				Pattern pattern = Pattern.compile("<img[^>]*src=[\\\"']?([^>\\\"']+)[\\\"']?[^>]*>"); // 이미지 잘라내기
//				Matcher matcher = pattern.matcher(target);
//				
//
//			} else {
//				
//			}
//			int result = reService.updateRecommend(recommendBoard);
//			if(result > 0) {
//				//fileInsert(request, recommendBoard);
//				session.removeAttribute("rPhotoList");
//				return "success";
//			} else {
//				session.removeAttribute("rPhotoList");
//				return "fail";
//			}
//		}
	// 수정함 update
//		@ResponseBody
//		@RequestMapping(value="recommendModify.dz", method= {RequestMethod.GET, RequestMethod.POST})
//		public String recommendUpdate(@ModelAttribute RecommendBoard recommendBoard, HttpServletRequest request) {
//			HttpSession session = request.getSession();
//			int recommendNo = recommendBoard.getRecommendNo();
//			int photoResult = reService.deleteBeforePhoto(recommendNo);
//			
//			ArrayList<RecommendPhoto> rPhotoList = (ArrayList<RecommendPhoto>)session.getAttribute("rPhotoList"); // 데이터 담아줄 ArrayList
//			if(!rPhotoList.isEmpty()) { // 세션에서 불러온 리스트가 null 아니라면
//				String target = recommendBoard.getRecommendContent();
//				Pattern pattern = Pattern.compile("<img[^>]*src=[\\\"']?([^>\\\"']+)[\\\"']?[^>]*>"); // 이미지 잘라내기
//				Matcher matcher = pattern.matcher(target);
//				
//
//			} else {
//				
//			}
//			int result = reService.updateRecommend(recommendBoard);
//			if(result > 0) {
//				//fileInsert(request, recommendBoard);
//				session.removeAttribute("rPhotoList");
//				return "success";
//			} else {
//				session.removeAttribute("rPhotoList");
//				return "fail";
//			}
//		}
}

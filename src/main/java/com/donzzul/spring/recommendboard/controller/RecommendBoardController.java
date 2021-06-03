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
	
	private static final int HashMap = 0;
	private static final int String = 0;
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
		
		
		
		
		RecommendBoard recommendBoard = reService.selectOneRecommend(recommendNo);
		if(recommendBoard != null) {
			updateRecommendHit(response, request, recommendNo); // 조회수
			mv.addObject("recommendBoard", recommendBoard).setViewName("board/recommend/recommendDetailView");
		} else {
			mv.addObject("msg", "게시글 상세 조회 실패").setViewName("common/errorPage");
		}
		return mv;
	}
	
	public void updateRecommendHit(HttpServletResponse response, HttpServletRequest request, int recommendNo) {
		User user = (User)request.getAttribute("loginUser");
		Cookie[] reqCookie = request.getCookies(); // 기존존재 쿠키가져옴
		Cookie nullCookie = null; // null 비교쿠키
		
		if(reqCookie != null && reqCookie.length > 0  && user != null) { // 로그인 되어있는 경우
			for(int i = 0; i < reqCookie.length; i++) {
				if(reqCookie[i].getName().equals("recommend" + user.getUserNo() + recommendNo)) {
					nullCookie = reqCookie[i];
				}
			}
		}
		if(reqCookie != null && reqCookie.length > 0 && user == null) { // 비로그인
			for(int i = 0; i < reqCookie.length; i++) {
				if(reqCookie[i].getName().equals("recommend"+recommendNo)) {
					nullCookie = reqCookie[i];
				}
			}
		}
		if(user != null && nullCookie == null) { // 로그인되어있는데 쿠키가 비어있음
			Cookie cookie = new Cookie("recommend"+user.getUserNo() + recommendNo, "recommend"+user.getUserNo() + recommendNo);
			cookie.setMaxAge(60*60*24*365);
			response.addCookie(cookie);
			
			int result = reService.updateHit(recommendNo);
			
			if(result > 0 ) {
				System.out.println("조회수 증가 성공");
			} else if(result <= 0) {
				System.out.println("조회수 증가 실패");
			}
		}
		
		if(user == null && nullCookie == null) { // 로그인X
			Cookie cookie = new Cookie("recommend" + recommendNo, "recommend" + recommendNo);
			cookie.setMaxAge(60*60*24*365);
			response.addCookie(cookie);
			int result = reService.updateHit(recommendNo);
			
			if(result > 0 ) {
				System.out.println("조회수 증가 성공");
			} else if(result <= 0) {
				System.out.println("조회수 증가 실패");
			}
		}
	}
	
	// 감사후기 글쓰기버튼으로 들어옴 
	@RequestMapping(value="recommendWriteView.dz", method=RequestMethod.GET)
	public String recommendWriteView() {
		return "board/recommend/recommendInsertForm";
	}
	
	
	// 글쓰기 올림 (사진파일추가) insert
	@ResponseBody
	@RequestMapping(value="recommendInsertForm.dz", method= RequestMethod.POST)
	public String recommendRegister(@ModelAttribute RecommendBoard recommendBoard, HttpServletRequest request) {
		
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
					continue;
				}
				int recommendNo = recommendBoard.getRecommendNo();
				rPhotoList.get(i).setRecommendNo(recommendNo);
				int photoResult = reService.insertPhoto(rPhotoList.get(i));
				if(photoResult > 0) {
					rtn = "success";
				}
			}
			rtn = "success";
		}else {
			rtn = "fail";
		}
		return rtn;
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
	
	
	//
	@RequestMapping(value="/uploadRecommendImg", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request )  {
		
		
		// 세션 attribute에 저장해서 옮기는 방법도 있음.
		HttpSession session = request.getSession(); // 세션
		JsonObject jsonObject = new JsonObject();
		// 내부경로로 저장
		String contextRoot = request.getSession().getServletContext().getRealPath("resources");
		String fileRoot = contextRoot+"\\boardImg\\Recommend\\";
		
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
		String savedFileName = "R" + UUID.randomUUID() + extension;	//저장될 파일 명
		RecommendPhoto recommendPhoto = new RecommendPhoto();
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
			
			rPhotoList.add(recommendPhoto);
	        session.setAttribute("rPhotoList", rPhotoList); // 사진을 ArrayList에 담아서 세션으로 보내준다
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
		String fileRoot = contextRoot+"/boardImg/Recommend/";
//		RecommendPhoto recommendPhoto = (RecommendPhoto) session.getAttribute("recommendPhoto");
//		recommendPhoto.setRecommendFilePath(contextRoot);
		
		
		// 업로드한 이미지를 보여줌
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
		ArrayList<RecommendPhoto> PhotoList = reService.selectPhoto(recommendNo);
		int result = reService.deleteRecommend(recommendNo);
		if(result > 0 ) {
			for(int i = 0; i < PhotoList.size(); i++) {
				String recommendRename = PhotoList.get(i).getRecommendRenameFileName();
				String recommendFilePath = PhotoList.get(i).getRecommendFilePath();
				fileDelete(recommendRename, recommendFilePath);
			}
			return "redirect:recommendMain.dz";
		} else {
			model.addAttribute("msg", "게시글 삭제에 실패했습니다.");
			return "common/errorPage";
		}
	}
	
	
	// 수정 주소
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
	
	// 수정
	@ResponseBody
	@RequestMapping(value="recommendModify.dz", method= {RequestMethod.GET, RequestMethod.POST})
	public String recommendUpdate(@ModelAttribute RecommendBoard recommendBoard, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int recommendNo = recommendBoard.getRecommendNo();
		ArrayList<RecommendPhoto> rPhotoList = null; // 올린 사진 데이터 담아준 ArrayList
		ArrayList<RecommendPhoto> beforePhotoList = reService.selectPhoto(recommendNo); // 이전데이터값
		int beforePhotoResult = 0;
		
		// 사진을 업로드하면 기존 테이블에 있던 이미지 삭제
		if(session.getAttribute("rPhotoList") != null) {
			rPhotoList = (ArrayList<RecommendPhoto>)session.getAttribute("rPhotoList");
			System.out.println("에디터 사진 모음 : " + rPhotoList.toString());
			beforePhotoResult = reService.deleteBeforePhoto(recommendNo);
			if (beforePhotoResult > 0) {
				System.out.println("사진 삭제 성공");
			} else {
				System.out.println("사진 삭제 실패");
			}
		}
		
		String target = recommendBoard.getRecommendContent();
		Pattern pattern = Pattern.compile("<img[^>]*src=[\\\"']?([^>\\\"']+)[\\\"']?[^>]*>"); // 이미지 잘라내기
		Matcher matcher = pattern.matcher(target);
		String rtn = "false";
		
		// 세션에서 받은 것과 이전에 있던 사진ArrayList 합치기
		rPhotoList.addAll(beforePhotoList);
		ArrayList<String> realList = new ArrayList<String>();
		int result = reService.updateRecommend(recommendBoard);
		if(result > 0) {
			while(matcher.find()) {
				String realName = matcher.group(1).substring(matcher.group(1).lastIndexOf("=") + 1); 
				realList.add(realName); // 게시글내용 코드에서 잘라온 img태그 이름들
			}
			for(int i = 0; i < rPhotoList.size(); i++) {
				String recommendRename = rPhotoList.get(i).getRecommendRenameFileName();
				if(!realList.contains(recommendRename)) {
					fileDelete(recommendRename, rPhotoList.get(i).getRecommendFilePath());
					beforePhotoResult = reService.deleteBeforePhoto(recommendNo);
					if (beforePhotoResult > 0) {
						System.out.println("사진 삭제 성공");
					} else {
						System.out.println("사진 삭제 실패");
					}
					continue;
				}
				
				rPhotoList.get(i).setRecommendNo(recommendNo);
				int photoResult = reService.insertPhoto(rPhotoList.get(i));
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
	
}

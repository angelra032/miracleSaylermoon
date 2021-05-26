package com.donzzul.spring.recommendboard.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
	public ModelAndView recommendMainView(ModelAndView mv, @RequestParam(value="page", required=false) Integer page) {
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
	public ModelAndView recommendDetailView(ModelAndView mv, @RequestParam("recommendNo") int recommendNo) {
		
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
//		@RequestParam("recommendTitle") String recommendTitle, @RequestParam("recommendContent") String recommendContent
//		@RequestParam(value="uploadFile", required=false)MultipartFile uploadFile, 
//		HttpServletRequest request, @ModelAttribute RecommendBoard recommendBoard,
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		recommendBoard.setUserType(user.getUserType());
		recommendBoard.setRecommendWriter(user.getUserNick());
		recommendBoard.setUserNo(user.getUserNo()); // recommendBoard 데이터 저장
		
		ArrayList<RecommendPhoto> recommendPhoto;// 데이터 담아줄 ArrayList
		String target = recommendBoard.getRecommendContent();
		Pattern pattern = Pattern.compile("<img[^>]*src=[\\\"']?([^>\\\"']+)[\\\"']?[^>]*>"); // 이미지 잘라내기
		Matcher matcher = pattern.matcher(target);
		while(matcher.find()) {
			String path = matcher.group(1).substring(0, matcher.group(1).lastIndexOf("/") + 1);
			String savedName = matcher.group(1).substring(matcher.group(1).lastIndexOf("/") + 1);
			System.out.println("패스 : " + path);
			System.out.println("세입네임 : " + savedName);
			String realName =  matcher.group(1).substring(matcher.group(1).lastIndexOf("=") + 1);
			System.out.println("리얼네임 : " + realName);
			
			
		}
		
		System.out.println(user.toString());
		System.out.println(recommendBoard.toString());
		int result = reService.insertRecommend(recommendBoard);
		if(result > 0) {
			return "success";
		} else {
			return "fail";
		}
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
	
	//***************************************************이미지완료 (지우지말자!!
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
		String savedFileName = "re" + UUID.randomUUID() + extension;	//저장될 파일 명
		RecommendPhoto recommendPhoto = new RecommendPhoto();
		HttpSession session = request.getSession(); // 세션
		
		File targetFile = new File(fileRoot + savedFileName);	
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
			jsonObject.addProperty("url", "/summernote/imageView.dz?imgName="+savedFileName); // contextroot + resources + 저장할 내부 폴더명
			jsonObject.addProperty("responseCode", "success");
			recommendPhoto.setRecommendOriginalFileName(originalFileName); // 사진원본이름
			recommendPhoto.setRecommendRenameFileName(savedFileName); // 사진 바뀐이름
			recommendPhoto.setRecommendFileSize(targetFile.length()); // 사진 사이즈
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			System.out.println(timestamp);
			recommendPhoto.setRecommendFileTime(timestamp); // 사진 타임스탬프
			session.setAttribute("recommendPhoto", recommendPhoto); // 세션에 저장...?
			
			
				
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		String a = jsonObject.toString();
		return a;
	}
	
	@RequestMapping(value="/summernote/imageView.dz")
	public void summerNoteImageView(@RequestParam("imgName") String imgName, HttpServletResponse response, HttpServletRequest request) throws Exception {
		OutputStream out = response.getOutputStream();
        FileInputStream fis = null;
        
        String contextRoot = request.getSession().getServletContext().getRealPath("resources");
		String fileRoot = contextRoot+"\\fileupload\\";
		HttpSession session = request.getSession();
		RecommendPhoto recommendPhoto = (RecommendPhoto) session.getAttribute("recommendPhoto");
		System.out.println(recommendPhoto.toString());
		recommendPhoto.setRecommendFilePath(contextRoot);
		System.out.println("테스트 : " + recommendPhoto.toString());
		ArrayList<RecommendPhoto> rPhotoList = new ArrayList<RecommendPhoto>();
		rPhotoList.add(recommendPhoto);
		session.setAttribute("rPhotoList", rPhotoList);
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
	//***************************************************이미지완료 (지우지말자!!

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
	public String recommendUpdateView() {
		return "";
	}
	
	// 수정함 update
	@RequestMapping(value="recommendModify.dz", method=RequestMethod.GET)
	public String recommendUpdate(@ModelAttribute RecommendBoard recommendBoard) {
		return "";
	}
}

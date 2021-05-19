package com.donzzul.spring.recommendboard.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.donzzul.spring.recommendboard.domain.RecommendBoard;
import com.donzzul.spring.recommendboard.domain.RecommendBoardPage;
import com.donzzul.spring.recommendboard.service.RecommendBoardService;

@Controller
public class RecommendBoardController {
	
	@Autowired
	private RecommendBoardService reService;
	
	// 주소로 들어옴 (리스트출력할곳) selectAll
	@RequestMapping(value="recommendMain.dz", method=RequestMethod.GET)
	public ModelAndView recommendMainView(ModelAndView mv, @RequestParam(value="page", required=false) Integer page) {
		int currentPage = (page != null) ? page : 1;
		int listCount = reService.getListCount();
		RecommendBoardPage pi = getPageInfo(currentPage, listCount);
		ArrayList<RecommendBoard> reList = reService.selectAllRecommend(pi);
		if(!reList.isEmpty()) {
			mv.addObject("rList", reList).addObject("pi", pi);
		} else {
			mv.addObject("msg", "가게추천 게시글이 없습니다");
		}
		mv.setViewName("board/recommend/recommendListView");
		return mv;
	}
	
	// 페이지 객체 리턴
	public RecommendBoardPage getPageInfo(int currentPage, int listCount) {
		RecommendBoardPage pi = null;
		int pageLimit = 5; // 한페이지당 보여줄 네비게이션 갯수
		int boardLimit = 10;	// 한 페이지에서 보여줄 게시글의 갯수
		
		int maxPage;		// 전체페이지 중 가장 마지막 페이지
		int startPage;		// 현재페이지에서 시작하는 첫번째 페이지
		int endPage;		// 현재 페이지에서 끝나는 마지막 페이지
		
		// 일반적인 페이지 계산법 // 0.9의 이유 : 0.1로 나왔을 때 int변환하면 0이 되어버리기 때문에 이를 방지하기 위해서다.
		maxPage = (int)((double) listCount/boardLimit + 0.9);
		startPage = (((int)((double)currentPage/pageLimit + 0.9)) - 1) * pageLimit + 1;
		endPage = startPage + pageLimit - 1;
		
		// 오류방지용
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		pi = new RecommendBoardPage(currentPage, boardLimit, pageLimit, startPage, endPage, listCount, maxPage);
		return pi;
	}
	
	
	// 페이지 끝
	
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
	@RequestMapping(value="recommendInsertForm.dz", method=RequestMethod.POST)
	public ModelAndView recommendRegister(ModelAndView mv,  @RequestParam("recommendTitle") String recommendTitle, @RequestParam("recommendContent") String recommendContent) {
//		@RequestParam(value="uploadFile", required=false)MultipartFile uploadFile, 
//		HttpServletRequest request, @ModelAttribute RecommendBoard recommendBoard,
		RecommendBoard recommendBoard = new RecommendBoard(recommendTitle, recommendContent);
		System.out.println(recommendBoard.toString());
		int result = 0;
		String path = "";
		result = reService.insertRecommend(recommendBoard);
		if(result > 0) {
			path="home";
		} else {
			mv.addObject("msg", "가게추천 글쓰기 실패");
			path="common/errorPage";
		}
		mv.setViewName(path);
		return mv;
	}
	
	// 파일
//	@RequestMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
//	@ResponseBody
//	public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {
//		
//		JsonObject jsonObject = new JsonObject();
//		
//		String fileRoot = "C:\\summernote_image\\";	//저장될 외부 파일 경로
//		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
//		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
//				
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
	
	// 파일
//	public String saveFile(MultipartFile file, HttpServletRequest request) {
//		// 파일 저장 경로 설정
//		String root = request.getSession().getServletContext().getRealPath("resources");
//		String savePath = root + "\\nuploadFiles";
//		// 저장 폴더 선택
//		File folder = new File(savePath);
//		// 폴더가 없을 경우 자동 생성
//		if(!folder.exists()) {
//			folder.mkdir();
//		}
//		String filePath = folder + "\\" + file.getOriginalFilename();
//		// 파일저장
//		try {
//			file.transferTo(new File(filePath));
//		} catch (IllegalStateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// 파일경로 리턴
//		return filePath;
//	}

	// 삭제 delete
	// @ResponseBody // 스프링에서 ajax를 사용하는데, 그 값을 받아서 쓰고싶을때 반드시 필요함
	@RequestMapping(value="recommendDelete.dz", method=RequestMethod.GET)
	public String recommendDelete(@RequestParam int recommendationNo) {
		return "";
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

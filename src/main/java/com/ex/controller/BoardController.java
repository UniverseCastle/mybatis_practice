package com.ex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ex.data.BoardDTO;
import com.ex.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {

	private final BoardService boardService;
	
//	리스트
	@GetMapping("list")
	public String list(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
					   Model model) {
		int pageSize = 10;
		int currentPage = pageNum;				// 연산을 위해 대입 받음
		int start = (currentPage - 1) * pageSize + 1;
		int end = currentPage * pageSize;
		int count = boardService.boardCount();	// 메서드로 만들어놓은것 호출
		
		List<BoardDTO> list = null;
		if (count > 0) {
			list = boardService.boardList(start, end);
		}
		
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = (int)((currentPage - 1) / 10) * 10 + 1;
		int pageBlock = 10;
		int endPage = startPage + pageBlock - 1;
		
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("startPage", startPage);
		model.addAttribute("pageBlock", pageBlock);
		model.addAttribute("endPage", endPage);
		
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("count", count);
		
		model.addAttribute("list", list);
		
		return "list";
	}
	
//	글 작성
	@GetMapping("insert")
	public String insert(BoardDTO boardDTO,
						 @RequestParam(name = "num", defaultValue = "0") int num,
						 Model model) {
		model.addAttribute("num", num);
		
		return "board_insert";
	}
	
	@PostMapping("insert")
	public String insert(BoardDTO boardDTO, Model model) {
		int result = boardService.boardInsert(boardDTO);
		if (result == 1) {
			model.addAttribute("result", result);
		}
		
		return "board_insert";
	}
	
//	글 내용
	@GetMapping("content")
	public String content(@RequestParam("num") int num,
						  @RequestParam("pageNum") int pageNum,
						  Model model) {
		BoardDTO boardDTO = boardService.boardNum(num);
		
		model.addAttribute("boardDTO", boardDTO);
		model.addAttribute("pageNum", pageNum);
		
		return "board_content";
	}
	
//	글 수정
	@GetMapping("update")
	public String update(@RequestParam("num") int num,
						 @RequestParam("pageNum") int pageNum,
						 Model model) {
		BoardDTO boardDTO = boardService.boardNum(num);
		model.addAttribute("boardDTO", boardDTO);
		model.addAttribute("pageNum", pageNum);
		
		return "board_update";
	}
	
	@PostMapping("update")
	public String update(BoardDTO boardDTO,
						 @RequestParam("num") int num,
						 @RequestParam("pageNum") int pageNum,
						 Model model) {
		String password = boardService.passwdNum(num);
		if (password.equals(boardDTO.getPasswd())) {
			int result = boardService.boardUpdate(boardDTO);
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("result", result);
		}
		return "board_update";
	}
	
//	글 삭제
	@GetMapping("delete")
	public String delete(@RequestParam("num") int num,
						 @RequestParam("pageNum") int pageNum,
						 Model model) {
		model.addAttribute("num", num);
		
		return "board_delete";
	}
	
	@PostMapping("delete")
	public String delete(@RequestParam("passwd") String passwd,
						 @RequestParam("num") int num, Model model) {
		String password = boardService.passwdNum(num);
		if (password.equals(passwd)) {
			int result = boardService.boardDelete(num);
			model.addAttribute("result", result);
		}else {
			model.addAttribute("msg", "비밀번호를 확인해주세요.");
		}
		
		return "board_delete";
	}
}
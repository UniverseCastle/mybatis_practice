package com.ex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ex.data.TestDTO;
import com.ex.service.TestService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@RequestMapping("/test/*")
public class TestController {

	private final TestService testService;
	
//	메인
	@GetMapping("main")
	public String main() {
		return "main";
	}
	
//	회원가입
	@GetMapping("insert")
	public String insert() {
		return "insert";
	}
	
	@PostMapping("insert")
	public String insert(TestDTO testDTO, Model model) {
		model.addAttribute("result", testService.testInsert(testDTO));
		
		return "insert";
	}
	
//	로그인
	@GetMapping("login")
	public String login() {
		return "login";
	}
	
//	논리삭제('y')된 아이디 로그인 불가능하게
	@PostMapping("login")
	public String login(@RequestParam("username") String username,
						@RequestParam("password") String password,
						HttpSession session) {
		int result = testService.testLogin(username, password);
		if (result == 1) {
			TestDTO testDTO = testService.testMyInfo(username);
			if (testDTO.getDelete_yn().equals("n")) {
				session.setAttribute("sid", username);
			}else {
				return "login";
			}
		}else {
			return "login";
		}
		return "main";
	}
	
//	로그아웃
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "main";
	}
	
//	내정보
	@GetMapping("myInfo")
	public String myInfo(HttpSession session, Model model) {
		String username = (String)session.getAttribute("sid");
		model.addAttribute("testDTO", testService.testMyInfo(username));
		
		return "myInfo";
	}
	
//	모든회원 정보
	@GetMapping("userInfo")
	public String userInfo(Model model) {
		model.addAttribute("list", testService.testUserInfo());
		
		return "userInfo";
	}
	
//	정보수정
	@GetMapping("update")
	public String update(HttpSession session, Model model) {
		String username = (String)session.getAttribute("sid");
		model.addAttribute("testDTO", testService.testMyInfo(username));
		
		return "update";
	}
	
	@PostMapping("update")
	public String update(TestDTO testDTO, Model model) {
		model.addAttribute("result", testService.testUpdate(testDTO));
		
		return "update";
	}
	
//	회원탈퇴
	@GetMapping("delete")
	public String delete() {
		return "delete";
	}
	
	@PostMapping("delete")
	public String delete(@RequestParam("password") String password,
						 HttpSession session, Model model) {
		String username = (String)session.getAttribute("sid");
		TestDTO testDTO = testService.testMyInfo(username);
		if (testDTO.getPassword().equals(password)) {
			model.addAttribute("result", testService.testDelete(testDTO));
			session.invalidate();
			return "delete";
		}else {
			return "delete";
		}
	}
}
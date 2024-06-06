package com.ex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ex.data.MyTestDTO;
import com.ex.service.TestService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mytest/*")
public class TestController {

	private final TestService testService;
	
//	메인
	@GetMapping("main")
	public String main() {
		return "main";
	}
	
//	회원가입 Get
	@GetMapping("insert")
	public String insert() {
		return "insert";
	}
	
//	회원가입 Post
	@PostMapping("insert")
	public String insert(MyTestDTO myTestDTO, Model model) {
		model.addAttribute("result", testService.mytestInsert(myTestDTO));
		
		return "insert";
	}
	
//	로그인 Get
	@GetMapping("login")
	public String login() {
		return "login";
	}
	
//	로그인 Post
	@PostMapping("login")
	public String login(@RequestParam("username") String username,
						 @RequestParam("password") String password,
						 HttpSession session) {
		int result = testService.mytestLogin(username, password);
		if (result == 1) {
			session.setAttribute("sid", username);
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
		model.addAttribute("myTestDTO", testService.mytestMyInfo(username));
		
		return "myInfo";
	}
	
//	전체회원 정보
	@GetMapping("userInfo")
	public String userInfo(Model model) {
		model.addAttribute("list", testService.mytestUserInfo());
		
		return "userInfo";
	}
	
//	회원탈퇴
	@GetMapping("delete")
	public String delete(HttpSession session) {
		String username = (String)session.getAttribute("sid");
		testService.mytestDelete(username);
		session.invalidate();
		
		return "main";
	}
	
//	회원수정 Get
	@GetMapping("update")
	public String update(HttpSession session, Model model) {
		String username = (String)session.getAttribute("sid");
		model.addAttribute("myTestDTO", testService.mytestMyInfo(username));
		
		return "update";
	}
	
//	회원수정 Post
	@PostMapping("update")
	public String update(MyTestDTO myTestDTO) {
		testService.mytestUpdate(myTestDTO);
		
		return "main";
	}
	
}
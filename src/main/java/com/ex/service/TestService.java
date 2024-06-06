package com.ex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ex.data.MyTestDTO;
import com.ex.repository.TestMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService {
	
	private final TestMapper testMapper;
	
//	회원가입
	public int mytestInsert(MyTestDTO myTestDTO) {
		return testMapper.mytestInsert(myTestDTO);
	}
	
//	로그인
	public int mytestLogin(String username, String password) {
		return testMapper.mytestLogin(username, password);
	}
	
//	내정보
	public MyTestDTO mytestMyInfo(String username) {
		return testMapper.mytestMyInfo(username);
	}
	
//	전체회원 정보
	public List<MyTestDTO> mytestUserInfo() {
		return testMapper.mytestUserInfo();
	}
	
//	회원탈퇴
	public int mytestDelete(String username) {
		return testMapper.mytestDelete(username);
	}
	
//	정보수정
	public int mytestUpdate(MyTestDTO myTestDTO) {
		return testMapper.mytestUpdate(myTestDTO);
	}
}
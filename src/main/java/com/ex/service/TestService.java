package com.ex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ex.data.TestDTO;
import com.ex.repository.TestMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService {

	private final TestMapper testMapper;
	
//	회원가입
	public int testInsert(TestDTO testDTO) {
		return testMapper.testInsert(testDTO);
	}
	
//	로그인
	public int testLogin(String username, String password) {
		return testMapper.testLogin(username, password);
	}
	
//	내정보
	public TestDTO testMyInfo(String username) {
		return testMapper.testMyInfo(username);
	}
	
//	전체회원 정보
	public List<TestDTO> testUserInfo() {
		return testMapper.testUserInfo();
	}
	
//	정보수정
	public int testUpdate(TestDTO testDTO) {
		return testMapper.testUpdate(testDTO);
	}
	
//	회원탈퇴 (논리삭제)
	public int testDelete(TestDTO testDTO) {
		return testMapper.testDelete(testDTO);
	}
}
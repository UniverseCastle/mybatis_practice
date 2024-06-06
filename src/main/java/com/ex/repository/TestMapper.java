package com.ex.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ex.data.TestDTO;

@Mapper
@Repository
public interface TestMapper {

//	회원가입
	public int testInsert(TestDTO testDTO);
	
//	로그인
	public int testLogin(@Param("username") String username, @Param("password") String password);
	
//	내정보
	public TestDTO testMyInfo(String username);
	
//	전체회원 정보
	public List<TestDTO> testUserInfo();
	
//	정보수정
	public int testUpdate(TestDTO testDTO);
	
//	회원탈퇴 (논리삭제)
	public int testDelete(TestDTO testDTO);
}
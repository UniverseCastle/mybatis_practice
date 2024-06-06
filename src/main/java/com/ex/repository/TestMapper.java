package com.ex.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ex.data.MyTestDTO;

@Mapper
@Repository
public interface TestMapper {

//	회원가입
	public int mytestInsert(MyTestDTO myTestDTO);
	
//	로그인
	public int mytestLogin(@Param("username") String username, @Param("password") String password);
	
//	내정보
	public MyTestDTO mytestMyInfo(String username);
	
//	전체회원 정보
	public List<MyTestDTO> mytestUserInfo();
	
//	회원탈퇴
	public int mytestDelete(String username);
	
//	정보수정
	public int mytestUpdate(MyTestDTO myTestDTO);
}
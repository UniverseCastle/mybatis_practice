package com.ex.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ex.data.BoardDTO;

@Mapper
@Repository
public interface BoardMapper {
	
//	마지막 글번호
	public int maxNum();
	
//	답글 추가
	public void reStepUp(BoardDTO boardDTO);
	
//	글 등록
	public int boardInsert(BoardDTO boardDTO);
	
//	글 개수
	public int boardCount();
	
//	글 목록
	public List<BoardDTO> boardList(@Param("start") int start,
									@Param("end") int end);
	
//	조회수 증가
	public void readCountUp(int num);
	
//	글번호로 글내용 가져오기
	public BoardDTO boardNum(int num);
	
//	비밀번호 확인
	public String passwdNum(int num);
	
//	글 수정
	public int boardUpdate(BoardDTO boardDTO);
	
//	글 삭제
	public int boardDelete(int num);
}
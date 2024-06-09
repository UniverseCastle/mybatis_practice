package com.ex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ex.data.BoardDTO;
import com.ex.repository.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardMapper boardMapper;
	
//	글 등록
	public int boardInsert(BoardDTO boardDTO) {
		int maxNum = boardMapper.maxNum();
		if (boardDTO.getNum() != 0) {		// 글번호가 있는 경우 -> 답글일 경우
			boardMapper.reStepUp(boardDTO);
			boardDTO.setRe_step(boardDTO.getRe_step()+1);
			boardDTO.setRe_level(boardDTO.getRe_level()+1);
		}else {					// 글 번호가 없는 경우 -> 새 글인 경우	// maxNum 처리 해줘야 함
			if (maxNum != 1) {
				maxNum += 1;
			}
			boardDTO.setRef(maxNum);
		}
		return boardMapper.boardInsert(boardDTO);
	}
	
//	글 개수
	public int boardCount() {
		return boardMapper.boardCount();
	}
	
//	글 목록
	public List<BoardDTO> boardList(int start, int end) {
		return boardMapper.boardList(start, end);
	}
	
//	글 내용 (+조회수)
	public BoardDTO boardNum(int num) {
		boardMapper.readCountUp(num);
		
		return boardMapper.boardNum(num);
	}
	
//	글 수정
	public int boardUpdate(BoardDTO boardDTO) {
		return boardMapper.boardUpdate(boardDTO);
	}
	
//	비밀번호 확인
	public String passwdNum(int num) {
		return boardMapper.passwdNum(num);
	}
	
//	글 삭제
	public int boardDelete(int num) {
		return boardMapper.boardDelete(num);
	}
}
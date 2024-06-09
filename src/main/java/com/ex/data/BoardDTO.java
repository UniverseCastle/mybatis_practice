package com.ex.data;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BoardDTO {
	private int num;
	private String writer;
	private String title;
	private String content;
	private String passwd;
	private int readCount;
	private int ref;
	private int re_step;
	private int re_level;
	private LocalDateTime reg;
	private String delete_yn;
}
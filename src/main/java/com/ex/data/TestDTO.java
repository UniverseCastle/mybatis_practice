package com.ex.data;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestDTO {
	private String username;
	private String password;
	private int age;
	private LocalDate birth;
	private LocalDateTime reg;
	private String delete_yn;
}
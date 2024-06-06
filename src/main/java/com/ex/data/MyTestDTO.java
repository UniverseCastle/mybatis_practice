package com.ex.data;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MyTestDTO {
	private String username;
	private String password;
	private int age;
	private LocalDate birth;
	private LocalDateTime reg;
}
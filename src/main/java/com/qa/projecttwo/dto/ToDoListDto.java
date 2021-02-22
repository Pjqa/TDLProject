package com.qa.projecttwo.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ToDoListDto {
	
	private long id;
	private String name;
	
	private List<ItemsDto> items = new ArrayList<>();

}

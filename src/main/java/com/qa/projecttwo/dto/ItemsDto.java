package com.qa.projecttwo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemsDto {

	private Long id;
	private String name;
	private String description;
	private boolean completed;
}

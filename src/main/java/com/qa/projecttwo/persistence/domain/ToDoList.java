package com.qa.projecttwo.persistence.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class ToDoList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private String name;
	
	private List<Items> items;

	public ToDoList(@NotNull String name) {
		super();
		this.name = name;
	}

	public ToDoList(long id, @NotNull String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	

}

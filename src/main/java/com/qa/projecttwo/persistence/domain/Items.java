package com.qa.projecttwo.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Items {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String description;
	@NotNull
	private boolean completed;
	
	@ManyToOne
	private ToDoList todolist = null;

	public Items(@NotNull String name, @NotNull String description, @NotNull boolean completed) {
		super();
		this.name = name;
		this.description = description;
		this.completed = completed;
	}

	public Items(Long id, @NotNull String name, @NotNull String description, @NotNull boolean completed) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.completed = completed;
	}
	
}

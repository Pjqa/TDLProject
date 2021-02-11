package com.qa.projecttwo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.projecttwo.dto.ToDoListDto;
import com.qa.projecttwo.persistence.domain.ToDoList;

@Service
public class ToDoListService {

	private ToDoListRepo repo;
	
	private ModelMapper mapper;
	
	private ToDoListDto mapToDto(ToDoList toDoList) {
		return this.mapper.map(toDoList, ToDoListDto.class);
	}
	
	@Autowired
	public ToDoListService (ToDoListRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	//CREATE
	public ToDoListDto create (ToDoList toDoList) {
		return this.mapToDto(this.repo.save(toDoList));
	}
	
	
}

package com.qa.projecttwo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.projecttwo.dto.ToDoListDto;
import com.qa.projecttwo.persistence.domain.ToDoList;

@Service
public class ToDoListService {

	private ToDoListRepo repo;
	
	private ModelMapper mapper;
	
	private ToDoListDto mapToDto (ToDoList toDoList) {
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
	
	//READ
	public List<ToDoListDto> readAll (){
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	//READ ID
	public ToDoListDto readById (Long id) {
		return this.mapToDto(this.repo.findById(id).orElseThrow());
	}
	
}

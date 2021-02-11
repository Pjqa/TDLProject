package com.qa.projecttwo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.projecttwo.dto.ToDoListDto;
import com.qa.projecttwo.persistence.domain.ToDoList;
import com.qa.projecttwo.persistence.repo.ToDoListRepo;
import com.qa.projecttwo.utilis.SpringBean;

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
		return this.repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
	}
	
	//READ ID
	public ToDoListDto readById (Long id) {
		return this.mapToDto(this.repo.findById(id).orElseThrow());
	}
	
	//DELETE
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
	
	//UPDATE
	public ToDoListDto update (ToDoListDto toDoListDto, Long id) {
		ToDoList toUpdate = this.repo.findById(id).orElseThrow();
		toUpdate.setName(toDoListDto.getName());
		SpringBean.mergeNotNull(toDoListDto, toUpdate);
		return this.mapToDto(this.repo.save(toUpdate));
	}
	
	//CUSTOMMETHODS
	public List <ToDoListDto> findByName (String name){
		return this.repo.findByName(name).stream().map(this::mapToDto).collect(Collectors.toList());
	}
	
}

package com.qa.projecttwo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.projecttwo.dto.ToDoListDto;
import com.qa.projecttwo.persistence.domain.ToDoList;
import com.qa.projecttwo.service.ToDoListService;

@RestController
@CrossOrigin
@RequestMapping("/toDoList")
public class ToDoListController {
	
	private ToDoListService service;
	
	@Autowired
	public ToDoListController(ToDoListService service) {
		super();
		this.service = service;
	}
	
	//CREATE
	@PostMapping("/create")
	public ResponseEntity <ToDoListDto> create (@RequestBody ToDoList toDoList){
		ToDoListDto created = this.service.create(toDoList);
		return new ResponseEntity <> (created,HttpStatus.CREATED);
	}

	//READ 
	@GetMapping ("/read/{id}")
	public ResponseEntity <List<ToDoListDto>> readAll (){
		return ResponseEntity.ok(this.service.readAll());
	}
}

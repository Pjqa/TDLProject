package com.qa.projecttwo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	@GetMapping ("/read")
	public ResponseEntity <List<ToDoListDto>> readAll (){
		return ResponseEntity.ok(this.service.readAll());
	}
	
	//READ ID
	@GetMapping ("/read/{id}")
	public ResponseEntity <ToDoListDto> solo (@PathVariable Long id){
		return ResponseEntity.ok(this.service.readById(id));
	}
	
	//DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity <ToDoListDto> delete (@PathVariable Long id){
		return this.service.delete(id)? new ResponseEntity <> (HttpStatus.NO_CONTENT): new ResponseEntity <> (HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//UPDATE
	@PutMapping("/update/{id}")
	public ResponseEntity <ToDoListDto> update (@PathVariable Long id, @RequestBody ToDoListDto toDoListDto){
		return new ResponseEntity <> (this.service.update(toDoListDto, id),HttpStatus.ACCEPTED);
	}
}

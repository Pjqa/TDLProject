package com.qa.projecttwo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.projecttwo.dto.ItemsDto;
import com.qa.projecttwo.persistence.domain.Items;
import com.qa.projecttwo.service.ItemsService;

@RestController
@CrossOrigin
@RequestMapping ("/items")
public class ItemsController {

	private ItemsService service;
	
	@Autowired
	public ItemsController(ItemsService itemsService) {
		super();
		this.service = itemsService;
	}
	
	//CREATE
	@PostMapping("/create")
	public ResponseEntity<ItemsDto> create(@RequestBody Items items){
 		ItemsDto created = this.service.create(items);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
	
}

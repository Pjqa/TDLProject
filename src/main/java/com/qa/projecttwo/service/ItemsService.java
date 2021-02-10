package com.qa.projecttwo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.projecttwo.dto.ItemsDto;
import com.qa.projecttwo.persistence.domain.Items;
import com.qa.projecttwo.persistence.repo.ItemsRepo;

@Service
public class ItemsService {
	
	private ItemsRepo repo;
	
	private ModelMapper mapper;
	
	private ItemsDto mapToDto (Items items) {
		return this.mapper.map(items, ItemsDto.class);
	}
	
	@Autowired
	public ItemsService (ItemsRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	//CREATE
	public ItemsDto create (Items items) {
		return this.mapToDto(this.repo.save(items));
	}

}

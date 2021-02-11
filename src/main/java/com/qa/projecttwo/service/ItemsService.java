package com.qa.projecttwo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.projecttwo.dto.ItemsDto;
import com.qa.projecttwo.persistence.domain.Items;
import com.qa.projecttwo.persistence.repo.ItemsRepo;
import com.qa.projecttwo.utilis.SpringBean;

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

	//READ
	public List<ItemsDto> readAll(){
		return this.repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
	}
	
	//READ ID
	public ItemsDto readById(Long id) {
	return this.mapToDto(this.repo.findById(id).orElseThrow());
	}
	
	//DELETE
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);		
	}
	
	//UPDATE
	public ItemsDto update(ItemsDto itemsDto, Long id) {
		Items toUpdate = this.repo.findById(id).orElseThrow();
		toUpdate.setName(itemsDto.getName());
		SpringBean.mergeNotNull(itemsDto, toUpdate);
		return this.mapToDto(this.repo.save(toUpdate));
	}
}

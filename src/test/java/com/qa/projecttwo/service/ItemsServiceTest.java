package com.qa.projecttwo.service;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.projecttwo.dto.ItemsDto;
import com.qa.projecttwo.persistence.domain.Items;
import com.qa.projecttwo.persistence.repo.ItemsRepo;

@SpringBootTest
@ActiveProfiles(profiles = "test")
public class ItemsServiceTest {
	
	@Autowired
	private ItemsService service;
	@MockBean
	private ItemsRepo repo;
	@Autowired
	private ModelMapper mapper;
	
	private ItemsDto mapToDto(Items items) {
		return this.mapper.map(items, ItemsDto.class);
	}

	public void createTest() {
		Items testModel1 = new Items (1L, "Bread", "50/50 mixed bread", true, 1, new ArrayList<>());
	}
}

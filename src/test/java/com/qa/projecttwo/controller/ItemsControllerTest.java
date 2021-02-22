package com.qa.projecttwo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.qa.projecttwo.dto.ItemsDto;
import com.qa.projecttwo.persistence.domain.Items;
import com.qa.projecttwo.service.ItemsService;

@SpringBootTest
@ActiveProfiles("test")
public class ItemsControllerTest {

	@Autowired
	private ItemsController controller;

	@MockBean
	private ItemsService service;

	@Autowired
	private ModelMapper mapper;

	private ItemsDto mapToDto(Items items) {
		return this.mapper.map(items, ItemsDto.class);
	}

	private final Long testId = 1L;

	private final Items itemsTest1 = new Items(1L, "Bread", "50/50 mixed bread", true);
	private final Items itemsTest2 = new Items(2L, "Sweets", "Lolly pops", false);
	private final Items itemsTest3 = new Items(3L, "GTA", "2 copies", true);

	private final List<Items> ListItems = List.of(itemsTest1, itemsTest2, itemsTest3);

	// CREATE
	@Test
	void createTest() throws Exception {
		when(this.service.create(itemsTest1)).thenReturn(this.mapToDto(itemsTest1));
		assertThat(new ResponseEntity<ItemsDto>(this.mapToDto(itemsTest1), HttpStatus.CREATED))
				.isEqualTo(this.controller.create(itemsTest1));
		verify(this.service, atLeastOnce()).create(itemsTest1);
	}

	// READ ALL
	@Test
	void readAllTest() throws Exception {
		List<Items> items = new ArrayList<>();
		items.add(itemsTest1);
		when(this.service.readAll()).thenReturn(items.stream().map(this::mapToDto).collect(Collectors.toList()));
		assertThat(this.controller.read().getBody().isEmpty()).isFalse();
		verify(this.service, atLeastOnce()).readAll();
	}

	// READ ONE
	@Test
	void readSoloTest() throws Exception {
		ItemsDto expected = this.mapToDto(itemsTest1);
		when(this.service.readById(testId)).thenReturn(expected);
		assertThat(new ResponseEntity<ItemsDto>(expected, HttpStatus.OK)).isEqualTo(this.controller.readSolo(testId));
		verify(this.service, atLeastOnce()).readById(testId);
	}
	
	// DELETE
	@Test
	void deleteTest() throws Exception {
		this.controller.delete(testId);
		verify(this.service, atLeastOnce()).delete(testId);
	}

	// UPDATE
	@Test
	void updateTest() throws Exception {
		ItemsDto expected = this.mapToDto(itemsTest1);
		when(this.service.update(expected, testId)).thenReturn(expected);
		assertThat(new ResponseEntity<ItemsDto>(expected, HttpStatus.ACCEPTED))
				.isEqualTo(this.controller.update(testId, expected));
		verify(this.service, atLeastOnce()).update(expected, testId);
	}

}

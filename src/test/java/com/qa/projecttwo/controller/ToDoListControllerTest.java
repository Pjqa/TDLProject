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
import com.qa.projecttwo.dto.ToDoListDto;
import com.qa.projecttwo.persistence.domain.Items;
import com.qa.projecttwo.persistence.domain.ToDoList;
import com.qa.projecttwo.service.ToDoListService;

@SpringBootTest
@ActiveProfiles("dev")
public class ToDoListControllerTest {
	
	@Autowired
	private ToDoListController controller;

	@MockBean
	private ToDoListService service;

	@Autowired
	private ModelMapper mapper;

	private ToDoListDto mapToDto(ToDoList toDoList) {
		return this.mapper.map(toDoList, ToDoListDto.class);
	}

	private final Long testId = 1L;
	
	private final ToDoList toDoListTest1 = new ToDoList(1L, "Food");
	private final ToDoList toDoListTest2 = new ToDoList(2L, "Games");
	private final ToDoList toDoListTest3 = new ToDoList(3L, "Meats");

	private final List<ToDoList> ListToDoList = List.of(toDoListTest1, toDoListTest2, toDoListTest3);
	

	// CREATE
	@Test
	void createTest() throws Exception {
		when(this.service.create(toDoListTest1)).thenReturn(this.mapToDto(toDoListTest1));
		assertThat(new ResponseEntity<ToDoListDto>(this.mapToDto(toDoListTest1), HttpStatus.CREATED))
				.isEqualTo(this.controller.create(toDoListTest1));
		verify(this.service, atLeastOnce()).create(toDoListTest1);
	}

	// READ ALL
	@Test
	void readAllTest() throws Exception {
		List<ToDoList> toDoList = new ArrayList<>();
		toDoList.add(toDoListTest1);
		when(this.service.readAll()).thenReturn(toDoList.stream().map(this::mapToDto).collect(Collectors.toList()));
		assertThat(this.controller.readAll().getBody().isEmpty()).isFalse();
		verify(this.service, atLeastOnce()).readAll();
	}

	// READ ONE
	@Test
	void readSoloTest() throws Exception {
		ToDoListDto expected = this.mapToDto(toDoListTest1);
		when(this.service.readById(testId)).thenReturn(expected);
		assertThat(new ResponseEntity<ToDoListDto>(expected, HttpStatus.OK)).isEqualTo(this.controller.solo(testId));
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
		ToDoListDto expected = this.mapToDto(toDoListTest1);
		when(this.service.update(expected, testId)).thenReturn(expected);
		assertThat(new ResponseEntity<ToDoListDto>(expected, HttpStatus.ACCEPTED))
				.isEqualTo(this.controller.update(testId, expected));
		verify(this.service, atLeastOnce()).update(expected, testId);
	}

}

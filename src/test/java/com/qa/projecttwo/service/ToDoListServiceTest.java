package com.qa.projecttwo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.projecttwo.dto.ItemsDto;
import com.qa.projecttwo.dto.ToDoListDto;
import com.qa.projecttwo.persistence.domain.Items;
import com.qa.projecttwo.persistence.domain.ToDoList;
import com.qa.projecttwo.persistence.repo.ItemsRepo;
import com.qa.projecttwo.persistence.repo.ToDoListRepo;

@SpringBootTest
@ActiveProfiles("test")
public class ToDoListServiceTest {

	@Autowired
	private ToDoListService service;
	@MockBean
	private ToDoListRepo repo;
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
		toDoListTest1.setId(testId);
		ToDoListDto expected = this.mapToDto(toDoListTest1);
		when(this.repo.save(toDoListTest1)).thenReturn(toDoListTest1);
		assertThat(this.service.create(toDoListTest1)).isEqualTo(expected);
		verify(this.repo, atLeastOnce()).save(toDoListTest1);
	}

	// READ
	@Test
	void readTest() throws Exception {
		List<ToDoList> toDoList = new ArrayList<>();
		toDoListTest1.setId(testId);
		toDoList.add(toDoListTest1);
		when(this.repo.findAll()).thenReturn(toDoList);
		assertThat(this.service.readAll().isEmpty()).isFalse();
		verify(this.repo, atLeastOnce()).findAll();
	}

	// READ ONE
	@Test
	void readSoloTest() throws Exception {
		toDoListTest1.setId(testId);
		ToDoListDto expected = this.mapToDto(toDoListTest1);
		when(this.repo.findById(testId)).thenReturn(Optional.of(toDoListTest1));
		assertThat(this.service.readById(testId)).isEqualTo(expected);
		verify(this.repo, atLeastOnce()).findById(testId);
	}

	// DELETE
	@Test
	void deleteTest() throws Exception {
		boolean found = false;
		when(this.repo.existsById(testId)).thenReturn(found);
		assertThat(this.service.delete(testId)).isNotEqualTo(found);
		verify(this.repo, atLeastOnce()).existsById(testId);
	}

	// UPDATE
	@Test
	void updateTest() throws Exception {
		toDoListTest1.setId(testId);
		ToDoListDto expected = this.mapToDto(toDoListTest1);
		when(this.repo.findById(testId)).thenReturn(Optional.of(toDoListTest1));
		when(this.repo.save(toDoListTest1)).thenReturn(toDoListTest1);
		assertThat(this.service.update(expected, testId)).isEqualTo(expected);
		verify(this.repo, atLeastOnce()).findById(testId);
		verify(this.repo, atLeastOnce()).save(toDoListTest1);
	}	
}

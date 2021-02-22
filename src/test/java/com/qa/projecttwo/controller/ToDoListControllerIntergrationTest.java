package com.qa.projecttwo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.qa.projecttwo.dto.ToDoListDto;
import com.qa.projecttwo.persistence.domain.ToDoList;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:my-data.sql", "classpath:my-schema.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("dev")
public class ToDoListControllerIntergrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper jsonifier;

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

	private final String URI = "/todolist";

	// CREATE
	@Test
	void createTest() throws Exception {
		ToDoListDto expected = this.mapToDto(toDoListTest1);
		this.mvc.perform(post(URI + "/create").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(this.jsonifier.writeValueAsString(toDoListTest1)))
				.andExpect(status().isCreated()).andExpect(content().json(this.jsonifier.writeValueAsString(expected)));
	}

	// READ ALL
	@Test
	void readTest() throws Exception {
		List<ToDoListDto> toDoList = new ArrayList<>();
		toDoList.add(this.mapToDto(toDoListTest1));
		toDoList.add(this.mapToDto(toDoListTest2));
		toDoList.add(this.mapToDto(toDoListTest3));
		this.mvc.perform(get(URI + "/read").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(this.jsonifier.writeValueAsString(toDoList)));
	}

	// READ ONE
	@Test
	void readSoloTest() throws Exception {
		ToDoListDto expected = this.mapToDto(toDoListTest1);
		this.mvc.perform(get(URI + "/read/" + testId).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));
	}

	// DELETE
	@Test
	void deleteTest() throws Exception {
		this.mvc.perform(delete(URI + "/delete/" + testId)).andExpect(status().isNoContent());
	}

	// UPDATE
	@Test
	void updateTest() throws Exception {
		ToDoListDto expected = this.mapToDto(toDoListTest1);
		this.mvc.perform(put(URI + "/update/" + testId).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(this.jsonifier.writeValueAsString(toDoListTest1)))
				.andExpect(status().isAccepted())
				.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));
	}
}

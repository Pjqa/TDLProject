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
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.projecttwo.dto.ItemsDto;
import com.qa.projecttwo.persistence.domain.Items;
import com.qa.projecttwo.persistence.domain.ToDoList;


@SpringBootTest 
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = { "classpath:schema.sql", "classpath:data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class ItemsControllerIntergrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper jsonifier;

	@Autowired
	private ModelMapper mapper;

	private ItemsDto mapToDto(Items items) {
		return this.mapper.map(items, ItemsDto.class);
	}

	private final Long testId = 1L;

	private final Items itemsTest1 = new Items(1L, "Bread", "50/50 mixed bread", true);
	private final Items itemsTest2 = new Items(2L, "Sweets", "Lolly pops", false);
	private final Items itemsTest3 = new Items(3L, "GTA", "2 copies", true);

	private final ToDoList testToDoList = new ToDoList(testId, "Food");
	private final List<Items> ListItems = List.of(itemsTest1, itemsTest2, itemsTest3);

	private final String URI = "/items";

	// CREATE
	@Test
	void createTest() throws Exception {
		itemsTest1.setId(testId + 1);
		itemsTest1.setTodolist(testToDoList);  
		ItemsDto expected = this.mapToDto(itemsTest1);
		this.mvc.perform(post(URI + "/create").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(itemsTest1))).andExpect(status().isCreated())
				.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));
	}
	
	// READ ALL	
	@Test
	void readTest() throws Exception {
		List<ItemsDto> items = new ArrayList<>();
		items.add(this.mapToDto(itemsTest1));
		items.add(this.mapToDto(itemsTest2));
		items.add(this.mapToDto(itemsTest3));
		this.mvc.perform(get(URI + "/read").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(this.jsonifier.writeValueAsString(items)));
	}
	
	// READ ONE
	@Test
	void readSoloTest() throws Exception {
		ItemsDto expected = this.mapToDto(itemsTest1);
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
		ItemsDto expected = this.mapToDto(itemsTest1);
		this.mvc.perform(put(URI + "/update/" + testId).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(itemsTest1))).andExpect(status().isAccepted())
				.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));
	}

}

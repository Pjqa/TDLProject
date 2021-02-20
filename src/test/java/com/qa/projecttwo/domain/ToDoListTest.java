package com.qa.projecttwo.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.qa.projecttwo.persistence.domain.ToDoList;

public class ToDoListTest {

	@Test
	public void testConstructor1() {
		ToDoList toDoList = new ToDoList("Food");
		assertEquals("Food", toDoList.getName());
	}

	@Test
	public void testConstructor2() {
		ToDoList toDoList = new ToDoList(1L, "Food");
		assertEquals(Long.valueOf("1"), toDoList.getId());
	}
	
	@Test
	public void testHashCode() {
		ToDoList toDoList = new ToDoList(1L, "Food");
		assertEquals(toDoList.hashCode(), toDoList.hashCode());
	}
	
}


package com.qa.projecttwo.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.qa.projecttwo.persistence.domain.Items;

public class ItemsTest {

	@Test
	public void testConstructor1() {
		Items items = new Items("Bread", "50/50 mixed bread", true);
		assertEquals("Bread", items.getName());
		assertEquals("50/50 mixed bread", items.getDescription());
		assertEquals(true, items.isCompleted());
	}

	@Test
	public void testConstructor2() {
		Items items = new Items(1L,"Bread", "50/50 mixed bread", true);
		assertEquals(Long.valueOf("1"), items.getId());
		assertEquals("Bread", items.getName());
		assertEquals("50/50 mixed bread", items.getDescription());
		assertEquals(true, items.isCompleted());
	}
	

	@Test
	public void testHashCode() {
		Items items = new Items(1L,"Bread", "50/50 mixed bread", true);
		assertEquals(items.hashCode(), items.hashCode());
	}
	
}

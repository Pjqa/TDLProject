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
import com.qa.projecttwo.persistence.domain.Items;
import com.qa.projecttwo.persistence.repo.ItemsRepo;

@SpringBootTest
@ActiveProfiles("test")
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

	private final Long testId = 1L;

	private final Items itemsTest1 = new Items(1L, "Bread", "50/50 mixed bread", true);
	private final Items itemsTest2 = new Items(2L, "Sweets", "Lolly pops", false);
	private final Items itemsTest3 = new Items(3L, "GTA", "2 copies", true);

	// CREATE
	@Test
	void createTest() throws Exception {
		itemsTest1.setId(testId);
		ItemsDto expected = this.mapToDto(itemsTest1);
		when(this.repo.save(itemsTest1)).thenReturn(itemsTest1);
		assertThat(this.service.create(itemsTest1)).isEqualTo(expected);
		verify(this.repo, atLeastOnce()).save(itemsTest1);
	}

	// READ
	@Test
	void readTest() throws Exception {
		List<Items> items = new ArrayList<>();
		itemsTest1.setId(testId);
		items.add(itemsTest1);
		when(this.repo.findAll()).thenReturn(items);
		assertThat(this.service.readAll().isEmpty()).isFalse();
		verify(this.repo, atLeastOnce()).findAll();
	}

	// READ ONE
	@Test
	void readSoloTest() throws Exception {
		itemsTest1.setId(testId);
		ItemsDto expected = this.mapToDto(itemsTest1);
		when(this.repo.findById(testId)).thenReturn(Optional.of(itemsTest1));
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
		itemsTest1.setId(testId);
		ItemsDto expected = this.mapToDto(itemsTest1);
		when(this.repo.findById(testId)).thenReturn(Optional.of(itemsTest1));
		when(this.repo.save(itemsTest1)).thenReturn(itemsTest1);
		assertThat(this.service.update(expected, testId)).isEqualTo(expected);
		verify(this.repo, atLeastOnce()).findById(testId);
		verify(this.repo, atLeastOnce()).save(itemsTest1);
	}
}

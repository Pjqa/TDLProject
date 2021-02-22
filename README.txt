Coverage: 67.8%

Jira Link - https://team-1608043016664.atlassian.net/secure/RapidBoard.jspa?rapidView=4&projectKey=PTDL&selectedIssue=PTDL-18

# To Do List  Web Application Project

To create an OOP-based web application, with utilisation of supporting tools, methodologies, and technologies, that encapsulates all fundamental and practical modules covered during training.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

In order to use this application, you will need:
```

- Java Version 11 or higher.
- MySQL Version 8 or higher.
- Git
- Maven
- Visual Studio Code 
- Spring Boot
- Postman

```


### Installing

You need to have the above installed.

Once you have the *war* file downloaded onto your system, you can execute it through your command line (cmd) or any terminal that can run executables (like Windows PowerShell for example) by running the following command:

java -jar ProjectTwo-0.0.1-SNAPSHOT.war

## Running The Tests

In order to run the tests on your own system, you must either fork and clone the repository down to your own local space or clone it using the git clone command and the root URL for this repository: 

git clone https://github.com/Pjqa/TDLProject.git
switch to he dev branch

### Unit Tests 

These are the tests that I created in order to test and figure out how the classes in the packages.domain package were interacting with one another, their getters and setters and how the create, update, read and delete functions worked in each data access object.

Unit Tests done from my items and todolist service classes:

```
	// CREATE ITEMS
	@Test
	void createTest() throws Exception {
		itemsTest1.setId(testId);
		ItemsDto expected = this.mapToDto(itemsTest1);
		when(this.repo.save(itemsTest1)).thenReturn(itemsTest1);
		assertThat(this.service.create(itemsTest1)).isEqualTo(expected);
		verify(this.repo, atLeastOnce()).save(itemsTest1);
	}

	// READ ITEMS
	@Test
	void readTest() throws Exception {
		List<Items> items = new ArrayList<>();
		itemsTest1.setId(testId);
		items.add(itemsTest1);
		when(this.repo.findAll()).thenReturn(items);
		assertThat(this.service.readAll().isEmpty()).isFalse();
		verify(this.repo, atLeastOnce()).findAll();
	}

	// DELETE TODOLIST
	@Test
	void deleteTest() throws Exception {
		boolean found = false;
		when(this.repo.existsById(testId)).thenReturn(found);
		assertThat(this.service.delete(testId)).isNotEqualTo(found);
		verify(this.repo, atLeastOnce()).existsById(testId);
	}

	// UPDATE TODOLIST
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

```

Intergration tests for both controller classes and Controller tests:

```

METHOD 1

METHOD 2

	// CREATE TODOLIST
	@Test
	void createTest() throws Exception {
		itemsTest1.setId(testId + 1);
		itemsTest1.setTodolist(testToDoList);  
		ItemsDto expected = this.mapToDto(itemsTest1);
		this.mvc.perform(post(URI + "/create").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(itemsTest1))).andExpect(status().isCreated())
				.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));
	}

	// DELETE TODOLIST
	@Test
	void deleteTest() throws Exception {
		this.mvc.perform(delete(URI + "/delete/" + testId)).andExpect(status().isNoContent());
	}

	// UPDATE ITEMS
	@Test
	void updateTest() throws Exception {
		ToDoListDto expected = this.mapToDto(toDoListTest1);
		this.mvc.perform(put(URI + "/update/" + testId).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(this.jsonifier.writeValueAsString(toDoListTest1)))
				.andExpect(status().isAccepted())
				.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));
	}

	// READ ALL TODOLIST
	@Test
	void readAllTest() throws Exception {
		List<ToDoList> toDoList = new ArrayList<>();
		toDoList.add(toDoListTest1);
		when(this.service.readAll()).thenReturn(toDoList.stream().map(this::mapToDto).collect(Collectors.toList()));
		assertThat(this.controller.readAll().getBody().isEmpty()).isFalse();
		verify(this.service, atLeastOnce()).readAll();
	}

	// READ ONE ITEM
	@Test
	void readSoloTest() throws Exception {
		ItemsDto expected = this.mapToDto(itemsTest1);
		when(this.service.readById(testId)).thenReturn(expected);
		assertThat(new ResponseEntity<ItemsDto>(expected, HttpStatus.OK)).isEqualTo(this.controller.readSolo(testId));
		verify(this.service, atLeastOnce()).readById(testId);
	}


```
  


## Deployment

If you want to deploy this project with an actual database, you will need to have your MySQL database working and currently running. 
Set up your databases and tables using the following commands tailored to this project:

```

drop table if exists items CASCADE; 
drop table if exists to_do_list CASCADE ;

create table items (id bigint generated by default as identity, completed boolean not null, description varchar(255) not null, name varchar(255) not null, todolist_id bigint, primary key (id));
create table to_do_list (id bigint generated by default as identity, name varchar(255) not null, primary key (id));
alter table items add constraint FK12gmn8pqi4yhjyegjkx1jlkpc foreign key (todolist_id) references to_do_list on delete cascade;

```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

Peprah Amoh-Donkor

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

## Acknowledgments

Vin
Gie
Peter

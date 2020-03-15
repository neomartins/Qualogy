package org.qualogy.assessment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.qualogy.assessment.model.Todo;
import org.qualogy.assessment.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@RequestMapping("/api")
public class TodoController {

	@Autowired
	TodoRepository todoRepository;

	@ApiOperation(value = "Get all todos")
	@GetMapping("/todoList")
	public ResponseEntity<List<Todo>> findAllTodo() {
		try {
			List<Todo> todoList = new ArrayList<Todo>();
			todoRepository.findAll().forEach(todoList::add);
			return new ResponseEntity<>(todoList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@ApiOperation(value = "Create Todo")
	@PostMapping("/todoList")
	public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
		try {
			Todo todoData = todoRepository.save(todo);
			return new ResponseEntity<>(todoData, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@ApiOperation(value = "Update todo")
	@PutMapping("/todoList/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable("id") String id, @RequestBody Todo todo) {
		Optional<Todo> todoData = todoRepository.findById(id);

		if (todoData.isPresent()) {
			return new ResponseEntity<>(todoRepository.save(todo), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Delete todo by ID")
	@DeleteMapping("/todoList/{id}")
	public ResponseEntity<HttpStatus> deleteTodoById(@PathVariable("id") String id) {
		try {
			todoRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@ApiOperation(value = "Delete all todos")
	@DeleteMapping("/todoList")
	public ResponseEntity<HttpStatus> deleteAllTodos() {
		try {
			todoRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
}

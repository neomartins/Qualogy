package org.qualogy.assessment.controller;

import java.util.List;
import org.qualogy.assessment.model.Todo;
import org.qualogy.assessment.service.TodoService;
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
     TodoService todoService;

     @ApiOperation(value = "Get all todos")
     @GetMapping("/todoList")
     public ResponseEntity<List<Todo>> findAllTodo() {
          return this.todoService.findAllTodo();
     }

     @ApiOperation(value = "Create Todo")
     @PostMapping("/todoList")
     public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
          return this.todoService.createTodo(todo);
     }

     @ApiOperation(value = "Update todo")
     @PutMapping("/todoList/{id}")
     public ResponseEntity<Todo> updateTodo(@PathVariable("id") String id, @RequestBody Todo todo) {
          return this.todoService.updateTodo(id, todo);
     }

     @ApiOperation(value = "Delete todo by ID")
     @DeleteMapping("/todoList/{id}")
     public ResponseEntity<HttpStatus> deleteTodoById(@PathVariable("id") String id) {
          return this.todoService.deleteTodoById(id);
     }

     @ApiOperation(value = "Delete all todos")
     @DeleteMapping("/todoList")
     public ResponseEntity<HttpStatus> deleteAllTodos() {
          return this.todoService.deleteAllTodos();
     }
}

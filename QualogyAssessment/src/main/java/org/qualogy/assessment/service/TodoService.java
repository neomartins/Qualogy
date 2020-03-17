package org.qualogy.assessment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.qualogy.assessment.model.Todo;
import org.qualogy.assessment.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

     @Autowired
     TodoRepository todoRepository;

     private Logger logger;

     public ResponseEntity<List<Todo>> findAllTodo() {
          try {
               List<Todo> todoList = new ArrayList<Todo>();
               todoRepository.findAll().forEach(todoList::add);
               return new ResponseEntity<>(todoList, HttpStatus.OK);
          } catch (Exception e) {
               logger.error(e.getMessage());
               return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
     }

     public ResponseEntity<Todo> createTodo(Todo todo) {
          try {
               Todo todoData = todoRepository.save(todo);
               return new ResponseEntity<>(todoData, HttpStatus.CREATED);
          } catch (Exception e) {
               logger.error(e.getMessage());
               return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
          }
     }

     public ResponseEntity<Todo> updateTodo(String id, Todo todo) {
          Optional<Todo> todoData = todoRepository.findById(id);

          if (todoData.isPresent()) {
               return new ResponseEntity<>(todoRepository.save(todo), HttpStatus.OK);
          } else {
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
     }

     public ResponseEntity<HttpStatus> deleteTodoById(String id) {
          try {
               todoRepository.deleteById(id);
               return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          } catch (Exception e) {
               logger.error(e.getMessage());
               return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
          }
     }

     public ResponseEntity<HttpStatus> deleteAllTodos() {
          try {
               todoRepository.deleteAll();
               return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          } catch (Exception e) {
               return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
          }
     }
}

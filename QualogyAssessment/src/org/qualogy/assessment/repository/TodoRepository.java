package org.qualogy.assessment.repository;

import org.qualogy.assessment.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepository extends MongoRepository<Todo, String>{
	
}

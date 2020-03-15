import axios from 'axios';

const USER_API_BASE_URL = 'http://localhost:8080/api/todoList';

class ApiService {

  fetchTodos() {
    return axios.get( USER_API_BASE_URL );
  }

  deleteTodoById( todoId ) {
    return axios.delete( USER_API_BASE_URL + '/' + todoId );
  }

  deleteAllTodos() {
    return axios.delete( USER_API_BASE_URL );
  }

  addTodo( todo ) {
    return axios.post( '' + USER_API_BASE_URL, todo );
  }

  editTodo( todo ) {
    return axios.put( USER_API_BASE_URL + '/' + todo.id, todo );
  }

}

export default new ApiService();

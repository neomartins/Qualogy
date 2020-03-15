import React, { Component } from 'react';
import injectTapEventPlugin from 'react-tap-event-plugin';
import IconButton from 'material-ui/IconButton';
import Snackbar from 'material-ui/Snackbar';
import ListIcon from 'material-ui/svg-icons/action/list';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import Paper from 'material-ui/Paper';
import uuid from 'uuid';
import { grey700 } from 'material-ui/styles/colors';
import AddTodo from './AddTodo';
import TodoList from './TodoList';
import ApiService from './ApiService';


injectTapEventPlugin();

class Main extends Component {
  constructor() {
    super();
    this.state = {
      todos: [],
      open: false,
    }
    this.handleClick = this.handleClick.bind(this);
    this.handleRemove = this.handleRemove.bind(this);
    this.handleCheck = this.handleCheck.bind(this);
    this.reloadTodoList = this.reloadTodoList.bind(this);
    this.deleteAllTodos = this.deleteAllTodos.bind(this);
    this.editTodo = this.editTodo.bind(this);
  }

  componentDidMount() {
        this.reloadTodoList();
    }

  reloadTodoList(){
    ApiService.fetchTodos().then(res => {
               this.setState({todos: res.data}) 
            });
  }

  handleClick(todo) {

        let todoInsert = {id: uuid(), description: todo, state: false};
        ApiService.addTodo(todoInsert)
            .then(res => {
                console.log(this.state)
                  this.setState({
                    todos: [
                      ...this.state.todos,
                      {
                        id:res.data.id,
                        description:res.data.description,
                        status:res.data.status,
                      }
                    ]

                  })
            });
  }

  deleteAllTodos(){
    ApiService.deleteAllTodos().then(response => {
                this.reloadTodoList()
            });
  }
  handleRemove(id) {
    
    ApiService.deleteTodoById(id).then(response => {
                this.reloadTodoList()
            });
  }

  handleCheck(id) {
    this.state.todos.map((todo) => {
        if(todo.id === id){
          todo.status =! todo.status
        } 
        ApiService.editTodo(todo).then(response => {
                this.reloadTodoList()
            });
      });
  } 

  handleRequestClose = () => {
    this.setState({
      open: false,
    })
  }

  editTodo(todo){
    ApiService.editTodo(todo).then(response => {
                this.reloadTodoList()
            });
  }

  
  render() {
    return (
      <MuiThemeProvider>
       <Paper 
          style={{paddingBottom: '20px', marginTop: 100, marginBottom: 100, marginRight: 20, marginLeft: 40}}>
          <div 
          style={{
            display: 'flex',
            
          }}
          >
            <div style={{marginLeft: '44%'}}>
              <h1 style={{ textAlign: 'center', color: grey700}}>
                Todo List 
              </h1>
            </div>
            <div style={{ marginRight:'10%', marginTop: 13}}>
              <IconButton >
                <ListIcon/>
              </IconButton>
            </div>
          </div>
          
          <TodoList 
            todos={this.state.todos}
            handleRemove={this.handleRemove} 
            handleCheck={this.handleCheck}
            editTodo={this.editTodo}
          />
          <br />
          <div style={{marginLeft: '5%'}}>
           <AddTodo handleClick={this.handleClick}
           deleteAllTodos={this.deleteAllTodos}/>
          </div>
          <Snackbar
          open={this.state.open}
          message="Todo Item deleted"
          autoHideDuration={2000}
          onRequestClose={this.handleRequestClose}
        />
        </Paper>
        
      
      </MuiThemeProvider>
      
    );
  }
}

export default Main;
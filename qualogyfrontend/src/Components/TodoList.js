import React, { Component, PropTypes } from 'react';
import List from 'material-ui/List';
import Todo from './Todo';

class TodoList extends Component {
  static propTypes = {
    handleRemove: PropTypes.func,
    handleCheck: PropTypes.func,
    editTodo: PropTypes.func,
    todos: PropTypes.array,
  }

  constructor( props ) {
    super( props );
  }
  render() {
    const {handleRemove, handleCheck, todos, editTodo, } = this.props;

    var todoNode = todos.map( (todo) => {
      return (
      <Todo key={ todo.id }
            todo={ todo.description }
            id={ todo.id }
            checked={ todo.status }
            handleRemove={ handleRemove }
            handleCheck={ handleCheck }
            editTodo={ editTodo } />
      )
    } )
    return (
    <List style={ { marginLeft: '5%' } }>
      <ul>
        { todoNode }
      </ul>
    </List>
    )
  }
}

export default TodoList;

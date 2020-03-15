import React, { Component, PropTypes } from 'react';
import { blueGrey800, red600,
} from 'material-ui/styles/colors';
import Checkbox from 'material-ui/Checkbox'
import DeleteIcon from 'material-ui/svg-icons/action/delete';
import EditIcon from 'material-ui/svg-icons/image/edit';
import IconButton from 'material-ui/IconButton';
import Divider from 'material-ui/Divider';
import { ListItem } from 'material-ui/List';
import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import TextField from 'material-ui/TextField';

const listElementStyles = {
  color: blueGrey800,
  fontSize: 18,
  lineHeight: '24px',
}

const listElementCheckedStyles = {
  ...listElementStyles,
  textDecoration: 'line-through',
}

class Todo extends Component {
  static propTypes = {
    todo: PropTypes.string,
  };

  constructor( props ) {
    super( props );
    this.onClick = this.onClick.bind( this );
    this.onCheck = this.onCheck.bind( this );
    this.handleClose = this.handleClose.bind( this );
    this.onSubmit = this.onSubmit.bind( this );
    this.state = {
      inputValue: '',
      open: false,
    }
  }

  onClick( event ) {
    this.props.handleRemove( this.props.id )
  }

  onCheck( event ) {
    console.log( this.props.id )
    this.props.handleCheck( this.props.id )
  }

  handleOpen = () => {
    this.setState( {
      open: true
    } );
  };

  handleClose() {
    this.setState( {
      open: false
    } );
  }

  onSubmit() {
    let todo = {
      id: this.props.id,
      description: this.state.inputValue,
      state: this.props.checked
    };
    this.props.editTodo( todo );
    this.setState( {
      open: false
    } );
  }


  render() {
    const {todo, } = this.props;
    const actions = [
      <FlatButton label="Cancel"
                  primary={ true }
                  onClick={ this.handleClose } />,
      <FlatButton label="Submit"
                  primary={ true }
                  onClick={ this.onSubmit } />,
    ];

    const listStyles = !this.props.checked ? listElementStyles : listElementCheckedStyles;
    return (
    <ListItem style={ { width: '90%' } } rightIconButton={ <div style={ { display: 'flex' } }>
                                                         <IconButton tooltip='remove'
                                                                     tooltipPosition='bottom-right'
                                                                     onClick={ this.onClick }
                                                                     iconStyle={ { color: red600 } }>
                                                           <DeleteIcon />
                                                         </IconButton>
                                                         <IconButton tooltip='edit'
                                                                     tooltipPosition='bottom-left'
                                                                     onClick={ this.handleOpen }>
                                                           <EditIcon />
                                                         </IconButton>
                                                         <Checkbox checked={ this.props.checked }
                                                                   onCheck={ this.onCheck }
                                                                   style={ { marginTop: 12 } } />
                                                       </div> }>
      <div style={ { display: 'flex' } }>
        <li style={ listStyles }>
          { todo }
        </li>
      </div>
      <Divider />
      <Dialog title="Edit to-do title"
              actions={ actions }
              modal={ true }
              open={ this.state.open }>
        <div style={ { marginLeft: '10px' } }>
          <TextField defaultValue={ todo }
                     className="AddText"
                     fullWidth={ true }
                     onChange={ (e) => this.setState( {
                                  inputValue: e.target.value
                                } ) }>
          </TextField>
        </div>
      </Dialog>
    </ListItem>
    )
  }
}


export default Todo;

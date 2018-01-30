/**
 * Created by viewhigh on 2017/6/12.
 */
import React, {Component} from 'react';
import {InputNumber} from 'antd';

class EditableCell extends React.Component {
  
  componentWillMount() {
    this.setState({
      value: this.props.value,
      editable: false,
    });
  }
  
  componentWillReceiveProps(nextProps) {
    this.setState({
      value: nextProps.value,
      editable: nextProps.editable,
    });
  }
  

  handleChange = (value) => {
    this.setState({ value });
  }
  check = () => {
    this.setState({ editable: false });
    if (this.props.onChange) {
      this.props.onChange(this.state.value);
    }
  }
  edit = () => {
    this.setState({ editable: true });
  }
  render() {
    const { value, editable } = this.state;
    return (
      <div className="editable-cell" onClick={this.edit}>
        {
          editable ?
            <div className="editable-cell-input-wrapper" >
              <InputNumber
                value={value}
                onChange={this.handleChange}
                onPressEnter={this.check}
                onBlur={this.check}
                step={0.2}
              />
            </div>
            :
            <div className="editable-cell-text-wrapper">
              {value || ' '}
            </div>
        }
      </div>
    );
  }
}
export default EditableCell;

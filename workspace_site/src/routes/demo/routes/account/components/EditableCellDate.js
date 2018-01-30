/**
 * Created by viewhigh on 2017/6/12.
 */
import React, {Component} from 'react';
import {DatePicker} from 'antd';
import moment from 'moment';


const dateFormat = 'YYYY-MM-DD';

class EditableCellDate extends React.Component {
  state = {
    value: this.props.value,
    editable: false,
  }

  componentWillReceiveProps(nextProps) {
    this.setState({
      value: nextProps.value,
      editable: nextProps.editable,
    });
  }

  handleChange = (value, dateString) => {
    debugger;
    this.state["value"] = dateString;
    this.state["editable"] = false;
    this.setState(this.state);
    if (this.props.onChange) {
      this.props.onChange(this.state.value);
    }
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
             <DatePicker
              value={moment(value, dateFormat)}
              onChange={this.handleChange}
              onPressEnter={this.check}
              onBlur={this.check}
              format={dateFormat}
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
export default EditableCellDate;

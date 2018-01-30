/**
 * Created by viewhigh on 2017/6/12.
 */
import React, {Component} from 'react';
import {Select,Icon} from 'antd';
const Option = Select.Option;

class EditableCellSelect extends React.Component {
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

  handleChange = (value) => {
    this.setState({value});
  }
  check = () => {
    this.setState({editable: false});
    if (this.props.onChange) {
      this.props.onChange(this.state.value);
    }
  }
  edit = () => {
    this.setState({editable: true});
  }

  drawDiv =(value)=>{
    if(value == '1'){
      return '长途';
    }
    if(value == '2'){
      return '住宿'
    }
    if(value == '3'){
      return '交通'
    }
    if(value == '4'){
      return '餐饮'
    }
  }

  render() {
    const {value, editable} = this.state;
    return (
      <div className="editable-cell" onClick={this.edit}>
        {
          editable ?
            <div className="editable-cell-input-wrapper">
              <Select defaultValue="1" value={value} onChange={this.handleChange}
                      onPressEnter={this.check}
                      onBlur={this.check}
              >
                <Option value="1">长途</Option>
                <Option value="2">住宿</Option>
                <Option value="3">交通</Option>
                <Option value="4">餐饮</Option>
              </Select>

            </div>
            :
            <div className="editable-cell-text-wrapper">
              {this.drawDiv(value)}
            </div>
        }
      </div>
    );
  }
}
export default EditableCellSelect;

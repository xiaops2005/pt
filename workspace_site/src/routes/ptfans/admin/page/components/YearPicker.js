import React from 'react'
import {Select} from 'antd';
const Option = Select.Option;
export default class YearPicker extends React.Component{
  render(){

    return(
      <Select>
        <Option value="2010">2010</Option>
        <Option value="2011">2011</Option>
        <Option value="2012">2012</Option>
        <Option value="2013">2013</Option>
        <Option value="2014">2014</Option>
        <Option value="2015">2015</Option>
        <Option value="2016">2016</Option>
        <Option value="2017">2017</Option>
        <Option value="2018">2018</Option>
        <Option value="2019">2019</Option>
      </Select>
    );
  }

}

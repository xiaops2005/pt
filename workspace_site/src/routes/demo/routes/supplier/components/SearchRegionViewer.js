/**
 * Created by Bojc on 2017/6/3.
 */

import React from 'react';
import {Input,Button} from 'antd';
import {GetProductDataByFilter} from './SupplierProcessor';

class SearchRegion extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  handleSearch(e) {
    e.preventDefault();
    var params = this.state;

    const dispatch = this.props.dispatch;
    GetProductDataByFilter(params,dispatch);

  }

  handleValueChange(field, value) {
    let params = this.state;
    params[field]=value;
    this.setState({
      [field]: value
    });

  }

  render() {
    this.state = this.props.data;
    const {supplier, standard, company} = this.state;
    return (
      <div className="div_search">
        <div className="div_item">
          <span className="sp_title">供应商</span>
          <Input value={supplier} ref="supplier" placeholder="供应商"
                 onChange={(e)=>this.handleValueChange("supplier",e.target.value)}/>
        </div>
        <div className="div_item">
          <span className="sp_title">规格型号</span>
          <Input value={standard} ref="standard" placeholder="规格型号"
                 onChange={(e)=>this.handleValueChange("standard",e.target.value)}/>
        </div>
        <div className="div_item">
          <span className="sp_title">厂商</span>
          <Input value={company} ref="company" placeholder="厂商"
                 onChange={(e)=>this.handleValueChange("company",e.target.value)}/>
        </div>
        <Button type="primary" onClick={(e)=>this.handleSearch(e)}>查询</Button>
      </div>
    );
  }
}

export default SearchRegion

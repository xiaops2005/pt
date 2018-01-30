/**
 * Created by Bojc on 2017/6/2.
 */
import React, { Component } from 'react';
import { Menu, Icon } from 'antd';
import {connect} from 'react-redux';
import {GetProductDataByFilter} from './SupplierProcessor';
import {NewSetFilter} from 'routes/demo/models/actions/demoAction';

class Category extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      data: this.props.data
    };
  }

  render() {
    const category = this.state.data;
    return (
      <div className="div_category">
        <p>材料分类</p>
        <CategoryItem data={category} dispatch={this.props.dispatch}/>
      </div>
    );
  }
}

class CategoryItem extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      data: this.props.data,
      curId: this.props.data.curId
    };
  }

  handleClick = (item, dispatch)=> {

    this.setState({
      data: this.state.data,
      curId: item.id
    });

    var params = {
      supplier: '',
      standard: '',
      company: '',
      categoryId: item.id,
      categoryName:item.name
    };
    //dispatch(NewSetFilter(params));
    GetProductDataByFilter(params,dispatch);
  }

  handleCategory = (name,dispatch)=> {
    this.setState({
      data: this.state.data,
      curId: ''
    });

    var params = {
      supplier: '',
      standard: '',
      company: '',
      categoryId: '',
      categoryName:name
    };
    //dispatch(NewSetFilter(params));
    GetProductDataByFilter(params,dispatch);
  }

  render() {
    const data = this.state.data;
    const {dispatch}=this.props;
    return (
      <div>
        <span className="sp_category" onClick={()=>this.handleCategory(data.name,dispatch)}>{data.name}</span>
        <ul>
          {
            data.list.map((item)=> {
              let active = item.id == this.state.curId ? "active" : '';
              return (
                <li key={item.id} className={active}
                    onClick={()=>this.handleClick(item,dispatch)}>{item.name}</li>
              );
            })
          }
        </ul>
      </div>
    );
  }
}

export default Category;

/**
 * Created by Bojc on 2017/6/4.
 */
import React from 'react';
import {Pagination} from 'antd';
import {LoadProductData} from './SupplierProcessor';

export default class QuickJumperPagination extends React.Component{
  constructor(props){
    super(props);
    this.state={
      current:1,
      total: 0
    }
  }
  onChange = (nextPage) => {
    this.setState={
      current:nextPage
    };
    var params=this.props.filter;
    params.pageIndex=nextPage;

    const dispatch=this.props.dispatch;
    LoadProductData(params,dispatch);
  }
  render(){
    const current=this.props.data.current;
    const total=this.props.data.total;
    return(
      <div className="pagination_r">
        <Pagination showQuickJumper current={current} total={total}  pageSize={8} onChange={this.onChange}/>
      </div>
    );
  }
}

export class SimplePagination extends React.Component{
  constructor(props){
    super(props);
    this.state={
      current:1,
      total: 0
    }
  }
  onChange = (nextPage) => {
    this.setState={
      current:nextPage
    };
    var params=this.props.filter;
    params.pageIndex=nextPage;
    const dispatch=this.props.dispatch;
    LoadProductData(params,dispatch);
  }
  render(){
    const current=this.props.data.current;
    const total=this.props.data.total;
    return(
      <div className="pagination_r" style={{paddingBottom:'0'}}>
        <Pagination simple current={current} total={total}  pageSize={8} onChange={this.onChange}/>
      </div>
    );
  }
}




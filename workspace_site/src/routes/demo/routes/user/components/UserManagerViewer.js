/**
 * Created by hp on 2017/6/15.
 */
import React from 'react';
import { Router } from 'react-router';
import { Form,Table, Input, Button, Icon } from 'antd';
import 'antd/dist/antd.css';
import { Pagination } from 'antd';
import {connect} from 'react-redux';

import HttpUtil from '../../../../../constants/httpUtil';
import  {userQueryAction} from '../../../models/actions/demoAction';
import UserFormViewer from './UserFormViewer';
import userManagerProcessor from './userManagerProcessor';
const columns = [{
  title: '编号',
  dataIndex: 'id',
  key: 'id'
}, {
  title: '账号',
  dataIndex: 'account',
  key: 'account',
}, {
  title: '姓名',
  dataIndex: 'name',
  key: 'name',
}, {
  title: '职位',
  key: 'position',
  dataIndex:'position'
}, {
  title: '司龄',
  key: 'ourAage',
  dataIndex:'ourAage'
}, {
  title: '职级',
  key: 'rank',
  dataIndex:'rank'
}, {
  title: '学历',
  key: 'education',
  dataIndex:'education'
}, {
  title: '性别',
  key: 'sex',
  dataIndex:'sex'
}, {
  title: '生日',
  key: 'birthdate',
  dataIndex:'birthdate'
}, {
  title: '国籍',
  key: 'nationality',
  dataIndex:'nationality'
}, {
  title: '证件类型',
  key: 'credentialsType',
  dataIndex:'credentialsType'
}, {
  title: '证件号码',
  key: 'credentialsNumber',
  dataIndex:'credentialsNumber'
}, {
  title: '是否在职',
  key: 'status',
  dataIndex:'status'
}];
const data = [];
const pagination=[];
const condition=null;
class UserManagerViewer extends React.Component {
  state = {
    data,
    pagination,
    condition
  };
  componentDidMount() {
  }
  componentWillReceiveProps(newProps)
  {
    if(newProps!=null&&newProps.condition!=null)
    {
      const ump = new userManagerProcessor();
      ump.handleTableChange({total:0,pageSize:5,current:0,defaultCurrent:0},newProps.condition, this);
    }
  };
  handleTableChange = (pagination, filters, sorter) =>
  {
    var a=this.props.condition;
    if(pagination!=null) {
      const ump = new userManagerProcessor();
      ump.handleTableChange(pagination,null, this);
    }
  };
  render() {
    //const { getFieldDecorator } = this.props.form;
    const UserFormViewTag = Form.create()(UserFormViewer);
    return (
      <div>
        <div>
          <UserFormViewTag  dispatch={this.props.dispatch} condition={this.props.condition}/>
        </div>
        <div>
          <Table columns={columns} dataSource={this.state.data} pagination={this.state.pagination} onChange={this.handleTableChange}/>
        </div>
      </div>
    );
  }
}
//export default Page;
const mapStateToProps = (store, ownProps) => {
  //state.condition=store.userReducer.condition;
  return {
    //data: store.userReducer.data,
    //pagination: store.userReducer.pagination,
    condition:store.userReducer.condition
  }
};

export default connect(
  mapStateToProps
)(UserManagerViewer);

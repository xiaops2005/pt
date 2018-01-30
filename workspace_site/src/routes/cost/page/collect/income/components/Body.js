/**
 * Created by hp on 2017/6/15.
 */
import React from 'react';
import { Router } from 'react-router';
import moment from 'moment';
import {Form, Icon, Input, Button, Row, Col, DatePicker, Popconfirm, Table,Select,Modal,message} from 'antd';
import 'antd/dist/antd.css';

import { Pagination } from 'antd';
import {connect} from 'react-redux';

import DateUtil from "../../../../../../constants/DateUtil";


const confirm = Modal.confirm;
const warn = Modal.warn;

const Option = Select.Option;
const FormItem = Form.Item;
const formData={};
const dateFormat = 'YYYY/MM/DD';
const dataSource = [];


class Body extends React.Component {
  constructor(props) {
    super(props);

    this.columns = [
      {
        title: '年度',
        dataIndex: 'accYear'
      }, {
        title: '收入类型',
        dataIndex: 'incomeType'
      }, {
        title: '收入项目',
        dataIndex: 'incomeItem'
      }, {
        title: '收入本年累计数',
        dataIndex: 'incomeAmountSum'
      }
      , {
        title: '成本项目',
        dataIndex: 'costItem'
      }
      , {
        title: '成本本年累计数',
        dataIndex: 'costAmountSum'
      }
      , {
        title: '状态',
        dataIndex: 'status'
      }
      , {
        title: '创建人',
        dataIndex: 'creator'
      }
      , {
        title: '创建时间',
        dataIndex: 'creationtime',
        render: function (value) {
          return DateUtil.formatDate(value);
        },
      }
      , {
        title: '审核人',
        dataIndex: 'approver'
      }
      , {
        title: '审核时间',
        dataIndex: 'approvetime',
        render: function (value) {
          return DateUtil.formatDate(value);
        },
      }
      , {
        title: '审核评语',
        dataIndex: 'approvenote'
      }
      ];
    this.state = {dataSource: [],selectedRowKeys:[],selectedRow:[]};
   // this.cacheData = dataSource.map(item => ({...item}));

  }

  componentWillReceiveProps(nextProps) {
    console.info('componentWillReceiveProps');
    this.setState({dataSource: [...nextProps.dataSource]});
  }

  add = () => {

  }
  edit = ()=> {
    const {dataSource,selectedRowKeys} = this.state;
    if (selectedRowKeys.length === 0) {
      message.warn("请选择要编辑的行！");
    } else {
      debugger
      dataSource.filter((item) => selectedRowKeys.some((key) => item.key === key)).map((item) =>
        Object.assign(item, {editable: true})
      );
      this.setState({
        dataSource: dataSource
      });
    }
  }
  checkData = (selectedDataSource) => {
    let checkInfo = {
      result: true,
      message: []
    };
    selectedDataSource.forEach((item, index) => {
      if (!item.staName) {
        checkInfo.message.push(`第${index + 1}行标准科室名称不能为空!`);
      }
      if (!item.staCode) {
        checkInfo.message.push(`第${index + 1}行标准科室编码不能为空!`);
      }
    });
    checkInfo.result = checkInfo.message.length === 0;
    return checkInfo;
  };
  save = ()=>  {
    const {selectedRowKeys,dataSource} = this.state;
    if (selectedRowKeys.length === 0) {
      message.warn("请选择要保存的行！");
    } else {
      let selectedDataSource = dataSource.filter((item) => selectedRowKeys.some((key) => item.key === key));
      let checkInfo = this.checkData(selectedDataSource);
      if (checkInfo.result) {
        let selectedData = selectedDataSource.map(item => {
          delete item.editable;
        });
        debugger
        process.handleSave(selectedData,(result) => {
          message.success("保存成功！");
          console.log('this.state',this.state);
          console.log('this.props',this.props);
          //this.state.handleSubmit();
          process.handleSubmit('','','',(data) => {
            debugger
            this.setState({"dataSource": data.getSinglePrimary().map((item, index) => Object.assign(item, {key: index}))});
          });
        })
        this.setState({
          dataSource: dataSource
        });
      } else {
        warn({
          title: '系统提示',
          content: <div> {checkInfo.message.map((message) => <p>{message}</p>)}</div>
        });
      }
    }
  }


  delete = ()=> {
    const { selectedRowKeys,selectedRow ,dataSource } = this.state;
    if (selectedRowKeys.length === 0) {
      message.warn("请选择要删除的行！");
    } else {
      confirm({
        title: '系统提示',
        content: '是否要删除所有选择行数据?',
        onOk: () => {
          const {dataSource} = this.state;
          const pkDeptSta = dataSource.filter(item => selectedRowKeys.some(key => item.key === key)).map(item =>item.pkDeptSta).join(",");
         debugger
          process.handleDelete(pkDeptSta, (result) => {
                message.success("删除成功！");
                process.handleSubmit('','','',(data) => {
                  this.setState({
                    "dataSource": data.getSinglePrimary().map((item, index) => Object.assign(item, {key: index})),
                    selectedRowKeys:[],
                    selectedRow:[]
                  });
                });
              })
        },
        onCancel: () => {
        }
      });
    }
  }



  onSelectChange = (selectedRowKeys,selectedRow) => {
    this.setState({ selectedRowKeys:selectedRowKeys,selectedRow:selectedRow });
  }


  render() {
    const {dataSource,selectedRowKeys,selectedRow} = this.state;
    const rowSelection = {
      selectedRowKeys,
      selectedRow,
      onChange: this.onSelectChange,
    };
    return (
      <div>
        <div className="operation-area">
          <Button className="editable-add-btn" onClick={this.add}>导入</Button>
          <Button className="editable-add-btn" onClick={this.add}>查看审批记录</Button>
          <Button className="editable-add-btn" onClick={this.add}>导出</Button>

        </div>
        <Table  bordered pagination={{pageSize: 10}} size='small'  rowSelection={rowSelection} dataSource={dataSource} columns={this.columns}/>

      </div>
    )
  }
}

export default Body;

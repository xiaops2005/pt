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
import {EditableCellInput, EditableCellSelect} from '../../component/EditableTable'
import deptProcess from './DeptProcessor';
import DateUtil from "../../../../../../constants/DateUtil";
import {BdDept} from "../../models/bdDictModel";

const confirm = Modal.confirm;
const warn = Modal.warn;

const Option = Select.Option;
const FormItem = Form.Item;
const process = new deptProcess();

const EditableCell = ({editable, value, onChange}) => (
  <div>
    {editable ? <Input style={{margin: '-5px,0'}} value={value} onChange={e => onChange(e.target.value)}/> : value}
  </div>
)



class Body extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      deptTypeList: [],
      mappedDeptType: {},
      belongSystemList:[],
      mappedBelongSystem:{},
      hospitalList: [],
      mappedHospital: {},
      selectedRowKeys: [],
      dataSource: []
    };

    this.columns = [
      {
        title: '标准科室编码',
        dataIndex: 'deptCode',
        render: (text, record) => this.renderColumns(text, record, 'deptCode'),
      }, {
        title: '标准科室名称',
        dataIndex: 'deptName',
        render: (text, record) => this.renderColumns(text, record, 'deptName'),
      },{
        title: '所属系统',
        dataIndex: 'belongSystemId',
        render: (text, record) => {
          return (
            <EditableCellSelect
              editable={record.editable}
              options={this.state.belongSystemList}
              mappedOptions={this.state.mappedBelongSystem}
              value={text}
              onChange={val => this.handleChange(val, record.key, "belongSystemId")}
              decodeVal='codeId'
              decodeDisplay='codeName'
            />
          )}
      },{
        title: '科室类型',
        dataIndex: 'deptTypeId',
        render: (text, record) => {
          return (
            <EditableCellSelect
              editable={record.editable}
              options={this.state.deptTypeList}
              mappedOptions={this.state.mappedDeptType}
              value={text}
              onChange={val => this.handleChange(val, record.key, "deptTypeId")}
              decodeVal='codeId'
              decodeDisplay='codeName'
            />
          )}
      },{
        title: '所属单位',
        dataIndex: 'pkHospital',
        render: (text, record) => {
          return (
            <EditableCellSelect
              editable={record.editable}
              options={this.state.hospitalList}
              mappedOptions={this.state.mappedHospital}
              value={text}
              onChange={val => this.handleChange(val, record.key, "pkHospital")}
              decodeVal='pkHospital'
              decodeDisplay='hospitalName'
            />
          )}
      },
      {
        title: '备注',
        dataIndex: 'remark',
        render: (text, record) => this.renderColumns(text, record, 'remark'),
      }
      , {
        title: '是否停用',
        dataIndex: 'isStop',
        render: (text, record) =>{
          return (
          <EditableCellSelect
            editable={record.editable}
            value={text}
            options={this.isStopOptions}
            decodeVal="code"
            decodeDisplay="name"
            onChange={value => this.handleChange(value, record.key, 'isStop')}
           />
          )}
      }
      , {
        title: '创建人',
        dataIndex: 'creator',
        render: function (value) { return value },
      } , {
        title: '创建时间',
        dataIndex: 'creationtime',
        render: function (value) {
          return DateUtil.formatDate(value);
        },
      }];
    // this.state = {dataSource: [], count: 0,selectedRowKeys:[],selectedRow:[]};
    // this.cacheData = dataSource.map(item => ({...item}));
    console.log('this.state',this.state)
    this.isStopOptions = [{name: '是', code: '1'}, {name: '否', code: '0'}]
  }
  componentWillReceiveProps(nextProps) {
    this.setState({...nextProps});
  }

  renderColumns(text, record, column) {
    return (
      <EditableCell
        editable={record.editable}
        value={text}
        onChange={value => this.handleChange(value, record.key, column)}
      />
    );
  }
  handleChange(value, key, column) {
    const newData = [...this.state.dataSource]
    const target = newData.filter(item => key === item.key)[0];
    if (target) {
      target[column] = value
      this.setState({dataSource: newData})
    }
  }

  add = () => {
    let {dataSource,selectedRowKeys} = this.state;
    selectedRowKeys = selectedRowKeys.map(key => key + 1);
    selectedRowKeys.unshift(0);
    let bdDept = new BdDept();
    bdDept.editable = true;
    bdDept.creationtime =moment(Date.now()).format('YYYY-MM-DD')  ;
    bdDept.creator='';
    bdDept.copyPropValues(bdDept);
    dataSource.unshift(bdDept);
    this.setState({
      dataSource: dataSource.map((item, index) =>
        Object.assign(item, {key: index})
      ),
      selectedRowKeys: selectedRowKeys
    });
  }
  edit = ()=> {
    const {dataSource,selectedRowKeys} = this.state;
    if (selectedRowKeys.length === 0) {
      message.warn("请选择要编辑的行！");
    } else {
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
      if (!item.deptName) {
        checkInfo.message.push(`第${index + 1}行标准科室名称不能为空!`);
      }
      if (!item.deptCode) {
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
          let bdDept = new BdDept();
          bdDept.isStandard = '1';
          bdDept.creator = '';
          bdDept.belongSystemId = item.belongSystemId;
          bdDept.deptTypeId = item.deptTypeId;
          bdDept.copyPropValues(item);
          delete item.editable;
          return bdDept;
        });
        debugger
        this.state.save(selectedData,(result) => {
          message.success("保存成功！");
         // this.state.query();
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
          const pkDept = dataSource.filter(item => selectedRowKeys.some(key => item.key === key)).map(item =>"'"+item.pkDept+"'").join(",");
          this.state.delete(pkDept,(result) => {
             message.success("删除成功！");
             this.state.query();
              // selectedRow.map((item)=>{
              //   dataSource.splice(dataSource.findIndex(value => value.key === item.key),1);
              // })
            }
          )
        }
      });
      this.setState({
            dataSource:dataSource,
            selectedRowKeys:[],
            selectedRow:[]
      })
    }
  }

  start = ()=> {
    const { selectedRowKeys,selectedRow ,dataSource } = this.state;
    if (selectedRowKeys.length === 0) {
      message.warn("请选择要启用的行！");
    }else{
      const pkDeptSta= dataSource.filter(item => selectedRowKeys.some(key => item.key === key)).map(item =>"'"+item.pkDept+"'").join(",");
      this.state.startOrStop(pkDeptSta,'0', (result) => {
        message.success("启用成功！");
        this.state.query();
      });
    }

  }
  stop = ()=> {
    const { selectedRowKeys,selectedRow ,dataSource } = this.state;
    if (selectedRowKeys.length === 0) {
      message.warn("请选择要停用的行！");
    }else{
      const pkDeptSta= dataSource.filter(item => selectedRowKeys.some(key => item.key === key)).map(item =>"'"+item.pkDept+"'").join(",");
      this.state.startOrStop(pkDeptSta,'1', (result) => {
        message.success("停用成功！");
        this.state.query();
      });
    }

  }


  onSelectChange = (selectedRowKeys,selectedRow) => {
    this.setState({ selectedRowKeys:selectedRowKeys,selectedRow:selectedRow });
  }


  render() {
    const {selectedRowKeys,dataSource,selectedRow} = this.state;
    const rowSelection = {
      selectedRowKeys,
      selectedRow,
      onChange: this.onSelectChange,
    };
    return (
      <div>
        <div className="operation-area">
          <Button className="editable-add-btn" onClick={this.add}>新增</Button>
          <Button className="editable-add-btn" onClick={this.edit}>修改</Button>
          <Button className="editable-add-btn" onClick={this.save}>保存</Button>
          <Button className="editable-add-btn" onClick={this.delete}>删除</Button>
          <Button className="editable-add-btn" onClick={this.start}>启用</Button>
          <Button className="editable-add-btn" onClick={this.stop}>停用</Button>
          <Button className="editable-add-btn" onClick={this.stop}>导入</Button>
        </div>
        <Table  bordered pagination={{pageSize: 10}} size='small'  rowSelection={rowSelection} dataSource={dataSource} columns={this.columns}/>

      </div>
    )
  }
}

export default Body;

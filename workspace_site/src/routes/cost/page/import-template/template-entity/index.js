import React from 'react';
import {connect} from 'react-redux';

import {TemplateService} from '../../../process/LoadService';
import {message, Modal} from 'antd';
import HeaderContainer from './container/HeaderContainer'
import DateUtil from "../../../../../constants/DateUtil";
import DictBody from "../../dict/component/DictBody";
import {EditableCellInput,EditableCellSelect} from "../../dict/component/EditableTable";
import {FileTemplate} from "../models/importTemplateModel";
import {queryTemplateEntity,getTemplateEntity,saveTemplateEntity,deleteTemplateEntity} from "../models/actions/importTemplateAction";
import {VHStrUtil} from "../../../../../constants/utils";
const processor = new TemplateService();

const warn = Modal.warn;


class TemplateEntity extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      dataSource:[],
      count: 0,
      dbTableNames:[],
      isStopList:[],
    };

    this.columns = [
      {
        title: '表名',
        dataIndex: 'dbTableName',
        width: '15%',
        render: (text, record) => this.renderColumnsSelect(text, record, this.state.dbTableNames,'dbTableName',"dbTableName","dbTableName"),
      }, {
      title: '模板名称',
      dataIndex: 'templateName',
      width: '15%',
      render: (text, record) => this.renderColumns(text, record, 'templateName'),
    },{
        title: '模板类',
        dataIndex: 'templateClass',
        width: '25%',
      },{
      title: '主键名',
      dataIndex: 'primaryKey',
      width: '15%',
    },{
        title: '创建人',
        dataIndex: 'creator',
        width: '15%',
      }, {
        title: '创建时间',
        dataIndex: 'creationTime',
        width: '15%',
        render: function (value) {
          return DateUtil.formatDate(value);
        },
        sorter:(a,b)=>a.creationTime - b.creationTime
      }
    ];
  }

  renderColumns(text, record, column) {
    return (
      <EditableCellInput
        editable={record.$$editable}
        value={text}
        onChange={value => this.handleChange(value, record.key, column)}
      />
    );
  }

  renderColumnsSelect(text, record,options, column,decodeVal,decodeDisplay) {
    return <EditableCellSelect
      editable={record.$$editable}
      options={options}
      value={text}
      onChange={val => this.onTemplateSelect(val, record.key, column)}
      decodeVal={decodeVal}
      decodeDisplay={decodeDisplay}
    />
  }

  onTemplateSelect =(value, key) =>{
    let {dbTableNames,dataSource} = this.state;
    if(this.checkRepeat(value)){
      warn({
        title: '提示',
        content: <div>已保存过该表名的数据,请勿重复选择</div>
      });
      return;
    }
    this.refreshOptions(dbTableNames,dataSource);
    let target = dataSource.filter(item => key === item.key)[0];
    const templateTarget = dbTableNames.filter(item => value === item.dbTableName)[0];
    if(!VHStrUtil.isNullOrEmpty(target.templateId)){
      templateTarget.templateId = target.templateId;
      templateTarget.key = target.key;
    }
    if (target) {
      target = Object.assign(target,templateTarget)
    }
    this.setState({dataSource: dataSource,dbTableNames:dbTableNames})
  }


  //把列表中已经有的，置为disabled
  refreshOptions(dbTableNames=[],dataSource=[]) {
    dbTableNames.map((table) => {
      const res = dataSource.filter(item => table.dbTableName === item.dbTableName)
      table.disable = res.length > 0;
    })
    this.setState({dbTableNames:dbTableNames})
  }

  checkRepeat = (value) =>{
    let {dataSource,dbTableNames} = this.state;
    const repeat = dataSource.filter(item => value === item.dbTableName);
    return repeat.length !== 0;
  }

  handleChange(value, key, column) {
    const {dataSource} = this.state;
    const target = dataSource.filter(item => key === item.key)[0];
    if (target) {
      target[column] = value
      this.setState({dataSource: dataSource})
    }

  }

  componentWillMount() {
    this.props.getTemplateEntity();
    this.props.queryTemplateEntity("","");
  }

  componentWillReceiveProps(nextProps) {
    if(nextProps.dbTableNames && nextProps.dbTableNames.length>0){
      this.setState({dbTableNames:nextProps.dbTableNames})
    };
      this.setState({dataSource:nextProps.dataSource});
      this.refreshOptions(nextProps.dbTableNames,nextProps.dataSource);
  }

  querySuccess = (result) => {
    message.info("获取成功")
  }

  saveList =(selectedDataSource,state) =>{
    const checkInfo = this.checkData(selectedDataSource);
    if (checkInfo.result) {
      let selectedDataList = selectedDataSource.map(item => {
        let model = new FileTemplate();
        delete item.$$editable;
        model.copyPropValues(item);
        return model;
      });
      this.props.saveTemplateEntity(selectedDataList,() => {
        message.info("保存成功");
        state.setState({
          selectedRowKeys: [],
        });
        this.props.queryTemplateEntity("", "")
      })

    }else{
      warn({
        title: '系统提示',
        content: <div> {checkInfo.message.map((message) => <p>{message}</p>)}</div>
      });
    }
  }

  checkData = (selectedDataSource) => {
    let checkInfo = {
      result: true,
      message: []
    };
    selectedDataSource.forEach((item, index) => {
      if (!item.templateName) {
        checkInfo.message.push(`第${index + 1}行模板名称不能为空`);
      }
      if (!item.dbTableName) {
        checkInfo.message.push(`第${index + 1}行表名不能为空`);
      }
    });
    checkInfo.result = checkInfo.message.length === 0;
    return checkInfo;
  };

  delete =(selectedRowKeys,state) =>{
    const {dataSource} = this.state;
    const pkHospitals = dataSource.filter(item => selectedRowKeys.some(key => item.key === key)).map(item => "'"+item.templateId+"'" ).join(",");
    this.props.deleteTemplateEntity(pkHospitals,()=>{
      message.success("删除成功！");
      state.setState({
        selectedRowKeys: []
      });
      this.props.queryTemplateEntity("", "")});
  }


  render() {
    return (
      <div className="vh">
        <HeaderContainer/>
        <DictBody
          columns={this.columns}
          dataSource={this.state.dataSource}
          onSave={this.saveList}
          onDelete={this.delete}
          model={FileTemplate}
          showStartOrStop={false}
        />
      </div>
    )
  }
}

const mapStateToProps = (state) => {
  return {...state.importTemplate}
};

const mapDispatchToProps = (dispatch) => {
  return {
    queryTemplateEntity: (templateName,dbTableName) => {
      if (templateName !== undefined && dbTableName !== undefined) {
        dispatch(queryTemplateEntity(templateName,dbTableName,this.querySuccess))
      }
    },
    getTemplateEntity: () => {
        dispatch(getTemplateEntity())
    },
    saveTemplateEntity:(selectedDataList,succFn)=>{
      if(selectedDataList!==undefined){
        dispatch(saveTemplateEntity(selectedDataList,succFn))
      }
    },
    deleteTemplateEntity:(pkHospitals,succFn)=>{
      if(pkHospitals!==undefined){
        dispatch(deleteTemplateEntity(pkHospitals,succFn))
      }
    }
  }
};

export default connect(mapStateToProps,mapDispatchToProps)(TemplateEntity)



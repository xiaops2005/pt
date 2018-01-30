import React from 'react';
import {ProjectCategoryService} from '../../../process/LoadService';
import {EditableCellInput, EditableCellSelect} from '../component/EditableTable'
import {message, Modal} from 'antd';
import {Header} from './components/Header'
import DictBody from '../component/DictBody'
import DateUtil from "../../../../../constants/DateUtil";
import {CategoryDictModel} from '../models/bdDictModel'
import {connect} from 'react-redux';
const processor = new ProjectCategoryService();
const warn = Modal.warn;

class ProjectLargeCategory extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      dataSource: [],
      count: 0,
      hospitalList: [],
      selectedRowKeys: [],
    };

    this.isStopOptions = [{key: 1, name: "是", value: '1'},
      {key: 0, name: "否", value: '0'}]

    this.columns = [
      {
        title: '项目大类编码',
        dataIndex: 'classCode',
        width: '20%',
        render: (text, record) => this.renderColumns(text, record, 'classCode'),
      },
      {
        title: '项目大类名称',
        dataIndex: 'className',
        width: '20%',
        render: (text, record) => this.renderColumns(text, record, 'className'),
      },
      {
        title: '所属医院',
        dataIndex: 'pkHospital',
        width: '12%',

        render: (text, record) => this.renderColumnsSelect(text, record,
          this.state.hospitalList, "pkHospital", "pkHospital", "hospitalName")

      }, {
        title: '是否停用',
        dataIndex: 'isStop',
        width: '10%',
        render: (text, record) => this.renderColumnsSelect(text,record,this.props.isStopList,"isStop","codeId","codeName"),
        // render: (text, record) => this.renderColumnsSelect(text, record, this.isStopOptions, "isStop", "value", "name")
      },
      {
        title: '备注',
        dataIndex: 'remark',
        width: '10%',
        render: (text, record) => this.renderColumns(text, record, 'remark'),
      },
      {
        title: '创建人',
        dataIndex: 'creator',
        width: '10%'
      },

      {
        title: '创建时间',
        dataIndex: 'creationTime',
        width: '14%',
        render: function (value) {
          return DateUtil.formatDate(value);
        }
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

  renderColumnsSelect(text, record, options, column, decodeVal, decodeDisplay) {
    return <EditableCellSelect
      editable={record.$$editable}
      options={options}
      value={text}
      onChange={val => this.handleChange(val, record.key, column)}
      decodeVal={decodeVal}
      decodeDisplay={decodeDisplay}
    />
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
    processor.queryHospitals((result) => {

      this.setState({hospitalList: result.getSinglePrimary()})
    })
    this.handleQueryChange("","","","");

  }

  querySuccess = (result) => {
    this.setState({
      dataSource: result.getSinglePrimary().map((item, index) =>
        Object.assign(item, {key: index}))
    })
  }

  handleQueryChange = (pkHospital, classCode, className, isStop) => {
    processor.queryCategory(pkHospital, classCode, className, isStop, this.querySuccess);
  }
  saveList =(selectedDataSource,state) =>{
    const checkInfo = this.checkData(selectedDataSource);
    if (checkInfo.result) {
      let selectedDataList = selectedDataSource.map(item => {
        //Tip BdDept 继承了Base带入了多余的父类属性，无法直接保存
        let model = new CategoryDictModel();
        //让dataSource选中行置为不可编辑状态
        delete item.$$editable;
        model.copyPropValues(item);
        return model;
      });
      processor.save(selectedDataList, (result) => {
        message.info("保存成功");
        state.setState({
          selectedRowKeys: [],
        });
        this.handleQueryChange("", "", "", "")
      });

    }else{
      warn({
        title: '系统提示',
        content: checkInfo.message.join(',')
      });
    }
  }

  checkData = (selectedDataSource) => {
    let checkInfo = {
      result: true,
      message: []
    };
    selectedDataSource.forEach((item, index) => {
      if (!item.classCode) {
        checkInfo.message.push(`第${index + 1}项目大类编码不能为空`);
      }
      if (!item.className) {
        checkInfo.message.push(`第${index + 1}项目大类名称不能为空`);
      }
      if (!item.pkHospital) {
        checkInfo.message.push(`第${index + 1}行所属医院不能为空`);
      }
      if (!item.isStop) {
        checkInfo.message.push(`第${index + 1}行是否停用不能为空`);
      }
    });
    checkInfo.result = checkInfo.message.length === 0;
    return checkInfo;
  };
  delete =(selectedRowKeys,state) =>{
    const {dataSource} = this.state;
    const pkHospitals = dataSource.filter(item =>
      selectedRowKeys.some(key => item.key === key)).map(item =>
      item.pkChargeClass ).join(",");
    processor.delete(pkHospitals, (result) => {
      message.success("删除成功！");
      state.setState({
        selectedRowKeys: []
      });
      this.handleQueryChange("", "", "", "")
    });
  }
  startOrStop =(selectedRowKeys,flag,state) =>{
    const {dataSource} = this.state;
    const pkHospitals = dataSource.filter(item =>
      selectedRowKeys.some(key => item.key === key)).map(item =>
      item.pkChargeClass ).join(",");
    processor.startOrStop(pkHospitals,flag, (result) => {
      message.info(result.parameters.result);
      state.setState({
        selectedRowKeys: []
      });
      this.handleQueryChange("","","","")
    });
  }
  render() {
    return (
      <div className="vh">
        <Header
          {...this.props}
          onQueryChange={this.handleQueryChange}/>
        <DictBody
          columns={this.columns}
          dataSource={this.state.dataSource}
          onSave={this.saveList}
          onDelete={this.delete}
          onStartOrStop={this.startOrStop}
          model={CategoryDictModel}
        />
      </div>
    )
  }
}

const mapStateToProps = (state) => {
  return {...state.bdDict}
};


export default connect(
  mapStateToProps,
  // mapDispatchToProps
)(ProjectLargeCategory)


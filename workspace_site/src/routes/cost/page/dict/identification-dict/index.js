import React from 'react';
import {connect} from 'react-redux';

import {BdDictService, IdentificationService} from '../../../process/LoadService';
import {EditableCellInput, EditableCellSelect} from '../component/EditableTable'
import {message, Modal} from 'antd';
import {Header} from './components/Header'
import DictBody from '../component/DictBody'
import DateUtil from "../../../../../constants/DateUtil";
import {IdentificationDictModel} from "../models/bdDictModel";
import {queryBdIsStop} from "../models/actions/bdDictAction";

const processor = new IdentificationService();
const bdProcessor = new BdDictService();

const warn = Modal.warn;

class Identification extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      dataSource: [],
      count: 0,
      hospitalOptions:[],
      selectedRowKeys:[],
      isStopList:[],
    };

    this.columns = [{
      title: '医护标识编码',
      dataIndex: 'identifyCode',
      width: '15%',
      render: (text, record) => this.renderColumns(text, record, 'identifyCode'),
      sorter:(a,b)=>a.identifyCode - b.identifyCode
    }, {
      title: '医护标识名称',
      dataIndex: 'identifyName',
      width: '15%',
      render: (text, record) => this.renderColumns(text, record, 'identifyName'),
    }, {
      title: '所属医院',
      dataIndex: 'pkHospital',
      width: '10%',
      render: (text, record) => this.renderColumnsSelect(text,record,this.state.hospitalOptions,"pkHospital","pkHospital","hospitalName"),
      // sorter:(a,b)=>{
      //   const {hospitalOptions} = this.state;
      //   const aHos = hospitalOptions.filter((option)=>option.pkHospital === a.pkHospital)[0].hospitalName;
      //   const bHos = hospitalOptions.filter((option)=>option.pkHospital === b.pkHospital)[0].hospitalName;
      //   return aHos.substring(0,aHos.indexOf("医院")) - bHos.substring(0,aHos.indexOf("医院"))
      // }
    }
      , {
        title: '备注',
        dataIndex: 'remark',
        width: '10%',
        render: (text, record) => this.renderColumns(text, record, 'remark'),
      }
      , {
        title: '是否停用',
        dataIndex: 'isStop',
        width: '10%',
        render: (text, record) => this.renderColumnsSelect(text,record,this.props.isStopList,"isStop","codeId","codeName"),
        sorter:(a,b)=>a.isStop - b.isStop,
      }, {
        title: '创建人',
        dataIndex: 'creator',
        width: '10%',

      }, {
        title: '创建时间',
        dataIndex: 'creationTime',
        width: '10%',
        render: function (value) {
          return DateUtil.formatDate(value);
        },
        sorter:(a,b)=>a.creationTime - b.creationTime
      }, {
        title: '修改人',
        dataIndex: 'modifier',
        width: '10%',

      }, {
        title: '修改时间',
        dataIndex: 'modifiedTime',
        width: '10%',
        render: function (value) {
          return DateUtil.formatDate(value);
        },
        sorter:(a,b)=>a.modifiedTime - b.modifiedTime
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
    processor.queryHospitals((result)=>{
      this.setState({hospitalOptions:result.getSinglePrimary()})
    })
    // this.props.queryBdDict();
    this.handleQueryChange("","","","");
  }


  querySuccess = (result) => {
    this.setState({dataSource:result.getSinglePrimary().map((item, index) =>
        Object.assign(item, {key: index}))})
  }

  handleQueryChange = (hospital,identifyCode,identifyName,isStop) => {
    processor.query(hospital,identifyCode,identifyName,isStop,this.querySuccess);
  }

  saveList =(selectedDataSource,state) =>{
    const checkInfo = this.checkData(selectedDataSource);
    if (checkInfo.result) {
      let selectedDataList = selectedDataSource.map(item => {
        //Tip BdDept 继承了Base带入了多余的父类属性，无法直接保存
        let model = new IdentificationDictModel();
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
      if (!item.identifyCode) {
        checkInfo.message.push(`第${index + 1}行医护标识编码不能为空`);
      }
      if (!item.identifyName) {
        checkInfo.message.push(`第${index + 1}行医护标识名称不能为空`);
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
    const pkHospitals = dataSource.filter(item => selectedRowKeys.some(key => item.key === key)).map(item => item.pkMedicalIdentify ).join(",");
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
      selectedRowKeys.some(key => item.key === key)).map(item => item.pkMedicalIdentify ).join(",");
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
          model={IdentificationDictModel}
        />
      </div>
    )
  }
}

const mapStateToProps = (state) => {
  return {...state.bdDict}
};

export default connect(
  mapStateToProps
)(Identification)


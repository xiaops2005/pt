import React from 'react';
import {Button, Table, Modal, message} from 'antd';
import {EditableCellSelect, EditableCellInput} from '../../../dict/component/EditableTable'
import DateUtil from "../../../../../../constants/DateUtil";
import { VHDomUtil } from '../../../../../../constants/utils';
// import {BdMaterials} from '../../models/bdDictModel'
import UploadModal from "../../component/UploadModal";
import ApproveRecord from "../../component/ApproveRecord";
import '../index.css'
const dateFormat = 'YYYY/MM/DD';
const dataSource = [];

const confirm = Modal.confirm;
const warn = Modal.warn;
var numeral = require('numeral');

class Body extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      yearList: [],
      mappedYear: {},
      isSingleList: [{
        codeId:'1',codeName:'是'
      },{
        codeId:'0',codeName:'否'
      }],
      mappedIsSingle: {},
      hospitalList: [],
      mappedHospital: {},
      deptList: [],
      mappedDept: {},
      materialsList:[],
      mappedMaterials:{},
      selectedRowKeys: [],
      dataSource: [],
    };
    this.columns = [
      {
        title: '年度',
        dataIndex: 'accYear',
        width: '4%',
      },{
        title: '材料科室编码',
        key:'pkDept1',
        dataIndex: 'pkDept',
        width: '8%',
        render: (value, record) => {
          return (
            <EditableCellSelect
              options={this.state.deptList}
              mappedOptions={this.state.mappedDept}
              value={value}
              decodeVal='pkDept'
              decodeDisplay='deptCode'
            />
          )}
      }, {
        title: '材料科室名称',
        key:'pkDept2',
        dataIndex: 'pkDept',
        width: '8%',
        render: (value, record) => {
          return (
            <EditableCellSelect
              options={this.state.deptList}
              mappedOptions={this.state.mappedDept}
              value={value}
              decodeVal='pkDept'
              decodeDisplay='deptName'
            />
          )}
      },{
        title: '材料编码',
        key:'pkMaterials1',
        dataIndex: 'pkMaterials',
        width: '8%',
        render: (value, record) => {
          return (
            <EditableCellSelect
              options={this.state.materialsList}
              mappedOptions={this.state.mappedMaterials}
              value={value}
              decodeVal='pkMaterials'
              decodeDisplay='materialsCode'
            />
          )}
      }, {
        title: '材料名称',
        key:'pkMaterials2',
        dataIndex: 'pkMaterials',
        width: '8%',
        render: (value, record) => {
          return (
            <EditableCellSelect
              options={this.state.materialsList}
              mappedOptions={this.state.mappedMaterials}
              value={value}
              decodeVal='pkMaterials'
              decodeDisplay='materialsName'
            />
          )}
      },{
        title: '计量单位',
        key:'pkMaterials3',
        dataIndex: 'pkMaterials',
        width: '4%',
        render: (value, record) => {
          return (
            <EditableCellSelect
              options={this.state.materialsList}
              mappedOptions={this.state.mappedMaterials}
              value={value}
              decodeVal='pkMaterials'
              decodeDisplay='unitName'
            />
          )}
      },{
        title: '规格型号',
        key:'pkMaterials4',
        dataIndex: 'pkMaterials',
        width: '4%',
        render: (value, record) => {
          return (
            <EditableCellSelect
              options={this.state.materialsList}
              mappedOptions={this.state.mappedMaterials}
              value={value}
              decodeVal='pkMaterials'
              decodeDisplay='model'
            />
          )
        }
      },{
        title: '数量',
        dataIndex: 'quantity',
        width: '3%',
      },{
        title: '单价',
        dataIndex: 'unitPrice',
        width: '4%',
        render: (value, record) => {
          return (
            <div>{numeral(value).format('0,0.00')}</div>
          )
        }
      },{
        title: '金额',
        dataIndex: 'amount',
        width: '4%',
        render: (value, record) => {
          return (
            <div>{numeral(value).format('0,0.00')}</div>
          )
        }
      },{
        title: '是否单收费',
        dataIndex: 'isCharges',
        width: '4%',
      },{
        title: '项目大类',
        dataIndex: 'pkChargeClass',
        width: '4%',
      },{
        title: '状态',
        dataIndex: 'statusId',
        width: '4%',
      },{
        title: '创建人',
        dataIndex: 'creator',
        width: '4%'
      }, {
        title: '创建时间',
        dataIndex: 'creationtime',
        width: '5%',
        render: function (value) {
          return DateUtil.formatDate(value);
        },
        // sorter:(a,b)=>a.creationTime - b.creationTime
      }, {
        title: '修改人',
        dataIndex: 'modifier',
        width: '4%'
      }, {
        title: '修改时间',
        dataIndex: 'modifiedtime',
        width: '5%',
        render: function (value) {
          return DateUtil.formatDate(value);
        },
      }, {
        title: '审核人',
        dataIndex: 'approver',
        width: '5%'
      }, {
        title: '审核时间',
        dataIndex: 'approvetime',
        width: '5%',
        render: function (value) {
          return DateUtil.formatDate(value);
        },
        // sorter:(a,b)=>a.creationTime - b.creationTime
      }, {
        title: '审核评语',
        dataIndex: 'approvenote',
        width: '5%'
      }];
  }

  componentWillMount() {
    console.log("Body componentWillMount");
  }

  componentWillReceiveProps(nextProps) {
    this.setState({...nextProps});
  }

  importExcel =(e)=>{
    e.preventDefault();
    this.refs.uploadModal.setState({visible:true})
  };

  importExcelExtractDict =()=>{

  };

  viewApproveRecord =(e)=>{
    e.preventDefault();
    this.refs.approveRecord.setState({visible:true})
  };

  exportExcel =(e)=>{
    e.preventDefault();
    // this.props.header.props.form.validateFields((err, values) => {
    //   if (!err) {
    //     console.info('values', values);
    //     console.info(`this.props.header.form.getFieldValue('materialsName'): ${this.props.header.props.form.getFieldValue('materialsName')}`);
    //   }
    // });
    // let year = this.props.header.props.form.getFieldValue('year');
    // let pkHospital = this.props.header.props.form.getFieldValue('pkHospital');
    // let materialsDeptCode = this.props.header.props.form.getFieldValue('materialsDeptCode');
    // let materialsDeptName = this.props.header.props.form.getFieldValue('materialsDeptName');
    // let isCharges = this.props.header.props.form.getFieldValue('isCharges');
    // let pkMaterials = this.props.header.props.form.getFieldValue('pkMaterials');
    let params = this.props.header.props.form.getFieldsValue()
    // this.state.exportReMateDetail(params);
    this.props.onExport(params)
  };

  // 组件挂载后触发函数
  componentDidMount() {
    window.addEventListener('resize', this.resetTableBodyHeight);
    this.resetTableBodyHeight();
  }

  // 修改表格高度
  resetTableBodyHeight = () => {
    let headerHeight = VHDomUtil.getElementHeight('assets-dict-header'),
      windowHeight = window.innerHeight;
    console.log(headerHeight + '----' + windowHeight);
    // 设置 table Body 的高度
    VHDomUtil.setTableBodyHeight(windowHeight - headerHeight - 380);
  };

  render() {
    const {selectedRowKeys} = this.state;
    const rowSelection = {
      selectedRowKeys,
      onChange: (selectedRowKeys, selectedRows) => {
        console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
        this.setState({selectedRowKeys});
      }
    };
    // console.log(this.state.modalVisible);
    // console.log(this.state.approveRecordVisible);
    console.log("Body render")
    return (
      <div className="vh-body">
        <div className="table-operations">
          <Button className="btn-primary" onClick={this.importExcel}>导入</Button>
          <Button className="btn-primary" onClick={this.importExcelExtractDict}>导入并提取字典</Button>
          <Button className="btn-primary" onClick={this.viewApproveRecord}>查看审批记录</Button>
          <Button className="btn-primary" onClick={this.exportExcel}>导出</Button>
        </div>
        <Table  bordered className="main-section" scroll={{ x: '200%',y: true}} pagination={{pageSize: 10}} rowSelection={rowSelection} dataSource={this.state.dataSource} columns={this.columns}/>
        <UploadModal downloadUrl="/static/fileTemplate/材料明细.xlsx"
                     jsonTemplate="properties/ReMateDetailTemplate.json"
                     templateName="材料明细"
                     action="upload"
                     ref="uploadModal"
        />
        <ApproveRecord ref="approveRecord"/>
      </div>
    )
  }
}

export default Body



import React from 'react';
import {Button, Table, Modal,message} from 'antd';
import { EditableCellInput} from '../../../dict/component/EditableTable'
import {BdAssets} from '../../../dict/models/bdDictModel'
import DateUtil from "../../../../../../constants/DateUtil";
import {ReCostService} from "../../../../process/LoadService";
import UploadModal from "../../component/UploadModal";
import NetUtil from "../../../../../../constants/httpUtil";
import ApproveRecord from "../../component/ApproveRecord";
const url = NetUtil.getUrl()+"/api/";

const confirm = Modal.confirm;
const warn = Modal.warn;
const processor = new ReCostService();

class Body extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      selectedRowKeys: [],
      dataSource: [],
      approvalDataSource: [],
      modalVisible: false,
      modalTitle: '',
      exportExcelPath:''
    };
    this.columns = [
      {
        title: '行号',
        dataIndex: 'key',
        width: '3%',
        render: (value11) => {
          return (
            <div>{value11 + 1}</div>
          );
        }
      },
      {
        title: '级次',
        dataIndex: 'deptLevel',
        width: '3%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "deptLevel")}
            />
          );
        }
      }, {
        title: '成本科室编码',
        dataIndex: 'deptCode',
        width: '7%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "deptCode")}
            />
          );
        }

      }, {
        title: '成本科室名称',
        dataIndex: 'deptName',
        width: '7%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "deptName")}
            />
          );
        }
      }
      , {
        title: '人员经费',
        dataIndex: 'wageAmount',
        width: '5%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "wageAmount")}
            />
          );
        }
      }, {
        title: '卫生材料费',
        dataIndex: 'materialsAmount',
        width: '6%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "materialsAmount")}
            />
          );
        }
      }, {
        title: '药品费',
        dataIndex: 'drugAmount',
        width: '4%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "drugAmount")}
            />
          );
        }
      }, {
        title: '固定资产折旧',
        dataIndex: 'assetsAmount',
        width: '7%',
        function (value,record) {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "assetsAmount")}
            />
          );
        }
      }, {
        title: '无形资产摊销',
        dataIndex: 'intangibleAmount',
        width: '7%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "intangibleAmount")}
            />
          );
        }
      }, {
        title: '提取风险基金',
        dataIndex: 'riskFundsAmount',
        width: '7%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "riskFundsAmount")}
            />
          );
        }
      }, {
        title: '其他费用',
        dataIndex: 'otherAmount',
        width: '5%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "otherAmount")}
            />
          );
        }
      }, {
        title: '科室合计',
        dataIndex: 'deptAmountSum',
        width: '5%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "deptAmountSum")}
            />
          );
        }
      }, {
        title: '状态',
        dataIndex: 'statusId',
        width: '3%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "statusId")}
            />
          );
        }
      }, {
        title: '创建人',
        dataIndex: 'creator',
        width: '4%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "creator")}
            />
          );
        }
      }, {
        title: '创建时间',
        dataIndex: 'creationtime',
        width: '5%',
        render: function (value) {
          return DateUtil.formatDate(value);
        }
      }, {
        title: '审核人',
        dataIndex: 'approver',
        width: '5%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "approver")}
            />
          );
        }
      },  {
        title: '审核时间',
        dataIndex: 'approvetime',
        width: '5%',
        render: function (value) {
          return DateUtil.formatDate(value);
        }
      },{
        title: '审核评语',
        dataIndex: 'approvenote',
        width: '5%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "approvenote")}
            />
          );
        }
      }];
    this.approvalColumns=[{
      title: '行号',
      dataIndex: 'key',
      width: '3%',
      render: (value11) => {
        return (
          <div>{value11 + 1}</div>
        );
      }
    },
      {
        title: '所属医院',
        dataIndex: 'pkHospital',
        width: '3%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "deptLevel")}
            />
          );
        }
      },];

  }

  componentWillReceiveProps(nextProps) {
    this.setState({...nextProps});
  }

  handleChange = (value, key, column) => {
    const {dataSource} = this.state;
    const target = dataSource.filter(item => key === item.key)[0];
    if (target) {
      target[column] = value;
      this.setState({dataSource: dataSource});
    }
  };

  viewApproveRecord =(e)=>{
    e.preventDefault();
    this.refs.approveRecord.setState({visible:true})
  };

  importExcel =(e)=>{
    e.preventDefault();
    this.refs.uploadModal.setState({visible:true})
  };


  exportExcel =()=>{
    processor.exportExcel("","","","",(data) =>{
      let path=data.parameters.result;
      window.location.href=url+path;
    });
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
    return (
      <div>
        <div className="class-buttons">
          <Button className="btn-primary" onClick={this.importExcel}>导入</Button>
          <Button className="btn-primary" onClick={this.importExcel}>导入并提取字典</Button>
          <Button className="btn-primary" onClick={this.viewApproveRecord}>查看审批记录</Button>
          <Button className="btn-primary" icon="download" onClick={this.exportExcel}>导出</Button>

        </div>
        <Table bordered pagination={{pageSize: 10}} size='small' rowSelection={rowSelection}
               dataSource={this.state.dataSource}
               columns={this.columns} scroll={{ x:2000, y: true }}/>
        <div>
          <UploadModal
            downloadUrl="/static/fileTemplate/成本报表2016.xlsx"
            jsonTemplate="properties/reCost.json"
            templateName="全成本报表"
            action="upload"
            ref="uploadModal"
          />
          <ApproveRecord ref="approveRecord" {...this.props}/>
        </div>
      </div>
    )
  }
}


export default Body



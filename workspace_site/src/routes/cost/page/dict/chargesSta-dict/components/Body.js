import React from 'react';
import {Button, Table, Modal,message} from 'antd';
import { EditableCellInput} from '../../../dict/component/EditableTable'
import DateUtil from "../../../../../../constants/DateUtil";
import {ReCostService} from "../../../../process/LoadService";
import NetUtil from "../../../../../../constants/httpUtil";
import UploadModal from "../../../collect/component/UploadModal";
const url = NetUtil.getUrl()+"/api/";

const confirm = Modal.confirm;
const warn = Modal.warn;
const processor = new ReCostService();

class Body extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      dataSource: [],
    };
    this.columns = [{
      title: '标准服务项目编码',
      dataIndex: 'chargesCode',
      width: '15%',
      render: (text, record) => this.renderColumns(text, record, 'identifyCode'),
      sorter:(a,b)=>a.identifyCode - b.identifyCode
    }, {
      title: '标准服务项目名称',
      dataIndex: 'chargesName',
      width: '15%',
      render: (text, record) => this.renderColumns(text, record, 'identifyName')
    }, {
      title: '项目描述',
      dataIndex: 'chargesDesc',
      width: '10%',
      render: (text, record) => this.renderColumns(text, record, 'chargesDesc')
    }
      , {
        title: '项目内涵',
        dataIndex: 'connotation',
        width: '10%',
        render: (text, record) => this.renderColumns(text, record, 'connotation'),
      }
      , {
        title: '除外内容',
        dataIndex: 'exceptContent',
        width: '10%',
        render: (text, record) => this.renderColumns(text, record, 'exceptContent')
      }, {
        title: '计量单位',
        dataIndex: 'unitName',
        width: '10%',
        render: (text, record) => this.renderColumns(text, record, 'unitName')
      }, {
        title: '单价',
        dataIndex: 'unitPrice',
        width: '10%',
        render: (text, record) => this.renderColumns(text, record, 'unitPrice')
      }, {
        title: '备注',
        dataIndex: 'remark',
        width: '10%',
        render: (text, record) => this.renderColumns(text, record, 'identifyCode')
      }, {
        title: '创建人',
        dataIndex: 'creator',
        width: '10%',
        render: (text, record) => this.renderColumns(text, record, 'identifyCode')
      }, {
        title: '创建时间',
        dataIndex: 'creationtime',
        width: '10%',
        render: function (value) {
          return DateUtil.formatDate(value);
        },
        sorter:(a,b)=>a.creationtime - b.creationtime
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


  importExcel =(e)=>{
    e.preventDefault();
    this.refs.uploadModal.setState({visible:true})
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

        </div>
        <Table bordered pagination={{pageSize: 10}} size='small' rowSelection={rowSelection}
               dataSource={this.state.dataSource}
               columns={this.columns} />
        <div>
          <UploadModal
            downloadUrl="/static/fileTemplate/成本报表2016.xlsx"
            jsonTemplate="properties/reCost.json"
            templateName="全成本报表"
            action="upload"
            ref="uploadModal"
          />
        </div>
      </div>
    )
  }
}


export default Body



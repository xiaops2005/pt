import React from 'react';
import {Button, Table, Modal} from 'antd';
import DateUtil from "../../../../../../constants/DateUtil";

import UploadModal from "../../component/UploadModal";

const confirm = Modal.confirm;
const warn = Modal.warn;

class Body extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      yearList: [],
      mappedYear: {},
      dataSource: [],
      selectedRowKeys: [],
    };
    this.columns = [

      {
        title: '年度',
        dataIndex: 'accYear',
        width: '5%',

      }, {
        title: '成本科室编码',
        dataIndex: 'project',
        width: '9%',
      }, {
        title: '成本科室名称',
        dataIndex: 'pkHospital',
        width: '9%',
      }
      , {
        title: '医生人数',
        dataIndex: 'doctorNum',
        width: '7%',
      }, {
        title: '医生经费',
        dataIndex: 'doctorWage',
        width: '7%',
      }, {
        title: '护士人数',
        dataIndex: 'nurseNum',
        width: '7%',
      }, {
        title: '护士经费',
        dataIndex: 'nurseWage',
        width: '7%',
      }, {
        title: '经费合计',
        dataIndex: 'amountSum',
        width: '7%',
      }, {
        title: '状态',
        dataIndex: 'statusId',
        width: '5%',
      }, {
        title: '创建人',
        dataIndex: 'creator' +
        ':\n' +
        '"',
        width: '6%',
      },
      {
        title: '创建时间',
        dataIndex: 'creationtime',
        width: '7%'
      },
      {
        title: '审核人',
        dataIndex: 'approver',
        width: '6%',
        render: function (value) {
          return DateUtil.formatDate(value);
        },
      },
      {
        title: '审核时间',
        dataIndex: 'approvetime',
        width: '7%'
      },
      {
        title: '审核评语',
        dataIndex: 'approvenote',
        width: '7%'
      }

    ];
  }

  componentWillReceiveProps(nextProps) {
    this.setState({...nextProps});
  }


  onSelectRowChange = (selectedRowKeys) => {
    console.log('selectedRowKeys changed: ', selectedRowKeys);
    this.setState({selectedRowKeys});
  }

  import =(e)=>{
    e.preventDefault();
    this.refs.uploadModal.setState({visible:true})
  };

  onCancel =() =>{

    this.setState({modalVisible:false})
  }


  //导出
  export =() =>{

  }
  //查看
  viewApprovalRecord = () =>{

  }


  render() {
    const {dataSource, selectedRowKeys} = this.state;
    const rowSelection = {
      selectedRowKeys,
      onChange: this.onSelectRowChange,
    };
    return (
      <div>

        <div className="class-buttons">
          <Button className="btn-primary" onClick={this.import}>导入</Button>

          <Button className="btn-primary" onClick={this.viewApprovalRecord}>查看审批记录</Button>
          <Button className="btn-primary" onClick={this.export}>导出</Button>
        </div>
        <Table bordered pagination={{pageSize: 10}} rowSelection={rowSelection} size='small'
               dataSource={this.state.dataSource} columns={this.columns}/>

        <UploadModal
                     downloadUrl="/static/fileTemplate/人员经费明细.xls"
                     jsonTemplate="properties/ReWageTemplate.json"
                     templateName="人员经费"
                     action="upload"
                     ref="uploadModal"
        />

      </div>
    )
  }
}

export default Body





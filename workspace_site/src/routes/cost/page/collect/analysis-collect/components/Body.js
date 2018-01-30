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
      modalVisible:false,
    };


    this.columns = [
      {
        title: '序号',
        dataIndex: 'key',
        width: '5%',
        render: (value) => {
          return (
            <div>{value + 1}</div>
          );
        }
      },
      {
        title: '年度',
        dataIndex: 'year',
        width: '5%',

      }, {
        title: '项目',
        dataIndex: 'project',
        width: '10%',
      }, {
        title: '单位',
        dataIndex: 'unit',
        width: '5%',
      }
      , {
        title: '期末数',
        dataIndex: 'endCount',
        width: '5%',
      }, {
        title: '上年同期数',
        dataIndex: 'lastCount',
        width: '5%',
      }, {
        title: '本月止累计数',
        dataIndex: 'totalCount',
        width: '5%',
      }, {
        title: '项目',
        dataIndex: 'project2',
        width: '10%',
      }, {
        title: '单位',
        dataIndex: 'unit2',
        width: '5%',
      }, {
        title: '期末数',
        dataIndex: 'endCount2',
        width: '5%',
      }, {
        title: '比上年同期数',
        dataIndex: 'lastCount2',
        width: '5%',
      },
      {
        title: '创建人',
        dataIndex: 'creator',
        width: '5%'
      }, {
        title: '创建时间',
        dataIndex: 'creationtime',
        width: '5%',
        render: function (value) {
          return DateUtil.formatDate(value);
        },
      }

    ];

  }

  componentWillReceiveProps(nextProps) {
    this.setState({...nextProps});
  }

  import =() =>{
    this.refs.uploadModal.setState({visible:true})
  }

  export =() =>{
    this.props.onExport();
  }

  render() {
    return (
      <div>
        <div className="class-buttons">
          <Button className="btn-primary" onClick={this.import}>导入</Button>
          <Button className="btn-primary" onClick={this.export}>导出</Button>
        </div>
        <Table bordered pagination={{pageSize: 10}} size='small'
               dataSource={this.state.dataSource}
               columns={this.columns}/>
        <UploadModal downloadUrl="/static/fileTemplate/人员经费明细.xls"
                     jsonTemplate="properties/ReWageTemplate.json"
                     templateName="人员经费"
                     ref="uploadModal"
        />
      </div>
    )
  }
}


export default Body



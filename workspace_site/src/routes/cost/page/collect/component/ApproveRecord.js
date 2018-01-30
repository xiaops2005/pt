import React from 'react';
import {connect} from 'react-redux';
import {Modal,Select,Table,message} from 'antd';
import NetUtil from "../../../../../constants/httpUtil";
import DateUtil from "../../../../../constants/DateUtil";
import {UploadService} from "../../../process/LoadService";
import {
  ApproveRecordService
} from './../../../process/LoadService'
const {Option} = Select.Option;
const processor = new ApproveRecordService();
const url = NetUtil.getUrl()+"/api/";

/**
 * 查看审批记录弹框
 *
 */

class ApproveRecord extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      approveRecordDataSource: [],
      visible: false
    };
    this.columns =[{
      title: '序号',
      dataIndex: 'key',
      width: '10%',
    },{
      title: '审核人',
      dataIndex: 'approver',
      width: '15%',
    },{
      title: '审核时间',
      dataIndex: 'approvetime',
      width: '20%',
      render: function (value) {
        return DateUtil.formatDate(value);
      },
    },{
      title: '审核意见',
      dataIndex: 'approvenote',
      width: '40%',
    },{
      title: '状态',
      dataIndex: 'statusId',
      width: '15%',
    }]
  }

  componentWillMount() {
    console.log("ApproveRecord componentWillMount");
    // this.props.queryApproveRecord();
    this.queryApproveRecord();
  }

  queryApproveRecord = () => {
    let that = this
    processor.queryApproveRecord((data) => {
      // dispatch(queryApproveRecord(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      that.setState({approveRecordDataSource:data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))})
    })
  }

  componentWillReceiveProps(nextProps) {
    console.log("ApproveRecord componentWillReceiveProps nextProps=",nextProps)
    this.setState({...nextProps});
  }

  render() {
    const {visible} = this.state;
    console.log("ApproveRecord  render visible=",visible)
    return (
      <Modal visible={visible}
             destroyOnClose={true}
             maskClosable={false}
             title={"查看审批记录"}
             footer={null}
             onCancel={() => this.setState({visible: false})}
             closable={true}>
        <Table  bordered pagination={{pageSize: 10}} size='small' dataSource={this.state.approveRecordDataSource} columns={this.columns}/>
      </Modal>
    )
  }
}

// const mapStateToProps = (state) => {
//   return {...state.bdDict}
// };
// const mapDispatchToProps = (dispatch, state) => {
//   return {
//     queryApproveRecord: () => {
//       processor.queryApproveRecord((data) => {
//         dispatch(queryApproveRecord(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
//       });
//     }
//   }
// };
// export default connect(
//   mapStateToProps,
//   mapDispatchToProps
// )(ApproveRecord)
export default ApproveRecord

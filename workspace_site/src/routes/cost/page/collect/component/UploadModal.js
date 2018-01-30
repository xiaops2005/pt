import React from 'react';

import {Modal,Select,Row,Col,Input,Button,Progress,Table,Upload,message} from 'antd';
import NetUtil from "../../../../../constants/httpUtil";
import DateUtil from "../../../../../constants/DateUtil";
import {UploadService} from "../../../process/LoadService";
import {VHStrUtil} from "../../../../../constants/utils";
import {HospitalService} from './../../../process/LoadService'

const hospitalProcessor = new HospitalService();
const Option = Select.Option;

const url = NetUtil.getUrl()+"/api/";
const uploadProcessor = new UploadService();

/**
 * 导入弹框
 *
 * 需要设置的属性：
 * downloadUrl：模板下载地址         例如：downloadUrl="/static/fileTemplate/人员经费明细.xls"
 * jsonTemplate：验证文件            例如： jsonTemplate="properties/ReWageTemplate.json"
 * templateName：显示的模板名称 （不设置的话，取downloadUrl最后一个/之后部分）      例如：templateName="人员经费"
 * action:url中/api/之后内容，大部分是“upload”      例如：action="upload"
 * visible:Modal框的显示和隐藏，true 或 false
 *
 */

class UploadModal extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      errorDataSource: [
        {key:1,error:"测试错误信息"},
        {key:2,error:"测试错误信息"},
        {key:3,error:"测试错误信息"},
        {key:4,error:"测试错误信息"},
        {key:5,error:"测试错误信息"},
        {key:6,error:"测试错误信息"}],
      count: 0,
      selectedRowKeys: [],
      hospitalOptions:[],
      visible:false,
      downloadUrl:"",
      tableDisplay:"none",
      uploadFile:null,
      jsonTemplate:"",
      action:"",
      uploadPercent: 0,
      validatePercent: 0,
      templateName:"",
    };
    this.columns =[{
      title: '序号',
      dataIndex: 'key',
      width: '15%',
    },{
      title: '错误信息',
      dataIndex: 'error',
      width: '85%',
    }]
  }

  componentWillReceiveProps(nextProps) {
    this.setState({...nextProps});
  }


  startUpload = () =>{
    const {uploadFile,jsonTemplate,templateName,action} = this.state
    uploadProcessor.upload(action,uploadFile,templateName,jsonTemplate);//TODO 还没实现
  }


  componentWillMount(){
    hospitalProcessor.queryBdHospitalAll((result)=>{
      this.setState({hospitalOptions:result.getSinglePrimaryList()})
    })
  }

  startValidate = (interval, info) => {
    setTimeout(() => {
      let {validatePercent} = this.state;
      if (info) {
        if (validatePercent >= 100) {
          // Window.progress.close();
          // 验证成功
          let file = info.file;
          file.status = 'done';
          file.time = DateUtil.formatDateTime(new Date());
          this.addFileToFileList(file);
          message.success(`${info.file.name} 文件上传成功`);
          this.setState({
            validatePercent: 100,
          })
        } else {
          this.setState({validatePercent: (parseFloat(validatePercent) + 1.01).toFixed(2)});
          this.startValidate(20, info);
        }
      } else if (validatePercent < 90) {
        this.setState({validatePercent: (parseFloat(validatePercent) + 0.06).toFixed(2)});
        this.startValidate(20);
      } else if (validatePercent < 99) {
        this.setState({validatePercent: (parseFloat(validatePercent) + 0.02).toFixed(2)});
        this.startValidate(100);
      }
    }, interval)
  };

  onCancel =() =>{
    this.setState({visible: false,
      uploadFile:null,
      uploadPercent:0,
      validatePercent:0});
  }

  render() {

    const {visible,downloadUrl,uploadPercent,validatePercent,action,uploadFile,errorDataSource,templateName} = this.state;

    const uploadProps = {
      action: url+action,
      accept:"application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
      beforeUpload: (file) => {
        this.setState({uploadFile:file});
        return false;
      },
    }
    const tableDisplay = errorDataSource.length>0?"flex":"none";

    return (
      <Modal visible={visible}
             destroyOnClose={true}
             maskClosable={false}
             onCancel={this.onCancel}
             footer={null}
             title={"导入"}
             closable={true}>

        <Row gutter={24} type="flex" align="middle">
          <Col span={2}/>
          <Col span={4}>所属单位:</Col>
          <Col span={8}>
            <Select allowClear style={{width:"100%"}}>
            {
              this.state.hospitalOptions.map((item)=> {
                return <Option key={item.pkHospital} value={item.pkHospital}>{item.hospitalName}</Option>;
              })
            }
            </Select>
          </Col>
        </Row>

        <Row gutter={24} style={{marginTop:"10px"}} type="flex" align="middle">
          <Col span={2}/>
          <Col span={4} ><span style={{verticalAlign:"center"}}>模板下载：</span></Col>
          <Col span={8}><a href={downloadUrl}>{VHStrUtil.isNullOrEmpty(templateName)?downloadUrl.substring(downloadUrl.lastIndexOf("/")+1):templateName}</a></Col>
        </Row>

        <Row gutter={24} style={{marginTop:"10px"}} type="flex" align="middle">
          <Col span={2}/>
          <Col span={4} >选择文件：</Col>
          <Col span={8}><Input style={{width:"100%"}} value={uploadFile && uploadFile.name} disabled={true}/></Col>
          <Col span={4}><Upload {...uploadProps}><Button type={uploadFile || "primary"}>浏览</Button></Upload></Col>
          <Col span={4}><Button onClick={this.startUpload} type={uploadFile && "primary"}>上传</Button></Col>
        </Row>

        <Row gutter={24} style={{marginTop:"10px"}} type="flex" align="middle">
          <Col span={2}/>
          <Col span={4} >上传进度：</Col>
          <Col span={16}><Progress percent={uploadPercent>=100?validatePercent:uploadPercent}/></Col>
        </Row>

        <Row gutter={24} style={{marginTop:"10px",display:tableDisplay}} type="flex" align="middle">
          <Col span={2}/>
          <Col span={20} >
            <Table columns={this.columns}
                   bordered
                   pagination={{pageSize: 5,hideOnSinglePage:true,size:"small"}}
                   size='small'
                   dataSource={this.state.errorDataSource}/>
          </Col>
        </Row>

      </Modal>
    )
  }

}

export default UploadModal;

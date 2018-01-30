import React from 'react'
import {Upload, Icon, message, Steps, Progress, Row, Col, Modal, Table,DatePicker,Button} from 'antd'
import DateUtil from "../../../../constants/DateUtil";
import excelTemplate from "../../excelTemplate.js"
import NetUtil from "../../../../constants/httpUtil";

const Dragger = Upload.Dragger;
const Step = Steps.Step;
const url = NetUtil.getUrl()+"/api/";

const { MonthPicker} = DatePicker;


export default class VhUpload extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      uploadPercent: 0,
      validatePercent: 0,
      insertPercent: 0,
      stepsNode: 0,
      stepsStatus: 'wait',
      excelTemplate:excelTemplate,
      modalVisible: false,
      modalTitle: '',
      modalDataSource: [],
      progressStatus: 'active',
      dateString:''//选择的日期  暂时还未使用，
    };

  }

  handleChange = (info,key) => {
    let status = info.file.status;
    const {stepsNode} = this.state;
    const response = info.file.response;

    if (response !== undefined) {
      //收到服务器端返回内容后执行
      if (response.status !== 1) {
        let file = info.file;
        file.status = 'error';
        file.time = DateUtil.formatDateTime(new Date());
        file.result = response.result;
        this.addFileToFileList(file,key);
        this.setState({ progressStatus: "exception"});
        status = 'error';
        const con = <div style={{display:"flex",flexDirection:'column',justifyContent:'space-between'}}>
          <span>{response.message?response.message:"未知原因"}</span>
          <span style={{marginTop:"10px"}}>详细信息请点击文件名查看</span>
        </div>;
        Modal.error({
          title: "验证失败",
          content: con
        })
      }
    }

    if (info.event && info.event.percent) {
      let percent = info.event.percent.toFixed(2);
      this.setState({uploadPercent: percent});
      if (percent === "100.00") {
        //上传完成，进入下一步骤
        this.setState({stepsNode: stepsNode + 1, progressStatus: "active"});
        this.startValidate(5,null,key)
      }
    }
    if (status === 'done') {
      this.startValidate(2, info,key);
    } else if (status === 'error') {
      Window.progress.close();
      if (stepsNode === 0) {
        message.error(`${info.file.name} 文件上传失败`);
        this.setState({progressStatus: "exception"})
      }
      this.setState({stepsStatus: 'error'})
    } else if (status === 'uploading') {
      this.setState({stepsStatus: 'process'})
    }
  };

  addFileToFileList =(file,key) =>{
    const {excelTemplate} = this.state;
    debugger
    excelTemplate.filter((template)=>{return template.key === key})[0].fileList.push(file);
    this.setState({excelTemplate})
  }

  startValidate = (interval, info,key) => {
    setTimeout(() => {
      let {validatePercent, stepsStatus} = this.state;
      if (info) {
        if (validatePercent >= 100) {
          Window.progress.close();
          // 验证成功
          let file = info.file;
          file.status = 'done';
          file.time = DateUtil.formatDateTime(new Date());
          this.addFileToFileList(file,key);
          message.success(`${info.file.name} 文件上传成功`);
          this.setState({
            stepsNode: this.state.stepsNode + 1,
            validatePercent: 100,
            progressStatus: "success"
          })
        } else {
          this.setState({validatePercent: (parseFloat(validatePercent) + 1.01).toFixed(2)});
          this.startValidate(20, info,key);
        }
      } else if (validatePercent < 90 && stepsStatus !== 'error') {
        this.setState({validatePercent: (parseFloat(validatePercent) + 0.06).toFixed(2)});
        this.startValidate(20,null,key);
      } else if (validatePercent < 99 && stepsStatus !== 'error') {
        this.setState({validatePercent: (parseFloat(validatePercent) + 0.02).toFixed(2)});
        this.startValidate(100,null,key);
      }
    }, interval)
  };

  resetUploadStatus = () => {
    if (Window.progress.isProcessing) {
      return false;
    }
    Window.progress.open();
    //上传之前重置一下steps和进度条状态
    this.setState({
      uploadPercent: 0,
      validatePercent: 0,
      stepsNode: 0,
      stepsStatus: 'wait',
      progressStatus: 'active',
    })
  };

  showModal = (file) => {
    let dataSource = [];
    if (file.result !== undefined && file.result.length > 0) {
      for (let i = 0, len = file.result.length; i < len; i++) {
        let result = file.result[i];
        if (result.error !== undefined && result.error.length > 0) {
          for (let j = 0, len = result.error.length; j < len; j++) {
            let error = result.error[j];
            error.key = `${i}-${j}`;
            error.row = result.row;
            dataSource.push(error);
          }
        }
      }
    }
    if (dataSource.length > 0) {
      this.setState({
        modalVisible: true,
        modalTitle: file.name,
        modalDataSource: dataSource
      });
    }
  }


  render() {
    const {
      uploadPercent, validatePercent, stepsNode, stepsStatus,
      modalVisible, modalTitle, modalDataSource,excelTemplate,
      progressStatus
    } = this.state;

    const upLoadOpt = {
      name: 'file',
      multiple: false,
      showUploadList: false,
      action: url,
      onChange: this.handleChange,
      beforeUpload: this.resetUploadStatus
    };
    const downloadUrl = 'http://www.voidtools.com/Everything-1.4.1.877.x64-Setup.exe';
    const creator = 'ceshiCreator';
    const hospital = 'ceshiHospital';

    const modalColumns = [{
      title: '错误行',
      dataIndex: 'row',
      key: 'row',
      width: '5%',
      className: 'column-error'
    }, {
      title: '错误列',
      dataIndex: 'column',
      key: 'column',
      width: '10%'
    }, {
      title: '错误信息',
      dataIndex: 'message',
      key: 'message',
      width: '30%'
    }, {
      title: '错误值',
      dataIndex: 'rejectedValue',
      key: 'rejectedValue',
      width: '55%'
    }];

    return (
      <div className="vh">
        <div style={{height:"100px",display:"flex",alignItems:"center",paddingTop:"20px",paddingLeft:"5px",paddingRight:"5px"}}>
          <Steps current={stepsNode} status={stepsStatus}>
            <Step  title="上传"
                  icon={<Icon type={(stepsStatus === 'process' && stepsNode === 0) ? "loading" : "upload"}/>}/>
            <Step title="验证"
                  icon={<Icon type={(stepsStatus === 'process' && stepsNode === 1) ? "loading" : "solution"}/>}/>
            <Step title="完成" icon={<Icon type="smile-o"/>}/>
          </Steps>
        </div>


        <div style={{height: "30px", paddingTop: "5px",paddingLeft:"5px",paddingRight:"10px"}}>
          <Progress percent={uploadPercent>=100?validatePercent:uploadPercent} status={progressStatus}/>
        </div>

        <div style={{marginTop:"30px",display:"flex",alignItems:"center"}}>
          <span style={{fontSize:"20px"}}>文件类型列表</span>
          <MonthPicker style={{marginLeft:20}} placeholder="请选择月份"
                        onChange={(date,dateString)=>{this.setState({dateString:dateString})}}
          /></div>

        <div className="file-type-upload">
          {excelTemplate.map((fileType) => {
            fileType.hospital = hospital;
            fileType.creator = creator;
            // fileType.downloadUrl = downloadUrl;
            return <div className="file-type-item">
              <div className="file-type-upload-item">
                <div className="ant-upload-list-item ant-upload-list-item-done" style={{height: "20px", flexGrow: 1}}>
                  <div className="ant-upload-list-item-info">
                     <span>
                        <Icon type="upload"/>
                          <Dragger className="antd-dragger-upload" {...Object.assign({},upLoadOpt,{action:upLoadOpt.action+fileType.action})}
                                         onChange={(info)=>this.handleChange(info,fileType.key)}
                                         data={fileType}
                                         accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
                             <p className="ant-upload-text">{fileType.name}</p>
                          </Dragger>
                       {/*<Upload className="antd-dragger-upload" {...Object.assign({},upLoadOpt,{action:upLoadOpt.action+fileType.action})}*/}
                               {/*onChange={(info)=>this.handleChange(info,fileType.key)}*/}
                               {/*data={fileType}*/}
                               {/*accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"*/}
                       {/*>*/}
                         {/*<Button style={{width:"150px"}}>*/}
                           {/*<Icon type="upload"/>{fileType.name}*/}
                         {/*</Button>*/}
                       {/*</Upload>*/}
                     </span>
                  </div>
                </div>
                <div>
                <Icon type='download' style={{marginLeft: "1em"}}/>
                <a target='_blank' className="ant-upload-list-text" href={fileType.downloadUrl}>模板</a>
                </div>
              </div>
              <div className="ant-upload-list ant-upload-list-text">
                {fileType.fileList.map((file) => {
                  const color = (file.status != null && file.status === 'error') ? '#f00' : '#000';
                  return <div className="ant-upload-list-item ant-upload-list-item-done">
                    <div className="ant-upload-list-item-info">
                      <Row gutter={16} type="flex">
                        <Col span={8}>
                        <span>
                          <Icon type="file" style={{color: color}}/>
                            <a target="_blank" rel="noopener noreferrer" className="ant-upload-list-text"
                               title={file.name} style={{color: color}}
                               onClick={() => this.showModal(file)}>{file.name}</a>
                        </span>
                        </Col>
                        <Col span={8}>
                          <span style={{fontSize:"12px"}}>{file.time}</span>
                        </Col>
                      </Row>
                    </div>
                  </div>
                })}
              </div>
            </div>
          })}
        </div>


        <Modal visible={modalVisible}
               title={modalTitle}
               footer={null}
               onCancel={() => this.setState({modalVisible: false})}
               width='70vw'
          /*wrapClassName='vertical-center-modal'*/
        >
          <Table bordered={true} columns={modalColumns} dataSource={modalDataSource} size='small'
                 pagination={{pageSize: 8, hideOnSinglePage: true}}/>
        </Modal>
      </div>
    )
  }
}

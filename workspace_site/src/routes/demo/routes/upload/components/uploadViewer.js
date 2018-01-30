/**
 * Created by viewhigh on 2017/6/19.
 */
import React, {Component} from 'react';
import { Upload,Row, Col, Button, Icon,Tag } from 'antd';

class uploadViewer extends Component {

  state = {
    fileList: [],
    editable:false,
    url:''
  }
  handleChange = (info) => {
    let fileList = info.fileList;
    console.log(fileList);
    // 1. Limit the number of uploaded files
    //    Only to show two recent uploaded files, and old ones will be replaced by the new
    //fileList = fileList.slice(-2);

    // 2. read from response and show file link
    fileList = fileList.map((file) => {
      if (file.response) {
        // Component will show file.url as link
        console.log(file.response);
        this.setState({
          editable:true,
          url:'http://localhost:8080/api/commonProcessor/commonMethodDownload?filePath='+new window.DataCenter(file.response.dataCenter).getParameter("result")
        });
      }
      return file;
    });

    // 3. filter successfully uploaded files according to response from server
    fileList = fileList.filter((file) => {
      if (file.response) {
        return file.response.status === 'success';
      }
      return true;
    });

    this.setState({ fileList });
  }
  render() {
    const props = {
      action: '/api/commonProcessor/commonMethodUpload',
      onChange: this.handleChange,
      multiple: false,
    };
    var dc = new window.DataCenter();
    var ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "demoUploadServiceImpl");
    dc.setParameter('_methodName', 'upload');
    dc.setParameter('_methodParameterTypes', 'String');
    dc.setParameter("_parameters", "id");
    dc.setParameter("id","001");

    const { url, editable } = this.state;

    return <div>
        <Row>
          <Col>
            <Upload {...props} fileList={this.state.fileList} data={{"data":dc.toBase64Json()}}>
              <Button>
                <Icon type="upload" /> 上传
              </Button>
            </Upload>
          </Col>
          <Col>
            {
              editable
                ?
                <Tag><a target='_blank' href={url} >点击下载文件</a></Tag>
                :
                <div></div>
            }
          </Col>
        </Row>
      </div>

  }

}

export  default  uploadViewer;

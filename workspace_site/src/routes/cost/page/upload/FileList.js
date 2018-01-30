import React from 'react'
import {Icon,Upload} from "antd";

const FileList = ({fileList}, {upLoadOpt}) => {
  return (
    <div class="ant-upload-list ant-upload-list-text">
      {fileList.map((file) => {
        return (
          <Upload {...upLoadOpt} >
          <div className="ant-upload-list-item ant-upload-list-item-done">
            <div className="ant-upload-list-item-info">
            <span>
              <Icon type="paper-clip"></Icon>
              <a target="_blank" rel="noopener noreferrer"
                 className="ant-upload-list-item-name" title={file.name}>{file.name}</a>
            </span>
            </div>
          </div>
          </Upload>
        )
      })}
    </div>
  );

}
export default FileList;

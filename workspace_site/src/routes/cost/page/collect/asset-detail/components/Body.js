/**
 * 数据采集 -- 资产折旧明细 -- Body
 * -- 包括：操作按钮、数据展示
 */
import React, { Component } from 'react';
import { Table, Button } from 'antd';
import { ReAssetDetail } from '../model/ReAssetDetail';
import { EditableCellInput, EditableCellSelect } from '../../../dict/component/EditableTable';
import UploadModal from "../../component/UploadModal";

class Body extends Component {
  constructor(props) {
    console.log("资产折旧明细 Body constructor 111111111", props);
    super(props);
    this.state = {
      reAssetData: [],
      selectedRowKeys: []
    }
    this.columns = [
      {title: '年度', dataIndex: 'accYear', key: 'accYear', width: 150
        , render: (text, record, index) => this.renderColumns(record, index, 'accYear', text)
      },
      {title: '月份', dataIndex: 'acctMonth', key: 'acctMonth', width: 150
        , render: (text, record, index) => this.renderColumns(record, index, 'acctMonth', text)
      },
      {title: '资产科室编码', dataIndex: 'deptCode', key: 'deptCode', width: 150
        , render: (text, record, index) => this.renderColumns(record, index, 'deptCode', text)
      },
      {title: '资产科室名称', dataIndex: 'deptName', key: 'deptName', width: 150
        , render: (text, record, index) => this.renderColumns(record, index, 'deptName', text)
      },
      {title: '资产编码', dataIndex: 'assetCode', key: 'assetCode', width: 150
        , render: (text, record, index) => this.renderColumns(record, index, 'assetCode', text)
      },
      {title: '资产名称', dataIndex: 'assetName', key: 'assetName', width: 150
        , render: (text, record, index) => this.renderColumns(record, index, 'assetName', text)
      },
      // {title: '资产类别', dataIndex: 'accYear', key: 'accYear', width: 150
      //   , render: (text, record, index) => this.renderColumns(record, index, 'accYear', text)
      // },
      // {title: '开始使用时间', dataIndex: 'accYear', key: 'accYear', width: 150
      //   , render: (text, record, index) => this.renderColumns(record, index, 'accYear', text)
      // },
      // {title: '使用年限', dataIndex: 'accYear', key: 'accYear', width: 150
      //   , render: (text, record, index) => this.renderColumns(record, index, 'accYear', text)
      // },
      // {title: '资产原值', dataIndex: 'accYear', key: 'accYear', width: 150
      //   , render: (text, record, index) => this.renderColumns(record, index, 'accYear', text)
      // },
      // {title: '本年计提折旧', dataIndex: 'accYear', key: 'accYear', width: 150
      //   , render: (text, record, index) => this.renderColumns(record, index, 'accYear', text)
      // },
      // {title: '资金来源', dataIndex: 'accYear', key: 'accYear', width: 150
      //   , render: (text, record, index) => this.renderColumns(record, index, 'accYear', text)
      // },
      // {title: '自筹比例', dataIndex: 'accYear', key: 'accYear', width: 150
      //   , render: (text, record, index) => this.renderColumns(record, index, 'accYear', text)
      // },
      // {title: '是否床位费', dataIndex: 'accYear', key: 'accYear', width: 150
      //   , render: (text, record, index) => this.renderColumns(record, index, 'accYear', text)
      // },
      // {title: '项目大类', dataIndex: 'accYear', key: 'accYear', width: 150
      //   , render: (text, record, index) => this.renderColumns(record, index, 'accYear', text)
      // },
      // {title: '状态', dataIndex: 'accYear', key: 'accYear', width: 150
      //   , render: (text, record, index) => this.renderColumns(record, index, 'accYear', text)
      // },
      // {title: '创建人', dataIndex: 'accYear', key: 'accYear', width: 150
      //   , render: (text, record, index) => this.renderColumns(record, index, 'accYear', text)
      // },
      // {title: '创建时间', dataIndex: 'accYear', key: 'accYear', width: 150
      //   , render: (text, record, index) => this.renderColumns(record, index, 'accYear', text)
      // },
      // {title: '修改人', dataIndex: 'accYear', key: 'accYear', width: 150
      //   , render: (text, record, index) => this.renderColumns(record, index, 'accYear', text)
      // },
      // {title: '修改时间', dataIndex: 'accYear', key: 'accYear', width: 150
      //   , render: (text, record, index) => this.renderColumns(record, index, 'accYear', text)
      // },
      // {title: '审核人', dataIndex: 'accYear', key: 'accYear', width: 150
      //   , render: (text, record, index) => this.renderColumns(record, index, 'accYear', text)
      // },
      // {title: '审核时间', dataIndex: 'accYear', key: 'accYear', width: 150
      //   , render: (text, record, index) => this.renderColumns(record, index, 'accYear', text)
      // },
      // {title: '审核评语', dataIndex: 'accYear', key: 'accYear', width: 150
      //   , render: (text, record, index) => this.renderColumns(record, index, 'accYear', text)
      // }
    ];
  }

  componentWillReceiveProps(nextProps){
    console.log("资产折旧明细 Body Receive Props 222222222222", nextProps);
    this.setState({ ...nextProps });
  }

  // 导入
  importAsset = (e) => {
    console.log("导入。");
    e.preventDefault();
    this.refs.uploadModal.setState({visible:true});
  }

  // 导入并提取字典
  importAndExtractAsset = (e) => {
    console.log("导入并提取字典。");
    e.preventDefault();
  }

  // 查看审批记录
  viewCheckLog = (e) => {
    console.log("查看审批记录。");
    e.preventDefault();
  }

  // 导出
  exportAsset = (e) => {
    console.log("导出。");
    e.preventDefault();
  }


  // 选择行改变
  rowSelectedChange = (selectedRowKeys) => {
    this.setState({ selectedRowKeys });
  }

  // 渲染单元格（文字模式/编辑模式）
  renderColumns = (record, index, key, text) => {
    let { editable } = record;
    if (!editable) {
      return text;
    }
    return (
      <EditableCellInput
        editable={ editable }
        value={ text }
        onChange={ value => this.handleChange(index, key, value) }
      />
    );
  }

  render () {
    const { reAssetData, selectedRowKeys } = this.state;

    const rowSelectionSet = {
      selectedRowKeys,
      onChange: this.rowSelectedChange
    };

    return(
      <div className="vh-body" ref="vh-body">
        <div className="table-operations">
          <Button onClick={ this.importAsset }>导入</Button>
          <Button onClick={ this.importAndExtractAsset }>导入并提取字典</Button>
          <Button onClick={ this.viewCheckLog }>查看审批记录</Button>
          <Button onClick={ this.exportAsset }>导出</Button>
        </div>

        <Table className="main-section" rowSelection={ rowSelectionSet } dataSource={ reAssetData }
               columns={ this.columns } rowKey={ item => reAssetData.indexOf(item) }
               bordered scroll={{ y: 430 }} pagination={{ pageSize: 10 }}
        />

        <UploadModal
          downloadUrl="/static/fileTemplate/资产折旧明细.xls"
          jsonTemplate="properties/ReAssetsOriginal.json"
          templateName="资产折旧明细"
          action="upload"
          ref="uploadModal"
        />
      </div>
    );
  }
}

export default Body;

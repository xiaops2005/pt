import React from 'react';
import {Table, Button, Modal, Select, message} from 'antd';
import {EditableCellSelect, EditableCellInput} from '../../../dict/component/EditableTable'
import MyUtil from '../../../../../../constants/MyUtil'
import $ from 'jquery'

const Option = Select.Option;

class Body extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      selectedRowKeys: [],
      dataSource: []
    };
    this.options = [{key: 0, codeId: 1, codeName: '是'}, {key: 1, codeId: 0, codeName: '否'}];
    this.columns = [{
      title: '字段名称',
      dataIndex: 'dbColumnName',
      key: 'dbColumnName',
      width: '20%'
    }, {
      title: '字段类型',
      dataIndex: 'type',
      key: 'type',
      width: '15%'
    }, {
      title: '位置',
      dataIndex: 'pos',
      key: 'pos',
      width: '8%',
      render: (value, record) => {
        return (
          <EditableCellInput
            editable={true}
            value={value}
            onChange={val => this.handleChange(val, record.key, "pos")}
          />
        );
      }
    }, {
      title: '模版名称',
      dataIndex: 'templateName',
      key: 'templateName',
      width: '20%',
      render: (value, record) => {
        return (
          <EditableCellInput
            editable={true}
            value={value}
            onChange={val => this.handleChange(val, record.key, "templateName")}
          />
        );
      }
    }, {
      title: ' 是否插入',
      dataIndex: 'isInsert',
      key: 'isInsert',
      width: '8%',
      render: (value, record) => {
        return (
          <EditableCellSelect
            editable={true}
            options={this.options}
            value={value}
            onChange={val => this.handleChange(val, record.key, "isInsert")}
          />
        );
      }
    }, {
      title: ' 是否必填',
      dataIndex: 'required',
      key: 'required',
      width: '8%',
      render: (value, record) => {
        return (
          <EditableCellSelect
            editable={true}
            options={this.options}
            value={value}
            onChange={val => this.handleChange(val, record.key, "required")}
          />
        );
      }
    }, {
      title: ' 最大长度',
      dataIndex: 'maxLength',
      key: 'maxLength',
      width: '8%',
      render: (value, record) => {
        return (
          <EditableCellInput
            editable={true}
            value={value}
            onChange={val => this.handleChange(val, record.key, "maxLength")}
          />
        );
      }
    }, {
      title: ' 最小长度',
      dataIndex: 'minLength',
      key: 'minLength',
      width: '8%',
      render: (value, record) => {
        return (
          <EditableCellInput
            editable={true}
            value={value}
            onChange={val => this.handleChange(val, record.key, "minLength")}
          />
        );
      }
    }];
  }

  componentWillReceiveProps(nextProps) {
    this.setState({...nextProps});
  }

  sortByPos = (dataSource) => {
    let dataSourceAll = JSON.parse(JSON.stringify(dataSource));
    const selectedDataSource = dataSource.filter(item => item.pos).sort((a, b) => a.pos - b.pos);
    dataSource.splice(0);
    selectedDataSource.forEach(item => {
      dataSource.push(item);
    });
    dataSourceAll.forEach(item => {
      if (selectedDataSource.every(selectedItem => selectedItem.key !== item.key)) {
        dataSource.push(item);
      }
    });
    this.setState({dataSource});
  };

  handleChange = (value, key, column) => {
    let {dataSource} = this.state;
    let target = dataSource.filter(item => key === item.key)[0];
    if (target) {
      const prevVal = target[column];
      target[column] = value;
      if (column === "pos" && dataSource.some(item => item.pos > prevVal)) {
        if (!value) {
          this.selectRow(key, false);
          Modal.confirm({
            title: '系统提示',
            content: '位置是否自动重新排序?',
            onOk: () => {
              dataSource.filter(item => item.pos).sort((a, b) => a - b).forEach((item, index) => {
                item.pos = index + 1;
              });
              this.sortByPos(dataSource);
            },
            onCancel: () => {
              this.sortByPos(dataSource);
            }
          })
        } else {
          this.selectRow(key, true);
          this.sortByPos(dataSource);
        }
      }
      this.setState({dataSource});
    }
  };

  selectRow = (key, isSelected) => {
    let {selectedRowKeys} = this.state;
    if (isSelected) {
      selectedRowKeys.push(key);
    } else {
      selectedRowKeys = selectedRowKeys.filter(selectedKey => selectedKey !== key);
    }
    this.setState({selectedRowKeys});
  };

  checkData = (selectedDataSource) => {
    let checkInfo = {result: true, message: []};
    if (MyUtil.isRepeat(selectedDataSource.map(item => item.pos))) {
      checkInfo.result = false;
      checkInfo.message.push("位置不能重复！");
    }
    if (selectedDataSource.some(item => !item.templateName)) {
      checkInfo.result = false;
      checkInfo.message.push("模版类型不能为空！");
    }
    return checkInfo;
  };

  save = () => {
    const {selectedRowKeys} = this.state;
    const {dataSource} = this.state;
    let selectedDataSource = dataSource.filter((item) => selectedRowKeys.some((key) => item.key === key));
    let checkInfo = this.checkData(selectedDataSource);
    if (checkInfo.result) {
      this.props.saveTemplateColumns(this.state.dataSource[0].templateId, selectedDataSource, (data) => {
        message.success("保存成功！");
        this.props.queryDataSource();
      }, (data) => {
        Modal.error({
          title: '系统提示',
          content: `${data.status},保存失败！`
        });
      })
    } else {
      Modal.warn({
        title: '系统提示',
        content: <div> {checkInfo.message.map((message) => <p>{message}</p>)}</div>
      });
    }
  };


  render() {
    const {selectedRowKeys} = this.state;
    const rowSelection = {
      selectedRowKeys,
      onChange: (selectedRowKeys, selectedRows) => {
        console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
        this.setState({selectedRowKeys});
      }, onSelect: (record, selected, selectedRows) => {
        console.log(record, selected, selectedRows);
        if (selected) {
          if (record.isInsert === null) {
            record.isInsert = 1;
          }
          let max = selectedRows.map(item => item.pos).sort((a, b) => b - a)[0];
          this.handleChange(max + 1, record.key, "pos");
        } else {
          this.handleChange("", record.key, "pos");
        }
      }, onSelectAll: (selected, selectedRows, changeRows) => {
        console.log(selected, selectedRows, changeRows);
        if (selected) {
          let max = selectedRows.map(item => item.pos).sort((a, b) => b - a)[0];
          changeRows.forEach((item, index) => {
            if (item.isInsert === null) {
              item.isInsert = 1;
            }
            item.pos = max + index + 1;
          });
        } else {
          changeRows.forEach(item => {
            item.pos = null;
          });
          selectedRows.sort((a, b) => a.pos - b.pos).forEach((item, index) => {
            item.pos = index + 1;
          })
        }
      }
    };
    return (
      <div>
        <div className="class-buttons">
          <Button className="btn-primary" onClick={this.save}>保存</Button>
        </div>
        <Table className="table-columns" bordered columns={this.columns} pagination={false}
               dataSource={this.state.dataSource} size='small' scroll={{y: $(window).height() - 310 + "px"}}
               rowSelection={rowSelection}/>
      </div>
    )
  }
}


export default Body;

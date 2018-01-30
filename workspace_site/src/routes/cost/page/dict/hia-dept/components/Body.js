import React from 'react';
import {Button, Table, Modal, message} from 'antd';
import {EditableCellSelect, EditableCellInput} from '../../component/EditableTable'
import {BdAssets, HiaDept} from "../../models/bdDictModel";
import DateUtil from "../../../../../../constants/DateUtil";

const confirm = Modal.confirm;
const warn = Modal.warn;

class Body extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      isStopList: [],
      mappedIsStop: {},
      selectedRowKeys: [],
      dataSource: []
    };

    this.columns = [
      {
        title: 'HIA科室编码',
        dataIndex: 'hiaCode',
        width: '12%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "hiaCode")}/>
          );
        },
      },
      {
        title: 'HIA科室名称',
        dataIndex: 'hiaName',
        width: '15%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "hiaName")}/>
          );
        }
      },
      {
        title: '备注',
        dataIndex: 'remark',
        width: '10%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "remark")}/>
          );
        }
      },
      {
        title: '是否停用',
        dataIndex: 'isStop',
        width: '10%',
        render: (value, record) => {
          return (
            <EditableCellSelect
              editable={record.$$editable}
              options={this.state.isStopList}
              mappedOptions={this.state.mappedIsStop}
              value={value}
              onChange={val => this.handleChange(val, record.key, "isStop")}/>
          );
        }
      },
      {
        title: '创建人',
        dataIndex: 'creator',
        width: '15%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "creator")}/>
          );
        }
      },
      {
        title: '创建时间',
        dataIndex: 'creationtime',
        width: '15%',
        render: function (value) {
          return DateUtil.formatDate(value);
        },
        sorter: (a, b) => a.creationtime - b.creationtime
      },
      {
        title: '修改人',
        dataIndex: 'modifier',
        width: '15%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "modifier")}/>
          );
        }
      },
      {
        title: '修改时间',
        dataIndex: 'modifiedtime',
        width: '15%',
        render: function (value) {
          return DateUtil.formatDate(value);
        },
        sorter: (a, b) => a.modifiedtime - b.modifiedtime
      }
    ]
  }

  // 接收数据
  componentWillReceiveProps(nextProps) {
    // console.log("<<<<<<3333333333333");
    // console.log(nextProps);
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

  add = () => {
    let {dataSource} = this.state;
    let {selectedRowKeys} = this.state;
    selectedRowKeys = selectedRowKeys.map(key => key + 1);
    selectedRowKeys.unshift(0);
    dataSource.unshift(new HiaDept());
    this.setState({
      dataSource: dataSource.map((item, index) =>
        Object.assign(item, {key: index})
      ),
      selectedRowKeys: selectedRowKeys
    });
  };

  checkData = (selectedDataSource) => {
    let checkInfo = {
      result: true,
      message: []
    };
    selectedDataSource.forEach((item, index) => {
      if (!item.hiaCode) {
        checkInfo.message.push(`第${index + 1}行HIA科室编码不能为空`);
      }
      if (!item.hiaCode) {
        checkInfo.message.push(`第${index + 1}行HIA科室名称不能为空`);
      }
    });
    checkInfo.result = checkInfo.message.length === 0;
    return checkInfo;
  };

  save = () => {
    const {selectedRowKeys} = this.state;
    if (selectedRowKeys.length === 0) {
      message.warn("请选择要保存的行！");
    } else {
      const {dataSource} = this.state;
      let selectedDataSource = dataSource.filter((item) => selectedRowKeys.some((key) => item.key === key));
      let checkInfo = this.checkData(selectedDataSource);
      if (checkInfo.result) {
        let selectedDataList = selectedDataSource.map(item => {
          let hiaDept = new HiaDept();
          delete item.$$editable;
          hiaDept.copyPropValues(item);
          hiaDept.modifier = 'kevin';
          return hiaDept;
        });
        console.log(selectedDataList);
        this.state.saveHiaDept(selectedDataList, (result) => {
          message.success("保存成功！");
          this.state.queryBdDeptHia();
        });
      } else {
        warn({
          title: '系统提示',
          content: <div> {checkInfo.message.map((message) => <p>{message}</p>)}</div>
        });
      }
    }
  };

  edit = () => {
    const {selectedRowKeys} = this.state;
    if (selectedRowKeys.length === 0) {
      message.warn("请选择要编辑的行！");
    } else {
      const {dataSource} = this.state;
      dataSource.filter((item) => selectedRowKeys.some((key) => item.key === key)).map((item) =>
        Object.assign(item, {$$editable: true})
      );
      this.setState({
        dataSource: dataSource
      });
    }
  };

  delete = () => {
    const {selectedRowKeys} = this.state;
    if (selectedRowKeys.length === 0) {
      message.warn("请选择要删除的行！");
    } else {
      confirm({
        title: '系统提示',
        content: '是否要删除所有选择行数据?',
        onOk: () => {
          const {dataSource} = this.state;
          const pkDeptHia = dataSource.filter(item => selectedRowKeys.some(key => item.key === key)).map(item => "'" + item.pkDeptHia + "'").join(",");
          this.state.deleteHiaDept(pkDeptHia, (result) => {
            message.success("删除成功！");
            this.setState({
              dataSource: dataSource.filter((item) => selectedRowKeys.every((key) => item.key !== key)),
              selectedRowKeys: []
            });
            this.state.queryBdDeptHia();
          });
        },
        onCancel: () => {
        }
      });
    }
  };

  setIsStop = (isStop) => {
    const {selectedRowKeys} = this.state;
    console.log(this.state.dataSource);
    if (selectedRowKeys.length === 0) {
      message.warn(`请选择要${isStop === '1' ? '停用' : '启用'}的行！`);
    } else if (this.state.dataSource.some(item => !item.pkDeptHia)) {
      message.warn(`请选择先保存新增行！`);
    } else {
      confirm({
        title: '系统提示',
        content: `是否要${isStop === '1' ? '停用' : '启用'}所有选择行数据?`,
        onOk: () => {
          const {dataSource} = this.state;
          const selectedDataList = dataSource.filter(item => selectedRowKeys.some(key => item.key === key)).map(item => {
            let hiaDept = new HiaDept();
            hiaDept.copyPropValues(item);
            hiaDept.modifier = 'sw';
            hiaDept.isStop = isStop;
            return hiaDept;
          });
          console.log(selectedDataList);
          this.state.saveHiaDept(selectedDataList, (result) => {
            message.success(`${isStop === '1' ? '停用' : '启用'}成功！`);
            this.state.queryBdDeptHia();
          });
        },
        onCancel: () => {
        }
      });
    }
  };

  render() {
    const {dataSource, selectedRowKeys} = this.state;
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
          <Button className="btn-primary" onClick={this.add}>新增</Button>
          <Button className="btn-primary" onClick={this.save}>保存</Button>
          <Button className="btn-primary" onClick={this.edit}>修改</Button>
          <Button className="btn-primary" onClick={this.delete}>删除</Button>
          <Button className="btn-primary" onClick={this.setIsStop.bind(this, '0')}>启用</Button>
          <Button className="btn-primary" onClick={this.setIsStop.bind(this, '1')}>停用</Button>
        </div>
        <Table bordered pagination={{pageSize: 10}} rowSelection={rowSelection} size='small'
               dataSource={this.state.dataSource}
               columns={this.columns}/>
      </div>
    )
  }
}

export default Body;

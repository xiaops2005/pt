import React from 'react';
import {Button, Table, Modal, message} from 'antd';
import {EditableCellSelect, EditableCellInput} from '../../component/EditableTable'
import {BdHospital} from '../../models/bdDictModel'
import DateUtil from "../../../../../../constants/DateUtil";

const confirm = Modal.confirm;
const warn = Modal.warn;

class Body extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      hospitalClassList: [],
      mappedHospitalClass: {},
      hospitalTypeList: [],
      mappedHospitalType: {},
      selectedRowKeys: [],
      dataSource: []
    };
    this.columns = [
      {
        title: '行号',
        dataIndex: 'key',
        width: '6%',
        render: (value) => {
          return (
            <div>{value + 1}</div>
          );
        }
      },
      {
        title: '医院编码',
        dataIndex: 'hospitalCode',
        width: '13%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "hospitalCode")}
            />
          );
        },
        sorter: (a, b) => a.hospitalCode - b.hospitalCode
      }, {
        title: '医院名称',
        dataIndex: 'hospitalName',
        width: '13%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "hospitalName")}
            />
          );
        }

      }, {
        title: '医院类型',
        dataIndex: 'hospitalTypeId',
        width: '13%',
        render: (value, record) => {
          return (
            <EditableCellSelect
              editable={record.$$editable}
              options={this.state.hospitalTypeList}
              mappedOptions={this.state.mappedHospitalType}
              value={value}
              onChange={val => this.handleChange(val, record.key, "hospitalTypeId")}
            />
          );
        }
      }
      , {
        title: '医院等级',
        dataIndex: 'hospitalClassId',
        width: '13%',
        render: (value, record) => {
          return (
            <EditableCellSelect
              editable={record.$$editable}
              options={this.state.hospitalClassList}
              mappedOptions={this.state.mappedHospitalClass}
              value={value}
              onChange={val => this.handleChange(val, record.key, "hospitalClassId")}
            />
          );
        }
      }, {
        title: '备注',
        dataIndex: 'remark',
        width: '13%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "remark")}
            />
          );
        }
      }, {
        title: '创建人',
        dataIndex: 'creator',
        width: '13%'
      }, {
        title: '创建时间',
        dataIndex: 'creationtime',
        width: '13%',
        render: function (value) {
          return DateUtil.formatDate(value);
        },
        sorter: (a, b) => a.creationtime - b.creationtime
      }];

  }

  componentWillReceiveProps(nextProps) {
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
    dataSource.unshift(new BdHospital());
    this.setState({
      dataSource: dataSource.map((item, index) =>
        Object.assign(item, {key: index})
      ),
      selectedRowKeys: selectedRowKeys
    });
  };

  edit = () => {
    const {selectedRowKeys} = this.state;
    if (selectedRowKeys.length === 0) {
      message.warn("请选择要编辑的行！");
    } else {
      const {dataSource} = this.state;
      dataSource.filter((item) => selectedRowKeys.some((key) => item.key === key)).map((item) =>
        Object.assign(item, {$$editable: !item.$$editable})
      );
      this.setState({
        dataSource: dataSource
      });
    }
  };

  checkData = (selectedDataSource) => {
    let checkInfo = {
      result: true,
      message: []
    };
    selectedDataSource.forEach((item) => {
      if (!item.hospitalName) {
        checkInfo.message.push(`第${item.key + 1}行医院名称不能为空!`);
      }
      if (!item.hospitalCode) {
        checkInfo.message.push(`第${item.key + 1}行医院编码不能为空!`);
      }
      if (!item.hospitalTypeId) {
        checkInfo.message.push(`第${item.key + 1}行医院类型不能为空!`);
      }
      if (!item.hospitalClassId) {
        checkInfo.message.push(`第${item.key + 1}行医院等级不能为空!`);
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
        selectedDataSource.forEach(item => {
          item.modifier = 'sw';
          delete item.$$editable;
        });
        console.log(selectedDataSource);
        this.state.saveBdHospital(selectedDataSource, (result) => {
          // this.setState({
          //   dataSource: dataSource,
          //   selectedRowKeys: []
          // });
          message.success("保存成功！");
          this.state.queryBdHospital();
        });
      } else {
        warn({
          title: '系统提示',
          content: <div> {checkInfo.message.map((message) => <p>{message}</p>)}</div>
        });
      }
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
          const pkHospitals = dataSource.filter(item => selectedRowKeys.some(key => item.key === key)).map(item => "'" + item.pkHospital + "'").join(",");
          this.state.deleteBdHospital(pkHospitals, (result) => {
            message.success("删除成功！");
            // this.setState({
            //   dataSource: dataSource.filter((item) => selectedRowKeys.every((key) => item.key !== key)),
            //   selectedRowKeys: []
            // });
            this.state.queryBdHospital();
          });
        },
        onCancel: () => {
        }
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
      }
    };
    return (
      <div>
        <div className="class-buttons">
          <Button className="btn-primary" onClick={this.add}>增加</Button>
          <Button className="btn-primary" onClick={this.edit}>修改</Button>
          <Button className="btn-primary" onClick={this.save}>保存</Button>
          <Button className="btn-primary" onClick={this.delete}>删除</Button>
        </div>
        <Table bordered pagination={{pageSize: 10}} size='small' rowSelection={rowSelection}
               dataSource={this.state.dataSource}
               columns={this.columns}/>

      </div>
    )
  }
}


export default Body



import React from 'react';
import {Button, Table, Modal, message} from 'antd';
import {EditableCellSelect, EditableCellInput} from '../../component/EditableTable'
import {BdDept} from '../../models/bdDictModel'
import DateUtil from "../../../../../../constants/DateUtil";

const confirm = Modal.confirm;
const warn = Modal.warn;

class Body extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      yearList: [],
      mappedYear: {},
      isStopList: [],
      mappedIsStop: {},
      deptTypeList: [],
      mappedDeptType: {},
      belongSystemList: [],
      mappedBelongSystem: {},
      hospitalList: [],
      mappedHospital: {},
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
        title: '科室编码',
        dataIndex: 'deptCode',
        width: '9%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "deptCode")}
            />
          );
        },
        sorter: (a, b) => a.deptCode - b.deptCode
      }, {
        title: '科室名称',
        dataIndex: 'deptName',
        width: '9%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "deptName")}
            />
          );
        }

      }, {
        title: '所属系统',
        dataIndex: 'belongSystem',
        width: '9%',
        render: (value, record) => {
          return (
            <EditableCellSelect
              editable={record.$$editable}
              options={this.state.belongSystemList}
              mappedOptions={this.state.mappedBelongSystem}
              value={value}
              onChange={val => this.handleChange(val, record.key, "belongSystem")}
            />
          );
        }
      }
      , {
        title: '科室类型',
        dataIndex: 'deptType',
        width: '9%',
        render: (value, record) => {
          return (
            <EditableCellSelect
              editable={record.$$editable}
              options={this.state.deptTypeList}
              mappedOptions={this.state.mappedDeptType}
              value={value}
              onChange={val => this.handleChange(val, record.key, "deptType")}
            />
          );
        }
      }, {
        title: '所属医院',
        dataIndex: 'pkHospital',
        width: '9%',
        render: (value, record) => {
          return (
            <EditableCellSelect
              editable={record.$$editable}
              decodeVal="pkHospital"
              decodeDisplay="hospitalName"
              options={this.state.hospitalList}
              mappedOptions={this.state.mappedHospital}
              value={value}
              onChange={val => this.handleChange(val, record.key, "pkHospital")}
            />
          );
        }
      }, {
        title: '年度',
        dataIndex: 'year',
        width: '9%',
        render: (value, record) => {
          return (
            <EditableCellSelect
              editable={record.$$editable}
              options={this.state.yearList}
              mappedOptions={this.state.mappedYear}
              value={value}
              onChange={val => this.handleChange(val, record.key, "year")}
            />
          );
        },
        sorter: (a, b) => a.year - b.year
      }, {
        title: '备注',
        dataIndex: 'remark',
        width: '9%',
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
        title: '是否停用',
        dataIndex: 'isStop',
        width: '9%',
        render: (value, record) => {
          return (
            <EditableCellSelect
              editable={record.$$editable}
              options={this.state.isStopList}
              mappedOptions={this.state.mappedIsStop}
              value={value}
              onChange={val => this.handleChange(val, record.key, "isStop")}
            />
          );
        }
      },
      {
        title: '创建人',
        dataIndex: 'creator',
        width: '9%'
      }, {
        title: '创建时间',
        dataIndex: 'creationtime',
        width: '9%',
        render: function (value) {
          return DateUtil.formatDate(value);
        },
        sorter: (a, b) => a.creationtime - b.creationtime
      }

    ];

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
    dataSource.unshift(new BdDept());
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
    selectedDataSource.forEach(item => {
      if (!item.deptCode) {
        checkInfo.message.push(`第${item.key + 1}行科室编码不能为空!`);
      }
      if (!item.deptName) {
        checkInfo.message.push(`第${item.key + 1}行科室名称不能为空!`);
      }
      if (!item.belongSystem) {
        checkInfo.message.push(`第${item.key + 1}行所属系统不能为空!`);
      }
      if (!item.deptType) {
        checkInfo.message.push(`第${item.key + 1}行科室类型不能为空!`);
      }
      if (!item.pkHospital) {
        checkInfo.message.push(`第${item.key + 1}行所属医院不能为空!`);
      }
      if (!item.year) {
        checkInfo.message.push(`第${item.key + 1}行年度不能为空!`);
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
          //Tip BdDept 继承了Base带入了多余的父类属性，无法直接保存
          let bdDept = new BdDept();
          //让dataSource选中行置为不可编辑状态
          delete item.$$editable;
          bdDept.copyPropValues(item);
          bdDept.modifier = 'sw';
          return bdDept;
        });
        console.log(selectedDataList);
        this.state.saveBdDept(selectedDataList, (result) => {
          message.success("保存成功！");
          // this.setState({
          //   dataSource: dataSource,
          //   selectedRowKeys: []
          // });
          this.state.queryBdDept();
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
          const pkDepts = dataSource.filter(item => selectedRowKeys.some(key => item.key === key)).map(item => "'" + item.pkDept + "'").join(",");
          this.state.deleteBdDept(pkDepts, (result) => {
            message.success("删除成功！");
            // this.setState({
            //   dataSource: dataSource.filter((item) => selectedRowKeys.every((key) => item.key !== key)),
            //   selectedRowKeys: []
            // });
            this.state.queryBdDept();
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
    } else if (this.state.dataSource.some(item => !item.pkDept)) {
      message.warn(`请选择先保存新增行！`);
    } else {
      confirm({
        title: '系统提示',
        content: `是否要${isStop === '1' ? '停用' : '启用'}所有选择行数据?`,
        onOk: () => {
          const {dataSource} = this.state;
          const selectedDataList = dataSource.filter(item => selectedRowKeys.some(key => item.key === key)).map(item => {
            let bdDept = new BdDept();
            bdDept.copyPropValues(item);
            bdDept.modifier = 'sw';
            bdDept.isStop = isStop;
            return bdDept;
          });
          console.log(selectedDataList);
          this.state.saveBdDept(selectedDataList, (result) => {
            message.success(`${isStop === '1' ? '停用' : '启用'}成功！`);
            this.state.queryBdDept();
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
          <Button className="btn-primary" onClick={this.setIsStop.bind(this, '0')}>启用</Button>
          <Button className="btn-primary" onClick={this.setIsStop.bind(this, '1')}>停用</Button>
        </div>
        <Table bordered pagination={{pageSize: 10}} size='small' rowSelection={rowSelection}
               dataSource={this.state.dataSource}
               columns={this.columns}/>

      </div>
    )
  }
}


export default Body



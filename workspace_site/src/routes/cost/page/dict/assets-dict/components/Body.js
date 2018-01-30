import React from 'react';
import {Button, Table, Modal, message} from 'antd';
import {EditableCellSelect, EditableCellInput} from '../../component/EditableTable'
import {BdAssets, BdDept} from '../../models/bdDictModel'
import DateUtil from "../../../../../../constants/DateUtil";
import { VHDomUtil } from '../../../../../../constants/utils';

const confirm = Modal.confirm;
const warn = Modal.warn;

class Body extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      hospitalClassList: [],
      hospitalTypeList: [],
      selectedRowKeys: [],
      dataSource: []
    };
    this.standardFlag = 0;
    this.columns = [
      {
        title: '行号',
        dataIndex: 'key',
        width: 50,
        render: (value) => {
          return (
            <div>{value + 1}</div>
          );
        }
      },
      {
        title: '资产编码',
        dataIndex: 'assetsCode',
        width: 100,
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "assetsCode")}
            />
          );
        }
      }, {
        title: '资产名称',
        dataIndex: 'assetsName',
        width: 100,
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "assetsName")}
            />
          );
        }

      }, {
        title: '资产描述',
        dataIndex: 'assetsDesc',
        width: 100,
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "assetsDesc")}
            />
          );
        }
      }
      , {
        title: '计量单位',
        dataIndex: 'unitName',
        width: 100,
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "unitName")}
            />
          );
        }
      }, {
        title: '规格型号',
        dataIndex: 'model',
        width: 100,
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "model")}
            />
          );
        }
      }, {
        title: '资产原值',
        dataIndex: 'originalValue',
        width: 100,
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "originalValue")}
            />
          );
        }
      }, {
        title: '开始使用时间',
        dataIndex: 'startDate',
        width: 150,
        function (value) {
          return DateUtil.formatDate(value);
        }
      }, {
        title: '使用年限',
        dataIndex: 'durableYears',
        width: 100,
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "durableYears")}
            />
          );
        }
      }, {
        title: '资金来源',
        dataIndex: 'capitalSourceId',
        width: 100,
        render: (value, record) => {
          return (
            <EditableCellSelect
              editable={record.$$editable}
              options={this.state.capitalSourceList}
              mappedOptions={this.state.mappedCapitalSource}
              value={value}
              onChange={val => this.handleChange(val, record.key, "capitalSourceId")}
            />
          );
        }
      }, {
        title: '是否床位费',
        dataIndex: 'isBunkfeeId',
        width: 100,
        render: (value, record) => {
          return (
            <EditableCellSelect
              editable={record.$$editable}
              options={this.state.isStopList}
              mappedOptions={this.state.mappedIsStop}
              value={value}
              onChange={val => this.handleChange(val, record.key, "isBunkfeeId")}
            />
          );
        }
      }, {
        title: '项目大类',
        dataIndex: 'pkChargeClass',
        width: 100,
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "pkChargeClass")}
            />
          );
        }
      }, {
        title: '所属医院',
        dataIndex: 'pkHospital',
        width: 150,
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "pkHospital")}
            />
          );
        }
      }, {
        title: '年度',
        dataIndex: 'accYear',
        width: 100,
        render: (value, record) => {
          return (
            <EditableCellSelect
              editable={record.$$editable}
              options={this.state.yearList}
              mappedOptions={this.state.mappedYear}
              value={value}
              onChange={val => this.handleChange(val, record.key, "accYear")}
            />
          );
        }
      }, {
        title: '备注',
        dataIndex: 'remark',
        width: 200,
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
        width: 100,
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
      }, {
        title: '创建人',
        dataIndex: 'creator',
        width: 100
      }, {
        title: '创建时间',
        dataIndex: 'creationtime',
        width: 100,
        render: function (value) {
          return DateUtil.formatDate(value);
        }
      }];

  }

  componentWillReceiveProps(nextProps) {
    let { match } = nextProps;
    console.log("Body Receive Props");
    console.log(nextProps);
    this.standardFlag = match.params.standardFlag === '1' ? 1 : 0;
    this.setState({...nextProps});
  }

  // 组件挂载后触发函数
  componentDidMount() {
    window.addEventListener('resize', this.resetTableBodyHeight);
    this.resetTableBodyHeight();
  }

  // 修改表格高度
  resetTableBodyHeight = () => {
    let headerHeight = VHDomUtil.getElementHeight('assets-dict-header'),
      windowHeight = window.innerHeight;
    console.log(headerHeight + '----' + windowHeight);
      // 设置 table Body 的高度
      VHDomUtil.setTableBodyHeight(windowHeight - headerHeight - 270);
  };


  handleChange = (value, key, column) => {
    const {dataSource} = this.state;
    const target = dataSource.filter(item => key === item.key)[0];
    if (target) {
      target[column] = value;
      this.setState({dataSource: dataSource});
    }
  };

  add = () => {
    let {dataSource, standardFlag} = this.state;
    let {selectedRowKeys} = this.state;
    selectedRowKeys = selectedRowKeys.map(key => key + 1);
    selectedRowKeys.unshift(0);
    dataSource.unshift(new BdAssets(standardFlag));
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
        Object.assign(item, {$$editable: true})
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
    selectedDataSource.forEach((item, index) => {
      if (!item.assetsCode) {
        checkInfo.message.push(`第${index + 1}行资产编码不能为空`);
      }
      if (!item.assetsName) {
        checkInfo.message.push(`第${index + 1}行资产名称不能为空`);
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
      const {dataSource, standardFlag} = this.state;
      let selectedDataSource = dataSource.filter((item) => selectedRowKeys.some((key) => item.key === key));
      let checkInfo = this.checkData(selectedDataSource);
      if (checkInfo.result) {
        let selectedDataList = selectedDataSource.map(item => {
          let bdAssets = new BdAssets(standardFlag);
          delete item.$$editable;
          bdAssets.copyPropValues(item);
          bdAssets.modifier = 'sw';
          return bdAssets;
        });
        console.log(selectedDataList);
        this.state.saveBdAssets(selectedDataList, (result) => {
          message.success("保存成功！");
          this.state.queryBdAssets();
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
          const pkAssets = dataSource.filter(item => selectedRowKeys.some(key => item.key === key)).map(item => "'" + item.pkAssets + "'").join(",");
          this.state.deleteBdAssets(pkAssets, (result) => {
            message.success("删除成功！");
            this.setState({
              dataSource: dataSource.filter((item) => selectedRowKeys.every((key) => item.key !== key)),
              selectedRowKeys: []
            });
            this.state.queryBdAssets();
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
          const {dataSource, standardFlag} = this.state;
          const selectedDataList = dataSource.filter(item => selectedRowKeys.some(key => item.key === key)).map(item => {
            let bdAssets = new BdAssets(standardFlag);
            bdAssets.copyPropValues(item);
            bdAssets.modifier = 'sw';
            bdAssets.isStop = isStop;
            return bdAssets;
          });
          console.log(selectedDataList);
          this.state.saveBdDept(selectedDataList, (result) => {
            message.success(`${isStop === '1' ? '停用' : '启用'}成功！`);
            this.state.queryBdAssets();
          });
        },
        onCancel: () => {
        }
      });
    }
  };

  importExcel =()=>{

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

    // 根据 standardFlag 判断是否展示 '年度'
    let showColumns = [];
    if (this.standardFlag === 1) {
      showColumns = this.columns.filter( column => {
        return  column.dataIndex !== 'accYear';
      });
    } else {
      showColumns = this.columns;
    }

    return (
      <div className="vh-body">
        <div className="table-operations">
          <Button className="btn-primary" onClick={this.add}>增加</Button>
          <Button className="btn-primary" onClick={this.edit}>修改</Button>
          <Button className="btn-primary" onClick={this.save}>保存</Button>
          <Button className="btn-primary" onClick={this.delete}>删除</Button>
          <Button className="btn-primary" onClick={this.setIsStop.bind(this, '0')}>启用</Button>
          <Button className="btn-primary" onClick={this.setIsStop.bind(this, '1')}>停用</Button>
          <Button className="btn-primary" onClick={this.importExcel}>导入</Button>
        </div>
        <Table bordered className="main-section" pagination={{pageSize: 10}} rowSelection={rowSelection}
               dataSource={this.state.dataSource}
               columns={showColumns} scroll={{ x: 2000, y: true }}/>

      </div>
    )
  }
}


export default Body



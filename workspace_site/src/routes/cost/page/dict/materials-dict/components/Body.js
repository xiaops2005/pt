import React from 'react';
import {Button, Table, Modal, message} from 'antd';
import {EditableCellSelect, EditableCellInput} from '../../component/EditableTable'
import {BdMaterials} from '../../models/bdDictModel'
import DateUtil from "../../../../../../constants/DateUtil";
const dateFormat = 'YYYY/MM/DD';
const dataSource = [];

const confirm = Modal.confirm;
const warn = Modal.warn;

class Body extends React.Component {
  constructor(props) {
    super(props);
    console.log("constructor")
    this.state = {
      yearList: [],
      mappedYear: {},
      isStopList: [{
        codeId:'1',codeName:'是'
      },{
        codeId:'0',codeName:'否'
      }],
      mappedIsStop: {},
      isSingleList: [{
        codeId:'1',codeName:'是'
      },{
        codeId:'0',codeName:'否'
      }],
      mappedIsSingle: {},
      hospitalList: [],
      mappedHospital: {},
      selectedRowKeys: [],
      dataSource: []
    };
    this.columns = [
      {
        title: '行号',
        dataIndex: 'key',
        width: '5%',
        render: (value) => {
          return (
            <div>{value + 1}</div>
          );
        }
      }, {
        title: '材料编码',
        dataIndex: 'materialsCode',
        width: '10%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "materialsCode")}
            />
          )}
      }, {
        title: '材料名称',
        dataIndex: 'materialsName',
        width: '20%',
        render: (value, record) => {
          return (
            <EditableCellInput
              editable={record.$$editable}
              value={value}
              onChange={val => this.handleChange(val, record.key, "materialsName")}
            />
          )}
      // },{
      //   title: '材料描述',
      //   dataIndex: 'materialsDesc',
      //   width: '10%',
      //   render: (value, record) => {
      //     return (
      //       <EditableCellInput
      //         editable={record.$$editable}
      //         value={value}
      //         onChange={val => this.handleChange(val, record.key, "materialsDesc")}
      //       />
      //     )}
      // },{
      //   title: '计量单位',
      //   dataIndex: 'unitName',
      //   width: '6%',
      //   render: (value, record) => {
      //     return (
      //       <EditableCellInput
      //         editable={record.$$editable}
      //         value={value}
      //         onChange={val => this.handleChange(val, record.key, "unitName")}
      //       />
      //     )}
      // },{
      //   title: '规格型号',
      //   dataIndex: 'model',
      //   width: '10%',
      //   render: (value, record) => {
      //     return (
      //       <EditableCellInput
      //         editable={record.$$editable}
      //         value={value}
      //         onChange={val => this.handleChange(val, record.key, "model")}
      //       />
      //     )}
      // },{
      //   title: '单价',
      //   dataIndex: 'unitPrice',
      //   width: '10%',
      //   render: (value, record) => {
      //     return (
      //       <EditableCellInput
      //         editable={record.$$editable}
      //         value={value}
      //         onChange={val => this.handleChange(val, record.key, "unitPrice")}
      //       />
      //     )}
      },{
        title: '是否单收费',
        dataIndex: 'isSingle',
        width: '10%',
        render: (value, record) => {
          return (
            <EditableCellSelect
              editable={record.$$editable}
              options={this.state.isSingleList}
              mappedOptions={this.state.mappedIsSingle}
              value={value}
              onChange={val => this.handleChange(val, record.key, "isSingle")}
            />
          )}
      // },{
      //   title: '项目大类',
      //   dataIndex: 'pkChargeClass',
      //   width: '10%',
      //   render: (value, record) => {
      //     return (
      //       <EditableCellInput
      //         editable={record.$$editable}
      //         value={value}
      //         onChange={val => this.handleChange(val, record.key, "pkChargeClass")}
      //       />
      //     )}
      },{
        title: '所属医院',
        dataIndex: 'pkHospital',
        width: '15%',
        render: (value, record) => {
          return (
            <EditableCellSelect
              editable={record.$$editable}
              options={this.state.hospitalList}
              mappedOptions={this.state.mappedHospital}
              value={value}
              onChange={val => this.handleChange(val, record.key, "pkHospital")}
              decodeVal='pkHospital'
              decodeDisplay='hospitalName'
            />
          )}
      },{
        title: '年度',
        dataIndex: 'accYear',
        width: '10%',
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
      // },{
      //   title: '备注',
      //   dataIndex: 'remark',
      //   width: '10%',
      //   render: (value, record) => {
      //     return (
      //       <EditableCellInput
      //         editable={record.$$editable}
      //         value={value}
      //         onChange={val => this.handleChange(val, record.key, "remark")}
      //       />
      //     )}
      },{
        title: '是否停用',
        dataIndex: 'isStop',
        width: '10%',
        render: (value, record) => {
          return (
            <EditableCellSelect
              editable={record.$$editable}
              options={this.state.isSingleList}
              mappedOptions={this.state.mappedIsStop}
              value={value}
              onChange={val => this.handleChange(val, record.key, "isStop")}
            />
          )}
      },{
        title: '创建人',
        dataIndex: 'creator',
        width: '10%'
      }, {
        title: '创建时间',
        dataIndex: 'creationtime',
        width: '10%',
        render: function (value) {
          return DateUtil.formatDate(value);
        }
      }];
  }

  componentWillReceiveProps(nextProps) {
    console.info('componentWillReceiveProps',nextProps);
    // this.setState({dataSource: [...nextProps.dataSource]});
    this.setState({...nextProps});
  }

  add = () => {
    let {dataSource} = this.state;
    let {selectedRowKeys} = this.state;
    selectedRowKeys = selectedRowKeys.map(key => key + 1);
    selectedRowKeys.unshift(0);
    dataSource.unshift(new BdMaterials());
    this.setState({
      dataSource: dataSource.map((item, index) =>
        Object.assign(item, {key: index})
    ),
      selectedRowKeys: selectedRowKeys
    });
  };


  handleChange = (value, key, column) => {
    const {dataSource} = this.state;
    const target = dataSource.filter(item => key === item.key)[0];
    if (target) {
      target[column] = value;
      this.setState({dataSource: dataSource});
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

  checkData = (selectedDataSource) => {
    let checkInfo = {
      result: true,
      message: []
    };
    selectedDataSource.forEach((item, index) => {
      if (!item.pkHospital) {
        checkInfo.message.push(`第${index + 1}行所属医院不能为空`);
      }
      if (!item.materialsCode) {
        checkInfo.message.push(`第${index + 1}行材料编码不能为空`);
      }
      if (!item.materialsName) {
        checkInfo.message.push(`第${index + 1}行材料名称不能为空`);
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
          //Tip BdMaterials 继承了Base带入了多余的父类属性，无法直接保存
          let bdMaterials = new BdMaterials();
          //让dataSource选中行置为不可编辑状态
          delete item.$$editable;
          console.log(item)
          bdMaterials.copyPropValues(item);
          console.log(bdMaterials)
          bdMaterials.modifier = 'xps';
          return bdMaterials;
        });
        console.log(selectedDataList);
        this.state.saveBdMaterials(selectedDataList, (result) => {
          message.success("保存成功！");
          this.state.queryBdMaterials();
        });
      } else {
        warn({
          title: '系统提示',
          content: checkInfo.message.join(',')
        });
      }
    }
  };

  cancel(key) {
    const newData = [...this.state.dataSource];
    const target = newData.filter(item => key === item.key)[0]
    if (target) {
      Object.assign(target, this.cacheData.filter(item => key === item.key)[0]);
      delete target.editable;
      this.setState({dataSource: newData});
    }
  }

  onSelectChange = (selectedRowKeys) => {
    console.log('selectedRowKeys changed: ', selectedRowKeys);
    this.setState({ selectedRowKeys });
  }

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
          const pkMaterials = dataSource.filter(item => selectedRowKeys.some(key => item.key === key)).map(item => "'" + item.pkMaterials + "'").join(",");
          this.state.deleteBdMaterials(pkMaterials, (result) => {
            message.success("删除成功！");
            this.setState({
              dataSource: dataSource.filter((item) => selectedRowKeys.every((key) => item.key !== key)),
              selectedRowKeys: []
            });
            this.state.queryBdMaterials(null, "", "", "");
          });
        },
        onCancel: () => {
        }
      });
    }
  };

  setIsStop = (isStop) => {
    const {selectedRowKeys} = this.state;
    if (selectedRowKeys.length === 0) {
      message.warn(`请选择要${isStop === '1' ? '停用' : '启用'}的行！`);
    } else {
      confirm({
        title: '系统提示',
        content: `是否要${isStop === '1' ? '停用' : '启用'}所有选择行数据?`,
        onOk: () => {
          const {dataSource} = this.state;
          const selectedDataList = dataSource.filter(item => selectedRowKeys.some(key => item.key === key)).map(item => {
            let bdMaterials = new BdMaterials();
            bdMaterials.copyPropValues(item);
            bdMaterials.modifier = 'xps';
            bdMaterials.isStop = isStop;
            return bdMaterials;
          });
          console.log(selectedDataList);
          this.state.saveBdMaterials(selectedDataList, (result) => {
            message.success(`${isStop === '1' ? '停用' : '启用'}成功！`);
            this.state.queryBdMaterials();
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
    return (
      <div>
        <div className="operation-area">
          <Button className="btn-primary" onClick={this.add}>增加</Button>
          <Button className="btn-primary" onClick={this.edit}>修改</Button>
          <Button className="btn-primary" onClick={this.save}>保存</Button>
          <Button className="btn-primary" onClick={this.delete}>删除</Button>
          <Button className="btn-primary" onClick={this.setIsStop.bind(this, '0')}>启用</Button>
          <Button className="btn-primary" onClick={this.setIsStop.bind(this, '1')}>停用</Button>
          <Button className="btn-primary" onClick={this.importExcel}>导入</Button>
        </div>
        <Table  bordered pagination={{pageSize: 10}} size='small' rowSelection={rowSelection} dataSource={this.state.dataSource} columns={this.columns}/>

      </div>
    )
  }
}

export default Body



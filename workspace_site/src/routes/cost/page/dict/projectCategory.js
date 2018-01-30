import React from 'react';

import {Form, Icon, Input, Button, Row, Col, DatePicker, Popconfirm, Table, Select } from 'antd';
import moment from 'moment';

import {ProjectCategoryService} from '../../process/LoadService';

const processor = new ProjectCategoryService();

const FormItem = Form.Item;

const dateFormat = 'YYYY/MM/DD';
const dataSource = [];

const Option = Select.Option;

class FormSearch extends React.Component {

  handleSearch = (e) => {
    e.preventDefault();
    console.info("提交");
    this.props.form.validateFields((err, values) => {
      if (!err) {
        console.info('values', values);
        console.info(`this.props.form.getFieldValue('dictName'): ${this.props.form.getFieldValue('dictName')}`);
      }
    });

    let affiliatedHospital = this.props.form.getFieldValue('affiliatedHospital');
    let projectCategoryCode = this.props.form.getFieldValue('projectCategoryCode')
    let projectCategoryName = this.props.form.getFieldValue('projectCategoryName')
    let isStop = this.props.form.getFieldValue('isStop')
    this.props.onQueryChange(affiliatedHospital,projectCategoryCode,projectCategoryName,isStop);
  }

  handleChange=(value)=> {
    console.log(`selected ${value}`);
  }
  render() {
    const {getFieldDecorator} = this.props.form;
    return (

      <div className="vh">
        <Form onSubmit={this.handleSearch} className="ant-advanced-search-form">
          <Row gutter={24}>
            <Col span={5} >
              <FormItem label="所属医院">
                {
                  getFieldDecorator('affiliatedHospital', {
                    rules: [
                      { required: true, message: '请选择所属医院!' },
                    ],
                  })
                  (<Select defaultValue="301医院" style={{ width: 110 }} onChange={this.handleChange}>
                    <Option value="01">北大医院</Option>
                    <Option value="02">北京第二医院</Option>
                    <Option value="03">西直门医院</Option>
                    <Option value="04">解放军第一人民医院</Option>
                  </Select>)
                }
              </FormItem>

            </Col>
            <Col span={5}>
              <FormItem label="项目大类编码:">
                {
                  getFieldDecorator('projectCategoryCode', {
                    rules: [
                      // {required: true, message: '字典名称 is required.'}
                    ]
                  })(
                    <Input/>
                  )
                }
              </FormItem>
            </Col>
            <Col span={5}>
              <FormItem label="项目大类名称：">
                {
                  getFieldDecorator('projectCategoryName', {
                    rules: [
                      // {required: true, message: '字典名称 is required.'}
                    ]
                  })(
                    <Input/>
                  )
                }
              </FormItem>
            </Col>
            <Col span={5}>
              <FormItem label="是否停用">
                {
                  getFieldDecorator('isStop', {
                    rules: [
                      // { required: true, message: '' },
                    ],
                  })
                  (<Select defaultValue="否" style={{ width: 120 }} onChange={this.handleChange}>
                    <Option value="01">是</Option>
                    <Option value="02">否</Option>

                  </Select>)
                }
              </FormItem>
            </Col>


            <Col span={4}>
              <Button type="primary" onClick={this.handleSearch}>查询</Button>
            </Col>
          </Row>
        </Form>
      </div>
    )
  }
}

const WrappedFormSearch = Form.create()(FormSearch);

const EditableCell = ({editable, value, onChange}) => (
  <div>
    {editable ? <Input style={{margin: '-5px,0'}} value={value} onChange={e => onChange(e.target.value)}/> : value}
  </div>
)


class EditableTable extends React.Component {
  constructor(props) {
    super(props);
    this.columns = [{
      title: '项目大类编码',
      dataIndex: 'projectCategoryCode',
      width: '12%',
      render: (text, record) => this.renderColumns(text, record, 'projectCategoryCode'),
    }, {
      title: '项目大类名称',
      dataIndex: 'projectCategoryName',
      width: '20%',
      render: (text, record) => this.renderColumns(text, record, 'projectCategoryName'),
    }, {
      title: '所属医院',
      dataIndex: 'affiliatedHospital',
      width: '12%',
      render: (text, record) => this.renderColumns(text, record, 'affiliatedHospital'),
    }
      , {
        title: '备注',
        dataIndex: 'remark',
        width: '10%',
        render: (text, record) => this.renderColumns(text, record, 'remark'),
      }
      , {
        title: '是否停用',
        dataIndex: 'isStop',
        width: '10%',
        render: (text, record) => <Select defaultValue={text} style={{width:"100px"}}>
          <Option value="1">是</Option>
          <Option value="0">否</Option>
        </Select>,
      },
      {
        title: '创建人',
        dataIndex: 'createPerson',
        width: '10%',
        render: (text, record) => this.renderColumns(text, record, 'createPerson'),
      },
      {
        title: '创建时间',
        dataIndex: 'createTime',
        width: '10%',
        render: (text, record) => this.renderColumns(text, record, 'createTime'),
      },

      {
        title: '操作',
        dataIndex: 'operation',
        render: (text, record) => {
          const {editable} = record;
          return (
            <div className="editable-row-operations">
              {
                editable ?
                  <span>
                    <a onClick={() => this.save(record.key)}>保存</a>
                    <Popconfirm title="是否取消编辑" onConfirm={() => this.cancel(record.key)}>
                      <a>取消</a>
                    </Popconfirm>

                </span> : <a onClick={() => this.edit(record.key)}>编辑</a>
              }

            </div>
          )
        }
      }];
    this.state = {dataSource: [], count: 0};
    this.cacheData = dataSource.map(item => ({...item}));
  }

  componentWillReceiveProps(nextProps) {
    console.info('componentWillReceiveProps');
    this.setState({dataSource: [...nextProps.dataSource]});
  }

  renderColumns(text, record, column) {
    return (
      <EditableCell
        editable={record.editable}
        value={text}
        onChange={value => this.handleChange(value, record.key, column)}
      />
    );
  }

  add = () => {
    if (this.props.onAdd) {
      const newObj = {
        codetypeId: "",
        codetypeName: "",
        codeId: "",
        codeName: "",
        isStop: "0",
        editable: true,
      }
      this.props.onAdd(newObj);
    }
  }

  saveAll = () => {

  }
  // //删除选中条目
  // delete = () =>{
  //   let {selectedRows} = this.state;
  //   this.setState({selectedRowKeys:[]})
  //   this.props.onDelete(selectedRows)
  // }

  //删除选中条目
  delete = () =>{
    let {selectedRows} = this.state;
    this.setState({selectedRowKeys:[],selectedRows:[]})
    this.props.onDelete(selectedRows)
  }
  handleChange(value, key, column) {
    const newData = [...this.props.dataSource]
    const target = newData.filter(item => key === item.key)[0];
    if (target) {
      target[column] = value
      this.setState({dataSource: newData})
    }

  }

  edit(key) {
    const newData = [...this.props.dataSource]
    const target = newData.filter(item => key === item.key)[0]
    if (target) {
      target.editable = true;
      this.setState({dataSource: newData});
    }

  }

  save(key) {
    const newData = [...this.state.dataSource];
    const target = newData.filter(item => key === item.key)[0];
    if (target) {
      delete target.editable;
      this.setState({dataSource: newData});
      this.cacheData = newData.map(item => ({...item}));
    }
  }

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
  render() {
    const {dataSource,selectedRowKeys} = this.state;
    const rowSelection = {
      selectedRowKeys,
      onChange: (selectedRowKeys, selectedRows) => {
        console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows,"dataSource::"+{...dataSource});
        this.setState({selectedRowKeys:selectedRowKeys,selectedRows:selectedRows})
      },
    };
    return (
      <div>
        <div className="operation-area">
          <Button className="editable-add-btn" onClick={this.add}>增加</Button>
          <Button className="editable-add-btn" onClick={this.saveAll}>修改</Button>
          <Button className="editable-add-btn" onClick={this.add}>保存</Button>
          <Button className="editable-add-btn" onClick={this.delete}>删除</Button>
          <Button className="editable-add-btn" onClick={this.add}>启用</Button>
          <Button className="editable-add-btn" onClick={this.saveAll}>停用</Button>

        </div>


        <Table bordered rowSelection={rowSelection} dataSource={dataSource} columns={this.columns}
               pagination={{pageSize: 10, hideOnSinglePage: true}}/>

      </div>
    )
  }

}

class ProjectCategory extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      dataSource: [],
      count: 0,
    };
  }

  handleOnAdd = (newObj) => {
    const {count, dataSource} = this.state;
    newObj.key = count;
    this.setState({
      dataSource: [...dataSource, newObj],
      count: count + 1
    })
  }
  handleEdit = () => {

  }
  // handleDelete =(selectedRows) =>{
  //   const {dataSource} = this.state;
  //   selectedRows.map((selectedItem) =>{
  //     dataSource.splice(dataSource.findIndex(value => value.key === selectedItem.key),1);
  //   })
  //   this.setState({dataSource:dataSource})
  // }
  handleDelete =(selectedRows) =>{
    const {dataSource} = this.state;
    if(selectedRows) {
      selectedRows.map((selectedItem) => {
        dataSource.splice(dataSource.findIndex(value => value.key === selectedItem.key), 1);
      })
      this.setState({dataSource:dataSource})
    }
  }

  QuerySuccess(result) {

  }
  querySuccess = (result) => {
    this.setState({dataSource:result})
  }
  // handleQueryChange = (dateSelected) => {
  //   processor.query(dateSelected, function (result) {
  //     console.info(result);
  //   });
  // }

  handleQueryChange = (affiliatedHospital,projectCategoryCode,projectCategoryName,isStop) => {
    processor.searchProjectCategory(affiliatedHospital,projectCategoryCode,projectCategoryName,isStop,this.querySuccess);
  }
  render() {
    return (
      <div className="vh">
        <WrappedFormSearch
          onQueryChange={this.handleQueryChange}/>
        <EditableTable
          dataSource={this.state.dataSource}
          onAdd={this.handleOnAdd}
          onEdit={this.handleEdit}
          onDelete={(selectedRows)=>this.handleDelete(selectedRows)}
        />
      </div>
    )
  }
}


export default ProjectCategory

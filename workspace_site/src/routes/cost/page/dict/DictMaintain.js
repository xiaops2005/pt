import React from 'react';
import {connect} from "react-redux"

import {Form, Icon, Input, Button, Row, Col, DatePicker, Popconfirm, Table} from 'antd';
import moment from 'moment';
import {LoadService} from '../../process/LoadService';

const processor = new LoadService();

const FormItem = Form.Item;

const dateFormat = 'YYYY/MM/DD';
const dataSource = [];


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
    let dictName = this.props.form.getFieldValue('dictName');
    let dateSelected = this.props.form.getFieldValue('dateSelected')
    this.props.onQueryChange(dateSelected);
  };

  render() {
    const {getFieldDecorator} = this.props.form;
    return (

      <div className="vh">
        <Form onSubmit={this.handleSearch} className="ant-advanced-search-form">
          <Row gutter={24}>
            <Col span={8}>
              <FormItem label="时间">
                {
                  getFieldDecorator('dateSelected', {
                    initialValue: moment('2015/01/01', dateFormat)
                  })(
                    <DatePicker format={dateFormat}/>
                  )
                }
              </FormItem>

            </Col>
            <Col span={8}>
              <FormItem label="字典名称">
                {
                  getFieldDecorator('dictName', {
                    rules: [
                      // {required: true, message: '字典名称 is required.'}
                    ]
                  })(
                    <Input/>
                  )
                }
              </FormItem>
            </Col>
            <Col span={8}>
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
    this.columns = [
      {
        title: '表英文名',
        dataIndex: 'codetypeId',
        width: '15%',
        render: (text, record) => this.renderColumns(text, record, 'codetypeId'),
      }, {
        title: '表中文名',
        dataIndex: 'codetypeName',
        width: '25%',
        render: (text, record) => this.renderColumns(text, record, 'codetypeName'),
      }, {
        title: '表字段ID',
        dataIndex: 'codeId',
        width: '15%',
        render: (text, record) => this.renderColumns(text, record, 'codeId'),
      }
      , {
        title: '表字段值',
        dataIndex: 'codeName',
        width: '25%',
        render: (text, record) => this.renderColumns(text, record, 'codeName'),
      }
      , {
        title: '是否停用',
        dataIndex: 'isStop',
        width: '10%',
        render: (text, record) => this.renderColumns(text, record, 'isStop'),
      }, {
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
    this.state = {dataSource: [], count: 0,selectedRowKeys:[]};
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
    const {count, dataSource} = this.state;
    const newObj = {
      key: count,
      codetypeId: "",
      codetypeName: "",
      codeId: "",
      codeName: "",
      isStop: "0",
      editable: true,
    }

    this.setState({
      dataSource: [...dataSource, newObj],
      count: count + 1
    })
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
      onChange: this.onSelectChange,
    };
    return (
      <div>
        <div className="operation-area">
          <Button className="editable-add-btn" onClick={this.add}>新增</Button>
          <Button className="editable-add-btn" onClick={this.saveAll}>停用</Button>
        </div>
        <Table  bordered rowSelection={rowSelection} dataSource={dataSource} columns={this.columns}/>

      </div>
    )
  }

}

class DictMaintain extends React.Component {
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


  handleQueryChange = (dateSelected) => {
    processor.query(dateSelected, function (result) {
      console.info(this.props);
      console.info(result);
    });
  };

  render() {
    return (
      <div className="vh">
        <WrappedFormSearch
          onQueryChange={this.handleQueryChange}/>
        <EditableTable
          dataSource={this.state.dataSource}
          onAdd={this.handleOnAdd}
          onEdit={this.handleEdit}
        />
      </div>
    )
  }
}


export default DictMaintain



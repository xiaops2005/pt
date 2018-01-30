import React from 'react';
import {Form, Icon, Input, Button, Row, Col, Select} from 'antd';
import YearPicker from "../../component/YearPicker"

const Option = Select.Option;

const FormItem = Form.Item;

class Header extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      belongSystemList: [],
      isStopList: [],
      yearList: [],
      hospitalList: []
    }
  }

  componentWillReceiveProps(nextProps) {
    this.setState({...nextProps});
  }

  handleSearch = (e) => {
    e.preventDefault();
    let year = this.props.form.getFieldValue('year');
    let pkHospital = this.props.form.getFieldValue('pkHospital');
    let deptCode = this.props.form.getFieldValue('deptCode');
    let deptName = this.props.form.getFieldValue('deptName');
    let belongSystem = this.props.form.getFieldValue('belongSystem');
    let isStop = this.props.form.getFieldValue('isStop');
    this.state.queryBdDept(year, pkHospital, deptCode, deptName, belongSystem, isStop);
  };

  render() {
    const formItemLayout = {
      labelCol: {
        xs: {span: 0},
        sm: {span: 6},
      },
      wrapperCol: {
        xs: {span: 0},
        sm: {span: 13},
      }
    };
    const {getFieldDecorator} = this.props.form;
    return (

      <div>
        <Form onSubmit={this.handleSearch} className="ant-advanced-search-form">
          <Row gutter={24}>
            <Col span={7}>
              <FormItem label="年度" {...formItemLayout}>
                {
                  getFieldDecorator('year', {
                    rules: []
                  })(
                    <Select allowClear>
                      {this.state.yearList.map((item) => {
                        return <Option key={item.key} value={item.codeId}>{item.codeName}</Option>
                      })}
                    </Select>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}>
              <FormItem label="所属医院" {...formItemLayout}>
                {
                  getFieldDecorator('pkHospital', {
                    rules: []
                  })(
                    <Select allowClear>
                      {this.state.hospitalList.map((item) => {
                        return <Option key={item.key} value={item.pkHospital}>{item.hospitalName}</Option>
                      })}
                    </Select>
                  )
                }
              </FormItem>
            </Col>

            <Col span={7}>
              <FormItem label="科室编码" {...formItemLayout}>
                {
                  getFieldDecorator('deptCode', {
                    rules: []
                  })(
                    <Input/>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={7}>
              <FormItem label="科室名称" {...formItemLayout}>
                {
                  getFieldDecorator('deptName', {
                    rules: []
                  })(
                    <Input/>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}>
              <FormItem label="所属系统" {...formItemLayout}>
                {
                  getFieldDecorator('belongSystem', {
                    rules: []
                  })(
                    <Select allowClear>
                      {this.state.belongSystemList.map((item) => {
                        return <Option key={item.key} value={item.codeId}>{item.codeName}</Option>
                      })}
                    </Select>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}>
              <FormItem label="是否停用" {...formItemLayout}>
                {
                  getFieldDecorator('isStop', {
                    rules: []
                  })(
                    <Select allowClear>
                      {this.state.isStopList.map((item) => {
                        return <Option key={item.key} value={item.codeId}>{item.codeName}</Option>
                      })}
                    </Select>
                  )
                }
              </FormItem>
            </Col>
            <Col span={3}>
              <Button type="primary" onClick={this.handleSearch}>查询</Button>
            </Col>
          </Row>
        </Form>
      </div>
    )
  }
}


export default Form.create()(Header)



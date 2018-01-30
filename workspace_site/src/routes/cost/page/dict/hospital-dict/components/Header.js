import React from 'react';
import {Form, Input, Button, Row, Col, Select} from 'antd';

const Option = Select.Option;

const FormItem = Form.Item;

class Header extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      hospitalClassList: [],
      hospitalTypeList: []
    }
  }

  componentWillReceiveProps(nextProps) {
    this.setState({...nextProps});
  }

  handleSearch = (e) => {
    e.preventDefault();
    let hospitalCode = this.props.form.getFieldValue('hospitalCode');
    let hospitalName = this.props.form.getFieldValue('hospitalName');
    let hospitalTypeId = this.props.form.getFieldValue('hospitalTypeId');
    let hospitalClassId = this.props.form.getFieldValue('hospitalClassId');
    this.state.queryBdHospital(hospitalCode, hospitalName, hospitalTypeId, hospitalClassId);
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
      },
    };
    const {getFieldDecorator} = this.props.form;

    return (

      <div>
        <Form onSubmit={this.handleSearch} className="ant-advanced-search-form">
          <Row gutter={24}>
            <Col span={7}>
              <FormItem label="医院编码" {...formItemLayout}>
                {
                  getFieldDecorator('hospitalCode', {
                    rules: [
                      // {required: false, message: '字典名称 is required.'}
                    ]
                  })(
                    <Input/>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}>
              <FormItem label="医院名称" {...formItemLayout}>
                {
                  getFieldDecorator('hospitalName', {
                    rules: []
                  })(
                    <Input/>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}>
              <FormItem label="医院类型" {...formItemLayout}>
                {
                  getFieldDecorator('hospitalTypeId', {
                    rules: []
                  })(
                    <Select allowClear>
                      {this.state.hospitalTypeList.map((item) => {
                        return <Option key={item.key} value={item.codeId}>{item.codeName}</Option>
                      })}
                    </Select>
                  )
                }
              </FormItem>
            </Col>
            <Col span={3}> </Col>
          </Row>
          <Row gutter={24}>
            <Col span={7}>
              <FormItem label="医院等级" {...formItemLayout}>
                {
                  getFieldDecorator('hospitalClassId', {
                    rules: []
                  })(
                    <Select allowClear>
                      {this.state.hospitalClassList.map((item) => {
                        return <Option key={item.key} value={item.codeId}>{item.codeName}</Option>
                      })}
                    </Select>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}> </Col>
            <Col span={7}> </Col>
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



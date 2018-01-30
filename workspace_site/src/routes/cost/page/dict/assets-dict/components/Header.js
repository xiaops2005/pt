import React from 'react';
import {Form, Input, Button, Row, Col, Select} from 'antd';

const Option = Select.Option;

const FormItem = Form.Item;

class Header extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isStopList: [],
      yearList: [],
      hospitalList: [],
      capitalSourceList: []
    }
  }

  componentWillReceiveProps(nextProps) {
    console.log("receive props ...");
    console.log(nextProps);
    this.setState({...nextProps});
  }

  handleSearch = (e) => {
    e.preventDefault();
    let accYear = this.props.form.getFieldValue('accYear');
    let pkHospital = this.props.form.getFieldValue('pkHospital');
    let assetsCode = this.props.form.getFieldValue('assetsCode');
    let assetsName = this.props.form.getFieldValue('assetsName');
    let capitalSourceId = this.props.form.getFieldValue('capitalSourceId');
    let isStopId = this.props.form.getFieldValue('isStopId');
    this.state.queryBdAssets(accYear, pkHospital, assetsCode, assetsName, capitalSourceId,isStopId, 0);
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

      <div id="assets-dict-header">
        <Form onSubmit={this.handleSearch} className="ant-advanced-search-form">
          <Row gutter={24}>
            <Col span={7}>
              <FormItem label="年度" {...formItemLayout}>
                {
                  getFieldDecorator('accYear', {
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
              <FormItem label="资产编码" {...formItemLayout}>
                {
                  getFieldDecorator('assetsCode', {
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
              <FormItem label="资产名称" {...formItemLayout}>
                {
                  getFieldDecorator('assetsName', {
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
              <FormItem label="资金来源" {...formItemLayout}>
                {
                  getFieldDecorator('capitalSourceId', {
                    rules: []
                  })(
                    <Select allowClear>
                      {this.state.capitalSourceList.map((item) => {
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
                  getFieldDecorator('isStopId', {
                    rules: []
                  })(
                    <Select allowClear>
                      {this.state.isStopList.map((item) => {
                        return <Option key={item.codeId} value={item.codeId}>{item.codeName}</Option>
                      })}
                    </Select>
                  )
                }
              </FormItem>
            </Col>
            <Col span={3}><Button type="primary" onClick={this.handleSearch}>查询</Button> </Col>
          </Row>
        </Form>
      </div>
    )
  }
}


export default Form.create()(Header)



/**
 * 标准资产字典 Header
 */
import React from 'react';
import {Form, Input, Button, Row, Col, Select} from 'antd';

const Option = Select.Option;

const FormItem = Form.Item;

class StandardHeader extends React.Component {
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
    this.setState({...nextProps});
  }

  handleSearch = (e) => {
    e.preventDefault();
    let pkHospital = this.props.form.getFieldValue('pkHospital');
    let assetsCode = this.props.form.getFieldValue('assetsCode');
    let assetsName = this.props.form.getFieldValue('assetsName');
    let capitalSourceId = this.props.form.getFieldValue('capitalSourceId');
    let isStopId = this.props.form.getFieldValue('isStopId');
    this.state.queryBdAssets(null, pkHospital, assetsCode, assetsName, capitalSourceId,isStopId, 1);
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
                        return <Option key={item.key + 's'} value={item.codeId}>{item.codeName}</Option>
                      })}
                    </Select>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7} />
            <Col span={3}><Button type="primary" onClick={this.handleSearch}>查询</Button> </Col>
          </Row>
        </Form>
      </div>
    )
  }
}


export default Form.create()(StandardHeader)



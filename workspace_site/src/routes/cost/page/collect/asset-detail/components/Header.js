 /**
 * 数据采集 -- 资产折旧明细 -- Header
 * -- 包括：查询条件、查询按钮
 */
import React, { Component } from 'react';
import { Button, Form, Select, Input, Row, Col } from 'antd';

const FormItem = Form.Item;
const Option = Select.Option;

class Header extends Component {
  constructor(props){
    console.log("资产折旧明细 Header constructor 11111111111,", props);
    super(props);
    this.state = {
      yearList: [],
      hospitalList: [],
      capitalSourceList: []
    };

  }


  componentWillReceiveProps(nextProps) {
    console.log("资产折旧明细 Header receive props  222222:", nextProps);
    this.setState({ ...nextProps });
  }

  // 查询按钮事件
  assetDepreciationQueryBtn = (e)=>{
    e.preventDefault();
    console.log('查询按钮事件 。。。');
    let thisForm = this.props.form;
    // let year = thisForm.getFieldValue('year');
    let fields = thisForm.getFieldsValue();
    console.log("fields :", fields);
    this.state.queryAssetDepreciation(fields);
  }

	render() {
    const { getFieldDecorator } = this.props.form;

    const formItemLayout = {
      labelCol: {
        span: 9
      },
      wrapperCol: {
        span: 13
      }
    };

		return (
			<div className="vh-header" id="re-asset-header">
        <Form className="ant-advanced-search-form">
          <Row gutter={ 24 }>
            <Col span={ 7 } key="col-1">
              <FormItem label="年度" { ...formItemLayout }>
                {
                  getFieldDecorator('year')(
                    <Select allowClear>
                      {this.state.yearList.map((item) => {
                        return <Option key={item.codeId} value={item.codeId}>{item.codeName}</Option>
                      })}
                    </Select>
                  )
                }
              </FormItem>
            </Col>
            <Col span={ 7 } key="col-2">
              <FormItem label="所属单位" { ...formItemLayout }>
                {
                  getFieldDecorator('pkHospital')(
                    <Select allowClear>
                      {this.state.hospitalList.map((item) => {
                        return <Option key={item.pkHospital} value={item.pkHospital}>{item.hospitalName}</Option>
                      })}
                    </Select>
                  )
                }
              </FormItem>
            </Col>
            <Col span={ 7 } key="col-3">
              <FormItem { ...formItemLayout } label="资产科室编码">
                {
                  getFieldDecorator('deptCode')(
                    <Input />
                  )
                }
              </FormItem>
            </Col>
            <Col span={ 3 } key="col-4"/>
          </Row>
          <Row gutter={ 24 }>
            <Col span={ 7 } key="col-5">
              <FormItem { ...formItemLayout } label="资产科室名称">
                {
                  getFieldDecorator('deptName')(
                    <Input />
                  )
                }
              </FormItem>
            </Col>
            <Col span={ 7 } key="col-6">
              <FormItem label="资金来源" { ...formItemLayout }>
                {
                  getFieldDecorator('capitalSourceId')(
                    <Select allowClear>
                      {this.state.capitalSourceList.map((item) => {
                        return <Option key={item.codeId} value={item.codeId}>{item.codeName}</Option>
                      })}
                    </Select>
                  )
                }
              </FormItem>
            </Col>
            <Col span={ 7 } key="col-7">
              <FormItem label="资产" { ...formItemLayout }>
                {
                  getFieldDecorator('pkAsset')(
                    <Input />
                  )
                }
              </FormItem>
            </Col>
            <Col span={ 3 } key="col-8">
              <Button type="primary" onClick={ this.assetDepreciationQueryBtn }>查询</Button>
            </Col>
          </Row>

        </Form>
			</div>
		);
	}
}
export default  Form.create()(Header);

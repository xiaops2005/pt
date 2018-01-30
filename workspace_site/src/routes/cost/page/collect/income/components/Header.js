import React from 'react';
import {Form, Icon, Input, Button, Row, Col, Select} from 'antd';

const Option = Select.Option;

const FormItem = Form.Item;


class HeaderForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      hospitalList: [],
      mappedHospital: {},
      yearList: [],
      mappedYear: {}
    }
  }
  handleSubmit = (e) => {
    e.preventDefault();
    let year = this.props.form.getFieldValue('year');
    let pkHospital = this.props.form.getFieldValue('pkHospital')
    let incomeType = this.props.form.getFieldValue('incomeType')
    console.log("this.state",this.state);

  }

  componentWillReceiveProps(nextProps) {
    this.setState({...nextProps});
  }

  render()
  {
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
    const { getFieldDecorator } = this.props.form;
    return(
      <Form layout="inline" onSubmit={ this.handleSubmit} >
        <Row gutter={24}>
          <Col span={6}>
            <FormItem label="年度" {...formItemLayout}>
              {
                getFieldDecorator('year', {
                  rules: [],
                  initialValue: null
                })(
                  <Select style={{ width: 130 }} allowClear>
                    {this.state.yearList.map((item) => {
                      return <Option key={item.key} value={item.codeId}>{item.codeName}</Option>
                    })}
                  </Select>
                )
              }
            </FormItem>
          </Col>
          <Col span={6}>
            <FormItem label="所属单位" {...formItemLayout}>
              {
                getFieldDecorator('pkHospital', {
                  rules: []
                })(
                  <Select style={{ width: 130 }} allowClear>
                    {this.state.hospitalList.map((item) => {
                      return <Option key={item.key} value={item.pkHospital}>{item.hospitalName}</Option>
                    })}
                  </Select>
                )
              }
            </FormItem>
          </Col>
          <Col span={6}>
            <FormItem label="收入类型" {...formItemLayout} >
              {getFieldDecorator('incomeType',{
                initialValue: ''
              })(
                <Select style={{ width: 130 }} allowClear>
                  <Option value="0">门诊</Option>
                  <Option value="1">住院</Option>
                </Select>
              )}
            </FormItem>
          </Col>
          <Col span={6}>
            <FormItem>
              <Button  type="primary"  htmlType="submit">查询</Button>
            </FormItem>
          </Col>
        </Row>
      </Form>
    );
  }
}
const Header = Form.create()(HeaderForm);
export default Header;

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
      belongSystemList: []
      // mappedIsStop: {}
    }
  }
  handleSubmit = (e) => {
    e.preventDefault();
    let pkHospital = this.props.form.getFieldValue('pkHospital');
    let deptCode = this.props.form.getFieldValue('deptCode');
    let deptName = this.props.form.getFieldValue('deptName');
    let belongSystem = this.props.form.getFieldValue('belongSystem');
    let isStop = this.props.form.getFieldValue('isStop');
    console.log('this.state',this.state)
    this.state.query(pkHospital,deptCode,deptName,belongSystem,isStop);
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
          <Col span={7}>
            <FormItem label="所属单位" {...formItemLayout}>
              {
                getFieldDecorator('pkHospital', {
                  rules: []
                })(
                  <Select style={{ width: 130 }} allowClear>
                    {this.state.hospitalList.map((item) => {
                      return <Option key={item.pkHospital} value={item.pkHospital}>{item.hospitalName}</Option>
                    })}
                  </Select>
                )
              }
            </FormItem>
          </Col>
          <Col span={7}>
            <FormItem label="标准科室编码" {...formItemLayout}>
              {getFieldDecorator('deptCode')(
                <Input/>
              )}
            </FormItem>
          </Col>
          <Col span={7}>
            <FormItem label="标准科室名称" {...formItemLayout}>
              {getFieldDecorator('deptName')(
                <Input />
              )}
            </FormItem>
          </Col>
        </Row>
        <Row gutter={24}>
          <Col span={7}>
            <FormItem label="所属系统" {...formItemLayout}>
              {getFieldDecorator('belongSystem',{
                initialValue: ''
              })(
                <Select style={{ width: 130 }} allowClear>
                  {this.state.belongSystemList.map((item) => {
                    return <Option key={item.codeId} value={item.codeId}>{item.codeName}</Option>
                  })}
                </Select>
              )}
            </FormItem>
          </Col>

          <Col span={7}>
            <FormItem label="是否停用" {...formItemLayout} >
              {getFieldDecorator('isStop',{
                initialValue: ''
              })(
                <Select style={{ width: 130 }} allowClear>
                  <Option value="0">否</Option>
                  <Option value="1">是</Option>
                </Select>
              )}
            </FormItem>
          </Col>
          <Col span={7}>
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

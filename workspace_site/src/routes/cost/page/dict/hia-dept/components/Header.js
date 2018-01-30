import React from 'react';
import {Form, Input, Button, Row, Col, Select} from 'antd';

const Option = Select.Option;
const FormItem = Form.Item;

class Header extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      isStopList: [],
      mappedIsStop: {}
    }
  }

  handleSearch = (e) => {
    e.preventDefault();
    let hiaCode = this.props.form.getFieldValue('hiaCode');
    let isStop = this.props.form.getFieldValue('isStop');
    this.props.queryBdDeptHia(hiaCode, isStop);
  };

  componentWillReceiveProps(nextProps) {
    console.log(nextProps);
    this.setState({...nextProps});
  }

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
      <Form layout="inline" onSubmit={this.handleSubmit}>
        <Row gutter={24}>
          <Col span={6}>
            <FormItem label="HIA科室编码" {...formItemLayout}>
              {getFieldDecorator('hiaCode')(
                <Input/>
              )}
            </FormItem>
          </Col>
          <Col span={6}>
            <FormItem label="HIA科室名称" {...formItemLayout}>
              {getFieldDecorator('deptName')(
                <Input/>
              )}
            </FormItem>
          </Col>
          <Col span={6}>
            <FormItem label="是否停用" {...formItemLayout} >
              {getFieldDecorator('isStop', {
                initialValue: ''
              })(
                <Select style={{width: 140}} allowClear>
                  {this.state.isStopList.map(item => <Option key={item.codeId}
                                                             value={item.codeId}>{item.codeName}</Option>)}
                </Select>
              )}
            </FormItem>
          </Col>
          <Col span={6}>
            <FormItem>
              <Button type="primary" onClick={this.handleSearch}>查询</Button>
            </FormItem>
          </Col>
        </Row>

      </Form>
    );
  }
}

export default Form.create()(Header);

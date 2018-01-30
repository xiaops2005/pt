import React from 'react';
import {Form, Input, Button, Row, Col,Select} from 'antd';
import {IdentificationService} from '../../../../process/LoadService';
const FormItem = Form.Item;
const Option = Select.Option;
const processor = new IdentificationService();

class Header extends React.Component {

  constructor(props) {
    super(props)
    this.state = {}
  }


  componentWillReceiveProps(nextProps) {
    this.setState({...nextProps});
  }


  handleSearch = (e) => {
    e.preventDefault();
    let templateName = this.props.form.getFieldValue('templateName');
    let dbTableName = this.props.form.getFieldValue('dbTableName')
    this.props.queryTemplateEntity(templateName,dbTableName);
  }

  render() {
    const {getFieldDecorator} = this.props.form;
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
    return (

      <div className="vh">
        <Form onSubmit={this.handleSearch} className="ant-advanced-search-form">
          <Row gutter={24}>
            <Col span={7}>
              <FormItem label="模板名称" {...formItemLayout}>
                {
                  getFieldDecorator('templateName', {
                    initialValue:"",
                    rules: [
                      // {required: true, message: '字典名称 is required.'}
                    ]
                  })(
                    <Input onPressEnter={this.handleSearch}/>
                  )
                }
              </FormItem>

            </Col>
            <Col span={7}>
              <FormItem label="数据库表名" {...formItemLayout}>
                {
                  getFieldDecorator('dbTableName', {
                    initialValue:"",
                    rules: [
                      // {required: true, message: '字典名称 is required.'}
                    ]
                  })(
                    <Input onPressEnter={this.handleSearch}/>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}/>
            <Col span={3}>
              <Button type="primary" htmlType="submit">查询</Button>
            </Col>
          </Row>
        </Form>
      </div>
    )
  }
}

export default Form.create()(Header);

import React from 'react';
import {Form, Input, Button, Row, Col, Select} from 'antd';

const Option = Select.Option;

const FormItem = Form.Item;

class Header extends React.Component {
  constructor(props) {
    super(props);
  }

  componentWillMount() {
    this.props.QueryTemplateEntityAdded();
  }

  shouldComponentUpdate(nextProps) {
    return this.props.doQuery !== nextProps.doQuery || this.props.templateClasses !== nextProps.templateClasses;
  }

  handleSearch = () => {
    setTimeout(() => {
      let templateId = this.props.form.getFieldValue('templateId');
      let filter = this.props.templateClasses.filter(template => template.templateId === templateId);
      if (filter.length > 0) {
        let template = filter[0];
        this.props.form.setFieldsValue({
          templateId: template.templateId,
          templateClass: template.templateClass,
          dbTableName: template.dbTableName,
          primaryKey: template.primaryKey,
          validator: template.validator
        });
        this.props.getTemplateColumns(template.templateId);
      }
    }, 0);
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

    if (this.props.doQuery) {
      console.log("doQuery");
      this.handleSearch();
    }
    return (

      <div>
        <Form className="ant-advanced-search-form">
          <Row gutter={24}>
            <Col span={7}>
              <FormItem label="模版名称" {...formItemLayout}>
                {
                  getFieldDecorator('templateId', {
                    rules: []
                  })(
                    <Select onSelect={this.props.queryDataSource}>
                      {this.props.templateClasses && this.props.templateClasses.map((item) => {
                        return <Option key={item.key} value={item.templateId}>{item.templateName}</Option>
                      })}
                    </Select>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}>
              <FormItem label="表名" {...formItemLayout}>
                {
                  getFieldDecorator('dbTableName', {
                    rules: []
                  })(
                    <Input disabled/>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}>
              <FormItem label="实体类" {...formItemLayout}>
                {
                  getFieldDecorator('templateClass', {
                    rules: []
                  })(
                    <Input disabled/>
                  )
                }
              </FormItem>
            </Col>
            <Col span={3}>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={7}>
              <FormItem label="主键" {...formItemLayout}>
                {
                  getFieldDecorator('primaryKey', {
                    rules: []
                  })(
                    <Input disabled/>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}>
              <FormItem label="验证类" {...formItemLayout}>
                {
                  getFieldDecorator('validator', {
                    rules: []
                  })(
                    <Input disabled/>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}> </Col>
            <Col span={3}>
            </Col>
          </Row>
        </Form>
      </div>
    )
  }
};


export default Form.create()(Header)



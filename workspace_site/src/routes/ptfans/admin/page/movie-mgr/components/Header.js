import React from 'react';
import {Form, Icon, Input, Button, Row, Col, Select} from 'antd';

const Option = Select.Option;

const FormItem = Form.Item;

class Header extends React.Component {
  constructor(props) {
    super(props);
    this.state = {

    }
  }

  componentWillMount() {
    console.log("Header componentWillMount");
  }

  componentWillReceiveProps(nextProps) {
    this.setState({...nextProps});
  }

  handleSearch = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        let params = this.props.form.getFieldsValue();
        this.props.onQuery(params);
      }
    });
  };

  render() {
    console.log("Header render")
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
              <FormItem label="电影名称" {...formItemLayout}>
                {
                  getFieldDecorator('title', {
                    rules: [],
                    initialValue: null
                  })(
                    <Input/>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}>
              <FormItem label="导演" {...formItemLayout}>
                {
                  getFieldDecorator('directors', {
                    rules: []
                  })(
                    <Input/>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}>
              <FormItem label="演员" {...formItemLayout}>
                {
                  getFieldDecorator('casts', {
                    rules: []
                  })(
                    <Input/>
                  )
                }
              </FormItem>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={7}>
              <FormItem label="年份" {...formItemLayout}>
                {
                  getFieldDecorator('year', {
                    rules: [],
                    initialValue: null
                  })(
                    <Input/>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}>
              <FormItem label="国家" {...formItemLayout}>
                {
                  getFieldDecorator('countries', {
                    rules: []
                  })(
                    <Input/>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}>
              <FormItem label="类型" {...formItemLayout}>
                {
                  getFieldDecorator('genres', {
                    rules: []
                  })(
                    <Input/>
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



import React from 'react';
import {Form, Input, Button, Row, Col, Select} from 'antd';

const Option = Select.Option;

const FormItem = Form.Item;

class Header extends React.Component {
  constructor(props) {
    super(props);
  }

  componentWillReceiveProps(nextProps) {
    this.setState({...nextProps});
    // this.state.isStopList=[{key:0,name:"是",value:"0"},{key:1,name:"否",value:"1"}];
  }

  handleSearch = (e) => {
    e.preventDefault();
    let chargesCode = this.props.form.getFieldValue('chargesCode');
    let chargesName = this.props.form.getFieldValue('chargesName');
    this.state.query(chargesCode,chargesName);
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

      <div className="vh">
        <Form onSubmit={this.handleSearch} className="ant-advanced-search-form">
          <Row gutter={24}>
            <Col span={10}>
              <FormItem label="标准服务项目编码：" {...formItemLayout}>
                {
                  getFieldDecorator('chargesCode', {
                    rules: [
                    ]
                  })(
                    <Input/>
                  )
                }
              </FormItem>

            </Col>
            <Col span={10}>
              <FormItem label="标准服务项目名称：" {...formItemLayout}>
                {
                  getFieldDecorator('chargesName', {
                    rules: [
                    ]
                  })(
                    <Input/>
                  )
                }
              </FormItem>
            </Col>
            <Col span={4}>
              <Button type="primary" onClick={this.handleSearch}>查询</Button>
            </Col>
          </Row>
        </Form>
      </div>
    )
  }
}


export default Form.create()(Header)



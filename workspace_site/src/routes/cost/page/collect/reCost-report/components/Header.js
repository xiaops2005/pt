import React from 'react';
import {Form, Input, Button, Row, Col, Select} from 'antd';

const Option = Select.Option;

const FormItem = Form.Item;

class Header extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      // isStopList: [{key:0,name:"是",value:"0"},{key:1,name:"否",value:"1"}],
      yearList: [],
      hospitalList: [],
      capitalSourceList: []
    }
  }

  componentWillReceiveProps(nextProps) {
    this.setState({...nextProps});
    // this.state.isStopList=[{key:0,name:"是",value:"0"},{key:1,name:"否",value:"1"}];
  }

  handleSearch = (e) => {
    e.preventDefault();
    let accYear = this.props.form.getFieldValue('accYear');
    let pkHospital = this.props.form.getFieldValue('pkHospital');
    let deptCode = this.props.form.getFieldValue('deptCode');
    let deptName = this.props.form.getFieldValue('deptName');
    this.state.queryReCost(accYear,pkHospital,deptCode,deptName);
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

      <div>
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
              <FormItem label="成本科室编码" {...formItemLayout}>
                {
                  getFieldDecorator('deptCode', {
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
              <FormItem label="成本科室名称" {...formItemLayout}>
                {
                  getFieldDecorator('deptName', {
                    rules: [
                      // {required: false, message: '字典名称 is required.'}
                    ]
                  })(
                    <Input/>
                  )
                }
              </FormItem>
              </Col>
            <Col span={7}></Col>
            <Col span={7}></Col>
            <Col span={3}><Button type="primary" onClick={this.handleSearch}>查询</Button> </Col>
          </Row>
        </Form>
      </div>
    )
  }
}


export default Form.create()(Header)



import React from 'react';
import {Form, Icon, Input, Button, Row, Col, Select} from 'antd';

const Option = Select.Option;

const FormItem = Form.Item;

class Header extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      yearList: [],
      hospitalList: [],
      classCode: '',
      className: ''
    }
  }

  componentWillReceiveProps(nextProps) {
    this.setState({...nextProps});
  }

  // componentWillMount() {
  //   processor.queryHospitals((result) => {
  //     this.setState({hospitalList: result.getSinglePrimary()})
  //   })
  // }


  handleSearch = (e) => {
    e.preventDefault();
    let year = this.props.form.getFieldValue('year');
    let pkHospital = this.props.form.getFieldValue('pkHospital');
    let classCode = this.props.form.getFieldValue('classCode');
    this.state.queryReWage(year, pkHospital, classCode);
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
      }
    };
    const {getFieldDecorator} = this.props.form;
    return (

      <div>
        <Form onSubmit={this.handleSearch} className="ant-advanced-search-form">
          <Row gutter={24}>
            <Col span={7}>
              <FormItem label="年度" {...formItemLayout}>
                {
                  getFieldDecorator('year', {
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
              <FormItem label="所属单位" {...formItemLayout}>
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
              <FormItem label="成本科室编码:" {...formItemLayout}>
                {
                  getFieldDecorator('classCode', {
                    rules: [
                      // {required: true, message: '字典名称 is required.'}
                    ]
                  })(
                    <Input/>
                  )
                }
              </FormItem>
            </Col>


            <Col span={3}>
            </Col>


          </Row>

          <Row gutter={24}>

            <Col span={7}>
              <FormItem label="成本科室名称:" {...formItemLayout}>
                {
                  getFieldDecorator('classCode', {
                    rules: [
                      // {required: true, message: '字典名称 is required.'}
                    ]
                  })(
                    <Input/>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}>
            </Col>
            <Col span={7}>
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



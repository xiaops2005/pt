import React from 'react';
import {Form, Icon, Input, Button, Row, Col, Select} from 'antd';

const Option = Select.Option;

const FormItem = Form.Item;

class Header extends React.Component {
  constructor(props) {
    super(props);
    console.log("constructor")
    this.state = {
      yearList: [],
      hospitalList: [],
      isSingleList: [{
        codeId:'1',codeName:'是'
      },{
        codeId:'0',codeName:'否'
      }],
      isStopList: [{
        codeId:'1',codeName:'是'
      },{
        codeId:'0',codeName:'否'
      }]
    }
  }

  componentWillReceiveProps(nextProps) {
    console.log("componentWillReceiveProps",nextProps)
    this.setState({...nextProps});
  }

  handleSearch = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        console.info('values', values);
        console.info(`this.props.form.getFieldValue('materialsName'): ${this.props.form.getFieldValue('materialsName')}`);
      }
    });
    let year = this.props.form.getFieldValue('year');
    let pkHospital = this.props.form.getFieldValue('pkHospital');
    let materialsCode = this.props.form.getFieldValue('materialsCode');
    let materialsName = this.props.form.getFieldValue('materialsName');
    let isSingle = this.props.form.getFieldValue('isSingle');
    let isStop = this.props.form.getFieldValue('isStop');
    this.state.queryBdMaterials(year,pkHospital,materialsCode,materialsName,isSingle,isStop);
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
                    rules: [],
                    initialValue: null
                  })(
                    <Select>
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
                    <Select  allowClear>
                      {this.state.hospitalList.map((item) => {
                        return <Option key={item.key} value={item.pkHospital}>{item.hospitalName}</Option>
                      })}
                    </Select>
                  )
                }
              </FormItem>
            </Col>

            <Col span={7}>
              <FormItem label="材料编码" {...formItemLayout}>
                {
                  getFieldDecorator('materialsCode', {
                    rules: []
                  })(
                    <Input/>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}>
            </Col>
          </Row>
          <Row gutter={24}>
            <Col span={7}>
              <FormItem label="材料名称" {...formItemLayout}>
                {
                  getFieldDecorator('materialsName', {
                    rules: []
                  })(
                    <Input/>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}>
              <FormItem label="是否单收费" {...formItemLayout}>
                {
                  getFieldDecorator('isSingle', {
                    rules: []
                  })(
                    <Select allowClear>
                      {this.state.isSingleList.map((item) => {
                        return <Option key={item.codeId}>{item.codeName}</Option>
                      })}
                    </Select>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}>
              <FormItem label="是否停用" {...formItemLayout}>
                {
                  getFieldDecorator('isStop', {
                    rules: []
                  })(
                    <Select allowClear>
                      {this.state.isStopList.map((item) => {
                        return <Option key={item.codeId}>{item.codeName}</Option>
                      })}
                    </Select>
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



import React from 'react';
import {Form, Icon, Input, Button, Row, Col, Select} from 'antd';

const Option = Select.Option;

const FormItem = Form.Item;

class Header extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      yearList: [],
      mappedYear: {},
      hospitalList: [],
      mappedHospital: {},
      isChargesList: [{
        codeId:'1',codeName:'是'
      },{
        codeId:'0',codeName:'否'
      }],
      mappedIsCharges: {},
      materialsList: [],
      mappedMaterialsList: {}
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
        console.info('values', values);
        console.info(`this.props.form.getFieldValue('materialsName'): ${this.props.form.getFieldValue('materialsName')}`);
      }
    });
    let year = this.props.form.getFieldValue('year');
    let pkHospital = this.props.form.getFieldValue('pkHospital');
    let materialsDeptCode = this.props.form.getFieldValue('materialsDeptCode');
    let materialsDeptName = this.props.form.getFieldValue('materialsDeptName');
    let isCharges = this.props.form.getFieldValue('isCharges');
    let pkMaterials = this.props.form.getFieldValue('pkMaterials');
    this.props.onQuery(year,pkHospital,materialsDeptCode,materialsDeptName,isCharges,pkMaterials);
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
              <FormItem label="年度" {...formItemLayout}>
                {
                  getFieldDecorator('year', {
                    rules: [],
                    initialValue: new Date().getFullYear()
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
              <FormItem label="材料科室编码" {...formItemLayout}>
                {
                  getFieldDecorator('materialsDeptCode', {
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
              <FormItem label="材料科室名称" {...formItemLayout}>
                {
                  getFieldDecorator('materialsDeptName', {
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
                  getFieldDecorator('isCharges', {
                    rules: []
                  })(
                    <Select allowClear>
                      {this.state.isChargesList.map((item) => {
                        return <Option key={item.codeId}>{item.codeName}</Option>
                      })}
                    </Select>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}>
              <FormItem label="材料" {...formItemLayout}>
                {
                  getFieldDecorator('pkMaterials', {
                    rules: []
                  })(
                    <Select allowClear>
                      {this.state.materialsList.map((item) => {
                        return <Option key={item.key} value={item.pkMaterials}>{item.materialsName}</Option>
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



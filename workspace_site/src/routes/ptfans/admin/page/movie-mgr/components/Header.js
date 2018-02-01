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
        let year = this.props.form.getFieldValue('year');
        let pkHospital = this.props.form.getFieldValue('pkHospital');
        let materialsDeptCode = this.props.form.getFieldValue('materialsDeptCode');
        let materialsDeptName = this.props.form.getFieldValue('materialsDeptName');
        let isCharges = this.props.form.getFieldValue('isCharges');
        let pkMaterials = this.props.form.getFieldValue('pkMaterials');
        this.props.onQuery(year,pkHospital,materialsDeptCode,materialsDeptName,isCharges,pkMaterials);
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
              <FormItem label="年度" {...formItemLayout}>
                {
                  getFieldDecorator('year', {
                    rules: [{ required: true, message: '年度不能为空' }],
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
              <FormItem label="所属单位" {...formItemLayout}>
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



import React from 'react';
import {Form, Input, Button, Row, Col, Select, DatePicker} from 'antd';
import YearPicker from '../../component/YearPicker';
import moment from 'moment';


const FormItem = Form.Item;
const Option = Select.Option;
const dateFormat = 'YYYY';
const formItem = {
  labelCol: {
    // xs: {span: 0},
    sm: {span: 9},
  },
  wrapperCol: {
    xs: {span: 0},
    sm: {span: 13},
  },
};

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

class FormSearch extends React.Component {


  handleSearch = (e) => {
    e.preventDefault();
    let values = this.props.form.getFieldsValue();
    this.props.onQueryChange(values);
  }

  render() {
    const {getFieldDecorator} = this.props.form;
    let hospitals = this.props.hospitals;
    let {yearList} = this.props;
    if (!yearList) {
      yearList = []
    }
    return (

      <div className="vh">
        <Form onSubmit={this.handleSearch} className="ant-advanced-search-form">
          <Row gutter={24}>
            <Col span={7}>
              <FormItem label="年度" {...formItemLayout}>
                {
                  getFieldDecorator('accYear', {
                    initialValue: '2018'
                  })(
                    <Select allowClear>
                      {yearList.map(item => {
                        return <Option key={item.codeId}>{item.codeName}</Option>
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
                    initialValue: "",
                  })(
                    <Select allowClear>
                      {hospitals.map(item => {
                        return <Option key={item.hospitalCode} value={item.hospitalCode}>{item.hospitalName}</Option>
                      })}
                    </Select>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}>
              <FormItem label="合并科室编码" {...formItem}>
                {
                  getFieldDecorator('mergeCode', {
                    initialValue: "",
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
              <FormItem label="科室名称" {...formItemLayout}>
                {
                  getFieldDecorator('mergeName', {
                    initialValue: "",
                  })(
                    <Input/>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}>

              <FormItem label="是否停用" {...formItemLayout}>
                {
                  getFieldDecorator('isStop', {
                    initialValue: "0"
                  })(
                    <Select>
                      <Option value="0">否</Option>
                      <Option value="1">是</Option>
                    </Select>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}></Col>
            <Col span={3}>
              <Button type="primary" onClick={this.handleSearch}>查询</Button>
            </Col>
          </Row>
        </Form>
      </div>
    )
  }
}


export const Header = Form.create()(FormSearch);

import React from 'react';
import ReactDOM from 'react-dom';
import {Form, Select, Row, Col, Input, Button, Icon} from 'antd';
import  processor from './leaderFormProcessor';

const FormItem = Form.Item;
const Option = Select.Option;
const quarterData = ['0', '1', '2', '3', '4'];
const monthData = {
  0: ['0'],
  1: ['0', '1', '2', '3'],
  2: ['0', '4', '5', '6'],
  3: ['0', '7', '8', '9'],
  4: ['0', '10', '11', '12'],
};
class LeaderFormViewer extends React.Component {
  constructor(props) {
    super(props);

  }

  state = {
    quarter: monthData[quarterData[0]],
    month: monthData[quarterData[0]][0],
    squarter: '0',
    smonth: '0',
  }
  handleQuarterChange = (value) => {

    this.setState({
      quarter: monthData[value],
      month: monthData[value][0],
    });
    this.props.form.setFieldsValue({
      fquarter: this.state.quarter,
      fmonth: this.state.smonth,
    });
  }
  onMonthChange = (value) => {
    this.setState({
      month: value,
    });
    this.props.form.setFieldsValue({

      fmonth: this.state.smonth,
    });
  }

  handleAjax = (e, fieldsValue) => {

    const pro = new processor();
    pro.handleAjax(this.props);
  }

  handleReset = () => {
    this.setState({
      quarter: monthData[0],
    });
    this.props.form.setFieldsValue({
      org: '',
      year: '',
      fquarter: '0',
      fmonth: '0',
    });

  }

  componentDidMount() {
    this.handleAjax();
  };

  render() {
    const {getFieldDecorator} = this.props.form;
    const formItemLayout = {
      labelCol: {span: 12},
      wrapperCol: {span: 10},
    };
    var date = new Date();
    var year = date.getFullYear();
    const childrenYear = [];
    for (let i = 0; i < 10; i++) {
      childrenYear.push(
        <Option value={`${year - i}`} key={`${year - i}`}>{year - i}</Option>
      );
    }

    const quarterOptions = quarterData.map(quarter => {
        let ss = quarter === '0' ? '请选择' : quarter;

        return (
          <Option key={quarter}>{ss}</Option>
        )
      }
    );
    const monthOptions = this.state.quarter.map(month => {
        let ss = month === '0' ? '请选择' : month;
        return (
          <Option key={month}>{ss}</Option>
        )
      }
    );

    return (
      <Form layout="inline" onSubmit={this.handleAjax}>
        <FormItem {...formItemLayout} label={`组织`}>
          {getFieldDecorator(`org`, {
            rules: [
              {required: true, message: '组织!'},
            ], initialValue: "三角集团公司",
          })(
            <Input size="default" placeholder="组织" style={{width: 100}}/>
          )}
        </FormItem>

        <FormItem {...formItemLayout} label="年份">
          {getFieldDecorator('year', {
            rules: [
              {required: true, message: '年份!'},
            ], initialValue: new Date().getFullYear(),
          })(
            <Select size="default" style={{width: 90}} placeholder="年份">
              {childrenYear}
            </Select>
          )}
        </FormItem>

        <FormItem {...formItemLayout} label="季度">
          {getFieldDecorator('fquarter', {
            initialValue: this.state.squarter,
          })(
            <Select size="default" style={{width: 80}} onChange={this.handleQuarterChange}>
              {quarterOptions}
            </Select>
          )}
        </FormItem>

        <FormItem {...formItemLayout} label="月度">
          {getFieldDecorator('fmonth', {
            initialValue: this.state.smonth,
          })(
            <Select size="default" style={{width: 80}} onChange={this.onMonthChange}>
              {monthOptions}
            </Select>
          )}
        </FormItem>

        <FormItem>
          <Button size="default" style={{marginLeft: 28}} type="primary" htmlType="submit">查询</Button>
        </FormItem>

        <FormItem>
          <Button size="default" style={{marginLeft: 8}} onClick={this.handleReset}>
            清空
          </Button>

        </FormItem>

      </Form>
    );

  }//render
}//LdQuery

export default Form.create()(LeaderFormViewer);



import React, {Component} from 'react';
import { Form, Input, Tooltip, Icon, Cascader, Select, Row, Col, Checkbox, Button,Modal,DatePicker,Radio,Slider } from 'antd';
import moment from 'moment';
const RadioGroup = Radio.Group;

const FormItem = Form.Item;
const Option = Select.Option;

const dateFormat = 'YYYY-MM-DD';


const residences = [{
  value: 'zhejiang',
  label: '浙江',
  children: [{
    value: 'hangzhou',
    label: '杭州',
    children: [{
      value: 'xihu',
      label: '西湖',
    }],
  }],
}, {
  value: 'jiangsu',
  label: '江苏',
  children: [{
    value: 'nanjing',
    label: '南京',
    children: [{
      value: 'zhonghuamen',
      label: '正华门',
    }],
  }],
}];

/**
 * 是否可以提交
 */
function checkformSubmit(form){
    return form.getFieldValue("agreement") ? false: true;
}

class RegistrationForm extends React.Component {

  
  state = {
    confirmDirty: false,
    captchaUrl:'/api/commonProcessor/captcha?v='+new Date(),
    model:{
        sex:1,
        slider:0
    },
    visible:false
  };

  /**
   * 弹出对话框
   */
  showModal = (values) => {
    this.setState({
      visible: true,
      model:values
    });
  }

  /**
  *点击对话框确认按钮 
  */
  handleOk = (e) => {
    console.log(e);
    this.setState({
      visible: false,
    });
  }

  /**
   * 点击对话框取消按钮
   */
  handleCancel = (e) => {
    console.log(e);
    this.setState({
      visible: false,
    });
  }

  /**
   * 点击注册提交表单
   */
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFieldsAndScroll((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
        this.showModal(values);
      }
    });
  }


  handleConfirmBlur = (e) => {
    const value = e.target.value;
    this.setState({ confirmDirty: this.state.confirmDirty || !!value });
  }

  /**
   * 验证两次密码是否一致
   */
  checkPassword = (rule, value, callback) => {
    const form = this.props.form;
    if (value && value !== form.getFieldValue('password')) {
      callback('两次密码输入不一致!');
    } else {
      callback();
    }
  }

  checkConfirm = (rule, value, callback) => {
    const form = this.props.form;
    if (value && this.state.confirmDirty) {
      form.validateFields(['confirm'], { force: true });
    }
    callback();
  }

 /**
  * 重置表单
  */
  formReset = (e) =>{
       this.props.form.resetFields();
  }

  /**
   * 设置表单值
   */
  setFormData = (e) =>{
      this.props.form.setFieldsValue({
            email:'demo@viewhigh.com',
            password:'demo',
            confirm:'demo',
            nickname:'demo',
            phone:'18600000000',
            birthday:moment('2017-06-30',dateFormat),
            captcha:'test'
      });
  }

  /**
   * 切换验证码
   */
  changeCaptcha = (e) =>{
      this.setState({captchaUrl:'/api/commonProcessor/captcha?v='+new Date()+Math.round(200)});
  }

  render() {
    const { getFieldDecorator,getFieldValue } = this.props.form;

    const formItemLayout = {
      labelCol: {
        xs: { span: 24 },
        sm: { span: 6 },
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 14 },
      },
    };
    const tailFormItemLayout = {
      wrapperCol: {
        xs: {
          span: 24,
          offset: 0,
        },
        sm: {
          span: 14,
          offset: 6,
        },
      },
    };
    const prefixSelector = getFieldDecorator('prefix', {
        initialValue: '86',
    })(
      <Select className="icp-selector">
        <Option value="86">+86</Option>
      </Select>
    );
    return <div>
       <Modal title="提示信息" visible={this.state.visible}
          onOk={this.handleOk} onCancel={this.handleCancel}
        >
         { JSON.stringify(this.state.model) }
      </Modal>
      <Form onSubmit={this.handleSubmit}>
        <FormItem
          {...formItemLayout}
          label="邮箱"
          hasFeedback
        >
          {getFieldDecorator('email', {
            rules: [{
              type: 'email', message: '请输入正确的邮箱地址!',
            }, {
              required: true, message: '请输入邮箱地址!',
            }],
          })(
            <Input  />
          )}
        </FormItem>
        <FormItem
          {...formItemLayout}
          label="密码"
          hasFeedback
        >
          {getFieldDecorator('password', {
            rules: [{
              required: true, message: '请输入密码!',
            }, {
              validator: this.checkConfirm,
            }],
          })(
            <Input type="password" />
          )}
        </FormItem>
        <FormItem
          {...formItemLayout}
          label="确认密码"
          hasFeedback
        >
          {getFieldDecorator('confirm', {
            rules: [{
              required: true, message: '请输入确认密码!',
            }, {
              validator: this.checkPassword,
            }],
          })(
            <Input type="password" onBlur={this.handleConfirmBlur} />
          )}
        </FormItem>
        <FormItem
          {...formItemLayout}
          label={(
            <span>
              昵称&nbsp;
              <Tooltip title="你想叫别人怎么称呼你?">
                <Icon type="question-circle-o" />
              </Tooltip>
            </span>
          )}
          hasFeedback
        >
          {getFieldDecorator('nickname', {
            rules: [{ required: true, message: '请输入昵称!' }],
          })(
            <Input />
          )}
        </FormItem>
        <FormItem
          {...formItemLayout}
          label="居住地址"
        >
          {getFieldDecorator('residence', {
            initialValue: ['zhejiang', 'hangzhou', 'xihu'],
            rules: [{ type: 'array', required: true, message: '请选择居住地址!' }],
          })(
            <Cascader options={residences} />
          )}
        </FormItem>
        <FormItem
          {...formItemLayout}
          label="电话号码"
        >
          {getFieldDecorator('phone', {
            rules: [{ required: true, message: '请输入电话号码!' }],
          })(
            <Input addonBefore={prefixSelector} />
          )}
        </FormItem>
        <FormItem
          {...formItemLayout}
          label="出生日期"
          hasFeedback
        >
          {getFieldDecorator('birthday', {
            rules: [{ type: 'object', required: true, message: '请选择出生日期!' }]
          })(
             <DatePicker format={dateFormat} />
          )}
        </FormItem>
        <FormItem
          {...formItemLayout}
          label="性别"
        >
          {getFieldDecorator('sex', {
            initialValue:this.state.model.sex
          })(
            <RadioGroup >
                <Radio value={0}>女</Radio>
                <Radio value={1}>男</Radio>
            </RadioGroup>
          )}
        </FormItem>
        <FormItem
          {...formItemLayout}
          label="级别"
        >
          {getFieldDecorator('slider',{
              initialValue:this.state.model.slider
          })(
            <Slider marks={{ 0: 'A', 20: 'B', 40: 'C', 60: 'D', 80: 'E', 100: 'F' }} />
          )}
        </FormItem>
        <FormItem
          {...formItemLayout}
          label="验证码"
        >
          <Row gutter={8}>
            <Col span={8}>
              {getFieldDecorator('captcha', {
                rules: [{ required: true, message: '请输入验证码!' }],
              })(
                <Input size="large" style={{left:-12}} />
              )}
            </Col>
            <Col span={8}>
              <img src={this.state.captchaUrl} title='点击图片切换' onClick={this.changeCaptcha} style={{height:'32px'}} />
            </Col>
          </Row>
        </FormItem>
        <FormItem {...tailFormItemLayout} style={{ marginBottom: 8 }}>
          {getFieldDecorator('agreement', {
            valuePropName: 'checked',
          })(
            <Checkbox>我同意 <a>隐私权政策</a></Checkbox>
          )}
        </FormItem>
        <FormItem {...tailFormItemLayout}>
            <Row gutter={8} justify="center" >
                <Col span={8} push={4}>
                    <Button type="primary" htmlType="submit" disabled={checkformSubmit(this.props.form)} size="large">注册</Button>   
                </Col>
                <Col span={8}  >
                    <Button size="large" onClick={this.formReset} >重置</Button>
                </Col>
                  <Col span={8} pull={4}  >
                    <Button size="large" onClick={this.setFormData} >设置值</Button>
                </Col>
            </Row>
        </FormItem>
      </Form>
    </div>
  }
}



export default Form.create()(RegistrationForm);
/**
 * Created by hp on 2017/6/15.
 */
import React from 'react';
import { Router } from 'react-router';
import { Form,Input, Button, Icon } from 'antd';
import 'antd/dist/antd.css';
import  processor from './userFormProcessor';
const FormItem = Form.Item;
const formData=[];
class UserFormViewer extends React.Component {
  state = {
    formData,
  };
  handleSubmit = (e,fieldsValue) => {
    const  pro=new processor();
    pro.handleSubmit(this.props);
  }
  render()
  {
    const { getFieldDecorator } = this.props.form;
    const condition=this.props.condition;
    const name=condition!=null?condition.getRowSet()["primary"][0]['name']:'';
    const status=condition!=null?condition.getRowSet()["primary"][0]['status']:'';
    return(
      <Form layout="inline" onSubmit={ this.handleSubmit} >
        <FormItem label="姓名">
          {getFieldDecorator('name',{initialValue: name})(
            <Input placeholder="name"  />
          )}
        </FormItem>
        <FormItem label="状态" >
          {getFieldDecorator('status',{initialValue: status})(
            <Input placeholder="status" />
          )}
        </FormItem>
        <FormItem>
          <Button  type="primary"  htmlType="submit">查询</Button>
        </FormItem>
      </Form>
    );
  }
}
export default UserFormViewer;

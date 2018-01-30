/**
 * Created by admin on 2017/6/30.
 */
import React from 'react';
import {Input,Button,notification,Modal,Radio,Form,Icon} from 'antd';
import {connect} from 'react-redux';
import {changeModalInfo} from 'routes/demo/models/actions/demoAction';
const RadioGroup = Radio.Group;
const FormItem = Form.Item;
class modalView extends React.Component {
  constructor(props){
    super(props);
   };
  state ={
      name:"",
      password:"",
      loading: false,
      visible:false,
       sex:""
     }
     showModal=()=>{
        this.setState({
          visible:true,
        });
     }

  handleOk = (e) => {
    this.setState({ loading: true });
      const person={
       name:this.props.form.getFieldsValue().name,
       password:this.props.form.getFieldsValue().password,
       sex:this.props.form.getFieldsValue().sex
     }
      const dispatch=this.props.dispatch;
      dispatch(changeModalInfo(person));
    setTimeout(() => {
      this.setState({ loading: false, visible: false});
    }, 1000);
  }
  componentWillReceiveProps=(nextProps)=>{
       this.setState({
         name:nextProps.name,
         password:nextProps.password,
         sex:nextProps.sex
       });
   }
  test=(e)=> {
      notification.open({
      description:"这里是修改后的信息 性别：" + this.state.sex + "姓名：" + this.state.name + "密码: " + this.state.password,
      icon: <Icon type="smile-circle" style={{ color: '#108ee9' }} />,
      duration:2.5,
      style:{
        marginTop:90
      }
    });
  }
  handleCancel = (e) => {
    console.log(e);
    this.setState({
      visible: false,
    });
  }
  onChange=(e)=>{
     this.setState({
       value:e.target.value
     })
  }
  render() {
    console.log(this.props)
    const {sex, visible, loading,name,password } = this.state;
    const { getFieldDecorator} = this.props.form;
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
    return(<div>
          <Button type="primary" onClick={this.showModal}>打开modal</Button>
          <Modal
            title="modal用例"
            visible={visible}
            onOk={this.handleOk}
            onCancel={this.handleCancel}
            closable="true"
            footer={[
              <Button key="取消" size="large" htmlType="submit">取消</Button>,
              <Button key="提交" type="primary" size="large" loading={loading} onClick={this.handleOk}>
                提交
              </Button>,
            ]}
          >
            <Form onSubmit={this.handleOk}>
              <FormItem label="name:" {...formItemLayout} hasFeedback>
                {getFieldDecorator('name',{
                  rules: [ {
                    required: true, message: 'Please input your name!',
                  }],
                  initialValue:name
                },)(
                  <Input    style={{ width: '50%', marginRight: '3%' }} placeholder=" input your name" />
                )}
              </FormItem>
              <FormItem label="password:" {...formItemLayout} hasFeedback>
                {getFieldDecorator('password', {
                  rules: [{
                    required: true, message: 'input your password!',
                  }, {
                    validator: this.checkConfirm,
                  }],
                  initialValue:password
                })(
                  <Input  style={{ width: '50%', marginRight: '3%' }} placeholder="input your password" />)}
              </FormItem>
              <FormItem label="sex" {...formItemLayout} hasFeedback>
                {getFieldDecorator('sex',{
                  initialValue:sex
                })(
                  <RadioGroup>
                    <Radio value={"a"}>男</Radio>
                    <Radio value={"b"}>女</Radio>
                  </RadioGroup>
                 )}
              </FormItem>
            </Form>
          </Modal>
           <Button onClick={this.test}>点我</Button>
         </div>)
  }
}
const modalForm = Form.create()(modalView);
const mapStateToProps = (state, ownProps) =>({
  name:state.modalViewer.name,
  password:state.modalViewer.password,
  sex:state.modalViewer.sex
});
export default connect(
  mapStateToProps
)(modalForm);

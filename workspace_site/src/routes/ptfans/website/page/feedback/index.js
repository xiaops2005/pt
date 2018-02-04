import React from 'react';
import CommonHeader from '../components/CommonHeader';
import CommonFooter from '../components/CommonFooter';
// import Body from './components/Body';
import { Layout, Input , Form, Button, Modal } from 'antd';
import {MovieMgrService} from "../../process/MovieMgrService";
const { Header, Content, Footer } = Layout;
const { TextArea } = Input;
const processor = new MovieMgrService()
const FormItem = Form.Item;
class FeedBack extends React.Component {
  constructor(props) {
    super(props);
  }

  componentWillMount() {

  }

  handleSubmit = (e) => {
    e.preventDefault();
    console.log(e)
    this.props.form.validateFields((err, fieldsValue) => {
      if (!err) {
        let content = this.props.form.getFieldValue('content')
        processor.postFeedback(content,(result)=>{
          if(result.header.code === 1){
            Modal.success({
              title: '提交成功',
              content: '感谢你的反馈，我们会努力做到最好！',
            });
          }
        })
      }
    })
  }

  render() {
    const {getFieldDecorator} = this.props.form;
    return (
    <Layout style={{ width: 1024, margin: '0 auto'}}>
      <CommonHeader {...this.props} current="feedback"/>
      <Content style={{ padding: '1'}}>
        <Form onSubmit={this.handleSubmit}>
          <FormItem>
            {getFieldDecorator('content', {
              rules: [{
                required: true, message: '请输入反馈意见!',
              }],
            })(
              <TextArea rows={4} placeholder="您的意见是我们最大的动力，感谢您的支持！"/>
            )}
          </FormItem>
          <Button type="primary" htmlType="submit" style={{width: 1024}}>
            发送
          </Button>
        </Form>
      </Content>
      <CommonFooter/>
    </Layout>
    )
  }
}
export default Form.create()(FeedBack);



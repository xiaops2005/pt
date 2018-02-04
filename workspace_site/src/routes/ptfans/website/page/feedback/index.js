import React from 'react';
import CommonHeader from '../components/CommonHeader';
import CommonFooter from '../components/CommonFooter';
// import Body from './components/Body';
import { Layout, Input , Form, Button } from 'antd';
const { Header, Content, Footer } = Layout;
const { TextArea } = Input;
class FeedBack extends React.Component {
  constructor(props) {
    super(props);
  }

  componentWillMount() {

  }

  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, fieldsValue) => {
      if (err) {
        return;
      }
    })
  }

  render() {
    return (
    <Layout style={{ width: 1024, margin: '0 auto'}}>
      <CommonHeader {...this.props} current="feedback"/>
      <Content style={{ padding: '1'}}>
        <Form onSubmit={this.handleSubmit}>
          <TextArea rows={4} placeholder="您的意见是我们最大的动力，感谢您的支持！"/>
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



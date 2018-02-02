/**
 * Created by xiaops on 2018/1/16.
 */
import React from 'react';
import {connect} from 'react-redux';
import CommonHeader from '../components/CommonHeader';
import CommonFooter from '../components/CommonFooter';
// import Body from './components/Body';
import { Layout, Menu, Breadcrumb, Icon } from 'antd';
// import './index.css'
const { Header, Content, Footer } = Layout;

class HomeTheater extends React.Component {
  constructor(props) {
    super(props);
  }

  componentWillMount() {

  }

  render() {
    return (
    <Layout className="layout">
      <CommonHeader current="feedback"/>
      <Content style={{ width: 1024, padding: '1px', margin: '0 auto'}}>
        意见反馈
      </Content>
      <CommonFooter/>
    </Layout>
    )
  }
}
export default HomeTheater



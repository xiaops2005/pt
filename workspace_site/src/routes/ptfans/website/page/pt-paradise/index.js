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

class PtParadise extends React.Component {
  constructor(props) {
    super(props);
  }

  componentWillMount() {

  }

  render() {
    return (
    <Layout style={{ width: 1024, margin: '0 auto'}}>
      <CommonHeader current="pt-paradise"/>
      <Content style={{ padding: '1px' }}>
        pt乐园
      </Content>
      <CommonFooter/>
    </Layout>
    )
  }
}
export default PtParadise



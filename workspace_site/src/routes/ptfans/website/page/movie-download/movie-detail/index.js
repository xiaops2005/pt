/**
 * Created by xiaops on 2018/1/16.
 */
import React from 'react';
import {connect} from 'react-redux';
import CommonHeader from '../../components/CommonHeader';
import CommonFooter from '../../components/CommonFooter';
import MovieDetail from './components/MovieDetail';
import { Card, Layout, Menu, Breadcrumb, Icon } from 'antd';
const { Header, Content, Footer } = Layout;

class MovieDownload extends React.Component {
  constructor(props) {
    super(props);
  }

  componentWillMount() {
    console.log('参数id:' + this.props.match.params.id)
  }

  render() {
    return (
      <Layout className="layout">
        <CommonHeader current="1"/>
        <Content style={{ padding: '1px' }}>
          <MovieDetail/>
        </Content>
        <CommonFooter/>
      </Layout>
    )
  }
}
export default MovieDownload



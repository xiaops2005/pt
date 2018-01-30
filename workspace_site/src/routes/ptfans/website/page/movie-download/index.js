/**
 * Created by xiaops on 2018/1/16.
 */
import React from 'react';
import {connect} from 'react-redux';
import CommonHeader from '../components/CommonHeader';
import CommonFooter from '../components/CommonFooter';
import MovieCard from './components/MovieCard';
import { Button, BackTop, Card, Layout, Menu, Breadcrumb, Icon } from 'antd';
import './index.css'
const { Header, Content, Footer } = Layout;

class MovieDownload extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      loading: false,
      movieList : [{
        img:'http://wx2.sinaimg.cn/large/ed5e6a1dly1fnjowb2wr6j20go0jddgu.jpg',
        name:'2017年 这个男人来自地球：全新纪 [这个男人来自地球的续集]',
        id:'alt1'
      },{
        img:'https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png',
        name:'name2',
        id:'alt2'
      },{
        img:'http://wx3.sinaimg.cn/large/ed5e6a1dly1floqqggx6xj20go0o8jsp.jpg',
        name:'name3',
        id:'alt3'
      },{
        img:'http://wx4.sinaimg.cn/large/ed5e6a1dly1fnabhn5w3vj20go0ngq4y.jpg',
        name:'name4',
        id:'alt4'
      },{
        img:'http://wx4.sinaimg.cn/large/ed5e6a1dly1fmtuflrfg1j20go0p8mz3.jpg',
        name:'name5',
        id:'alt5'
      }]
    }
  }

  enterLoading = () => {
    this.setState({ loading: true });

    setTimeout(function(that){
      let obj = [{
        img:'http://wx2.sinaimg.cn/large/ed5e6a1dly1fnjowb2wr6j20go0jddgu.jpg',
        name:'2017年 这个男人来自地球：全新纪 [这个男人来自地球的续集]',
        id:'alt1'
      },{
        img:'https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png',
        name:'name2',
        id:'alt2'
      },{
        img:'http://wx3.sinaimg.cn/large/ed5e6a1dly1floqqggx6xj20go0o8jsp.jpg',
        name:'name3',
        id:'alt3'
      },{
        img:'http://wx4.sinaimg.cn/large/ed5e6a1dly1fnabhn5w3vj20go0ngq4y.jpg',
        name:'name4',
        id:'alt4'
      },{
        img:'http://wx4.sinaimg.cn/large/ed5e6a1dly1fmtuflrfg1j20go0p8mz3.jpg',
        name:'name5',
        id:'alt5'
      }]
      let movieList = that.state.movieList;
      movieList.push.apply(movieList,obj);
      that.setState(movieList)
      that.setState({ loading: false });
    },1000,this)
    // this.setState({
    //   movieList :
    // })
  }

  componentWillMount() {

  }

  render() {
    return (
      <Layout className="layout">
        <CommonHeader current="1"/>
        <Content style={{ padding: '1px' }}>
          <MovieCard movieList={this.state.movieList}/>
          <Button type="primary" loading={this.state.loading} onClick={this.enterLoading} style={{width:'100%'}}>
            下一页
          </Button>
          <BackTop/>
        </Content>
        <CommonFooter/>
      </Layout>
    )
  }
}
export default MovieDownload



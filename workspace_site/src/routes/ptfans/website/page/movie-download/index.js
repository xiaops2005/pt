import React from 'react';
import {connect} from 'react-redux';
import CommonHeader from '../components/CommonHeader';
import CommonFooter from '../components/CommonFooter';
import MovieCard from './components/MovieCard';
import { Button, BackTop, Card, Layout, message, Breadcrumb, Icon } from 'antd';
import {MovieMgrService} from "../../process/MovieMgrService";
import './index.css'
const { Header, Content, Footer } = Layout;
const processor = new MovieMgrService()

class MovieDownload extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      pageNum: 1,
      loading: false,
      movieList : []
    }
  }

  enterLoading = () => {
    this.setState({ loading: true });
    this.queryPublishMovieList()
  }

  componentWillMount() {
    this.queryPublishMovieList();
  }

  queryPublishMovieList = () => {
    processor.queryPublishMovieList(this.state.pageNum, (result) => {
      if (result.header.code === 1) {
        if(result.getSinglePrimary().length > 0) {
          this.setState({
            movieList: result.getSinglePrimary().concat(this.state.movieList),
            loading: false,
            pageNum: this.state.pageNum+1
          })
        }else{
          message.warning("最后一页了")
          this.setState({
            loading: false
          })
        }
      }
    });
  }

  render() {
    console.log("render")
    return (
      <Layout style={{ width: 1024, margin: '0 auto'}}>
        <CommonHeader {...this.props} current="movie-download"/>
        <Content style={{ padding: '1' }}>
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



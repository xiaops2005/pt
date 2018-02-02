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

let pageNum = 1
class MovieDownload extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      loading: false,
      movieList : []
    }
  }

  enterLoading = () => {
    this.setState({ loading: true });
    this.query()
  }

  componentWillMount() {
    this.query();
  }

  query = () => {
    processor.query(pageNum, (result) => {
      pageNum++
      if (result.header.code === 1) {
        if(result.getSinglePrimary().length > 0) {
          this.setState({
            movieList: result.getSinglePrimary().concat(this.state.movieList),
            loading: false
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
      <Layout className="layout">
        <CommonHeader current="movie-download"/>
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



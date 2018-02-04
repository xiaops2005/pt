/**
 * Created by xiaops on 2018/1/16.
 */
import React from 'react';
import {connect} from 'react-redux';
import CommonHeader from '../components/CommonHeader';
import CommonFooter from '../components/CommonFooter';
import {NavLink, Link} from 'react-router-dom';
import {Card, Layout, Menu, Icon, message, Button, Divider} from 'antd';
import {MovieMgrService} from "../../process/MovieMgrService";
const {Header, Content, Footer, Sider } = Layout;
const processor = new MovieMgrService()

var pageNum = 1;
class MovieSearch extends React.Component {
  constructor(props) {
    console.log("constructor")
    super(props);
    this.state = {
      movieList: [],
      // pageNum: 1,
      loading: false,
    }
  }

  componentWillMount() {
    console.log('componentWillMount')
    if (this.props.location.state != undefined) {
      let {movieList} = this.props.location.state
      if (movieList != undefined) {
        this.setState({
          movieList: this.props.location.state.movieList,
          // pageNum: 2,
        })
        pageNum = 1
      }
    } else {
      let {keyword} = this.props.match.params
      if (keyword != null && keyword != undefined) {
        this.searchMovie(keyword)
      }
    }
  }

  componentWillUpdate(nextProps, nextState){
      console.log('componentWillUpdate',nextProps,nextState)
    // this.setState({
    //   movieList:nextState.movieList
    // })
  }

  componentWillReceiveProps(nextProps) {
    debugger
    console.log('componentWillReceiveProps', nextProps)
    if (nextProps.location.state != undefined) {
      let {movieList} = nextProps.location.state
      this.setState({
        movieList: movieList,
        // pageNum: 2,
      })
      pageNum = 1
    } else {
      let {keyword} = this.props.match.params
      if (keyword != null && keyword != undefined) {
        this.searchMovie(keyword)
      }
    }
  }

  searchMovie = (keyword,pageNum=1) => {
    // let {pageNum} = this.state
    processor.searchMovie(keyword,pageNum, (result) => {
      if (result.header.code === 1) {
        if(result.getSinglePrimary().length > 0) {
          let list = [];
          if(pageNum>1){
            list = result.getSinglePrimary().concat(this.state.movieList)
          }else{
            list = result.getSinglePrimary()
          }
          this.setState({
            movieList: list,
            loading: false,
            // pageNum: this.state.pageNum+1
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

  enterLoading = () => {
    this.setState({ loading: true });
    let {keyword} = this.props.match.params
    this.searchMovie(keyword,++pageNum)
  }

  render() {
    console.log("search render")
    const {movieList} = this.state;
    return (
      <Layout style={{ width: 1024, margin: '0 auto'}}>
        <CommonHeader {...this.props} current="movie-search"/>
        <Content style={{ padding: 10}}>
          {movieList.map((movie) => {
            let {id, title, originalTitle, directors, casts, genres, countries, akas, smallImage} = movie
            const href = '/ptfans/movie-detail/' + id
            return (
              <div>
                <Layout>
                  <Sider style={{background: '#fff'}} width="270">
                    <NavLink to={href} target="_blank">
                    <picture>
                      <source width="100%" height="380" type="image/webp" srcSet={smallImage}/>
                      <img width="100%" height="380"  src={smallImage}/>
                    </picture>
                    </NavLink>
                  </Sider>
                  <Content style={{padding: 10}}>
                    <h4>
                      <NavLink to={href} target="_blank">
                        {title}
                      </NavLink>
                    </h4>
                    <div>
                      原名：{originalTitle}
                    </div>
                    <div>
                      导演：{directors}
                    </div>
                    <div>
                      主演：{casts}
                    </div>
                    <div>
                      类型：{genres}
                    </div>
                    <div>
                      国家：{countries}
                    </div>
                    <div>
                      又名：{akas}
                    </div>
                  </Content>
                </Layout>
                <Divider />
              </div>
            )})
          }
          <Button type="primary" loading={this.state.loading} onClick={this.enterLoading} style={{width:'100%'}}>
            下一页
          </Button>
        </Content>
        <CommonFooter/>
      </Layout>
    )
  }
}
export default MovieSearch



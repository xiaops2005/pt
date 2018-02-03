/**
 * Created by xiaops on 2018/1/16.
 */
import React from 'react';
import {connect} from 'react-redux';
import CommonHeader from '../components/CommonHeader';
import CommonFooter from '../components/CommonFooter';
import {NavLink} from 'react-router-dom';
import {Card, Layout, Menu, Icon, message, Button, Divider} from 'antd';
import {MovieMgrService} from "../../process/MovieMgrService";
const {Header, Content, Footer, Sider } = Layout;
const processor = new MovieMgrService()
class MovieSearch extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      movieList: [],
      pageNum: 1,
      loading: false,
      // keyword: this.props.match.params.keyword
    }
  }

  componentWillMount() {
    console.log('参数keyword:' + this.props.match.params.keyword)
    let {keyword} = this.props.match.params
    this.searchMovie(keyword)
  }

  searchMovie = (keyword) => {
    let {pageNum} = this.state
    processor.searchMovie(keyword,pageNum, (result) => {
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

  enterLoading = () => {
    this.setState({ loading: true });
    let {keyword} = this.props.match.params
    this.searchMovie(keyword)
  }

  render() {
    const {movieList} = this.state;
    return (
      <Layout style={{ width: 1024, margin: '0 auto'}}>
        <CommonHeader current="movie-search"/>
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
                    <NavLink to={href} target="_blank">
                      <h4>{title}</h4>
                    </NavLink>
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



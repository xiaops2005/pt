/**
 * Created by xiaops on 2018/1/16.
 */
import React from 'react';
import {connect} from 'react-redux';
import CommonHeader from '../components/CommonHeader';
import CommonFooter from '../components/CommonFooter';
import {Card, Layout, Menu, Breadcrumb, Icon} from 'antd';
import {MovieMgrService} from "../../process/MovieMgrService";
const {Header, Content, Footer} = Layout;
const processor = new MovieMgrService()
class MovieDownload extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      movieInfo: {},
      torrentList: [],
      subtitleList: []
    }
  }

  componentWillMount() {
    console.log('参数id:' + this.props.match.params.id)
    let id = this.props.match.params.id
    this.queryById(id)
    this.queryTorrents(id)
    // this.querySubtitleList(id)
  }

  createMarkup = () => {
    return {__html: this.state.movieInfo.summary};
  }

  queryById = (id) => {
    processor.queryById(id, (result) => {
      if (result.header.code === 1) {
        this.setState({movieInfo: result.getSinglePrimaryList()[0]})
      }
    });
  }

  queryTorrents = (id) => {
    processor.queryTorrents(id, (result) => {
      if (result.header.code === 1) {
        debugger
        this.setState({torrentList: result.getSinglePrimaryList()})
      }
    });
  }

  // querySubtitleList = (id) =>{
  //   processor.queryById(id, (result) => {
  //     if(result.header.code == 1){
  //       this.setState({movieInfo:result.getSinglePrimaryList()[0]})
  //     }
  //   });
  // }

  render() {
    const {year, title, originalTitle, smallImage, directors, casts, genres, countries, akas} = this.state.movieInfo;
    const {torrentList} = this.state
    const name = title===originalTitle?title:title+' '+originalTitle
    const titleEx = year + ' ' + name
    return (
      <Layout style={{ width: 1024, margin: '0 auto'}}>
        <CommonHeader {...this.props} current="movie-download"/>
        <Content style={{ padding: 10}}>
          <h4>{titleEx}</h4>
          <picture class="picture">
            <source type="image/webp" srcset={smallImage}/>
            <img class="image" src={smallImage}/>
          </picture>
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
          <p>
            <div dangerouslySetInnerHTML={this.createMarkup()}/>
          </p>
          {torrentList.map((torrent) => {
            return (
              <p>
                <div>
                清晰度:{torrent.definition}  大小:{torrent.size}
                </div>
                <a href={torrent.magnet} target="_blank">{torrent.name}</a>
              </p>
            )
          })
          }
        </Content>
        <CommonFooter/>
      </Layout>
    )
  }
}
export default MovieDownload



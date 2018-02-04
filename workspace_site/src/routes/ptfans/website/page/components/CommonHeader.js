import React from 'react';
import {Menu, Icon, Layout,Input, Select} from 'antd';
import {NavLink} from 'react-router-dom';
import {browserHistory} from 'React-router'
import {MovieMgrService} from "../../process/MovieMgrService";
import './index.css';
const processor = new MovieMgrService()
const {Header} = Layout;
const Search = Input.Search;
const Option = Select.Option;
class CommonHeader extends React.Component {
  constructor(props) {
    super(props);
    console.log('当前为第' + this.props.current + '个页签')
    this.state = {
      current: this.props.current,
    }
  }

  handleClick = (e) => {
    this.setState({
      current: e.key
    })
  }

  search = (keyword) =>{
    this.searchMovie(keyword)
  }

  searchMovie = (keyword) => {
    processor.searchMovie(keyword,1, (result) => {
      if (result.header.code === 1) {
        let url = '/ptfans/movie-search/' + keyword
        this.props.history.push({
          pathname:url,
          state:{movieList:result.getSinglePrimary()}
        })

        // if(result.getSinglePrimary().length > 0) {
        //   this.setState({
        //     movieList: result.getSinglePrimary().concat(this.state.movieList),
        //     loading: false,
        //     pageNum: this.state.pageNum+1
        //   })
        // }else{
        //   message.warning("最后一页了")
        //   this.setState({
        //     loading: false
        //   })
        // }
      }
    });
  }

  render() {
    // const searchType = (
    //   <Select defaultValue="电影" style={{ width: 80 }}>
    //     <Option value="1">电影</Option>
    //     <Option value="2">游戏</Option>
    //   </Select>
    // );
    return (
      <Header style={{background:'#fff',padding:'0 0 0 10px'}}>
        <div className="logo">
          <img src="/ptfans/image/logo.png" style={{width:120,height:48}}/>
        </div>
        <Menu
          theme='light'
          onClick={this.handleClick}
          mode="horizontal"
          selectedKeys={[this.state.current]}
          style={{lineHeight: '64px'}}
        >
          <Menu.Item key="movie-download">
            <NavLink to="ptfans/movie-download" target="_self">电影下载</NavLink>
          </Menu.Item>
          {/*<Menu.Item key="game-download">*/}
            {/*<NavLink to="ptfans/game-download" target="_self">游戏下载</NavLink>*/}
          {/*</Menu.Item>*/}
          {/*<Menu.Item key="pt-paradise">*/}
            {/*<NavLink to="ptfans/pt-paradise" target="_self">PT乐园</NavLink>*/}
          {/*</Menu.Item>*/}
          {/*<Menu.Item key="4">*/}
          {/*<NavLink to="home-theater" target="_self">家庭影院</NavLink>*/}
          {/*</Menu.Item>*/}
          <Menu.Item key="feedback">
            <NavLink to="ptfans/feedback" target="_self">意见反馈</NavLink>
          </Menu.Item>
          {/*<span className="ant-divider"/>*/}
            <Search
              style={{ width: 350, marginTop: 17 }}
              // addonBefore={searchType}
              placeholder="请输入名称"
              onSearch={this.search.bind(this)}
              enterButton
            />
        </Menu>
      </Header>
    )
  }
}
export default CommonHeader

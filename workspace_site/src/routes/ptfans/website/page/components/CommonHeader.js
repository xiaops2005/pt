import React from 'react';
import { Menu, Icon, Layout } from 'antd';
import {NavLink} from 'react-router-dom';
const { Header } = Layout;

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
      current:e.key
    })
  }

  render() {
    return (
      <Header>
      <Menu
        // theme='light'
        onClick={this.handleClick}
        mode="horizontal"
        selectedKeys={[this.state.current]}
        style={{ lineHeight: '64px' }}
      >
        <Menu.Item key="1">
          <NavLink to="/movie-download" target="_self">电影下载</NavLink>
          {/*<a href="#/ptfans/movie-download" target="_self" rel="noopener noreferrer">电影下载</a>*/}
        </Menu.Item>
        <Menu.Item key="2">
          <NavLink to="/movie-recommend" target="_self">电影推荐</NavLink>
          {/*<a href="#/ptfans/movie-recommend" target="_self" rel="noopener noreferrer">电影推荐</a>*/}
        </Menu.Item>
        <Menu.Item key="3">
          <NavLink to="pt-paradise" target="_self">PT乐园</NavLink>
          {/*<a href="#/ptfans/pt-paradise" target="_self" rel="noopener noreferrer">PT乐园</a>*/}
        </Menu.Item>
        <Menu.Item key="4">
          <NavLink to="home-theater" target="_self">家庭影院</NavLink>
          {/*<a href="#/ptfans/home-theater" target="_self" rel="noopener noreferrer">家庭影院</a>*/}
        </Menu.Item>
        <Menu.Item key="5">
          <NavLink to="feedback" target="_self">意见反馈</NavLink>
          {/*<a href="#/ptfans/feedback" target="_self" rel="noopener noreferrer">意见反馈</a>*/}
        </Menu.Item>
      </Menu>
      </Header>
    )
  }
}
export default CommonHeader

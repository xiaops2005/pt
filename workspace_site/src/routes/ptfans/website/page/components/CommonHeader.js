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
      <Header style={{ width: 1024, margin: '0 auto'}}>
      <Menu
        theme='light'
        onClick={this.handleClick}
        mode="horizontal"
        selectedKeys={[this.state.current]}
        style={{ lineHeight: '64px', background: '#ffffff'}}
      >
        <Menu.Item key="movie-download">
          <NavLink to="ptfans/movie-download" target="_self">电影下载</NavLink>
        </Menu.Item>
        {/*<Menu.Item key="2">*/}
          {/*<NavLink to="/movie-recommend" target="_self">电影推荐</NavLink>*/}
        {/*</Menu.Item>*/}
        {/*<Menu.Item key="3">*/}
          {/*<NavLink to="pt-paradise" target="_self">PT乐园</NavLink>*/}
        {/*</Menu.Item>*/}
        {/*<Menu.Item key="4">*/}
          {/*<NavLink to="home-theater" target="_self">家庭影院</NavLink>*/}
        {/*</Menu.Item>*/}
        <Menu.Item key="feedback">
          <NavLink to="ptfans/feedback" target="_self">意见反馈</NavLink>
        </Menu.Item>
      </Menu>
      </Header>
    )
  }
}
export default CommonHeader

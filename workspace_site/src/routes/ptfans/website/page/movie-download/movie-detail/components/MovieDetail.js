/**
 * Created by xiaops on 2018/1/17.
 */
import React from 'react';
import { Card, Layout, Menu, Breadcrumb, Icon } from 'antd';

class MovieDetail extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      title : '2017年 这个男人来自地球：全新纪 [这个男人来自地球的续集]',
      content : '<html>123</html>'
    }
  }

  createMarkup = () => {
    return {__html: this.state.content};
  }

  render() {
    const {title, content} = this.state;
    const gridStyle = {
      width: '25%',
      textAlign: 'center',
    };
    return (
      <div>
      <h2>{title}</h2>
      <p><div dangerouslySetInnerHTML={this.createMarkup()} /></p>

      </div>
    )
  }
}
export default MovieDetail

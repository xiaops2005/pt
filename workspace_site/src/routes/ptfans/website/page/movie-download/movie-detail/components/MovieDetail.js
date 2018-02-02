/**
 * Created by xiaops on 2018/1/17.
 */
import React from 'react';
import { Card, Layout, Menu, Breadcrumb, Icon } from 'antd';

class MovieDetail extends React.Component {
  constructor(props) {
    super(props);
  }

  componentWillReceiveProps(nextProps){
    this.setState({...nextProps})
  }

  createMarkup = () => {
    return {__html: this.state.content};
  }

  render() {
    debugger;
    const {title, content} = this.state;
    debugger;
    const gridStyle = {
      width: '25%',
      textAlign: 'center',
    };
    return (
      <div>
        <h4>{title}</h4>
        <p><div dangerouslySetInnerHTML={this.createMarkup()} /></p>
      </div>
    )
  }
}
export default MovieDetail

/**
 * Created by xiaops on 2018/1/17.
 */
import React from 'react';
import { Menu, Icon, Layout } from 'antd';
const { Footer } = Layout;

class CommonFooter extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <Footer style={{ width: 1024,'text-align': 'center'}}>
        Ant Design ©2016 Created by Ant UED
      </Footer>
    )
  }
}
export default CommonFooter

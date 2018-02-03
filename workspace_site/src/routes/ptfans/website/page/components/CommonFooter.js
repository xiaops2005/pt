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
      <Footer style={{'text-align': 'center'}}>
        <div>
          Copyright © 2013-2018 PT爱好者 站长统计
        </div>
        <div>
          声明：本站为资讯类网站，我们不提供任何影视的上传、下载、存储、播放，版权归属原电影制作公司
        </div>
      </Footer>
    )
  }
}
export default CommonFooter

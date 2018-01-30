import React, {Component} from 'react';
import {notification ,Tabs,Icon,Layout,Card,Dropdown,Menu,Col,Row } from 'antd';
import APPCONFIG from 'constants/Config';
const { Header, Footer, Sider, Content} = Layout;
const TabPane = Tabs.TabPane;
const queryJob=function({key}){
  notification.open({
    message: key,
    description: '下达的命令已经处理，请耐心等待后续的信息',
    icon: <Icon type="smile-circle" style={{ color: '#108ee9' }} />,
    duration:2.5,
    style:{
      marginTop:90
     }
  });
}
const menu=(
  <Menu onClick={queryJob}>
    <Menu.Item key="安排加班">
      <a target="_blank" rel="">安排加班</a>
    </Menu.Item>
    <Menu.Item key="安排休假">
      <a target="_blank" rel="" >安排休假</a>
    </Menu.Item>
    <Menu.Item key="安排公出">
      <a target="_blank" rel="">安排公出</a>
    </Menu.Item>
    <Menu.Item key="排班">
      <a target="_blank" rel="" >排班</a>
    </Menu.Item>
    <Menu.Item key="调班">
      <a target="_blank" rel="" >调班</a>
    </Menu.Item>
    <Menu.Item key="休假记录">
      <a target="_blank" rel="" >休假记录</a>
    </Menu.Item>
    <Menu.Item key="假期信息">
      <a target="_blank" rel="" >假期信息</a>
    </Menu.Item>
    <Menu.Item key="公出记录">
      <a target="_blank" rel="" >公出记录</a>
    </Menu.Item>
    <Menu.Item key="加班记录">
      <a target="_blank" rel="" >加班记录</a>
    </Menu.Item>
  </Menu>
);

class EmpolyesTab extends Component{
  getSelectedValue=function(dispatch){
  };
  constructor(props){
    super(props);
    this.state={
      childData:this.props.childData
    }
  };
  render(){
    const childData=this.props.childData
    return(
      <Col  span={8} push={1}>
        <Tabs>
          <TabPane animated="false" style={{border:'1px #F0FFFF'}}>
            <Layout style={{background:'white'}}>
              <Layout style={{background:'white'}}>
                <Sider style={{background:'#F8F8FF',width:'80'}}>
                  <div style={{marginTop:10}}>
                    <div style={{width:'60',height:'60',borderRadius:'50px 50px 50px 50px',overflow:'hidden',border:'1px solid',align:'center'}}><img  src={APPCONFIG.PublicURL+'assets/demo/'+childData.picSrc} style={{width:60,height:60}}></img></div>
                    <div style={{width:'30',height:'20',background:'#00BFFF',borderRadius:'10px',marginLeft:10,marginTop:4}}><Icon type="user"/> 3</div>
                  </div>
                  <Content style={{background:'#F8F8FF',float:'right',marginTop:'-90'}}>
                    <div>
                      <Dropdown overlay={menu} trigger={['click']}>
                        <a style={{pointerEvents:'auto'}} className="ant-dropdown-link">
                          <Icon type="bars" style={{color:'#00BFFF',marginLeft:'110'}}/>
                        </a>
                      </Dropdown>
                      <table style={{marginTop:'-20'}}>
                        <tr><td style={{textAlign:'left'}}><span style={{fontSize: '10px',fontWeight:'bold'}}></span>{childData.name}</td><td></td></tr>
                        <tr><td style={{textAlign:'left'}}><span style={{fontSize: '10px',fontWeight:'bold'}}></span>{childData.position}</td><td></td></tr>
                        <tr><td style={{textAlign:'left'}}><span style={{fontSize: '10px',fontWeight:'bold'}}>司龄</span></td><td></td></tr>
                        <tr><td style={{textAlign:'left'}}><span style={{fontSize: '10px',fontWeight:'bold'}}>职级</span></td><td></td></tr>
                        <tr><td style={{textAlign:'left'}}><span style={{fontSize: '10px',fontWeight:'bold'}}>出差累计:</span></td><td><sapn style={{fontSize: '10px'}}>1天3小时</sapn></td></tr>
                        <tr><td style={{textAlign:'left'}}><span style={{fontSize: '10px',fontWeight:'bold'}}>休假累计:</span></td><td><span style={{fontSize: '10px'}}>2天3小时</span></td></tr>
                      </table>
                    </div>
                  </Content>
                </Sider>
              </Layout>
              <Footer style={{background:'#F8F8FF',width:'200px',overflowY:'hidden',height:'58',paddingLeft:'2px',paddingTop:'3px'}}>
                <div style={{marginTop:'3px',paddingLeft:'20px'}}>
                  <table>
                    <tr>
                      <td style={{border:'1px',background:'#F0F0F0'}}>
                        <div  style={{ width:50,height:40,textAlign:'center'}}>
                          <span>2016</span><br></br>
                          <span>优秀</span>
                        </div>
                      </td>
                      &nbsp;
                      <td style={{border:'1px',background:'#F0F0F0'}}>
                        <div style={{ width:50,height:40,textAlign:'center'}}>
                          <span>2015</span><br></br>
                          <span>良好</span>
                        </div>
                      </td>
                      &nbsp;
                      <td style={{border:'1px',background:'#F0F0F0'}}>
                        <div style={{ width:50 ,height:40,textAlign:'center'}}>
                          <span>2015</span><br></br>
                          <span>良好</span>
                        </div>
                      </td>
                    </tr>
                  </table>
                </div>
              </Footer>
            </Layout>
          </TabPane>
        </Tabs>
      </Col>
    )
  }
}

class EmpolyesRow extends Component{
  constructor(props){
    super(props);
    this.state={
      data:this.props.data,
    }
  };
  render(){
    const data1=this.props.data;
    console.log(data1);
    return(
      <Row style={{pointerEvents:'none'}}>
        {
          data1.map((item)=> {
            return <EmpolyesTab childData={item}/>;
          })
        }
      </Row>
    )
  }
}
export default EmpolyesRow;

import React, {Component} from 'react';
import {notification, Icon, Select,Layout} from 'antd';
import EmpolyesRow from './EmpolyesRow';
import {connect} from 'react-redux';
import APPCONFIG from 'constants/Config';
import {requestData,prePage,afterPage} from './ManagerSelfProcessor';

const {Footer, Sider, Content} = Layout;
class ManagerSelfSer extends Component{
  getInitialState= function() {
    return {liked1:"",liked2:""};
  };
  constructor(props){
    super(props);
    this.state = {
      dropdownOpen: false,
      array:[],
      currentPage:1,
      sumPage:1,
      buttonFlag:1,
      liked1:"#00BFFF",
      initFunction:this.handClick(1),
     }
   };

     handClick=function(a){
        const dispatch = this.props.dispatch;
        this.setState({liked1:"#00BFFF",liked2:"",buttonFlag:a});
         if(a==1){
           requestData(1,dispatch,a);
          }else{
            requestData(1,dispatch,a);
            this.setState({liked1:"",liked2:"#00BFFF"});
        }
        this.setState({currentPage:this.props.currentPage});
    };
  orginInfo=function(){
    notification.open({
      description: '组织信息功能占位',
      icon: <Icon type="smile-circle" style={{ color: '#108ee9' }} />,
      duration:2.5,
      style:{
        marginTop:90
      }
    });
  };
  communctionInfo=function(){
    notification.open({
      description: '通讯信息功能占位',
      icon: <Icon type="smile-circle" style={{ color: '#108ee9' }} />,
      duration:2.5,
      style:{
        marginTop:90
      }
    });
  };
  reLoadInfo=function(){
    notification.open({
      description: '已刷新',
      icon: <Icon type="smile-circle" style={{ color: '#108ee9' }} />,
      duration:2.5,
      style:{
        marginTop:90
      }
    });
  };
  choseQuery=function(value,key){
    notification.open({
      description: value+"已查询",
      icon: <Icon type="smile-circle" style={{ color: '#108ee9' }} />,
      duration:2.5,
      style:{
        marginTop:90
      }
    });
  }

  render(){
    var text=this.state.liked1;
    var text1=this.state.liked2;
    var style1={
      color:text
    };
    var style2={
      color:text1
    }

    return (
         <div>
         <text style={{top: '5px'}}><strong>下属员工信息</strong></text><br/>
         <hr style={{marginTop:'1px',height:'1px',borderColor:'#00BFFF'}}></hr>
         <div style={{marginTop:'2px'}}>
           <span><label onClick={()=>this.handClick(1)} style={style1}>直接下级</label>&nbsp;&nbsp;&nbsp;<label onClick={()=>this.handClick(2)} style={style2}>所有下级</label></span>
           <hr style={{marginTop:'-1px',height:'1px'}}/>
         </div>
          <div style={{marginTop:'5px'}}>
              <span style={{float:'right'}}><a><Icon type="user" onClick={()=>this.orginInfo()}/> 组织</a> | <a><Icon type="contacts" onClick={()=>this.communctionInfo()}/> 通讯</a> | <a><Icon type="retweet" onClick={()=>this.reLoadInfo()}/> 刷新</a> |&nbsp;
                <Select style={{width:80}} defaultValue="奖金分配" onSelect={this.choseQuery}>
                  <option  value="奖金分配">奖金分配</option>
                  <option value="职能工资">职能工资</option>
               </Select>&nbsp;
                <Select style={{width:80}} defaultValue="人才招聘   " onSelect={(value,key)=>{notification.open({
                  description: "暂无"+value,
                  icon: <Icon type="smile-circle" style={{ color: '#108ee9' }} />,
                  duration:2.5,
                  style:{
                    marginTop:90
                  }
                })}} >
                <option value="人才招聘">人才招聘</option>
               </Select>
              </span>
          </div>
           <div style={{height:'40'}}></div>
           <div><span id="flag1"  style={{marginLeft:900,fontSize:'12',fontWeight:'10',display:'none'}}>第 {this.props.currentPage} 页/共 {this.props.sumPage} 页</span></div>
             <div>
               <div>
                 <img  id="flag2"  onClick={()=>prePage(this.props.currentPage,this.props.dispatch,this.state.buttonFlag)} src={APPCONFIG.PublicURL+'assets/demo/managerSelf/leftFlag.png'} style={{marginLeft:'-30',marginBottom:'-730',display:'none'}}/>
                 <div style={{height:'450'}}><EmpolyesRow data={this.props.array}/></div>
                 <div><img id="flag3"   onClick={()=>afterPage(this.props.currentPage,this.props.dispatch,this.props.sumPage,this.state.buttonFlag)} src={APPCONFIG.PublicURL+'assets/demo/managerSelf/rightFlag.png'} style={{marginTop:'-197',marginLeft:'1000',display:'none',position:'relative'}}/></div>
               </div>
           </div>
</div>)
  }
}
const mapStateToProps = (state, ownProps) =>({
  array: state.managerSelfSer.array,
  currentPage:state.managerSelfSer.currentPage,
  sumPage:state.managerSelfSer.sumPage
});
export default connect(
  mapStateToProps
)(ManagerSelfSer);



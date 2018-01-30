/**
 * Created by Bojc on 2017/6/15.
 */
import React from 'react';
import { Tree,Radio,Icon,notification } from 'antd';
import UserTable from './UserTable';
const RadioGroup =Radio.Group;

const TreeNode = Tree.TreeNode;
const divStyle = {
  padding: 0
};
const divLeftStyle = {
  float: 'left',
  width: '22%'
};
const divRightStyle = {
  float: 'right',
  width: '78%'
};
class TreeDemo extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      radioValue:"a",
      depId: '',
      expandedKeys: ['20002', '20003'],
      gData: [{
        id: 10001,
        name: '总经理',
        children: [
          {
            id: 20001,
            name: '营销经理',
            children: [
              {
                id: 201301,
                name: '销售部'
              }
            ]
          },
          {
            id: 20002,
            name: '财务经理',
            children: [
              {
                id: 202301,
                name: '财务部'
              }
            ]
          },
          {
            id: 20003,
            name: '总工程师',
            children: [
              {
                id: 203301,
                name: '项目管理部'
              },
              {
                id: 203302,
                name: '售后服务部'
              }
            ]
          },
          {
            id: 20004,
            name: '行政经理',
            children: [
              {
                id: 204301,
                name: '行政部'
              }
            ]
          }
        ]
      }]
    };
  }

  onSelect = (id, info) => {
    if(id==10001){
      this.setState({
        depId: ''
      });
      return;
    }
    if(info.node.props.children){
      let arr= info.node.props.children.map(item=>item.key);
      this.setState({
        depId: arr.join(',')
      });
      return;
    }
    this.setState({
      depId: id
    });
  }
 
  render() {
   const {gData} = this.state;
    const loop = data => data.map((item) => {
      if (item.children) {
        return (
          <TreeNode key={item.id} title={item.name}>
            {loop(item.children)}
          </TreeNode>
        );
      }
      return <TreeNode key={item.id} title={item.name}/>;
    });
    return (
      <div style={divStyle}>
        <div style={divLeftStyle}>
          <Tree defaultExpandedKeys={this.state.expandedKeys} onSelect={this.onSelect}>
            {loop(gData)}
          </Tree>
        </div>
        <div style={divRightStyle}>
          <UserTable data={this.state.depId}/>
        </div>

      </div>
    );
  }
}

export default TreeDemo

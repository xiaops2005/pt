/**
 * Created by Bojc on 2017/6/16.
 */
import React from 'react';
import ReactDOM from 'react-dom';
import { Table,Modal,Button } from 'antd';

const columns = [{
  title: '姓名',
  dataIndex: 'name',
  sorter: (a, b) => a.name.length - b.name.length,
}, {
  title: '年龄',
  dataIndex: 'age',
  sorter: (a, b) => a.age - b.age,
}, {
  title: '性别',
  dataIndex: 'gender',
  sorter: (a, b) => a.gender.length - b.gender.length,
}];

const data = [{
  id: 10000,
  name: "一韬",
  age: 25,
  gender: "male",
  pid: 201301
},
  {
    id: 10001,
    name: "张三",
    age: 30,
    gender: "female",
    pid: 201301
  },
  {
    name: "John Brown",
    age: 22,
    gender: "female",
    id: 10003,
    pid: 201301
  },
  {
    name: "Luce",
    age: 1,
    gender: "female",
    id: 10005,
    pid: 201301
  },
  {
    name: "Jim Green",
    age: 11,
    gender: "male",
    id: 10006,
    pid: 202301
  },
  {
    name: "Joe Black",
    age: "22",
    gender: "male",
    id: 10007,
    pid: 202301
  },
  {
    name: "Disabled User",
    age: "22",
    gender: "male",
    id: 10008,
    pid: 202301
  },
  {
    name: "李四",
    age: 25,
    gender: "male",
    id: 10009,
    pid: 203301
  },
  {
    name: "王五",
    age: 25,
    gender: "male",
    id: 10010,
    pid: 203301
  },
  {
    name: "赵六",
    age: 25,
    gender: "male",
    id: 10011,
    pid: 203301
  },
  {
    name: "周",
    age: 25,
    gender: "male",
    id: 10012,
    pid: 203302
  },
  {
    name: "吴",
    age: 25,
    gender: "male",
    id: 10013,
    pid: 203302
  },
  {
    name: "郑",
    age: 25,
    gender: "male",
    id: 10014,
    pid: 203302
  },
  {
    name: "王",
    age: 25,
    gender: "male",
    id: 10015,
    pid: 204301
  },
  {
    name: "钱",
    age: 25,
    gender: "male",
    id: 10016,
    pid: 204301
  },
  {
    name: "孙",
    age: 25,
    gender: "male",
    id: 10017,
    pid: 204301
  },
  {
    name: "李",
    age: 25,
    gender: "male",
    id: 10018,
    pid: 204301
  }];

function onChange(pagination, sorter) {
  //console.log('params', pagination, sorter);
}

class UserTable extends React.Component {
  state = {
    visible: false,
    userInfo:{
      id:'',
      name:'',
      age:'',
      gender:''
    }
  }

  hideModal = () => {
    this.setState({
      visible: false,
    });
  }
  onRowClick = (record, index)=> {
    console.log(record)
    console.log(index)
    this.setState({
      visible: true,
      userInfo:record
    });
  }

  render() {
    const depId = this.props.data;
    let newData = data;
    if (depId.length > 0) {
      newData = data.filter(item => depId.indexOf(item.pid.toString()) > -1).map(item=>item);
    }
    return (
      <div>
        <Table columns={columns} dataSource={newData} onChange={onChange} onRowClick={this.onRowClick}/>
        <Modal
          title="用户信息"
          visible={this.state.visible}
          onOk={this.hideModal}
          onCancel={this.hideModal}
          okText="确认"
          footer={[
            <Button key="submit" type="primary" size="large" onClick={this.hideModal}>
              确 定
            </Button>
          ]}
        >
          <p>用户ID：{this.state.userInfo.id}</p>
          <p>姓名：{this.state.userInfo.name}</p>
          <p>年龄：{this.state.userInfo.age}</p>
          <p>性别：{this.state.userInfo.gender}</p>
        </Modal>
      </div>

    );
  }
}

export default UserTable;

import React from 'react';

import {Button, Table, message} from 'antd';


class Body extends React.Component {
  constructor(props) {
    super(props);
    this.state = {dataSource: [], count: 0, selectedRowKeys: []};
  }


  componentWillReceiveProps(nextProps) {
    this.setState({...nextProps});
  }

  add = () => {
    let {dataSource, selectedRowKeys} = this.state;
    let model = this.props.model;
    selectedRowKeys = selectedRowKeys.map(key => key + 1);
    selectedRowKeys.unshift(0);
    dataSource.unshift(new model());
    this.setState({
      dataSource: dataSource.map((item, index) =>
        Object.assign(item, {key: index})
      ),
      selectedRowKeys: selectedRowKeys
    });
  };

  edit = () => {
    const {selectedRowKeys} = this.state;
    if (selectedRowKeys.length === 0) {
      message.warn("请选择要编辑的行！");
    } else {
      const {dataSource} = this.state;
      dataSource.filter((item) => selectedRowKeys.some((key) => item.key === key)).map((item) =>
        Object.assign(item, {$$editable: !item.$$editable})
      );
      this.setState({
        dataSource: dataSource
      });
    }
  };

  save = () => {
    const {selectedRowKeys} = this.state;
    if (selectedRowKeys.length === 0) {
      message.warn("请选择要保存的行！");
    } else {
      const {dataSource} = this.state;
      let selectedDataSource = dataSource.filter((item) => selectedRowKeys.some((key) => item.key === key));
      selectedDataSource.forEach(item => {
        delete item.$$editable;
      });
      console.log(selectedDataSource);
      this.props.onSave(selectedDataSource);
    }
  };

  delete = () => {
    const {selectedRowKeys} = this.state;
    if (selectedRowKeys.length === 0) {
      message.warn("请选择要删除的行！");
    } else {
      const ids = selectedRowKeys.map(item => "'" + item + "'").join(",");
      this.props.onDelete(ids);
    }
  };

  onSelectRowChange = (selectedRowKeys) => {
    console.log('selectedRowKeys changed: ', selectedRowKeys);
    this.setState({selectedRowKeys});
  }


  render() {

    const {dataSource, selectedRowKeys} = this.state;
    const rowSelection = {
      selectedRowKeys,
      onChange: this.onSelectRowChange,
    };
    return (
      <div>
        <div className="operation-area">
          <Button className="editable-add-btn" onClick={this.add}>新增</Button>
          <Button className="editable-add-btn" onClick={this.edit}>编辑</Button>
          <Button className="editable-add-btn" onClick={this.save}>保存</Button>
          <Button className="editable-add-btn" onClick={this.delete}>删除</Button>
          <Button className="editable-add-btn" onClick={this.stop}>停用</Button>

        </div>
        <Table bordered rowSelection={rowSelection} dataSource={dataSource}
               columns={this.props.columns}/>

      </div>
    )
  }

}

export default Body;

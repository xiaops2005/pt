import React from 'react';
import {Button, Table, Modal, message} from 'antd';
import {EditableCellSelect, EditableCellInput} from '../../components/EditableTable'
import AddMovieModal from "./AddMovieModal";
import DateUtil from "../../../../../../constants/DateUtil";
import { VHDomUtil } from '../../../../../../constants/utils';
// import {BdMaterials} from '../../models/bdDictModel'
import '../index.css'
const dateFormat = 'YYYY-MM-DD';
const dataSource = [];

const confirm = Modal.confirm;
const warn = Modal.warn;

class Body extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      yearList: [],
      mappedYear: {},
      selectedRowKeys: [],
      dataSource: [],
    };
    this.columns = [
      {
        title: '电影名称',
        dataIndex: 'title',
        width: '10%',
      },{
        title: '评分',
        dataIndex: 'rating',
        width: '5%',
      }, {
        title: '年份',
        dataIndex: 'year',
        width: '5%',
      },{
        title: '小图片URL',
        dataIndex: 'small_image',
        width: '10%',
      }, {
        title: '中图片URL',
        dataIndex: 'medium_image',
        width: '10%',
      },{
        title: '大图片URL',
        dataIndex: 'large_image',
        width: '10%',
      },{
        title: '豆瓣URL',
        dataIndex: 'alt',
        width: '10%',
      },{
        title: '国家',
        dataIndex: 'countries',
        width: '5%',
      },{
        title: '类型',
        dataIndex: 'genres',
        width: '5%',
      },{
        title: '第几季',
        dataIndex: 'current_season',
        width: '5%',
      },{
        title: '原始标题',
        dataIndex: 'original_title',
        width: '10%',
      },{
        title: '介绍',
        dataIndex: 'summary',
        width: '10%',
      },{
        title: '类型',
        dataIndex: 'subtype',
        width: '10%',
      },{
        title: '别名',
        dataIndex: 'akas',
        width: '10%',
      },{
        title: '演员',
        dataIndex: 'casts',
        width: '10%',
      },{
        title: '导演',
        dataIndex: 'directors',
        width: '10%',
      }];
  }

  componentWillMount() {
    console.log("Body componentWillMount");
  }

  componentWillReceiveProps(nextProps) {
    this.setState({...nextProps});
  }

  addMovie = (e) => {
    e.preventDefault();
    this.formRef.setState({visible:true})
  };

  render() {
    const {selectedRowKeys} = this.state;
    const rowSelection = {
      selectedRowKeys,
      onChange: (selectedRowKeys, selectedRows) => {
        console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
        this.setState({selectedRowKeys});
      }
    };
    console.log("Body render")
    return (
      <div className="vh-body">
        <div className="table-operations">
          <Button className="btn-primary" onClick={this.addMovie}>添加影片信息</Button>
        </div>
        <Table  bordered className="main-section" scroll={{ x: '100%',y: true}} pagination={{pageSize: 10}} rowSelection={rowSelection} dataSource={this.state.dataSource} columns={this.columns}/>
        <AddMovieModal wrappedComponentRef={(inst) => this.formRef = inst}/>
      </div>
    )
  }
}

export default Body



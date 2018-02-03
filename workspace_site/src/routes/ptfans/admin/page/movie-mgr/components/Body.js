import React from 'react';
import {Button, Table, Modal, Divider, message } from 'antd';
import {EditableCellSelect, EditableCellInput} from '../../components/EditableTable'
import AddMovieModal from "./AddMovieModal";
import AddTorrentModal from "./AddTorrentModal";
import {MovieMgrService} from "../../../process/MovieMgrService";
import DateUtil from "../../../../../../constants/DateUtil";
import { VHDomUtil } from '../../../../../../constants/utils';
import '../index.css'
const processor = new MovieMgrService()
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
        title: '豆瓣ID',
        dataIndex: 'id',
        width: '8%',
        render: (text, record) => (
          <div style={{ color: record.publishFlag==='1'  ? '#00ff00' : '#ff0000' }}>
            {text}
          </div>
        )
      },{
        title: '电影名称',
        dataIndex: 'title',
        width: '10%',
      },{
        title: '导演',
        dataIndex: 'directors',
        width: '10%',
      },{title: '演员',
        dataIndex: 'casts',
        width: '20%',
      },{
        title: '评分',
        dataIndex: 'rating',
        width: '8%',
      }, {
        title: '年份',
        dataIndex: 'year',
        width: '8%',
      },{
        title: '国家',
        dataIndex: 'countries',
        width: '8%',
      },{
        title: '类型',
        dataIndex: 'genres',
        width: '8%',
      },{
        title: '操作',
        key: 'action',
        width: '10%',
        render: (text, record) => (
          <span>
            <a style={{ color: record.torrentFlag==='1' ? '#00ff00' : '#ff0000' }} onClick={() => this.addTorrent(record.id)}>种子</a>
            <Divider type="vertical"/>
            <a style={{ color: record.subtitleFlag=='1'  ? '#00ff00' : '#ff0000' }} onClick={() => this.addSubTitle(record.id)}>字幕</a>
         </span>
        )
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

  addTorrent(id){
    this.formTorrent.setState({visible:true,movieId:id})
    processor.queryTorrents(id, (result) => {
      this.formTorrent.setState({dataSource:result.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))})
    })
  };

  addSubTitle(key){

  };

  publish = (publishFlag) => {
    const {selectedRowKeys} = this.state;
    if (selectedRowKeys.length === 0) {
      message.warn(`请选择要${publishFlag==='1'? '发布' : '下架'}的行！`);
    } else {
      confirm({
        title: '系统提示',
        content: `是否要${publishFlag==='1'? '发布' : '下架'}所有选择行数据?`,
        onOk: () => {
          console.log(selectedRowKeys)
            processor.publish(selectedRowKeys,publishFlag,(result) => {
                message.success(`${publishFlag==='1'? '发布' : '下架'}成功！`);
                this.props.header.props.onQuery();
            })
        },
        onCancel: () => {
        }
      });
    }
  };

  delete = () => {
    const {selectedRowKeys} = this.state;
    if (selectedRowKeys.length === 0) {
      message.warn(`请选择要删除的行！`);
    } else {
      confirm({
        title: '系统提示',
        content: `是否要删除所有选择行数据?`,
        onOk: () => {
          processor.deleteByIds(selectedRowKeys,(result) => {
            message.success('删除成功！');
            this.state.query();
          })
        },
        onCancel: () => {
        }
      });
    }
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
          <Button className="btn-primary" onClick={this.addMovie}>豆瓣添加影片信息</Button>
          <Button className="btn-primary" onClick={this.publish.bind(this, '1')}>发布影片</Button>
          <Button className="btn-primary" onClick={this.publish.bind(this, '0')}>下架影片</Button>
        </div>
        <Table  bordered className="main-section" scroll={{ x: '100%',y: false}} pagination={{pageSize: 10}} rowSelection={rowSelection} dataSource={this.state.dataSource} columns={this.columns}/>
        <AddMovieModal wrappedComponentRef={(inst) => this.formRef = inst}/>
        <AddTorrentModal wrappedComponentRef={(inst) => this.formTorrent = inst}/>
      </div>
    )
  }
}

export default Body



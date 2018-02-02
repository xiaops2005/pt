import React from 'react';
import {Modal, Select, Table, message, Form, Layout, Input, Button, Row, Col} from 'antd';
import NetUtil from "../../../../../../constants/httpUtil";
import {MovieMgrService} from "../../../process/MovieMgrService";
import '../index.css'
const {Option} = Select.Option;
const processor = new MovieMgrService()
const url = NetUtil.getUrl() + "/api/";

/**
 * 添加影片信息弹框
 *
 */
const {Header, Footer, Sider, Content} = Layout;
const FormItem = Form.Item;
const Search = Input.Search;
class AddTorrentModal extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      visible: false,
      movieId:'',
      dataSource:[],
    };
    this.columns = [
      {
        title: '种子名称',
        dataIndex: 'name',
        width: '30%',
      },{
        title: '磁力链',
        dataIndex: 'magnet',
        width: '30%',
      },{title: '大小',
        dataIndex: 'size',
        width: '10%',
      },{
        title: '清晰度',
        dataIndex: 'definition',
        width: '18%',
      },{
        title: '操作',
        key: 'action',
        width: '12%',
        render: (text, record) => (
          <span>
            <a onClick={() => this.delTorrent(record.id)}>删除</a>
         </span>
        ),
      }]
  }

  componentWillMount() {
    console.log("AddTorrentModal componentWillMount");
  }
  componentDidMount() {
    console.log("AddTorrentModal componentDidMount");
  }

  componentWillReceiveProps(nextProps) {
    console.log("AddTorrentModal componentWillReceiveProps nextProps=", nextProps)
    this.setState({...nextProps});
  }

  componentWillUpdate(nextProps, nextState){
    console.log("AddTorrentModal componentWillUpdate nextProps=", nextProps)
    console.log("AddTorrentModal componentWillUpdate nextState=", nextState)
  }

  componentDidUpdate(prevProps, prevState){
    console.log("AddTorrentModal componentDidUpdate prevProps=", prevProps)
    console.log("AddTorrentModal componentDidUpdate prevState=", prevState)
  }

  shouldComponentUpdate(nextProps, nextState){
    console.log("AddTorrentModal shouldComponentUpdate nextProps=", nextProps)
    console.log("AddTorrentModal shouldComponentUpdate nextState=", nextState)
    let {movieId, visible} = nextState
    // if(visible == true){
    //   processor.queryTorrents(this.state.movieId, (result) => {
    //     this.setState({dataSource:result.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))})
    //   })
    // }
    return true
  }

  delTorrent(id){
    processor.delTorrent(id, (result) => {
      if(result.header.code == 1){
        processor.queryTorrents(this.state.movieId,(result)=>{
          this.setState({dataSource:result.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))})
        })
      }
    })
  }

  handleOk = (e) => {
    this.props.form.validateFields((err, values) => {
      if (!err) {
        let params = this.props.form.getFieldsValue();
        params.movieId = this.state.movieId
        console.log(params)
        processor.saveOrUpdateTorrent(params, (result) => {
          if(result.header.code == 1){
            processor.queryTorrents(this.state.movieId,(result)=>{
              this.setState({dataSource:result.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))})
            })
          }
        })
      }
    });
  }

  render() {
    console.log("AddTorrentModal render state=", this.state)
    const {visible} = this.state
    const {getFieldDecorator, getFieldValue} = this.props.form;
    const formItemLayout = {
      labelCol: {
        xs: {span: 24},
        sm: {span: 8},
      },
      wrapperCol: {
        xs: {span: 24},
        sm: {span: 16},
      },
    };
    return (
      <Modal visible={visible}
             destroyOnClose={true}
             maskClosable={false}
             title={"添加种子信息"}
             width="1024px"
             onOk={this.handleOk}
             onCancel={() => this.setState({visible: false})}
             closable={true}>
        <Layout>
          <Content>
            <Table  bordered className="main-section" scroll={{ x: '100%',y: true}} pagination={{pageSize: 10}}  dataSource={this.state.dataSource} columns={this.columns}/>
          </Content>
        <Sider width="360" style={{ background: '#ffffff' }}>
          <Form>
            <FormItem
              {...formItemLayout}
              label='名称'
            >
              {getFieldDecorator('name', {
                rules: [{
                  required: true,
                  whitespace: true,
                  message: "请输入种子名称",
                }],
              })(
                <Input placeholder="名称"/>
              )}
            </FormItem>
            <FormItem
              {...formItemLayout}
              label='磁力链地址'
            >
              {getFieldDecorator('magnet', {
                rules: [{
                  required: true,
                  whitespace: true,
                  message: "请输入磁力链地址",
                }],
              })(
                <Input placeholder="磁力链地址"/>
              )}
            </FormItem>
            <FormItem
              {...formItemLayout}
              label='大小'
            >
              {getFieldDecorator('size', {
                rules: [{
                  required: true,
                  whitespace: true,
                  message: "请输入种子大小",
                }],
              })(
                <Input placeholder="大小" style={{width: '60%', marginRight: 8}}/>
              )}
            </FormItem>
            <FormItem
              {...formItemLayout}
              label='清晰度'
            >
              {getFieldDecorator('definition', {
                rules: [{
                  required: true,
                  message: "请输入种子清晰度",
                }],
              })(
                <Input placeholder="清晰度" style={{width: '60%', marginRight: 8}}/>
              )}
            </FormItem>
          </Form>
        </Sider>
        </Layout>
      </Modal>
    )
  }
}
export default Form.create()(AddTorrentModal);


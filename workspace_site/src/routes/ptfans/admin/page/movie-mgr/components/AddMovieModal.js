import React from 'react';
import {Modal, Select, Table, message, Form, Layout, Input, Button} from 'antd';
import NetUtil from "../../../../../../constants/httpUtil";
import {MovieMgrService} from "../../../process/MovieMgrService";
// import {MovieMgrService} from "../../../process/LoadService";

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
class AddMovieModal extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      visible: false,
      year: '',
      title: '',
      original_title: '',
      smallImage: '',
      directors: '',
      casts: '',
      genres: '',
      countries: '',
      akas: '',
      summary: '',
    };
  }

  componentWillMount() {
    console.log("AddMovieModal componentWillMount");
    // this.props.queryApproveRecord();
  }

  componentWillReceiveProps(nextProps) {
    console.log("AddMovieModal componentWillReceiveProps nextProps=", nextProps)
    this.setState({...nextProps});
  }

  handleSubmit = (e) => {
    e.preventDefault();
  }

  getDoubanInfo = (value) => {
    this.props.form.validateFields((err, values) => {
      if (!err) {
        let that = this;
        processor.saveDoubanJson(value, (result) => {
          if (Object.keys(result.getDataStores()).length == 0) {
            return;
          }
          let obj = result.getSinglePrimaryList()[0];
          console.log(obj)
          // that.setState({year: obj.year})
          let title = obj.title + ' ' + obj.original_title + ' (' + obj.year + ')'
          that.setState({title: title })
          // that.setState({original_title: obj.original_title})
          that.setState({smallImage: obj.smallImage})
          that.setState({directors: obj.directors})
          that.setState({casts: obj.casts})
          that.setState({genres: obj.genres})
          that.setState({countries: obj.countries})
          that.setState({aka: obj.aka})
          that.setState({summary: obj.summary})
        })
      }
    });

  }

  handleOk = (e) => {
    console.log(e);
    this.setState({
      visible: false,
    });
  }

  render() {
    const {visible, year, title, original_title, smallImage, directors, casts, genres, countries, akas, summary} = this.state;
    console.log("AddMovieModal  render visible=", visible)
    const {getFieldDecorator} = this.props.form;
    const tailFormItemLayout = {
      wrapperCol: {
        xs: {
          span: 24,
          offset: 0,
        },
        sm: {
          span: 16,
          offset: 8,
        },
      },
    };
    const formItemLayout = {
      labelCol: {
        xs: {span: 24},
        sm: {span: 8},
      },
      wrapperCol: {
        xs: {span: 24},
        sm: {span: 8},
      },
    };
    return (
      <Modal visible={visible}
             destroyOnClose={true}
             maskClosable={false}
             title={"添加影片信息"}
             width="1024px"
             onOk={this.handleOk}
             onCancel={() => this.setState({visible: false})}
             closable={true}>
        <Form onSubmit={this.handleSubmit}>
          <FormItem
            {...formItemLayout}
            label="豆瓣编号"
          >
            {getFieldDecorator('doubanId', {
              rules: [{
                required: true, message: '请输入豆瓣编号!',
              }],
            })(
              <Search placeholder="豆瓣编号" enterButton="查询豆瓣信息"
                      onSearch={this.getDoubanInfo} size="large"/>
            )}
          </FormItem>
          <Layout className="layout">
            <Header style={{background: '#fff'}}>{title}</Header>
            <Layout>
              <Sider style={{background: '#fff'}} width="300">
                <picture class="picture">
                  <source type="image/webp" srcset={smallImage}/>
                  <img class="image" src={smallImage}/>
                </picture>
              </Sider>
              <Content>
                <div>导演: {directors}</div>
                <div>主演: {casts}</div>
                <div>类型: {genres}</div>
                <div>国家: {countries}</div>
                <div>又名: {akas}</div>
              </Content>
              <Sider style={{background: '#fff'}} width="400">{summary}</Sider>
            </Layout>
          </Layout>
        </Form>
      </Modal>
    )
  }
}
export default Form.create()(AddMovieModal);


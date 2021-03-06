import React, {Component} from "react";
import {Modal, Button} from "antd";
import Body from './components/Body';
import Header from './components/Header';
import {MovieMgrService} from "../../process/MovieMgrService";
import './index.css'
const processor = new MovieMgrService()
// const FormItem = Form.Item;

class MovieMgr extends Component {
  constructor(props) {
    super(props);
    this.state = {}
    this.query();
  }
  query = (params) => {
    processor.query(params, (data) => {
      console.log('query result',data.getSinglePrimary())
      this.setState({dataSource:data.getSinglePrimary().map((item, index) => Object.assign(item, {key: item.id}))})
    });
  }

  render() {
    console.log("movie-mgr render")
    return (
      <div className="vh-content">
        <Header {...this.props}
                onQuery={this.query}
                wrappedComponentRef={(inst) => this.formRef = inst}/>
        <Body {...this.props}
              dataSource={this.state.dataSource}
              header={this.formRef}/>
      </div>
    )
  }
}
export default MovieMgr


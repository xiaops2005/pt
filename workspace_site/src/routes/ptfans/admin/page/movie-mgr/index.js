import React, {Component} from "react";
import {Modal, Button} from "antd";
import Body from './components/Body';
import Header from './components/Header';
import {MovieMgrService} from "../../process/MovieMgrService";

const processor = new MovieMgrService()
// const FormItem = Form.Item;

class MovieMgr extends Component {
  constructor(props) {
    super(props);
    this.state = {}
  }

  render() {
    console.log("movie-mgr render")


    return (
      <div className="vh">
        <Header {...this.props}
                onQuery={this.queryReMateDetail}
                wrappedComponentRef={(inst) => this.formRef = inst}/>
        <Body {...this.props}
              dataSource={this.state.dataSource}
              onExport={this.exportReMateDetail}
              header={this.formRef}/>
      </div>
    )
  }
}
export default MovieMgr


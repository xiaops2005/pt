import React, {Component} from "react";
import { Modal, Button } from "antd";
// import MovieInfo from './components/MovieInfo';
// import Header from './components/Header';
import AddMovieModal from './components/AddMovieModal';
import {MovieMgrService} from "../../process/MovieMgrService";

const processor = new MovieMgrService()
// const FormItem = Form.Item;

class MovieMgr extends Component {
  constructor(props) {
    super(props);
    this.state = {}
  }

  addMovie =(e)=>{
    e.preventDefault();
    // this.refs.addMovieModal.setState({visible:true})
    this.setState({visible: true})
  };

  render() {
    console.log("movie-mgr render")


    return (
      <div>
        <Button className="btn-primary" onClick={this.addMovie}>添加影片信息</Button>
        <AddMovieModal ref="addMovieModal" visible={this.state.visible}/>
      </div>
    )
  }
}
export default MovieMgr


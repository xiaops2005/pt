import React, {Component} from "react";
import {Redirect, Route, Switch} from "react-router-dom";

import MovieDownload from "./website/page/movie-download/index.js";
import MovieDetail from "./website/page/movie-download/movie-detail/index.js";
import MovieRecommend from "./website/page/movie-recommend/index.js";
import PtParadise from "./website/page/pt-paradise/index.js";
import HomeTheater from "./website/page/home-theater/index.js";
import Feedback from "./website/page/feedback/index.js";


class PtFans extends Component {
  render() {
    const {match} = this.props;
    console.log("PtFans render url=",match.url)
    return (
        <Switch>
          <Route path={`${match.url}/movie-download`} component={MovieDownload}/>
          <Route path={`${match.url}/movie-detail/:id`} component={MovieDetail}/>
          <Route path={`${match.url}/movie-recommend`} component={MovieRecommend}/>
          <Route path={`${match.url}/pt-paradise`} component={PtParadise}/>
          <Route path={`${match.url}/home-theater`} component={HomeTheater}/>
          <Route path={`${match.url}/feedback`} component={Feedback}/>
          <Redirect from={match.url} to={`${match.url}/movie-download`}/>
        </Switch>
    )
  }
}

export default PtFans

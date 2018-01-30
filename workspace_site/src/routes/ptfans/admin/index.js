import React, {Component} from "react";
import {Redirect, Route, Switch} from "react-router-dom";
import {MainApp} from './components'
import MovieMgr from './page/movie-mgr'

class AdminPage extends Component {
  render() {
    const {match} = this.props;
    console.log("AdminPage url=",match.url)
    return (
      <MainApp {...this.props}>
        <Switch>
          <Route path={`${match.url}/movie-mgr`} component={MovieMgr}/>
          <Redirect from={match.url} to={`${match.url}/movie-mgr`}/>
        </Switch>
      </MainApp>
    )
  }
}
export default AdminPage

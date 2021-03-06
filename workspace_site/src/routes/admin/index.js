import React, {Component} from 'react';
import {Link, Switch, Route, Redirect} from 'react-router-dom';
import {connect} from 'react-redux'
import {injectReducer} from 'reducers';

import {fetchSiteById} from './models/site';

import Header from './components/Header';
import Sidebar from './components/Sidebar';
import Breadcrumb from './components/Breadcrumb';
import Aside from './components/Aside';
import Footer from './components/Footer';

import Dashboard from './routes/dashboard/';
import Sites from './routes/sites/';
import Users from './routes/users/';
import Security from './routes/security';
import Pages from './routes/pages/';
import reducer from './models/site';

class Page extends Component {
  componentWillMount() {
    const {store} = this.context;


    injectReducer(store, {key: 'site', reducer});


  }
  componentDidMount(){
    const {dispatch} = this.props;
    const {store} = this.context;
    console.log(store);
    dispatch(fetchSiteById(1))
  }
  render() {
    const {match} = this.props;

    return (
      <div className="app header-fixed ">
        <Header />
        <div className="app-body">
          <Sidebar {...this.props}/>
          <main className="main">
            <Breadcrumb/>
            <div className="container-fluid">
              <Switch>
                <Route path={`${match.url}/dashboard`} name="概览" component={Dashboard}/>
                <Route path={`${match.url}/sites`} name="站点管理" component={Sites}/>
                <Route path={`${match.url}/users`} name="用户管理" component={Users}/>
                <Route path={`${match.url}/security`} name="验证授权" component={Security}/>
                <Route path={`${match.url}/pages`} name="页面定义" component={Pages}/>
                <Redirect from={match.url} to={`${match.url}/dashboard`}/>
              </Switch>
            </div>
          </main>
          <Aside />
        </div>
        <Footer />
      </div>
    )
  }
}
Page.contextTypes = {
  store: React.PropTypes.object
};
const mapStateToProps = (state, ownProps) => ({
  site: state.site,
});
export default connect(mapStateToProps)(Page);


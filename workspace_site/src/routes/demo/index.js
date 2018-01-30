import React, {Component} from 'react';
import {Link, Switch, Route, Redirect} from 'react-router-dom';
import {connect} from 'react-redux'
import {injectReducer} from 'reducers';
import {MainApp} from './components';

import SupplierReducer from './models/reducers/supplier';
import QueryReducer from './models/reducers/query';
import AccountReducer from './models/reducers/SettingsAccount';
import UserReducer from './models/reducers/userFormReducer';
import ManagerReducer from './models/reducers/managerSelfSer';
import ModalReducer from './models/reducers/modalViewer';

import Dashboard from './routes/dashboard/';
import LeaderManagerViewer from './routes/leader/components/LeaderManagerViewer';
import MangeSelf from './routes/mangeSelf/components/ManagerSelfSerViewer';
import Supplier from './routes/supplier/components/SupplierViewer';
import Tree from './routes/tree/TreeDemo';
import account from './routes/account/components/accountViewer';
import uploadViewer from './routes/upload/components/uploadViewer';
import UserManagerViewer from './routes/user/components/UserManagerViewer';
import formViewer from './routes/form/components/formViewer';
import modalView from './routes/modalDemo/components/modalView';

class Page extends Component {
  componentWillMount() {
    const {store} = this.context;
    injectReducer(store, {key: 'supplier', SupplierReducer});
    injectReducer(store, {key: 'query', QueryReducer});
    injectReducer(store, {key: 'account', AccountReducer});
    injectReducer(store, {key: 'userReducer', UserReducer});
    injectReducer(store, {key: 'managerSelfSer', ManagerReducer});
    injectReducer(store, {key: 'modalViewer', ModalReducer});
  }
  componentDidMount(){
    const {dispatch} = this.props;
    const {store} = this.context;
  }
  render() {
    const {match} = this.props;

    return (
      <MainApp {...this.props}>
        <Switch>
          <Route path={`${match.url}/dashboard`} component={Dashboard}/>
          <Route path={`${match.url}/oesDemo/account`} component={account}/>
          <Route path={`${match.url}/oesDemo/leader`} component={LeaderManagerViewer}/>
          <Route path={`${match.url}/oesDemo/mangeSelf`} component={MangeSelf}/>
          <Route path={`${match.url}/oesDemo/mangeSelf`} component={MangeSelf}/>
          <Route path={`${match.url}/oesDemo/supplier`}  component={Supplier}/>
          <Route path={`${match.url}/oesDemo/tree`}  component={Tree}/>
          <Route path={`${match.url}/oesDemo/user`} component={UserManagerViewer}/>
          <Route path={`${match.url}/oesDemo/upload`}  component={uploadViewer}/>
          <Route path={`${match.url}/oesDemo/formViewer`}  component={formViewer}/>
          <Route path={`${match.url}/oesDemo/modalDemo`}  component={modalView}/>

          <Redirect from={match.url} to={`${match.url}/dashboard`}/>
        </Switch>
      </MainApp>
    )
  }
}
Page.contextTypes = {
  store: React.PropTypes.object
};
const mapStateToProps = (state, ownProps) => ({
  demo: state.demo,
});
export default connect(mapStateToProps)(Page);


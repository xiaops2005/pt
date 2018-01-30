import React, {Component} from 'react';
import {Layout} from 'antd';
import AccountLeft from './AccountLeftViewer';
import AccountRight from "./AccountRightViewer";
import {connect} from 'react-redux';
const { Sider } = Layout;


class account extends Component {

  constructor(props) {
    super(props);
    this.state = {
      loadTime: new Date().getTime()
    };
  }

  render() {
    return (

      <div className="animated fadeIn">
        <Layout  >
          <Sider style={{background: 'white'}} width={280}>
            <AccountLeft dispatch={this.props.dispatch} loadTime={this.state.loadTime} />
          </Sider>
          <Layout
            style={{background: 'white', borderLeft: ' 1px', borderLeftColor: '#e4e5e6', borderLeftStyle: 'solid',paddingLeft:'8px'}}>
            <AccountRight dispatch={this.props.dispatch}  />
          </Layout>
        </Layout>
      </div>
    );
  }
}



const mapStateToProps = (state, ownProps) => ({
  documentModel: state.account.documentModel,
  left : state.account.left
});

export default connect(
  mapStateToProps,
)(account);

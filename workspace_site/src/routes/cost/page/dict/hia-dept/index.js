import React from 'react';
import {connect} from 'react-redux';

import Header from './components/Header'
import Body from './components/Body'
import {HiaDeptSevice} from './../../../process/LoadService'
import {queryHiaDept} from "../models/actions/bdDictAction";

const hiaDeptProcessor = new HiaDeptSevice();

class HiaDept extends React.Component {

  componentWillMount() {
    this.props.queryBdDeptHia();
  }

  render() {
    return (
      <div>
        <Header {...this.props}/>
        <Body {...this.props}/>
      </div>
    )
  }
}

const mapStateToProps = (state) => {
  return {...state.bdDict}
};

const mapDispatchToProps = (dispatch, state) => {
  return {
    queryBdDeptHia: (hiaCode, isStop) => {
      hiaDeptProcessor.queryHiaDept(hiaCode, isStop, (data) => {
        dispatch(queryHiaDept(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
    },
    saveHiaDept: (hiaDeptList, callback) => {
      hiaDeptProcessor.saveHiaDept(hiaDeptList, callback);
    },
    deleteHiaDept: (pkDeptHia, callback) => {
      hiaDeptProcessor.deleteHiaDept(pkDeptHia, callback);
    }
  }
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HiaDept);

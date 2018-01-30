import React from 'react';
import { Router } from 'react-router';
import 'antd/dist/antd.css';
import {connect} from 'react-redux';
import Body from './components/Body';
import Header from './components/Header';
import DeptProcessor from './components/DeptProcessor';
import {
  HospitalService,
  BdDictService
} from './../../../process/LoadService'
import {queryBdHospitalAll, queryBdDeptType,queryBdBelongSystem, queryStandartDept} from '../models/actions/bdDictAction'


const bdProcessor = new BdDictService();
const hospitalProcessor = new HospitalService();
const deptProcess = new DeptProcessor();



class DeptList extends React.Component {
  constructor(props) {
    super(props);
  }
  componentWillMount() {
    this.props.queryBdDict();
  }


  handleQueryChange = (deptCode,deptName,isStop) => {
    process.handleSubmit(deptCode,deptName,isStop,(data) => {
      this.setState({"dataSource": data.getSinglePrimary().map((item, index) => Object.assign(item, {key: index}))});
    });
  };

  render() {
    return (
      <div className="vh">
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
    query: (pkHospital,deptCode,deptName,belongSystem,isStop) => {
      deptProcess.queryData(pkHospital,deptCode,deptName,belongSystem,isStop,(data) => {
        dispatch(queryStandartDept(data.getSinglePrimary().map((item, index) => Object.assign(item, {key: index}))));
      });
    },
    delete: (pkDepts, successFn) => {
       deptProcess.delete(pkDepts, successFn);
    },
    save: (bdDeptList, successFn) => {
       deptProcess.save(bdDeptList, successFn);
    },
    startOrStop: (pkDepts,flag, successFn) => {
      deptProcess.startOrStop(pkDepts,flag, successFn);
    },
    queryBdDict: () => {
      bdProcessor.queryBdCode("DEPT_TYPE", (data) => {
        dispatch(queryBdDeptType(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
      bdProcessor.queryBdCode("BELONG_SYSTEM", (data) => {
        dispatch(queryBdBelongSystem(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
      hospitalProcessor.queryBdHospitalAll((data) => {
        dispatch(queryBdHospitalAll(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      })
    }
  }
};
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DeptList)


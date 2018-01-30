import React from 'react';
import {connect} from 'react-redux';
import Header from './components/Header';
import Body from './components/Body';
import {DeptService, HospitalService, BdDictService} from './../../../process/LoadService'
import {
  queryBdDept,
  queryBdBelongSystem,
  queryBdHospitalAll,
  queryBdYear,
  queryBdIsStop,
  queryBdDeptType
} from '../models/actions/bdDictAction'
import './index.css'

const processor = new DeptService();
const bdProcessor = new BdDictService();
const hospitalProcessor = new HospitalService();

class DeptDict extends React.Component {
  constructor(props) {
    super(props);
  }

  componentWillMount() {
    console.log("componentWillMount");
    this.props.queryBdDict();
    this.props.queryBdDept();
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
    queryBdDept: (year, pkHospital, deptCode, deptName, belongSystem, isStop) => {
      processor.queryBdDept(year, pkHospital, deptCode, deptName, belongSystem, isStop, (data) => {
        dispatch(queryBdDept(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
    },
    deleteBdDept: (pkDepts, callback) => {
      processor.deleteBdDept(pkDepts, callback);
    },
    saveBdDept: (bdDeptList, callback) => {
      processor.saveBdDept(bdDeptList, callback);
    },
    queryBdDict: () => {
      bdProcessor.queryBdCode("BELONG_SYSTEM", (data) => {
        dispatch(queryBdBelongSystem(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
      bdProcessor.queryBdCode("YEAR", (data) => {
        dispatch(queryBdYear(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
      bdProcessor.queryBdCode("IS_STOP", (data) => {
        dispatch(queryBdIsStop(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
      bdProcessor.queryBdCode("DEPT_TYPE", (data) => {
        dispatch(queryBdDeptType(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
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
)(DeptDict)



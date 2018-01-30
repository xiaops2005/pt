import React from 'react';
import {connect} from 'react-redux';

import Header from './components/Header';
import Body from './components/Body';
import {HospitalService, BdDictService, ReWageService} from './../../../process/LoadService'
import {queryBdHospitalAll, queryBdYear, queryReWages} from '../../dict/models/actions/bdDictAction'

const hospitalProcessor = new HospitalService();
const bdProcessor = new BdDictService();
const reWageProcessor = new ReWageService();

class PersonnelExpense extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      dataSource: [],
    }
  }

  componentWillMount() {
    this.props.queryBdDict();
    this.props.queryReWage();
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
    queryReWage: (year, pkHospital, deptCode) => {
      reWageProcessor.queryReWage(year, pkHospital, deptCode, (data) => {
        dispatch(queryReWages(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
    },
    queryBdDict: () => {
      bdProcessor.queryBdCode("YEAR", (data) => {
        dispatch(queryBdYear(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
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
)(PersonnelExpense)



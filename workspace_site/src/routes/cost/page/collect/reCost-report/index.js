import React from 'react';
import {connect} from 'react-redux';
import Header from './components/Header';
import Body from './components/Body';
import {ReCostService, BdDictService,HospitalService} from '../../../process/LoadService'
import {queryReCost, queryBdYear, queryBdHospitalAll} from '../../dict/models/actions/bdDictAction'
import './index.css'

const processor = new ReCostService();
const bdProcessor = new BdDictService();
const hospitalProcessor = new HospitalService();

class reCostReport extends React.Component {
  constructor(props) {
    super(props);
  }

  componentWillMount() {
    this.props.queryBdDict();
    this.props.queryReCost();


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
    queryReCost: (accYear, pkHospital, deptCode, deptName) => {
      processor.queryReCost(accYear, pkHospital, deptCode, deptName, (data) => {
        dispatch(queryReCost(data.getSinglePrimary().map((item, index) => Object.assign(item, {key: index}))));
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
)(reCostReport)



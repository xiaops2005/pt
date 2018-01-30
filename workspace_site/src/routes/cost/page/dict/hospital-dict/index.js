import React from 'react';
import {connect} from 'react-redux';
import Header from './components/Header';
import Body from './components/Body';
import {HospitalService, BdDictService} from './../../../process/LoadService'
import {queryBdHospital, queryBdHosType, queryBdHosClass} from '../models/actions/bdDictAction'
import './index.css'

const processor = new HospitalService();
const bdProcessor = new BdDictService();

class HospitalDict extends React.Component {
  constructor(props) {
    super(props);
  }

  componentWillMount() {
    console.log("componentWillMount");
    this.props.queryBdCode();
    this.props.queryBdHospital();
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
    queryBdHospital: (hospitalCode, hospitalName, hospitalTypeId, hospitalClassId) => {
      processor.queryBdHospital(hospitalCode, hospitalName, hospitalTypeId, hospitalClassId, (data) => {
        dispatch(queryBdHospital(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
    },
    deleteBdHospital: (pkHospitals, callback) => {
      processor.deleteBdHospital(pkHospitals, callback);
    },
    saveBdHospital: (bdHospitalList, callback) => {
      processor.saveBdHospital(bdHospitalList, callback);
    },
    queryBdCode: () => {
      bdProcessor.queryBdCode("HOS_CLSAA", (data) => {
        dispatch(queryBdHosClass(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
      bdProcessor.queryBdCode("HOS_TYPE", (data) => {
        dispatch(queryBdHosType(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
    }
  }
};
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HospitalDict)



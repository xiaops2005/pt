import React from 'react';
import { Router } from 'react-router';
import 'antd/dist/antd.css';
import {connect} from 'react-redux';
import Body from './components/Body';
import Header from './components/Header';

import {
  HospitalService,
  BdDictService
} from './../../../process/LoadService'
import {queryBdHospitalAll} from '../../dict/models/actions/bdDictAction'
import {queryBdYear} from "../../dict/models/actions/bdDictAction";


const bdProcessor = new BdDictService();
const hospitalProcessor = new HospitalService();



class Income extends React.Component {
  constructor(props) {
    super(props);
  }
  componentWillMount() {
    this.props.queryBdDict();
  }


  handleQuery = (deptCode,deptName,isStop) => {
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
    // query: (year,pkHospital,materialsCode,materialsName,isSingle,isStop) => {
    //   process.queryBdMaterials(year,pkHospital,materialsCode,materialsName,isSingle,isStop, (data) => {
    //     dispatch(queryBdMaterials(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
    //   });
    // },

    queryBdDict: () => {
      hospitalProcessor.queryBdHospitalAll((data) => {
        dispatch(queryBdHospitalAll(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      })
      bdProcessor.queryBdCode("YEAR", (data) => {
        dispatch(queryBdYear(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
    }
  }
};
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Income)

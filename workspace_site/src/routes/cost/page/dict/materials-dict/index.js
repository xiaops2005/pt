import React from 'react';
import {connect} from 'react-redux';
import Header from './components/Header';
import Body from './components/Body';
import {
  MaterialsService,
  HospitalService,
  BdDictService
} from './../../../process/LoadService'
import {
  queryBdIsStop,
  queryBdIsSingle,
  queryBdYear,
  queryBdHospitalAll,
  queryBdMaterials
} from '../models/actions/bdDictAction'
import './index.css'
const processor = new MaterialsService();
const bdProcessor = new BdDictService();
const hospitalProcessor = new HospitalService();

class MaterialsDict extends React.Component {
  constructor(props) {
    super(props);
    console.log("constructor")
  }

  componentWillMount() {
    console.log("componentWillMount");
    this.props.queryBdDict();
    this.props.queryBdMaterials();
  }

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
  console.log("mapStateToProps")
  return {...state.bdDict}
};
const mapDispatchToProps = (dispatch, state) => {
  console.log("mapDispatchToProps")
  return {
    queryBdMaterials: (year,pkHospital,materialsCode,materialsName,isSingle,isStop) => {
      processor.queryBdMaterials(year,pkHospital,materialsCode,materialsName,isSingle,isStop, (data) => {
        dispatch(queryBdMaterials(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
    },
    deleteBdMaterials: (pkMaterials, callback) => {
      processor.deleteBdMaterials(pkMaterials, callback);
    },
    saveBdMaterials: (bdMaterialsList, callback) => {
      processor.saveBdMaterials(bdMaterialsList, callback);
    },
    queryBdDict: () => {
      bdProcessor.queryBdCode("YEAR", (data) => {
        dispatch(queryBdYear(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
      // bdProcessor.queryBdCode("IS_STOP", (data) => {
      //   dispatch(queryBdIsStop(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      // });
      // bdProcessor.queryBdCode("IS_SINGLE", (data) => {
      //   dispatch(queryBdIsSingle(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      // });
      hospitalProcessor.queryBdHospitalAll((data) => {
        dispatch(queryBdHospitalAll(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      })
    }
  }
};
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(MaterialsDict)



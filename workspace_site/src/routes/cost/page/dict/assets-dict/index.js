import React from 'react';
import {connect} from 'react-redux';
import Header from './components/Header';
import StandardHeader from './components/StandardHeader';
import Body from './components/Body';
import {AssetsService, BdDictService,HospitalService} from './../../../process/LoadService'
import {
  queryBdHospital, queryBdAssets,
  queryBdIsStop, queryBdYear, queryBdHospitalAll, queryBdDeptType,queryCapitalSource
} from '../models/actions/bdDictAction'
import './index.css'

const processor = new AssetsService();
const bdProcessor = new BdDictService();
const hospitalProcessor = new HospitalService();

class assetsDict extends React.Component {
  constructor(props) {
    super(props);
    this.standardFlag = null;
  }

  componentDidMount() {
    this.props.queryBdAssets(null, null, null, null, null, null, this.standardFlag);
  }

  componentWillReceiveProps(nextProps) {
    let nextStandardFlag = nextProps.match.params.standardFlag;
    if (this.standardFlag !== nextStandardFlag) {
      this.props.resetDataSource(); // 标准字典/非标准字典改变时，重置 DataSource
    }
  }

  render() {
    let { match } = this.props;
    this.standardFlag = match.params.standardFlag;

    return (
      <div>
        { this.standardFlag === '1'  ? <StandardHeader {...this.props}/>:  <Header {...this.props}/>}
        <Body {...this.props}/>
      </div>
    )
  }
}

const mapStateToProps = (state) => {
  return {
    dataSource: state.bdDict.dataSource || [],
    yearList: state.bdDict.yearList || [],
    deptTypeList: state.bdDict.deptTypeList || [],
    isStopList: state.bdDict.isStopList || [],
    capitalSourceList: state.bdDict.capitalSourceList || [],
    hospitalList: state.bdDict.hospitalList || []
  }
};
const mapDispatchToProps = (dispatch, state) => {
  return {
    resetDataSource: () => {
      dispatch(queryBdAssets([]));
    },
    queryBdAssets: (accYear, pkHospital, assetsCode, assetsName, capitalSourceId,isStopId, standardFlag) => {
      processor.queryBdAssets(accYear, pkHospital, assetsCode, assetsName, capitalSourceId,isStopId,standardFlag, (data) => {
        dispatch(queryBdAssets(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
    },
    deleteBdAssets: (pkAssets, callback) => {
      processor.deleteBdAssets(pkAssets, callback);
    },
    saveBdAssets: (bdAssetsList, callback) => {
      processor.saveBdAssets(bdAssetsList, callback);
    },
    queryBdDict: () => {

      bdProcessor.queryBdCode("YEAR", (data) => {
        dispatch(queryBdYear(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
      bdProcessor.queryBdCode("IS_STOP", (data) => {
        dispatch(queryBdIsStop(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
      bdProcessor.queryBdCode("DEPT_TYPE", (data) => {
        dispatch(queryBdDeptType(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
      bdProcessor.queryBdCode("CAPITAL_SOURCE", (data) => {
        dispatch(queryCapitalSource(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
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
)(assetsDict)



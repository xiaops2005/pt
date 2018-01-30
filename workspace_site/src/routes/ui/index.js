import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Switch, Route, Redirect} from 'react-router-dom';
import {MainApp} from './components';


import VhUpload from "../cost/page/upload/upload.js";
import DictMaintain from "../cost/page/dict/DictMaintain";
import Identification from "../cost/page/dict/identification-dict";
import DeptDict from "../cost/page/dict/dept-dict";
import HospitalDict from "../cost/page/dict/hospital-dict";
import ProjectLargeCategory from "../cost/page/dict/category-dict/index";
import MaterialsDict from "../cost/page/dict/materials-dict";
import DeptList from "../cost/page/dict/standarddept/index";
import MergeDict from "../cost/page/dict/merge-dict";
import ExecuteMethod from "../cost/page/dict/execute-method";
import AssetsDict from "../cost/page/dict/assets-dict";
import HiaDept from '../cost/page/dict/hia-dept'
import ChargesStaDict from '../cost/page/dict/chargesSta-dict'

import ReCostReport from "../cost/page/collect/reCost-report";
import AnalysisCollect from "../cost/page/collect/analysis-collect";
import MaterialDetail from "../cost/page/collect/material-detail";
import AssetDetail from "../cost/page/collect/asset-detail";
import PersonnelExpense from "../cost/page/collect/personnel-expense";
import {
  queryBdBelongSystem, queryBdDeptType, queryBdHosClass, queryBdHospitalAll, queryBdHosType, queryBdIsSingle,
  queryBdIsStop,
  queryBdYear, queryCapitalSource
} from "../cost/page/dict/models/actions/bdDictAction";
import { BdDictService, HospitalService} from "../cost/process/LoadService";
import TemplateEntity from "../cost/page/import-template/template-entity";
import TemplateColumns from "../cost/page/import-template/template-columns";
import income from "../cost/page/collect/income";


const bdProcessor = new BdDictService();
const hospitalProcessor = new HospitalService();

class UI extends Component {

  componentWillMount() {
      this.props.queryBdCode();
  }

  render() {
    const {match} = this.props;
    return (
      <MainApp {...this.props}>
        <Switch>

          <Route path={`${match.url}/upload`} component={VhUpload}/>
          <Route path={`${match.url}/base-data/dict-maintain`} component={DictMaintain}/>
          <Route path={`${match.url}/base-data/identification-dict`} component={Identification}/>
          <Route path={`${match.url}/base-data/hospital-dict`} component={HospitalDict}/>
          <Route path={`${match.url}/base-data/dept-dict`} component={DeptDict}/>
          <Route path={`${match.url}/base-data/projectLargeCategory`} component={ProjectLargeCategory}/>
          <Route path={`${match.url}/base-data/materials-dict`} component={MaterialsDict}/>
          <Route path={`${match.url}/base-data/standardDept`} component={DeptList}/>
          <Route path={`${match.url}/base-data/merge-dict`} component={MergeDict}/>
          <Route path={`${match.url}/base-data/execute-method`} component={ExecuteMethod}/>
          <Route path={`${match.url}/base-data/assets-dict/:standardFlag`} component={AssetsDict}/>
          <Route path={`${match.url}/base-data/hia-dept`} component={HiaDept}/>
          <Route path={`${match.url}/base-data/chargesSta-dict`} component={ChargesStaDict}/>

          <Route path={`${match.url}/data-collect/analysis-collect`} component={AnalysisCollect}/>
          <Route path={`${match.url}/data-collect/material-detail`} component={MaterialDetail}/>
          <Route path={`${match.url}/data-collect/asset-detail`} component={AssetDetail}/>
          <Route path={`${match.url}/data-collect/reCost-Report`} component={ReCostReport}/>
          <Route path={`${match.url}/data-collect/personnel-expense`} component={PersonnelExpense}/>

          <Route path={`${match.url}/import-template/template-entity`} component={TemplateEntity}/>
          <Route path={`${match.url}/import-template/template-columns`} component={TemplateColumns}/>

          <Route path={`${match.url}/data-collect/income`} component={income}/>

          <Redirect from={match.url} to={`${match.url}/upload`}/>
        </Switch>
      </MainApp>
    )
  }
}

const mapStateToProps = (state) => {
  return {...state.bdDict}
}

const mapDispatchToProps = (dispatch, state) => {
  return {
    queryBdCode: () => {
      //所属系统    belongSystemList
      bdProcessor.queryBdCode("BELONG_SYSTEM", (data) => {
        dispatch(queryBdBelongSystem(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
      //年度        yearList
      bdProcessor.queryBdCode("YEAR", (data) => {
        dispatch(queryBdYear(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
      //是否停用    isStopList
      bdProcessor.queryBdCode("IS_STOP", (data) => {
        dispatch(queryBdIsStop(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
      //科室类型    deptTypeList
      bdProcessor.queryBdCode("DEPT_TYPE", (data) => {
        dispatch(queryBdDeptType(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
      //资金来源    capitalSourceList
      bdProcessor.queryBdCode("CAPITAL_SOURCE", (data) => {
        dispatch(queryCapitalSource(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
      //医院等级    hospitalClassList
      bdProcessor.queryBdCode("HOS_CLSAA", (data) => {
        dispatch(queryBdHosClass(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
      //医院类型
      bdProcessor.queryBdCode("HOS_TYPE", (data) => {
        dispatch(queryBdHosType(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
      //是否单收费   isSingleList
      bdProcessor.queryBdCode("IS_SINGLE", (data) => {
        dispatch(queryBdIsSingle(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
      //所属单位   hospitalList
      hospitalProcessor.queryBdHospitalAll((data) => {
        dispatch(queryBdHospitalAll(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
    }

  }
}

 export default connect(
  mapStateToProps,
  mapDispatchToProps)(UI)

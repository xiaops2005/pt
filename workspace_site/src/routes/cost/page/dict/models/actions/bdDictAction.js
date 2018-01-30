import * as actionTypes from './ActionTypes';

const queryBdDept = (data) => {
  return {type: actionTypes.Query_Bd_Dept, data: data};
};
const queryBdHospital = (data) => {
  return {type: actionTypes.Query_Bd_Hospital, data: data};
};
const queryBdHosType = (data) => {
  return {type: actionTypes.Query_Bd_HOS_TYPE, data: data};
};
const queryBdHosClass = (data) => {
  return {type: actionTypes.Query_Bd_HOS_CLSAA, data: data};
};
const queryBdMaterials = (data) => {
  return {type: actionTypes.Query_Bd_Materials, data: data};
};
const queryBdMaterialsAll = (data) => {
  return {type: actionTypes.Query_Bd_Materials_All, data: data};
};

const queryBdBelongSystem = (data) => {
  return {type: actionTypes.Query_Bd_BELONG_SYSTEM, data: data};
};

const queryBdHospitalAll = (data) => {
  return {type: actionTypes.Query_Bd_Hospital_All, data: data};
};
const queryBdYear = (data) => {
  return {type: actionTypes.Query_Bd_Year, data: data};
};

const queryBdIsStop = (data) => {
  return {type: actionTypes.Query_Bd_Is_Stop, data: data};
};
const queryBdIsSingle = (data) => {
  return {type: actionTypes.Query_Bd_Is_Single, data: data};
};
const queryBdDeptType = (data) => {
  return {type: actionTypes.Query_Bd_Dept_Type, data: data};
};
const queryBdAssets = (data) => {
  return {type: actionTypes.Query_Bd_Assets, data: data};
};
const queryCapitalSource = (data) => {
  return {type: actionTypes.Query_Capital_Source, data: data};
};

//人员经费
const queryReWages = (data) => {
  return {type: actionTypes.QUERY_RE_WAGE, data: data};
};

const queryReCost = (data) => {
  return {type: actionTypes.Query_Re_Cost, data: data};
};
//hia科室
const queryHiaDept = (data) => {
  return {type: actionTypes.QUERY_HIADEPT, data: data};
};
//标准科室字典
const queryStandartDept = (data) => {
  return {type: actionTypes.Query_StandartDept, data: data};
};
const queryBdChargesHos = (data) => {
  return {type: actionTypes.Query_Bd_Charges_Hos, data: data};
};

const queryChargesStaDict = (data) => {
  return {type: actionTypes.Query_Bd_Charges_Sta, data: data};
};

export {
  queryBdDept,
  queryBdHospital,
  queryBdHosType,
  queryBdHosClass,
  queryBdBelongSystem,
  queryBdHospitalAll,
  queryBdMaterials,
  queryBdMaterialsAll,
  queryBdYear,
  queryBdIsStop,
  queryBdIsSingle,
  queryBdDeptType,
  queryBdAssets,
  queryCapitalSource,
  queryReWages,
  queryReCost,
  queryHiaDept,
  queryStandartDept,
  queryBdChargesHos,
  queryChargesStaDict
}

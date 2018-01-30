import * as actionTypes from '../actions/ActionTypes';
import MyUtil from '../../../../../../constants/MyUtil'

const bdDict = (state = {dataSource: []}, action) => {
  switch (action.type) {
    case actionTypes.Query_Bd_Dept:
      return Object.assign({}, state, {
        deptList: action.data,
        mappedDept: MyUtil.mapListToObject2(action.data, "pkDept")
      });
    case actionTypes.Query_Bd_Hospital:
    case  actionTypes.Query_Bd_Materials:
      return Object.assign({}, state, {
        dataSource: action.data,
        selectedRowKeys: []
      });
    case  actionTypes.Query_Bd_Materials_All:
      return Object.assign({}, state, {
        materialsList: action.data,
        mappedMaterials: MyUtil.mapListToObject2(action.data, "pkMaterials")
      });
    case actionTypes.Query_Bd_Hospital_All:
      return Object.assign({}, state, {
        hospitalList: action.data,
        mappedHospital: MyUtil.mapListToObject(action.data, "pkHospital", "hospitalName")
      });
    case actionTypes.Query_Bd_HOS_CLSAA:
      return Object.assign({}, state, {
        hospitalClassList: action.data,
        mappedHospitalClass: MyUtil.mapListToObject(action.data)
      });
    case
    actionTypes.Query_Bd_HOS_TYPE:
      return Object.assign({}, state, {
        hospitalTypeList: action.data,
        mappedHospitalType: MyUtil.mapListToObject(action.data)
      });
    case
    actionTypes.Query_Bd_BELONG_SYSTEM:
      return Object.assign({}, state, {
        belongSystemList: action.data,
        mappedBelongSystem: MyUtil.mapListToObject(action.data)
      });
    case
    actionTypes.Query_Bd_Year:
      return Object.assign({}, state, {
        yearList: action.data,
        mappedYear: MyUtil.mapListToObject(action.data)
      });
    case
    actionTypes.Query_Bd_Is_Stop:
      return Object.assign({}, state, {
        isStopList: [{codeId: "1", codeName: "是"}, {codeId: "0", codeName: "否"}],
        mappedIsStop: MyUtil.mapListToObject([{codeId: "1", codeName: "是"}, {codeId: "0", codeName: "否"}])
      });
    case
    actionTypes.Query_Bd_Is_Single:
      return Object.assign({}, state, {
        isSingleList: action.data,
        mappedIsSingle: MyUtil.mapListToObject(action.data)
      });
    case
    actionTypes.Query_Bd_Dept_Type:
      return Object.assign({}, state, {
        deptTypeList: action.data,
        mappedDeptType: MyUtil.mapListToObject(action.data)
      });
    case
    actionTypes.Query_Bd_Assets:
      return Object.assign({}, state, {
        dataSource: action.data,
        selectedRowKeys: []
      });
    case
    actionTypes.Query_Capital_Source:
      return Object.assign({}, state, {
        capitalSourceList: action.data,
        mappedCapitalSource: MyUtil.mapListToObject(action.data)
      });
    case
    actionTypes.QUERY_RE_WAGE:
      return Object.assign({}, state, {
        dataSource: action.data,
        selectedRowKeys: []
      });
    case
    actionTypes.Query_Re_Cost:
      return Object.assign({}, state, {
        dataSource: action.data,
        selectedRowKeys: []
      });
    case
    actionTypes.QUERY_HIADEPT:
      return Object.assign({}, state, {
        dataSource: action.data,
        selectedRowKeys: []
      });
    case  actionTypes.Query_StandartDept:
      return Object.assign({}, state, {
        dataSource: action.data,
        selectedRowKeys: []
      });
    case  actionTypes.Query_Bd_Charges_Hos:
      return Object.assign({}, state, {
        chargesHostList: action.data,
        selectedRowKeys: []
      });
    case  actionTypes.Query_Bd_Charges_Sta:
      return Object.assign({}, state, {
        dataSource: action.data,
      });
    default:
      return state;
  }
};


export default bdDict;

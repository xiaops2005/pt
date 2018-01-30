/**
 * Created by Bojc on 2017/6/14.
 */
import NetUtil from 'constants/httpUtil';
import * as types from './ActionTypes';

//供应商Action Begin
export function LoadProductList(data) {
  return {type: types.Demo_Supplier_InitData, data: data};
}

export function GetProductListByFilter(data,params){
  return {type: types.Demo_Supplier_SearchData, data: data,params:params};
}

export function NewSetFilter(data) {
  return {type: types.Demo_Supplier_SetFilter, data: data};
}

//供应商Action End

//经理自助Action Begin

export function AprePage(data){
  return {type:types.Demo_ManagerSelfSer_prePage,data};
}
export function AafterPage(data){
  return {type:types.Demo_ManagerSelfSer_afterPage,data};
}
export function AsucuessApi(data){
  return {type:types.Demo_ManagerSelfSer_AsucuessApi,data};
}

//经理自助Action End

//我的报销单Action Begin

export function changeAccountRightTop(documentModel) {
  console.log("actions....: changeAccountRightTop");
  return {type:'ACCOUNT_RIGHT_TOP',documentModel};
}

export function LoadDataLeft(result){
  return {type: 'showLeftInfo', data:{array: result}};
}

export function LoadDataByInfo(result){
  return {type: 'showInfo', data:result};
}
//modal框实例
 export function changeModalInfo(result){
   return {type:'changeInfo',data:result};
 }
//我的报销单Action End
//用户查询Action
export function demoUserQueryAction(data) {
  console.log("userQueryAction....");
  return {type:types.Demo_USER_QUERY,data:data};
}
//用户查询Action end

//领导自助Action Start
export function loadedData(data) {
  return {type: "loadedData", data: data};
}
//领导自助Action End



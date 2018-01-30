import * as actionTypes from './ActionTypes';
import * as API from './RestUrls';
import PromiseUtil from '../../../../../../constants/PromiseUtil'

const queryDataSource = () => {
  return {
    type: actionTypes.Query_Data_Source,
  };
};

const QueryTemplateEntityAdded = () => {
  return {
    type: actionTypes.Query_Template_Entity_Added,
    payload: PromiseUtil.doPost(`${API.Query_Template_Entity}?templateName=&dbTableName=`)
  };
};


const queryTemplateColumns = (templateId) => {
  return {
    type: actionTypes.Get_Template_Columns,
    payload: PromiseUtil.doPost(`${API.Get_Template_Columns}?templateId=${templateId}`)
  };
};

const saveTemplateColumns = (templateId, templateColumnList, succFn, errFn) => {
  return {
    type: actionTypes.Save_Template_Columns,
    payload: PromiseUtil.doPost(`${API.Save_Template_Columns}?templateId=${templateId}`, templateColumnList, succFn, errFn)
  };
};

const queryTemplateEntity = (templateName, dbTableName,succFn) => {
  return {
    type: actionTypes.Query_Template_Entity,
    payload: PromiseUtil.doPost(`${API.Query_Template_Entity}?templateName=${templateName}&dbTableName=${dbTableName}`,{},succFn)
  };
};

const getTemplateEntity = () => {
  return {
    type: actionTypes.Get_Template_Entity,
    payload: PromiseUtil.doGet(API.Get_Template_Entity)
  };
};

const saveTemplateEntity = (selectedDataList,succFn) => {
  return {
    type: actionTypes.Save_Template_Entity,
    payload: PromiseUtil.doPost(API.Save_Template_Entity, selectedDataList,succFn)
  };
};

const deleteTemplateEntity = (pkHospitals,succFn) => {
  return {
    type: actionTypes.Delete_Template_Entity,
    payload: PromiseUtil.doPost(`${API.Delete_Template_Entity}?templateIds=${pkHospitals}`,{},succFn)
  };
};

export {
  queryDataSource, QueryTemplateEntityAdded, queryTemplateColumns, saveTemplateColumns
  , queryTemplateEntity, saveTemplateEntity
  , getTemplateEntity,deleteTemplateEntity
}

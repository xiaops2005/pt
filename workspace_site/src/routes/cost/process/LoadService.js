import {queryHiaDept} from "../page/dict/models/actions/bdDictAction";
import NetUtil from "../../../constants/httpUtil";
import PromiseUtil from "../../../constants/PromiseUtil";

class LoadService {

}

class BdDictService {
  queryBdCode(codeTypeId, successFn) {
    // const {dispatch} = props;
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "bdCodeServiceImpl");
    dc.setParameter('_methodName', 'findByCodeTypeId');
    dc.setParameter('_methodParameterTypes', 'String');
    dc.setParameter("_parameters", "codeTypeId");
    dc.setParameter("codeTypeId", codeTypeId);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }
}

class mergeDictService {
  query(SerachObj, successFn) {
    var dc = new window.DataCenter();

    var ds = new window.DataStore();
    ds.setRowSetName("com.viewhigh.entity.BdDeptMerge");
    let array = [];
    array.push(SerachObj);
    let rowSet = new window.RowSet(array);

    ds.setRowSet(rowSet);
    dc.addDataStore("mergeDict", ds);

    dc.setParameter("_boId", "mergeDictServiceImpl");
    dc.setParameter('_methodName', 'findByParam');
    dc.setParameter('_methodParameterTypes', 'com.viewhigh.entity.BdDeptMerge');
    dc.setParameter("_parameters", "mergeDict");
    // dc.setParameter("userName", "你好啊");
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  save(models, successFn) {
    var dc = new window.DataCenter();

    var ds = new window.DataStore();
    ds.setRowSetName("com.viewhigh.vo.MergeDictVO");
    let rowSet = new window.RowSet(models);

    ds.setRowSet(rowSet);
    dc.addDataStore("mergeDictVOs", ds);

    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "mergeDictServiceImpl");
    dc.setParameter('_methodName', 'saveList');
    dc.setParameter('_methodParameterTypes', 'java.util.List<com.viewhigh.vo.MergeDictVO>');
    dc.setParameter("_parameters", "mergeDictVOs");
    // dc.setParameter("userName", "你好啊");
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  del(selectedIds, successFn) {


    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "mergeDictServiceImpl");
    dc.setParameter('_methodName', 'deleteByIds');
    dc.setParameter('_methodParameterTypes', 'String');
    dc.setParameter("_parameters", "ids");
    dc.setParameter("ids", selectedIds);
    // dc.setParameter("userName", "你好啊");
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }
}


class ProjectCategoryService {
  //项目大类
  queryCategory(pkHospital, classCode, className, isStop, successFn) {

    // debugger;

    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "bdChargeClassServiceImpl");
    dc.setParameter('_methodName', 'getList');
    dc.setParameter('_methodParameterTypes', 'String,String,String,String');
    dc.setParameter("_parameters", "hospital,itemCode,itemName,isStop");

    dc.setParameter("hospital", pkHospital);
    dc.setParameter("itemCode", classCode);
    dc.setParameter("itemName", className);
    dc.setParameter("isStop", isStop);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  save(models, successFn) {
    var dc = new window.DataCenter();
    var ds = new window.DataStore();
    ds.setRowSetName("com.viewhigh.excel.domain.entity.BdChargesClass");
    let rowSet = new window.RowSet(models);
    ds.setRowSet(rowSet);
    dc.addDataStore("lst", ds);
    dc.setParameter("_boId", "bdChargeClassServiceImpl");
    dc.setParameter('_methodName', 'save');
    dc.setParameter('_methodParameterTypes', 'java.util.List<com.viewhigh.excel.domain.entity.BdChargesClass>');
    dc.setParameter("_parameters", "lst");
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  queryHospitals(successFn) {
    var dc = new window.DataCenter();
    var ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "bdChargeClassServiceImpl");
    dc.setParameter('_methodName', 'getHospital');
    dc.setParameter('_methodParameterTypes', '');
    dc.setParameter("_parameters", "");
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  delete(ids, successFn) {

    var dc = new window.DataCenter();
    var ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "bdChargeClassServiceImpl");
    dc.setParameter('_methodName', 'delete');
    dc.setParameter('_methodParameterTypes', 'String');
    dc.setParameter("_parameters", "arr");
    dc.setParameter("arr", ids);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  startOrStop(ids, flag, successFn) {
    var dc = new window.DataCenter();
    var ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "bdChargeClassServiceImpl");
    dc.setParameter('_methodName', 'startOrStop');
    dc.setParameter('_methodParameterTypes', 'String,String');
    dc.setParameter("_parameters", "arr,flag");
    dc.setParameter("arr", ids);
    dc.setParameter("flag", flag);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }


}

class IdentificationService {
  //医护标识
  query(hospital, identifyCode, identifyName, isStop, successFn) {
    var dc = new window.DataCenter();
    var ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "bdMedicalIdentifyServiceImpl");
    dc.setParameter('_methodName', 'getList');
    dc.setParameter('_methodParameterTypes', 'String,String,String,String');
    dc.setParameter("_parameters", "hospital,itemCode,itemName,isStop");
    dc.setParameter("hospital", hospital);
    dc.setParameter("itemCode", identifyCode);
    dc.setParameter("itemName", identifyName);
    dc.setParameter("isStop", isStop);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  save(models, successFn) {
    var dc = new window.DataCenter();
    var ds = new window.DataStore();
    ds.setRowSetName("com.viewhigh.excel.domain.entity.BdMedicalIdentify");
    let rowSet = new window.RowSet(models);
    ds.setRowSet(rowSet);
    dc.addDataStore("list", ds);
    dc.setParameter("_boId", "bdMedicalIdentifyServiceImpl");
    dc.setParameter('_methodName', 'saveList');
    dc.setParameter('_methodParameterTypes', 'java.util.List<com.viewhigh.excel.domain.entity.BdMedicalIdentify>');
    dc.setParameter("_parameters", "list");
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  delete(ids, successFn) {
    console.log("deleteIds:" + ids);
    var dc = new window.DataCenter();
    var ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "bdMedicalIdentifyServiceImpl");
    dc.setParameter('_methodName', 'delete');
    dc.setParameter('_methodParameterTypes', 'String');
    dc.setParameter("_parameters", "arr");
    dc.setParameter("arr", ids);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  queryHospitals(successFn) {
    var dc = new window.DataCenter();
    var ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "bdMedicalIdentifyServiceImpl");
    dc.setParameter('_methodName', 'getHospital');
    dc.setParameter('_methodParameterTypes', '');
    dc.setParameter("_parameters", "");
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  startOrStop(ids, flag, successFn) {
    var dc = new window.DataCenter();
    var ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "bdMedicalIdentifyServiceImpl");
    dc.setParameter('_methodName', 'startOrStop');
    dc.setParameter('_methodParameterTypes', 'String,String');
    dc.setParameter("_parameters", "arr,flag");
    dc.setParameter("arr", ids);
    dc.setParameter("flag", flag);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }
}

class HospitalService {
  queryBdHospitalAll(successFn) {
    // const {dispatch} = props;
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "bdHospitalServiceImpl");
    dc.setParameter('_methodName', 'findALl');
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  queryBdHospital(hospitalCode, hospitalName, hospitalTypeId, hospitalClassId, successFn) {
    // const {dispatch} = props;
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "bdHospitalServiceImpl");
    dc.setParameter('_methodName', 'findByParam');
    dc.setParameter('_methodParameterTypes', 'String,String,String,String');
    dc.setParameter("_parameters", "hospitalCode,hospitalName,hospitalTypeId,hospitalClassId");
    dc.setParameter("hospitalCode", hospitalCode);
    dc.setParameter("hospitalName", hospitalName);
    dc.setParameter("hospitalTypeId", hospitalTypeId);
    dc.setParameter("hospitalClassId", hospitalClassId);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  deleteBdHospital(pkHospitals, successFn) {
    // const {dispatch} = props;
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "bdHospitalServiceImpl");
    dc.setParameter('_methodName', 'deleteByIds');
    dc.setParameter('_methodParameterTypes', 'String');
    dc.setParameter("_parameters", "pkHospitals");
    dc.setParameter("pkHospitals", pkHospitals);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  saveBdHospital(bdHospitalList, successFn) {
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    let rowSet = new window.RowSet(bdHospitalList);
    ds.setRowSet(rowSet);
    ds.setRowSetName("com.viewhigh.entity.BdHospital");
    dc.addDataStore("list", ds);
    dc.setParameter("_boId", "bdHospitalServiceImpl");
    dc.setParameter('_methodName', 'saveBdHospital');
    dc.setParameter('_methodParameterTypes', 'java.util.List<com.viewhigh.entity.BdHospital>');
    dc.setParameter("_parameters", "list");
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }
}

class DeptService {
  queryBdDept(year, pkHospital, deptCode, deptName, belongSystem, isStop, successFn) {
    // const {dispatch} = props;
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "bdDeptServiceImpl");
    dc.setParameter('_methodName', 'findByParam');
    dc.setParameter('_methodParameterTypes', 'Integer,String,String,String,String,Integer');
    dc.setParameter("_parameters", "year, pkHospital, deptCode, deptName, belongSystem, isStop");
    dc.setParameter("year", year);
    dc.setParameter("pkHospital", pkHospital);
    dc.setParameter("deptCode", deptCode);
    dc.setParameter("deptName", deptName);
    dc.setParameter("belongSystem", belongSystem);
    dc.setParameter("isStop", isStop);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  deleteBdDept(pkDepts, successFn) {
    // const {dispatch} = props;
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "bdDeptServiceImpl");
    dc.setParameter('_methodName', 'deleteByIds');
    dc.setParameter('_methodParameterTypes', 'String');
    dc.setParameter("_parameters", "pkDepts");
    dc.setParameter("pkDepts", pkDepts);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  saveBdDept(bdDeptList, successFn) {
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    let rowSet = new window.RowSet(bdDeptList);
    ds.setRowSet(rowSet);
    ds.setRowSetName("com.viewhigh.excel.domain.entity.BdDept");
    dc.addDataStore("list", ds);
    dc.setParameter("_boId", "bdDeptServiceImpl");
    dc.setParameter('_methodName', 'saveBdDept');
    dc.setParameter('_methodParameterTypes', 'java.util.List<com.viewhigh.excel.domain.entity.BdDept>');
    dc.setParameter("_parameters", "list");
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }
}

class MaterialsService {
  queryBdMaterialsAll(successFn) {
    // const {dispatch} = props;
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "bdMaterialsServiceImpl");
    dc.setParameter('_methodName', 'findAll');
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  queryBdMaterials(year, pkHospital, materialsCode, materialsName, isSingle, isStop, successFn) {
    // const {dispatch} = props;
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "bdMaterialsServiceImpl");
    dc.setParameter('_methodName', 'findByParam');
    dc.setParameter('_methodParameterTypes', 'Integer,String,String,String,String,String');
    dc.setParameter("_parameters", "year,pkHospital,materialsCode,materialsName,isSingle,isStop");
    dc.setParameter("year", year);
    dc.setParameter("pkHospital", pkHospital);
    dc.setParameter("materialsCode", materialsCode);
    dc.setParameter("materialsName", materialsName);
    dc.setParameter("isSingle", isSingle);
    dc.setParameter("isStop", isStop);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  deleteBdMaterials(pkMaterials, successFn) {
    // const {dispatch} = props;
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "bdMaterialsServiceImpl");
    dc.setParameter('_methodName', 'deleteByIds');
    dc.setParameter('_methodParameterTypes', 'String');
    dc.setParameter("_parameters", "pkMaterials");
    dc.setParameter("pkMaterials", pkMaterials);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  saveBdMaterials(bdMaterialsList, successFn) {
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    let rowSet = new window.RowSet(bdMaterialsList);
    ds.setRowSet(rowSet);
    ds.setRowSetName("com.viewhigh.excel.domain.entity.BdMaterials");
    dc.addDataStore("list", ds);
    dc.setParameter("_boId", "bdMaterialsServiceImpl");
    dc.setParameter('_methodName', 'saveBdMaterials');
    dc.setParameter('_methodParameterTypes', 'java.util.List<com.viewhigh.excel.domain.entity.BdMaterials>');
    dc.setParameter("_parameters", "list");
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }
}
class ReMateDetailService {
  queryReMateDetail(year,pkHospital,materialsDeptCode,materialsDeptName,isCharges,pkMaterials, successFn) {
    // const {dispatch} = props;
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "reMateDetailServiceImpl");
    dc.setParameter('_methodName', 'findByParam');
    dc.setParameter('_methodParameterTypes', 'Integer,String,String,String,String,String');
    dc.setParameter("_parameters", "year,pkHospital,materialsDeptCode,materialsDeptName,isCharges,pkMaterials");
    dc.setParameter("year", year);
    dc.setParameter("pkHospital", pkHospital);
    dc.setParameter("materialsDeptCode", materialsDeptCode);
    dc.setParameter("materialsDeptName", materialsDeptName);
    dc.setParameter("isCharges", isCharges);
    dc.setParameter("pkMaterials", pkMaterials);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  exportReMateDetail(year,pkHospital,materialsDeptCode,materialsDeptName,isCharges,pkMaterials, successFn) {
    // const {dispatch} = props;
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "reMateDetailServiceImpl");
    dc.setParameter('_methodName', 'exportReMateDetail');
    dc.setParameter('_methodParameterTypes', 'Integer,String,String,String,String,String');
    dc.setParameter("_parameters", "year,pkHospital,materialsDeptCode,materialsDeptName,isCharges,pkMaterials");
    dc.setParameter("year", year);
    dc.setParameter("pkHospital", pkHospital);
    dc.setParameter("materialsDeptCode", materialsDeptCode);
    dc.setParameter("materialsDeptName", materialsDeptName);
    dc.setParameter("isCharges", isCharges);
    dc.setParameter("pkMaterials", pkMaterials);
    //post请求
    NetUtil.post('/api/restful/commonMethod', dc, successFn);
  }
}

class AssetsService {
  queryBdAssets(accYear, pkHospital, assetsCode, assetsName, capitalSourceId, isStopId, standardFlag, successFn) {
    // const {dispatch} = props;
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "bdAssetsServiceImpl");
    dc.setParameter('_methodName', 'findByParam');
    dc.setParameter('_methodParameterTypes', 'String,String,String,String,String,String,Integer');
    dc.setParameter("_parameters", "accYear,pkHospital,assetsCode,assetsName,capitalSourceId,isStopId,standardFlag");
    dc.setParameter("accYear", accYear);
    dc.setParameter("pkHospital", pkHospital);
    dc.setParameter("assetsCode", assetsCode);
    dc.setParameter("assetsName", assetsName);
    dc.setParameter("capitalSourceId", capitalSourceId);
    dc.setParameter("isStopId", isStopId);
    dc.setParameter("standardFlag", standardFlag);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  deleteBdAssets(pkAssets, successFn) {
    // const {dispatch} = props;
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "bdAssetsServiceImpl");
    dc.setParameter('_methodName', 'deleteByIds');
    dc.setParameter('_methodParameterTypes', 'String');
    dc.setParameter("_parameters", "pkAssets");
    dc.setParameter("pkAssets", pkAssets);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  saveBdAssets(bdHospitalList, successFn) {
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    let rowSet = new window.RowSet(bdHospitalList);
    ds.setRowSet(rowSet);
    ds.setRowSetName("com.viewhigh.entity.BdAssets");
    dc.addDataStore("list", ds);
    dc.setParameter("_boId", "bdAssetsServiceImpl");
    dc.setParameter('_methodName', 'saveBdAssets');
    dc.setParameter('_methodParameterTypes', 'java.util.List<com.viewhigh.entity.BdAssets>');
    dc.setParameter("_parameters", "list");
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }
}

class ReCostService {

  queryReCost(year, pkHospital, deptCode, deptName, successFn) {
    // const {dispatch} = props;
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "reCostServiceImpl");
    dc.setParameter('_methodName', 'findByParam');
    dc.setParameter('_methodParameterTypes', 'String,String,String,String');
    dc.setParameter("_parameters", "year,pkHospital,deptCode,deptName");
    dc.setParameter("year", year);
    dc.setParameter("pkHospital", pkHospital);
    dc.setParameter("deptCode", deptCode);
    dc.setParameter("deptName", deptName);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }
  exportExcel(year, pkHospital, deptCode, deptName, successFn)
  {
    //post请求
    var dc = new window.DataCenter();
    var ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "reCostServiceImpl");
    dc.setParameter('_methodName', 'exportExcel');
    dc.setParameter('_methodParameterTypes', 'String,String,String,String');
    dc.setParameter("_parameters", "year,pkHospital,deptCode,deptName");
    dc.setParameter("year", year);
    dc.setParameter("pkHospital", pkHospital);
    dc.setParameter("deptCode",deptCode);
    dc.setParameter("deptName", deptName);
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }
}

export const ExecuteMethodService = {
  /**
   * 执行方法--查询
   * @param params  查询参数 { hospital: 医院, methodCode: 方法编码, methodName: 方法名, isStopId: 是否停用 }
   * @param successFunc
   * @param errFunc
   */
  queryExecuteMethod: (params, successFunc, errFunc) => {
    var dc = new window.DataCenter();
    var ds = new window.DataStore();
    dc.addDataStore("executeMethod", ds);
    dc.setParameter("_boId", "BdExecuteMethodService");
    dc.setParameter('_methodName', 'getExecuteMethodList');
    dc.setParameter('_methodParameterTypes', 'String,String,String,Integer');
    dc.setParameter("_parameters", "hospital,methodCode,methodName,stopFlag");
    dc.setParameter("hospital", params.hospital ? params.hospital : null);
    dc.setParameter("methodCode", params.methodCode ? params.methodCode : null);
    dc.setParameter("methodName", params.methodName ? params.methodName : null);
    dc.setParameter("stopFlag", params.isStopId ? params.isStopId : null);

    NetUtil.post("/api/commonProcessor/commonMethod", dc, (res) => {
      successFunc(res.getSinglePrimaryList());
    }, errFunc);
  },

  /**
   * 执行方法--保存
   *
   * @param params  -- {data: 保存对象}
   * @param successFunc
   * @param errFunc
   */
  saveExecuteMethod: (params, successFunc, errFunc) => {
    console.log("保存服务 params：", params);
    let dc = new window.DataCenter();//创建一个数据中心
    let ds = new window.DataStore();
    let rowSet = new window.RowSet(params.data);
    ds.setRowSet(rowSet);
    ds.setRowSetName("com.viewhigh.entity.BdExecuteMethod")
    dc.addDataStore("saveList", ds);

    //指定请求对应的后端java业务类的名称
    dc.setParameter("_boId", "BdExecuteMethodService");
    //指定请求对应的后端java业务类对应的业务方法
    dc.setParameter('_methodName', 'saveExecuteMethodList');
    dc.setParameter('_methodParameterTypes', 'java.util.List<com.viewhigh.entity.BdExecuteMethod>');
    dc.setParameter("_parameters", "saveList");

    NetUtil.post("/api/commonProcessor/commonMethod", dc, (res) => {
      successFunc(res);
    }, errFunc);
  },

  /**
   * 执行方法--删除
   * @param params  { delKeys ：删除数据的主键 | String 主键之间用','分割  }
   * @param successFunc
   * @param errFunc
   */
  delExecuteMethod: (params, successFunc, errFunc) => {
    console.log("删除服务 params：", params);
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("executeMethodDel", ds);
    dc.setParameter("_boId", "BdExecuteMethodService");
    dc.setParameter('_methodName', 'delExecuteMethodList');
    dc.setParameter('_methodParameterTypes', 'String');
    dc.setParameter("_parameters", "delKeys");
    dc.setParameter("delKeys", params.delKeys);

    NetUtil.post("/api/commonProcessor/commonMethod", dc, (res) => {
      successFunc(res);
    }, errFunc);
  },

  /**
   * 执行方法--启用、停用
   * @param params  { data: String |‘启用/停用’数据的主键 用‘,’分割,  startOrStopFlag:  Integer | 1-启用/0-停用}
   * @param successFunc
   * @param errFunc
   */
  startOrStopExecuteMethod: (params, successFunc, errFunc) => {
    console.log("启用、停用服务 params：", params);
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("executeMethodDel", ds);
    dc.setParameter("_boId", "BdExecuteMethodService");
    dc.setParameter('_methodName', 'startOrStopExecuteMethodList');
    dc.setParameter('_methodParameterTypes', 'String,Integer');
    dc.setParameter("_parameters", "delKeys,startOrStopFlag");
    dc.setParameter("delKeys", params.data);
    dc.setParameter("startOrStopFlag", params.startOrStopFlag);	// 启用/停用操作

    NetUtil.post("/api/commonProcessor/commonMethod", dc, (res) => {
      successFunc(res);
    }, errFunc);
  },

  /**
   * 执行方法 -- 对象字段校验
   *
   * @param params  校验数据
   * @param successFunc
   * @param errFunc
   */
  checkoutExecuteMethodList: (params, successFunc, errFunc) => {
    //checkoutSaveObjList
  }
}

/**
 *人员经费
 */
class ReWageService {
  /**
   * 查询
   * @param year
   * @param pkHospital
   * @param deptCode
   * @param successFn
   */
  queryReWage(year, pkHospital, deptCode, successFn) {
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "reWageServiceImpl");
    dc.setParameter('_methodName', 'getReWage');
    dc.setParameter('_methodParameterTypes', 'String,String,String');
    dc.setParameter("_parameters", "year, pkHospital, deptCode");
    dc.setParameter("year", year);
    dc.setParameter("pkHospital", pkHospital);
    dc.setParameter("deptCode", deptCode);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }


}

/**
 * hia科室
 */
class HiaDeptSevice {
  /**
   * 查询
   * @param hiaCode
   * @param isStop
   * @param successFn
   */
  queryHiaDept(hiaCode, isStop, successFn) {
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "hiaDeptServiceImpl");
    dc.setParameter('_methodName', 'queryBdDeptHia');
    dc.setParameter('_methodParameterTypes', 'String,String');
    dc.setParameter("_parameters", "hiaCode, isStop");
    dc.setParameter("hiaCode", hiaCode);
    dc.setParameter("isStop", isStop);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  /**
   * 保存
   * @param bdHospitalList
   * @param successFn
   */
  saveHiaDept(hiaDeptList, successFn) {
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    let rowSet = new window.RowSet(hiaDeptList);
    ds.setRowSet(rowSet);
    ds.setRowSetName("com.viewhigh.entity.BdDeptHia");
    dc.addDataStore("list", ds);
    dc.setParameter("_boId", "hiaDeptServiceImpl");
    dc.setParameter('_methodName', 'saveHiaDept');
    dc.setParameter('_methodParameterTypes', 'java.util.List<com.viewhigh.entity.BdDeptHia>');
    dc.setParameter("_parameters", "list");
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  deleteHiaDept(pkDeptHia, successFn) {
    // const {dispatch} = props;
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "hiaDeptServiceImpl");
    dc.setParameter('_methodName', 'deleteByIds');
    dc.setParameter('_methodParameterTypes', 'String');
    dc.setParameter("_parameters", "pkDeptHia");
    dc.setParameter("pkDeptHia", pkDeptHia);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }
}

class UploadService {
  //上传
  upload(action, file, templateName, jsonTemplate, successFn) {
    var fd = new FormData();
    fd.append("file", file);
    fd.append("jsonTemplate", jsonTemplate);
    fd.append("hospital", "301医院");
    fd.append("creator", "pc");
    // //post请求
    NetUtil.ajaxPost('/api/' + action, fd, successFn);

  }
}

export class MaterialDetailService {
  queryBdMaterials(year, pkHospital, materialsDeptCode, materialsDeptName, isCharges, materialsCode, successFn) {
    // const {dispatch} = props;
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "reMateDetailServiceImpl");
    dc.setParameter('_methodName', 'findByParam');
    dc.setParameter('_methodParameterTypes', 'Integer,String,String,String,String,String');
    dc.setParameter("_parameters", "year,pkHospital,materialsDeptCode,materialsDeptName,isCharges,materialsCode");
    dc.setParameter("year", year);
    dc.setParameter("pkHospital", pkHospital);
    dc.setParameter("materialsCode", materialsDeptName);
    dc.setParameter("materialsName", materialsDeptName);
    dc.setParameter("isCharges", isCharges);
    dc.setParameter("materialsCode", materialsCode);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  deleteBdMaterials(pkMaterials, successFn) {
    // const {dispatch} = props;
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "bdMaterialsServiceImpl");
    dc.setParameter('_methodName', 'deleteByIds');
    dc.setParameter('_methodParameterTypes', 'String');
    dc.setParameter("_parameters", "pkMaterials");
    dc.setParameter("pkMaterials", pkMaterials);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  saveBdMaterials(bdMaterialsList, successFn) {
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    let rowSet = new window.RowSet(bdMaterialsList);
    ds.setRowSet(rowSet);
    ds.setRowSetName("com.viewhigh.excel.domain.entity.BdMaterials");
    dc.addDataStore("list", ds);
    dc.setParameter("_boId", "bdMaterialsServiceImpl");
    dc.setParameter('_methodName', 'saveBdMaterials');
    dc.setParameter('_methodParameterTypes', 'java.util.List<com.viewhigh.excel.domain.entity.BdMaterials>');
    dc.setParameter("_parameters", "list");
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }
}

class TemplateService {
  query(templateName,dbTableName, successFn) {
    const data = new FormData();
    data.append("templateName",templateName);
    data.append("dbTableName",dbTableName);
    //post请求
    NetUtil.ajaxPost('api/query-template-entity', data, successFn);
  }

  save(models, successFn) {
    const data = new FormData();
    data.append("fileTemplateList",models);
    //post请求
    NetUtil.ajaxPost('api/save-template-entity', data, successFn);
  }

  delete(ids, successFn) {
    const data = new FormData();
    data.append("fileTemplateIds",ids);
    //post请求
    NetUtil.ajaxPost('api/delete-template-entity', data, successFn);
  }

  queryTemplates(successFn) {
    NetUtil.ajaxGet("api/get-template-entity","",successFn)
  }

}

export class ApproveRecordService {
  queryApproveRecord(successFn) {
    // const {dispatch} = props;
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "approveRecordServiceImpl");
    dc.setParameter('_methodName', 'findAll');
    // dc.setParameter('_methodParameterTypes', 'Integer,String,String,String,String,String');
    // dc.setParameter("_parameters", "year,pkHospital,materialsDeptCode,materialsDeptName,isCharges,materialsCode");
    // dc.setParameter("year", year);
    // dc.setParameter("pkHospital", pkHospital);
    // dc.setParameter("materialsCode", materialsDeptName);
    // dc.setParameter("materialsName", materialsDeptName);
    // dc.setParameter("isCharges", isCharges);
    // dc.setParameter("materialsCode", materialsCode);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }
}

class  ChargesHosService{
  queryChargesHos(successFn) {
    var dc = new window.DataCenter();
    var ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "bdChargesHosServiceImpl");
    dc.setParameter('_methodName', 'findAll');
    NetUtil.post('/api/commonProcessor/commonMethod',dc,successFn);
  }
}

class ChargesStaDictService {

  query(chargesCode, chargesName, successFn) {
    // const {dispatch} = props;
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "bdChargesStaServiceImpl");
    dc.setParameter('_methodName', 'findByParam');
    dc.setParameter('_methodParameterTypes', 'String,String');
    dc.setParameter("_parameters", "chargesCode,pkHospital");
    dc.setParameter("chargesCode", chargesCode);
    dc.setParameter("chargesName", chargesName);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

}

class AnalysisCollectService {

  query(year, pkHospital, successFn) {
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "");
    dc.setParameter('_methodName', '');
    dc.setParameter('_methodParameterTypes', 'String,String');
    dc.setParameter("_parameters", "year, pkHospital");
    dc.setParameter("year", year);
    dc.setParameter("pkHospital", pkHospital);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

}

export {BdDictService}
export {mergeDictService}
export {IdentificationService}
export {LoadService}
export {ProjectCategoryService}
export {HospitalService}
export {DeptService}
export {MaterialsService}
export {ReMateDetailService}
export {AssetsService}
export {ReWageService}
export {UploadService}
export {ReCostService}
export {TemplateService}
export {HiaDeptSevice}
export {ChargesHosService}
export {ChargesStaDictService}
export {AnalysisCollectService}

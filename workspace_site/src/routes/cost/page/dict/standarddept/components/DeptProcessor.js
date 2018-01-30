import HttpUtil from "../../../../../../constants/httpUtil";
import moment from 'moment';
import { message } from 'antd';


class DeptProcessor {
  queryData(hospital,deptCode,deptName,belongSystem,isStop,fn) {
    var dc = new window.DataCenter();
    var ds = new window.DataStore();
    dc.setParameter("_boId", "standardDeptServiceImpl");
    dc.setParameter('_methodName', 'getStandardDept');
    dc.setParameter('_methodParameterTypes', 'String,String,String,String,String');
    dc.setParameter("_parameters","hospital,deptCode,deptName,belongSystem,isStop");
    dc.setParameter("hospital",hospital);
    dc.setParameter("deptCode",deptCode);
    dc.setParameter("deptName",deptName);
    dc.setParameter("belongSystem",belongSystem);
    dc.setParameter("isStop",isStop);
    HttpUtil.post('/api/commonProcessor/commonMethod',dc,fn);
  }

  save(deptList,fn) {
    debugger;
    var dc = new window.DataCenter();//创建一个数据中心
    var ds = new window.DataStore();
    let rowSet = new window.RowSet(deptList);
    ds.setRowSetName("com.viewhigh.excel.domain.entity.BdDept")
    ds.setRowSet(rowSet);
    dc.addDataStore("list", ds);
    //指定请求对应的后端java业务类的名称
    dc.setParameter("_boId", "standardDeptServiceImpl");
    //指定请求对应的后端java业务类对应的业务方法
    dc.setParameter('_methodName', 'save');
    dc.setParameter('_methodParameterTypes','java.util.List<com.viewhigh.excel.domain.entity.BdDept>');
    dc.setParameter("_parameters", "list");
    HttpUtil.post('/api/commonProcessor/commonMethod',dc,fn);
  }
  delete(arr,fn) {
    var dc = new window.DataCenter();//创建一个数据中心
    var ds = new window.DataStore();
    //指定请求对应的后端java业务类的名称
    dc.setParameter("_boId", "standardDeptServiceImpl");
    //指定请求对应的后端java业务类对应的业务方法
    dc.setParameter('_methodName', 'delete');
    dc.setParameter('_methodParameterTypes','String');
    dc.setParameter("_parameters","arr");
    dc.setParameter("arr",arr);
    HttpUtil.post('/api/commonProcessor/commonMethod',dc,fn);
  }

  startOrStop(arr,flag,fn) {
    var dc = new window.DataCenter();//创建一个数据中心
    var ds = new window.DataStore();
    //指定请求对应的后端java业务类的名称
    dc.setParameter("_boId", "standardDeptServiceImpl");
    //指定请求对应的后端java业务类对应的业务方法
    dc.setParameter('_methodName', 'startOrStop');
    dc.setParameter('_methodParameterTypes','String,String');
    dc.setParameter("_parameters","arr,flag");
    dc.setParameter("arr",arr);
    dc.setParameter("flag",flag);
    HttpUtil.post('/api/commonProcessor/commonMethod',dc,fn);
  }
}
export default DeptProcessor;

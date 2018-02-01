// import {queryHiaDept} from "../page/dict/models/actions/bdDictAction";
import NetUtil from "../../../../constants/httpUtil";

export class  MovieMgrService{
  saveDoubanJson(doubanId, successFn) {
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "movieMgrServiceImpl");
    dc.setParameter('_methodName', 'saveDoubanJson');
    dc.setParameter('_methodParameterTypes', 'String');
    dc.setParameter("_parameters", "doubanId");
    dc.setParameter("doubanId", doubanId);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  saveDoubanValue(doubanId, successFn) {
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "movieMgrServiceImpl");
    dc.setParameter('_methodName', 'saveDoubanValue');
    dc.setParameter('_methodParameterTypes', 'String');
    dc.setParameter("_parameters", "doubanId");
    dc.setParameter("doubanId", doubanId);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }
}

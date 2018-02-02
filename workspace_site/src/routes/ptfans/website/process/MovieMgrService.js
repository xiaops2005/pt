// import {queryHiaDept} from "../page/dict/models/actions/bdDictAction";
import NetUtil from "../../../../constants/httpUtil";

class LoadService {

}

const pageSize = 50
export class  MovieMgrService{
  query(pageNum, successFn) {
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    // ds.setPageNumber(pageNum)
    // ds.setPageSize(pageSize)
    ds.setRowSetName("com.viewhigh.entity.MovieInfo");
    let array = [];
    array.push(array);
    let rowSet = new window.RowSet(array);
    dc.addDataStore("mi", ds);
    dc.setParameter("_boId", "movieMgrServiceImpl");
    dc.setParameter('_methodName', 'query');
    dc.setParameter('_methodParameterTypes', "com.viewhigh.entity.MovieInfo");
    dc.setParameter("_parameters", "mi");
    dc.setParameter("_parameters", "mi");
    dc.setParameter("_pageNumber", pageNum);
    dc.setParameter("_pageSize", pageSize);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  queryById(id, successFn) {
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "movieMgrServiceImpl");
    dc.setParameter('_methodName', 'queryById');
    dc.setParameter('_methodParameterTypes', "String");
    dc.setParameter("_parameters", "id");
    dc.setParameter("id", id);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  queryTorrents(movieId, successFn) {
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "torrentServiceImpl");
    dc.setParameter('_methodName', 'queryTorrents');
    dc.setParameter('_methodParameterTypes', "String");
    dc.setParameter("_parameters", "movieId");
    dc.setParameter("movieId", movieId);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

}

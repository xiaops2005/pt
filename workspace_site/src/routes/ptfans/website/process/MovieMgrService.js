// import {queryHiaDept} from "../page/dict/models/actions/bdDictAction";
import NetUtil from "../../../../constants/httpUtil";

class LoadService {

}

const pageSize = 40
export class  MovieMgrService{
  /**
   * 查询影片列表
   * @param pageNum
   * @param successFn
   */
  queryPublishMovieList(pageNum, successFn) {
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    // ds.setPageNumber(pageNum)
    // ds.setPageSize(pageSize)
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "movieMgrServiceImpl");
    dc.setParameter('_methodName', 'queryPublishMovieList');
    dc.setParameter('_methodParameterTypes', "");
    dc.setParameter("_pageNumber", pageNum);
    dc.setParameter("_pageSize", pageSize);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  /**
   * 通过影片ID查询影片内容
   * @param id
   * @param successFn
   */
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

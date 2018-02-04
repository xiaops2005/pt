// import {queryHiaDept} from "../page/dict/models/actions/bdDictAction";
import NetUtil from "../../../../constants/httpUtil";

class LoadService {

}

const pageSize = 40
const pageSizeSearch = 10
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

  searchMovie(keyword, pageNum, successFn) {
    console.log('查询第'+pageNum+'页,参数:',keyword)
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "movieMgrServiceImpl");
    dc.setParameter('_methodName', 'searchMovie');
    dc.setParameter('_methodParameterTypes', "String");
    dc.setParameter("_parameters", "keyword");
    dc.setParameter("_pageNumber", pageNum);
    dc.setParameter("_pageSize", pageSizeSearch);
    dc.setParameter("keyword", keyword);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  postFeedback(content, successFn) {
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "movieMgrServiceImpl");
    dc.setParameter('_methodName', 'postFeedback');
    dc.setParameter('_methodParameterTypes', "String");
    dc.setParameter("_parameters", "content");
    dc.setParameter("content", content);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

}

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

  query(params, successFn) {
    var dc = new window.DataCenter();
    var ds = new window.DataStore();
    ds.setRowSetName("com.viewhigh.entity.MovieInfo");
    let array = [];
    array.push(params);
    let rowSet = new window.RowSet(array);
    ds.setRowSet(rowSet);
    dc.addDataStore("mi", ds);
    dc.setParameter("_boId", "movieMgrServiceImpl");
    dc.setParameter('_methodName', 'query');
    dc.setParameter('_methodParameterTypes', 'com.viewhigh.entity.MovieInfo');
    dc.setParameter("_parameters", "mi");
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  saveOrUpdateTorrent(torrent, successFn) {
    var dc = new window.DataCenter();
    var ds = new window.DataStore();
    ds.setRowSetName("com.viewhigh.entity.TorrentInfo");
    let array = [];
    array.push(torrent);
    let rowSet = new window.RowSet(array);
    ds.setRowSet(rowSet);
    dc.addDataStore("torrentInfo", ds);
    dc.setParameter("_boId", "torrentServiceImpl");
    dc.setParameter('_methodName', 'saveOrUpdateTorrent');
    dc.setParameter('_methodParameterTypes', 'com.viewhigh.entity.TorrentInfo');
    dc.setParameter("_parameters", "torrentInfo");
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  queryTorrents(movieId, successFn) {
    console.log(movieId)
    var dc = new window.DataCenter();
    var ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "torrentServiceImpl");
    dc.setParameter('_methodName', 'queryTorrents');
    dc.setParameter('_methodParameterTypes', 'String');
    dc.setParameter("_parameters", "movieId");
    dc.setParameter("movieId", movieId);
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }

  delTorrent(id, successFn) {
    console.log(id)
    var dc = new window.DataCenter();
    var ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "torrentServiceImpl");
    dc.setParameter('_methodName', 'delTorrent');
    dc.setParameter('_methodParameterTypes', 'String');
    dc.setParameter("_parameters", "id");
    dc.setParameter("id", id);
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }
}

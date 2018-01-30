// import {queryHiaDept} from "../page/dict/models/actions/bdDictAction";
import NetUtil from "../../../../constants/httpUtil";

export class  MovieMgrService{
  getDoubanInfo(doubanId, successFn) {
    // let url = `http://api.douban.com/v2/movie/subject/${doubanId}`
    //http://api.douban.com/v2/movie/subject/26942674
    // console.log('获取豆瓣信息URL：' + url)
    let dc = new window.DataCenter();
    let ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "movieMgrServiceImpl");
    dc.setParameter('_methodName', 'getDoubanMovieInfo');
    dc.setParameter('_methodParameterTypes', 'String');
    dc.setParameter("_parameters", "doubanId");
    dc.setParameter("doubanId", doubanId);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, successFn);
  }
}

/**
 * Created by Bojc on 2017/6/14.
 */
import {LoadProductList,GetProductListByFilter,NewSetFilter} from 'routes/demo/models/actions/demoAction';
import NetUtil from 'constants/httpUtil';

export function LoadProductData(params, dispatch) {
  var dc = new window.DataCenter();
  dc.setParameter("_boId", "demoProductServiceImpl");
  dc.setParameter('_methodName', 'findPageByFilter');
  dc.setParameter('_methodParameterTypes', 'String,String,String,String');
  dc.setParameter('_methodParameterTypes', 'String,String,String,String,String,String');
  dc.setParameter("_parameters", "supplier,standard,company,categoryId,sort,sortName");
  dc.setParameter("supplier", params.supplier);
  dc.setParameter("standard", params.standard);
  dc.setParameter("company", params.company);
  dc.setParameter("categoryId", params.categoryId);
  dc.setParameter("sort", params.sort);
  dc.setParameter("sortName", params.sortName);
  dc.setParameter("_pageNumber", params.pageIndex);
  dc.setParameter("_pageSize", 8);
  dc.setParameter("_calc", true);
  //post请求
  NetUtil.post('/api/commonProcessor/commonMethod', dc, function (data) {
    //下面的就是请求来的数据
    dispatch(LoadProductList(data));
  });
}

export function GetProductDataByFilter(params, dispatch) {
  var dc = new window.DataCenter();

  dc.setParameter("_boId", "demoProductServiceImpl");
  dc.setParameter('_methodName', 'findPageByFilter');

  dc.setParameter('_methodParameterTypes', 'String,String,String,String,String,String');
  dc.setParameter("_parameters", "supplier,standard,company,categoryId,sort,sortName");
  dc.setParameter("supplier", params.supplier);
  dc.setParameter("standard", params.standard);
  dc.setParameter("company", params.company);
  dc.setParameter("categoryId", params.categoryId);
  dc.setParameter("sort", params.sort);
  dc.setParameter("sortName", params.sortName);

  dc.setParameter("_pageNumber", 1);
  dc.setParameter("_pageSize", 8);
  dc.setParameter("_calc", true);

  //post请求
  NetUtil.post('/api/commonProcessor/commonMethod', dc, function (data) {
    //下面的就是请求来的数据
    dispatch(GetProductListByFilter(data,params));
  });
}


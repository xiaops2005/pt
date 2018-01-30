/**
 * Created by viewhigh on 2017/6/16.
 */
import NetUitl from 'constants/httpUtil';

class AccountRightProcessor {

  /**
   * 保存报销单
   * @param obj
   * @param details
   * @param saveSuccess
   */
  ajaxSave(obj, details, saveSuccess) {

    obj["user.id"] = obj.userId;

    var dsDetails = new window.DataStore();
    var rowSetDetails = new window.RowSet(details);
    dsDetails.setRowSetName("com.viewhigh.vadp.framework.demo.entity.DemoReimbDocumentDetail");
    dsDetails.setRowSet(rowSetDetails);


    var dc = new window.DataCenter();
    var ds = new window.DataStore();
    var rowSet = new window.RowSet([obj]);
    ds.setRowSet(rowSet);
    ds.setRowSetName("com.viewhigh.vadp.framework.demo.entity.DemoReimbDocument");

    dc.addDataStore("model", ds);
    dc.addDataStore("list", dsDetails);
    dc.setParameter("_boId", "demoReimbDocumentServiceImpl");
    dc.setParameter('_methodName', 'save');
    dc.setParameter('_methodParameterTypes', "com.viewhigh.vadp.framework.demo.entity.DemoReimbDocument,java.util.List<com.viewhigh.vadp.framework.demo.entity.DemoReimbDocumentDetail>");
    dc.setParameter("_parameters", "model,list");
    //post请求
    NetUitl.post('/api/commonProcessor/commonMethod', dc, saveSuccess);

  }

  /**
   * 查询用户列表
   * @param getUserListSuccess
   */
  ajaxQuery(getUserListSuccess) {
    var dc = new window.DataCenter();
    var ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "demoReimbDocumentServiceImpl");
    dc.setParameter('_methodName', 'findByUserList');
    dc.setParameter('_methodParameterTypes', '');
    dc.setParameter("_parameters", "");
    //post请求
    NetUitl.post('/api/commonProcessor/commonMethod', dc, getUserListSuccess);
  }

}

export  default  AccountRightProcessor;

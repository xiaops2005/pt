/**
 * Created by viewhigh on 2017/6/16.
 */
import {changeAccountRightTop,LoadDataByInfo,loadDataByInfoList,LoadDataLeft} from 'routes/demo/models/actions/demoAction';
import ACCOUNTCONFIG from 'constants/AccountConfig';
import NetUtil from 'constants/httpUtil';

const  documentModel = ACCOUNTCONFIG.documentModel;

class AccountLeftProcessor {



  /**
   * 新建报销单
   * @param e
   */
  btnCreateNew(props) {
    const {dispatch} = props;
    dispatch(changeAccountRightTop(documentModel))
  }

  /**
   * 显示报销单详情
   * @param e
   */
  btnShowInfo(id, props) {
    const {dispatch} = props;
    var dc = new window.DataCenter();
    var ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "demoReimbDocumentServiceImpl");
    dc.setParameter('_methodName', 'findByInfo');
    dc.setParameter('_methodParameterTypes', 'String');
    dc.setParameter("_parameters", "id");
    dc.setParameter("id",id);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod',dc,function (data) {
      var result = data.dataStores.result.rowSet.primary[0];
      result['userId'] = result.user.id;
      result['details'] = data.dataStores.details.rowSet.primary;
      result['amount'] = (result.reimbursementAmount - result.writeDownAmount).toFixed(2);
      dispatch(LoadDataByInfo(result))
    });

  }

  /**
   * 查询报销单列表
   * @param value
   * @param props
   */
  queryLeftData(value,props){
    const {dispatch} = props;
    var dc = new window.DataCenter();
    var ds = new window.DataStore();
    dc.addDataStore("items", ds);
    dc.setParameter("_boId", "demoReimbDocumentServiceImpl");
    dc.setParameter('_methodName', 'findList');
    dc.setParameter('_methodParameterTypes', 'String');
    dc.setParameter("_parameters", "title");
    dc.setParameter("title", value);
    //post请求
    NetUtil.post('/api/commonProcessor/commonMethod', dc, function (data) {
      //下面的就是请求来的数据
      var result = data.getSinglePrimaryList();
      dispatch(LoadDataLeft(result))
    });
  }

}

export default AccountLeftProcessor;

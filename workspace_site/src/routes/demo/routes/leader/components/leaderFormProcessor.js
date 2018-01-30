
import HttpUtil from 'constants/httpUtil';
import  {loadedData} from 'routes/demo/models/actions/demoAction';

class leaderFormProcessor {
  handleAjax = (props) => {

    const {dispatch} = props;
    props.form.validateFields((err, values) => {
        if(err==null){
            var dc = new window.DataCenter();
            dc.setParameter("_boId", "demoDemissionServiceImpl");
            dc.setParameter('_methodName', 'findList');
            dc.setParameter('_methodParameterTypes', 'String,String,String,String');
            dc.setParameter("_parameters", "org,year,quarter,month");
            dc.setParameter("org",""+props.form.getFieldValue('org'));
            dc.setParameter("year",""+props.form.getFieldValue('year'));
            dc.setParameter("quarter",""+props.form.getFieldValue('fquarter'));
            dc.setParameter("month",""+props.form.getFieldValue('fmonth'));

          //post请求
          HttpUtil.post('/api/commonProcessor/commonMethod', dc, function (data) {
            //下面的就是请求来的数据
            dispatch(loadedData(data));
          });

        }
      });

  }

}
export default leaderFormProcessor;

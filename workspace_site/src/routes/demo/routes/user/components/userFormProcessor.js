/**
 * Created by hp on 2017/6/15.
 */

import  {demoUserQueryAction} from 'routes/demo/models/actions/demoAction';
class userFormProcessor {
  handleSubmit(props) {
    var ds = new window.DataStore();
    ds.setRowSetName("com.viewhigh.vadp.framework.demo.entity.DemoUser");
    var rowSet = new window.RowSet([{
      "name": props.form.getFieldValue('name'),
      "status": props.form.getFieldValue('status')
    }]);
    ds.setRowSet(rowSet);
    var dispath = props.dispatch;
    dispath(demoUserQueryAction({
          condition: ds
    }));
  }
}
export default userFormProcessor;

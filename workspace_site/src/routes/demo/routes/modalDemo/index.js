/**
 * Created by admin on 2017/6/30.
 */
export default {
  path: 'modalDemo',
  name:"模态框用例",
  breadcrumbName:"模态框用例",
  getComponent(nextState, cb) {
    require.ensure([], (require) => {
      cb(null, require('./components/modalView').default);
    });
  }
};

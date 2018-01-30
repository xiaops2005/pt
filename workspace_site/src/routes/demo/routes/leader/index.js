/**
 * Created by hp on 2017/6/15.
 */
export default {
  path: 'leader',
  name: 'leader',
  breadcrumbName: '领导自助',

  getComponent(nextState, cb) {
    require.ensure([], (require) => {
      cb(null, require('./components/LeaderManagerViewer').default);
    });
  }
};

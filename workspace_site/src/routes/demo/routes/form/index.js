export default {
  path: 'formViewer',
  name:"表单样例",
  breadcrumbName:"表单样例",
  getComponent(nextState, cb) {
    require.ensure([], (require) => {
      cb(null, require('./components/formViewer').default);
    });
  }
};

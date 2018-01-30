export default {
  path: 'mangeSelf',
  name: 'mangeSelf',
  breadcrumbName: '经理自助',

  getComponent(nextState, cb) {
    require.ensure([], (require) => {
      cb(null, require('./components/ManagerSelfSerView').default);
    });
  }
};



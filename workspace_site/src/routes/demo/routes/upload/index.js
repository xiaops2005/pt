/**
 * Created by viewhigh on 2017/6/19.
 */
export default {
  path: 'upload',
  name:"上传下载 ",
  breadcrumbName:"上传下载",
  getComponent(nextState, cb) {
    require.ensure([], (require) => {
      cb(null, require('./components/uploadViewer').default);
    });
  }
};

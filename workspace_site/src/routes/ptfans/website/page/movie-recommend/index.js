/**
 * Created by xiaops on 2018/1/16.
 */
import React from 'react';
import {connect} from 'react-redux';
import CommonHeader from '../components/CommonHeader';
import CommonFooter from '../components/CommonFooter';
// import Body from './components/Body';
import { Layout, Menu, Breadcrumb, Icon } from 'antd';
// import './index.css'
const { Header, Content, Footer } = Layout;

class MovieRecommend extends React.Component {
  constructor(props) {
    super(props);
  }

  componentWillMount() {

  }

  render() {
    return (
    <Layout className="layout">
      <CommonHeader current="movie-recommend"/>
      <Content style={{ padding: '1px' }}>
        电影推荐
      </Content>
      <CommonFooter/>
    </Layout>
    )
  }
}

const mapStateToProps = (state) => {
  return {...state.bdDict}
};
const mapDispatchToProps = (dispatch, state) => {
  return {
    // queryBdMaterials: (year,pkHospital,materialsCode,materialsName,isSingle,isStop) => {
    //   processor.queryBdMaterials(year,pkHospital,materialsCode,materialsName,isSingle,isStop, (data) => {
    //     dispatch(queryBdMaterials(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
    //   });
    // },
    // deleteBdMaterials: (pkMaterials, callback) => {
    //   processor.deleteBdMaterials(pkMaterials, callback);
    // },
    // saveBdMaterials: (bdMaterialsList, callback) => {
    //   processor.saveBdMaterials(bdMaterialsList, callback);
    // },
    // queryBdDict: () => {
    //   bdProcessor.queryBdCode("YEAR", (data) => {
    //     dispatch(queryBdYear(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
    //   });
    //   bdProcessor.queryBdCode("IS_STOP", (data) => {
    //     dispatch(queryBdIsStop(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
    //   });
    //   bdProcessor.queryBdCode("IS_SINGLE", (data) => {
    //     dispatch(queryBdIsSingle(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
    //   });
    //   hospitalProcessor.queryBdHospitalAll((data) => {
    //     dispatch(queryBdHospitalAll(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
    //   })
    // }
  }
};
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(MovieRecommend)



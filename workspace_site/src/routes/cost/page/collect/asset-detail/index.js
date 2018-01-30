/**
 *  数据采集 -- 资产折旧明细
 */
import React, { Component } from 'react';
import { connect } from 'react-redux';
import Header from './components/Header';
import Body from './components/Body';
import { ReAssetDetail } from './model/ReAssetDetail';
import './index.css';

class AssetDetail extends Component {
	constructor(props) {
		console.log('asset detail Constructor -- props : ', props);
		super(props);
    this.state = {
      // Header
      yearList: props.yearList || [],
      hospitalList: props.hospitalList || [],
      capitalSourceList: props.capitalSourceList || [],
      queryAssetDepreciation: this.queryAssetDepreciation,

      // Body
      reAssetData: []

    }
	}

	// 组件挂载前执行函数
	componentWillMount () {
		console.log('component will Mount ----');

	}

  componentWillReceiveProps (nextProps) {
    console.log('资产折旧明细 Index.js Receive Props :');
    console.log(nextProps);
    this.setState({ ...nextProps });
  }

  queryAssetDepreciation = (queryParams) => {
    console.log('资产折旧明细 查询方法。查询参数：', queryParams);
    let arr = [];
    for (let i = 0; i < 20; i++) {
      let asset = new ReAssetDetail();
      asset.pkAssetsOriginal = 'pkDetail-' + i;
      asset.pkHospital = '医院名称';
      asset.accYear = 2017;
      asset.pkDept = '科室';

      arr.push(asset);
    }
    this.setState({reAssetData: arr});
  }

	render() {
		return (
			<div className="vh-content" id="execute-method-index">
        <Header { ...this.state } />
        <Body { ...this.state } />
			</div>
		);
	}
}
const mapStateToProps = (state) => {
	console.log('map state to props === state : ', state);
	return {
    yearList: state.bdDict.yearList || [],
    hospitalList: state.bdDict.hospitalList || [],
    capitalSourceList: state.bdDict.capitalSourceList || []
  };
}

const mapDispatchToProps = (dispatch, state) => {
	console.log('map dispatch to props ===');
	console.log("dispatch ：", dispatch);
	console.log("state ：", state);
	return {

  };
}
export default connect(mapStateToProps, mapDispatchToProps)(AssetDetail);

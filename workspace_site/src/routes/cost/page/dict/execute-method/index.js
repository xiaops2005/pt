import React, { Component } from 'react';
import { connect } from 'react-redux';
import { message } from 'antd';
import Header from './components/Header';
import Body from './components/Body';
import { BdDictService, HospitalService, ExecuteMethodService as emService } from '../../../process/LoadService';
import { VHStrUtil, VHArrayUtil, VHUtil } from '../../../../../constants/utils';
import './index.css';

// 声明服务（用于和后台交互）
const BdService = new BdDictService();
const HsptlProcessor = new HospitalService();

class ExecuteMethod extends Component {
	constructor(props){
		console.log("=============", props);
		super(props);
		this.state = {
			// Header 参数
			hospitalDict: [],
			isStopDict: [],
			queryParams: {},
			queryBtnFun: this.queryBtnFun,

			// Body 参数
			tableDataSource: [],
			dataSource: [],
			selectedRowKeys: [],
			isStopDictObj: {},
			saveBtnFuncHandler: this.saveBtnFuncHandler,
			delBtnFuncHandler: this.delBtnFuncHandler,
			startOrStopBtnFuncHandler: this.startOrStopBtnFuncHandler
		};
	}

	// 组件挂载前
	componentWillMount() {
		console.log("component will mount ...");
		// 加载医院字典
		HsptlProcessor.queryBdHospitalAll((data) => {
			let hospitalDict = data.getSinglePrimaryList();
			let hospitalDictObj = VHArrayUtil.toObj(hospitalDict);
			this.setState({ hospitalDict, hospitalDictObj });
		})

		// 加载是否
		let isStopDict = VHUtil.isStopDate();
		let isStopDictObj = VHArrayUtil.toObj(isStopDict);
		this.setState({ isStopDict, isStopDictObj });
	}

  componentWillReceiveProps(props) {
    console.log("index will receive props ", props);
  }

	// 查询数据
	queryBtnFun = (queryParams) => {
		console.log("查询参数：", JSON.stringify(queryParams));
		// 调用查询服务
		emService.queryExecuteMethod(queryParams, (res) => {
			console.log("Query SUCCESS !!!", res);
			// 设置表格数据
			this.setState({ tableDataSource: res , queryParams});
		}, (errRes) => {
			console.log("Query ERROR !!!");
			console.log(errRes);
		});
	}

	// 保存数据
	saveBtnFuncHandler = (saveRowKeys) => {
		let saveRowItems = [], { tableDataSource, queryParams } = this.state, checkoutStr = '';
		saveRowKeys.forEach( index => {
			let rowItem = tableDataSource[index];
			delete rowItem.$$operationFlag;
			let _checkoutObj = this.checkoutObj(rowItem);
			if (!VHStrUtil.isNullOrEmpty(_checkoutObj)) {
				checkoutStr = checkoutStr + index + ': ' + _checkoutObj + ';';
			}
			saveRowItems.push(rowItem);
		});
		if (!VHStrUtil.isNullOrEmpty(checkoutStr)) {
			message.warning('所选保存数据字段不能为空：' + checkoutStr);
			return ;
		}
		emService.saveExecuteMethod({ data: saveRowItems }, (saveRes) => {
			console.log("Save service SUCCESS !!!", saveRes);
			emService.queryExecuteMethod(queryParams, (queryRes) => {
				this.setState({ tableDataSource: JSON.parse(JSON.stringify(queryRes)) });
			}, (errRes) => {
				console.log("After Save service, QUERY SERVICE ERROR !!!", errRes);
			})
		}, (errRes) => {
			console.log("Save service ERROR !!!", errRes);
		});
	}

	// 删除功能按钮
	delBtnFuncHandler = (delRowKeys) => {
		console.log("删除 Btn：" + delRowKeys);
		// 调用删除服务
		let delRowPKs = this.selectedRowKeysToPK(delRowKeys),
			{ queryParams } = this.state;
		console.log("del pk : ", delRowPKs);
		emService.delExecuteMethod({ delKeys: delRowPKs.join(',') }, (res) => {
			console.log("Del SUCCESS !!!", res);
			// 设置表格数据
			emService.queryExecuteMethod(queryParams, (queryRes) => {
				this.setState({ tableDataSource: JSON.parse(JSON.stringify(queryRes)) });
			}, (errRes) => {
				console.log("After DEL service, QUERY SERVICE ERROR !!!", errRes);
			})
		}, (errRes) => {
			console.log("Del ERROR !!!", errRes);
		});
	}

	// 启用、停用按钮功能实现
	startOrStopBtnFuncHandler = (startOrStopFlag, recordKeys) => {
		let{ queryParams } = this.state,
			startOrStopRowPKs = this.selectedRowKeysToPK(recordKeys);

		emService.startOrStopExecuteMethod({ data: startOrStopRowPKs.join(','), startOrStopFlag }, (res) => {
			console.log("Start OR Stop SUCCESS !!!", res);
			// 设置表格数据
			emService.queryExecuteMethod(queryParams, (queryRes) => {
				this.setState({ tableDataSource: JSON.parse(JSON.stringify(queryRes)) });
			}, (errRes) => {
				console.log("After Start OR Stop service, QUERY SERVICE ERROR !!!", errRes);
			});
		}, (errRes) => {
			console.log("Start OR Stop ERROR !!!");
			console.log(errRes);
		});
	}

	// 选中的行索引转化为对象主键
	selectedRowKeysToPK = (selectedRowKeys) => {
		let selectedRecordPK = [], { tableDataSource } = this.state;
		selectedRowKeys.forEach( index => {
			let rowItem = tableDataSource[index];
			if (!VHStrUtil.isNullOrEmpty(rowItem.pkExecuteMethod)) {
				selectedRecordPK.push(rowItem.pkExecuteMethod);
			}
		});
		return selectedRecordPK;
	}

	// 字段非空校验
	checkoutObj = (item) => {
		let checkoutStr = '';

		if(VHStrUtil.isNullOrEmpty(item.pkHospital)) {
			checkoutStr += "所属医院为空，"
		}
		if(VHStrUtil.isNullOrEmpty(item.methodCode)) {
			checkoutStr += "方法编码为空，"
		}
		if(VHStrUtil.isNullOrEmpty(item.methodName)) {
			checkoutStr += "方法名称为空，"
		}
		if(VHStrUtil.isNullOrEmpty(item.isStop)) {
			checkoutStr += "是否启用为空，"
		}

		return checkoutStr;
	}

	render () {
		return(
			<div className="vh-content" id="execute-method-index">
				<Header {...this.state}/>
				<Body {...this.state} />
			</div>
		);
	}
}


// 监听 Redux Store，必须返回一个对象，对象与 props 合并
const mapStateToProps = (state) => {
	console.log('map state to props === ', state);
	return { ...state.bdDict };
}

const mapDispatchToProps = (dispatch, state) => {
	return {
		// queryBdCode: () => {
		// 	// HsptlProcessor.queryBdHospitalAll((data) => {
		// 	// 	dispatch(queryBdHospitalAll(data.map((item, index) => Object.assign(item, {key: index}))));
		// 	// })
		// }
	};
}
export default connect(mapStateToProps, mapDispatchToProps)(ExecuteMethod);


import React, { Component } from 'react';
import { Form, Icon, Row, Col, Input, Select, Button } from 'antd';

// 声明子组件
const FormItem = Form.Item;
const Option = Select.Option;

// 查询组件
class FormSearch extends Component {
	constructor(props){
		super(props);
		this.state = {
			hospitalDict: [],
			initHospital: '',
			isStopDict: [],
			initStop: '0'
		}
	}
	// 接收 props
	componentWillReceiveProps(nextProps) {
		console.log("Header 接受 props: ", nextProps);
		console.log(this.state);
		let { hospitalDict, isStopDict, queryBtnFun } = nextProps;
		let _initHospital = hospitalDict.length > 0 ? hospitalDict[0].hospitalCode : '';
		this.setState({ hospitalDict, isStopDict, queryBtnFun, initHospital: _initHospital});
	}

	// 查询按钮
	handleSearch = (e) => {
		e.preventDefault();
		console.log("Handle Search Form 。。。");

		// 查询参数
		let _params = {
			hospital: this.props.form.getFieldValue('pkHospital'),
			methodCode: this.props.form.getFieldValue('methodCode'),
			methodName: this.props.form.getFieldValue('methodName'),
			isStop: this.props.form.getFieldValue('isStop') ? this.props.form.getFieldValue('isStop') : null
		};
		console.log("_params:" + _params);
		this.state.queryBtnFun(_params);
	}

	render() {
		const { getFieldDecorator } = this.props.form;

		// const formItemLayout = {
		// 	labelCol: { span: 9 },
		// 	wrapperCol: { span: 15 }
		// };

		const hospitalOptions = this.state.hospitalDict.map(hspt => <Option key={ hspt.hospitalCode} value={ hspt.hospitalCode }>{ hspt.hospitalName }</Option>);
		const stopOptions = this.state.isStopDict.map(stop => <Option key={ stop.codeId } value={ stop.codeId }>{ stop.codeName }</Option>);

		return (
			<div className="vh-header" id="execute-method-header">
				<Form onSubmit={ this.handleSearch } className="ant-advanced-search-form">
					<Row gutter={ 24 }>
						<Col span={ 7 } key="1">
							<FormItem label="所属医院">
								{
									getFieldDecorator('pkHospital', { initialValue: this.state.initHospital })(
										<Select style={{ width: '147px' }} allowClear >
											{ hospitalOptions }
										</Select>
									)
								}
							</FormItem>
						</Col>
						<Col span={ 7 }  key="2">
							<FormItem label="办法编码">
								{
									getFieldDecorator('methodCode')(
										<Input placeholder="执行办法编码" />
									)
								}
							</FormItem>
						</Col>
						<Col span={ 7 }  key="3">
							<FormItem label="办法名称">
								{
									getFieldDecorator('methodName')(
										<Input placeholder="执行办法名称" />
									)
								}
							</FormItem>
						</Col>
						<Col span={ 3 } key="4"/>
					</Row>
					<Row>
						<Col span={ 7 } key="5">
							<FormItem label="是否停用">
								{
									getFieldDecorator('isStop', {initialValue: this.state.initStop})(
										<Select style={{ width: '147px' }} allowClear>
											{ stopOptions }
										</Select>
									)
								}
							</FormItem>
						</Col>
						<Col span={ 7 } key="6"/>
						<Col span={ 7 } key="7"/>
						<Col span={ 3 } key="8">
							<Button type="primary" onClick={ this.handleSearch }>查询</Button>
						</Col>
					</Row>
				</Form>
			</div>
		);
	}
}
const WrappedFormSearch = Form.create()(FormSearch);

export default WrappedFormSearch;

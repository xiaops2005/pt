import React, { Component } from 'react';
import { Input, Select, Button, Table, message } from 'antd';
import { ExecuteMethod } from '../../models/bdDictModel';
import { EditableCellSelect } from '../../component/EditableTable';
import { VHDateUtil, VHDomUtil } from '../../../../../../constants/utils';

// 编辑单元格 -- input
const EditableCell = ({editable, value, onChange}) => (
	<div>
		{
			editable ? <Input style={{ margin: '-5px,0' }} value={ value }
						   onChange={ e => onChange(e.target.value) }/> : value
		}
	</div>
);

// 表格组件
class TableView extends Component {
	constructor(props) {
		console.log("Table View Constructor");
		console.log(props);

		super(props);
		this.state = {
			refs: {},
			tableDataSource: [],
			selectedRowKeys: [],
			isStopDictObj: {},
			columns: [
				{title: '办法编码', dataIndex: 'methodCode', key: 'methodCode', width: 150
					, render: (text, record, index) => this.renderColumns(record, index, 'methodCode', text)
				},
				{title: '办法名称', dataIndex: 'methodName', key: 'methodName', width: 150
					, render: (text, record, index) => this.renderColumns(record, index, 'methodName', text)
				},
				{title: '所属医院', dataIndex: 'pkHospital', key: 'pkHospital', width: 150
					, render: (text, record, index) => {
						return (
							<EditableCellSelect
								editable={ record.editable }
								value={ text }
								options={ this.state.hospitalDict }
								decodeVal="hospitalCode"
								decodeDisplay="hospitalName"
								onChange={ val => this.handleChange(index, 'pkHospital',val) }
							/>
						);
					}
				},
				{title: '停用', dataIndex: 'isStop', key: 'isStop', width: 80
					, render: (text, record, index) => {
						return (
							<EditableCellSelect
								editable={ record.editable }
								value={ text }
								options={ this.state.isStopDict }
								onChange={ val => this.handleChange(index, 'isStop',val) }
							/>
						);
					}
				},
				{title: '备注', dataIndex: 'remark', key: 'remark', width: 200
					, render: (text, record, index) => this.renderColumns(record, index, 'remark', text)
				},
				{title: '创建人', dataIndex: 'creator', key: 'creator', width: 150},
				{title: '创建时间', dataIndex: 'creationtime', key: 'creationtime', width: 150
					, render: (text, record, index) => {
						return text ? VHDateUtil.dateFormat(text) : '';
					}
				}
			]
		};
	}

	// 组件挂载后触发函数
	componentDidMount() {
    // 添加监听
		window.addEventListener('resize', this.resetTableBodyHeight);
    this.resetTableBodyHeight();
	}

  // 组件卸载前触发函数
  componentWillUnmount() {
    // 删除监听
    window.removeEventListener('resize', this.removeListener);
  }

	// 修改表格高度
	resetTableBodyHeight = () => {
		let headerHeight = VHDomUtil.getElementHeight('execute-method-header'),
			windowHeight = window.innerHeight;
    // 设置 table Body 的高度
    VHDomUtil.setTableBodyHeight(windowHeight - headerHeight - 270);
	}
  removeListener = ()=> {
    console.log("卸载组件，移除监听。");
  }

	// 接收数据
	componentWillReceiveProps(nextProps) {
		this.setState({ ...nextProps });
	}

	// “新增”按钮
	addBtnHandler = () => {
		console.log("Add Btn Handler...");
		let newObj = new ExecuteMethod();
		newObj.editable = true;
		// 设置选中
		let { selectedRowKeys, tableDataSource } = this.state;
		selectedRowKeys = selectedRowKeys.map( rowKey => rowKey + 1);
		selectedRowKeys.unshift(0);

		tableDataSource.unshift(newObj);
		this.setState({ selectedRowKeys, tableDataSource });

	}

	// 编辑选中行
	modifyBtnHandler = () => {
		const { tableDataSource, selectedRowKeys } = this.state;
		if (selectedRowKeys.length === 0) {
			message.warning('请勾选要编辑的执行方法！');
			return false;
		}
		selectedRowKeys.forEach( rowIndex => {
			tableDataSource[rowIndex].editable = true;
		})
		this.setState({ tableDataSource });
	}

	// "保存"按钮
	saveBtnHandler = () => {
		let { selectedRowKeys } = this.state;
		if (!selectedRowKeys.length) {
			message.warn("没有选择的保存项目，请先勾选！");
			return false;
		}
		// 调用绑定的方法（父层中定义的方法）
		this.state.saveBtnFuncHandler(selectedRowKeys);
	}

	// “删除”按钮
	delBtnHandler = () => {
		let { selectedRowKeys } = this.state;
		if (!selectedRowKeys.length) {
			message.warning("请选择删除项！");
			return false;
		}
		this.state.delBtnFuncHandler(selectedRowKeys);
	}

	// “启用”按钮
	startBtnHandler = () => {
		this.startOrStopBtnFunc(1);
	}

	// “停用”按钮
	stopBtnHandler = () => {
		this.startOrStopBtnFunc(0);
	}

	// “启用/停用”按钮实现
	startOrStopBtnFunc = (startOrStopFlag) => {
		let _operation = startOrStopFlag ===0 ? "停用" : "启用",
			{ selectedRowKeys } = this.state;
		console.log(_operation + "项目:", selectedRowKeys.join("、"));
		if (selectedRowKeys.length === 0) {
			message.warning(`请选择要${ _operation }的对象！`);
			return false;
		}
		this.state.startOrStopBtnFuncHandler(0, selectedRowKeys);
	}

	// 渲染单元格（文字模式/编辑模式）
	renderColumns = (record, index, key, text) => {
		let { editable } = record;
		if (!editable) {
			return text;
		}
		return (
			<EditableCell
				editable={ editable }
				value={ text }
				onChange={ value => this.handleChange(index, key, value) }
			/>
		);
	}

	// 单元格 change 事件
	handleChange = (index, key, newValue) => {
		const { tableDataSource } = this.state;
		tableDataSource[index][key] = newValue;
		this.setState({ tableDataSource });
	}

	// 选择行改变
	rowSelectedChange = (selectedRowKeys) => {
		console.log('selectedRowKeys changed: ', selectedRowKeys);
		this.setState({ selectedRowKeys });
	}

	render() {
		const { tableDataSource, columns, selectedRowKeys } = this.state;
		const rowSelectionSet = {
			selectedRowKeys,
			onChange: this.rowSelectedChange
		};
		return(
			<div className="vh-body" ref="vh-body">
				<div className="table-operations">
					<Button onClick={ this.addBtnHandler }>新增</Button>
					<Button onClick={ this.modifyBtnHandler }>修改</Button>
					<Button onClick={ this.saveBtnHandler }>保存</Button>
					<Button onClick={ this.delBtnHandler }>删除</Button>
					<Button onClick={ this.startBtnHandler }>启用</Button>
					<Button onClick={ this.stopBtnHandler }>停用</Button>
				</div>

				<Table ref="table" className="main-section" rowSelection={ rowSelectionSet } dataSource={ tableDataSource }
					   columns={ columns } rowKey={ item => tableDataSource.indexOf(item) } bordered
					   scroll={{ y: 430 }}
					   pagination={{ pageSize: 10 }}
				/>
			</div>
		);
	}
}

export default TableView;

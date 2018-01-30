/* 执行方法查询--服务 */
import HttpUtil from "constants/httpUtil";

export default class ExecuteMethodService {
	// 执行方法--查询
	queryExecuteMethod (params, successFunc, errFunc) {
		var dc = new window.DataCenter();
		var ds = new window.DataStore();
		dc.addDataStore("executeMethod", ds);
		dc.setParameter("_boId", "BdExecuteMethodService");
		dc.setParameter('_methodName', 'getExecuteMethodList');
		dc.setParameter('_methodParameterTypes', 'String,String,String,String');
		dc.setParameter("_parameters", "hospital,methodCode,methodName,stopFlag");
		dc.setParameter("hospital", params.hospital ? params.hospital : null);
		dc.setParameter("methodCode", params.methodCode ? params.methodCode : null);
		dc.setParameter("methodName", params.methodName ? params.methodName : null);
		dc.setParameter("stopFlag", params.isStopId ? params.isStopId : null);

		HttpUtil.post("/api/commonProcessor/commonMethod", dc, (res) => {
			successFunc(res.getSinglePrimaryList());
		}, errFunc);
	}

	/**
	 * 执行方法--保存
	 *
	 * @param params	-- {data: 保存对象, queryParams: 查询条件}
	 * @param successFunc
	 * @param errFunc
	 */
	saveExecuteMethod(params, successFunc, errFunc) {
		console.log("保存服务 params：", params);
		let dc = new window.DataCenter();//创建一个数据中心
		let ds = new window.DataStore();
		let rowSet = new window.RowSet(params.data);
		ds.setRowSet(rowSet);
		ds.setRowSetName("com.viewhigh.entity.BdExecuteMethod")
		dc.addDataStore("saveList", ds);

		//指定请求对应的后端java业务类的名称
		dc.setParameter("_boId", "BdExecuteMethodService");
		//指定请求对应的后端java业务类对应的业务方法
		dc.setParameter('_methodName', 'saveExecuteMethodList');
		dc.setParameter('_methodParameterTypes','java.util.List<com.viewhigh.entity.BdExecuteMethod>');
		dc.setParameter("_parameters", "saveList");

		HttpUtil.post("/api/commonProcessor/commonMethod", dc, (res) => {
			successFunc(res);
		}, errFunc);
	}

	// 执行方法--删除
	delExecuteMethod (params, successFunc, errFunc) {
		console.log("删除服务 params：", params);
		let dc = new window.DataCenter();
		let ds = new window.DataStore();
		dc.addDataStore("executeMethodDel", ds);
		dc.setParameter("_boId", "BdExecuteMethodService");
		dc.setParameter('_methodName', 'delExecuteMethodList');
		dc.setParameter('_methodParameterTypes', 'String');
		dc.setParameter("_parameters", "delKeys");
		dc.setParameter("delKeys", params.delKeys);

		HttpUtil.post("/api/commonProcessor/commonMethod", dc, (res) => {
			successFunc(res);
		}, errFunc);
	}
	
	// 执行方法--启用、停用
	startOrStopExecuteMethod(params, successFunc, errFunc) {
		console.log("启用、停用服务 params：", params);
		let dc = new window.DataCenter();
		let ds = new window.DataStore();
		dc.addDataStore("executeMethodDel", ds);
		dc.setParameter("_boId", "BdExecuteMethodService");
		dc.setParameter('_methodName', 'startOrStopExecuteMethodList');
		dc.setParameter('_methodParameterTypes', 'String,Integer');
		dc.setParameter("_parameters", "delKeys,startOrStopFlag");
		dc.setParameter("delKeys", params.data);
		dc.setParameter("startOrStopFlag", params.startOrStopFlag);	// 启用/停用操作

		HttpUtil.post("/api/commonProcessor/commonMethod", dc, (res) => {
			successFunc(res);
		}, errFunc);
	}
}



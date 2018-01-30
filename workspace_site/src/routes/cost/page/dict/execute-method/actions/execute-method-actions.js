import * as actionTypes from "./execute-method-action-types"

export const queryExecuteMethod = (data) => {
	return { type: actionTypes.Query_Execute_Method, data: data};
}


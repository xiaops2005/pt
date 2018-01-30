/**
 * 领导自助对应的reducer
 */
const query = (state = {data:[]}, action) => {
	switch (action.type) {
	    case "loadingData":
	      return {
	        ...state
	      };
	     case "loadedData":
	      return {
	        ...state,
	        data: action.data
	      };
	    default:
      		return state;
	}
}
export default query;

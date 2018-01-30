/**
 * Created by admin on 2017/6/7.
 */
import * as types from '../actions/ActionTypes';
const managerSelfSer = (state={
  array:[],
  currentPage:1,
  sumPage:'',
}, action) => {
  let newState;
  switch (action.type) {
    case types.Demo_ManagerSelfSer_prePage:
      newState={
        ...state,
        currentPage: action.data.currentPage
      };
      return newState;
    case types.Demo_ManagerSelfSer_afterPage:
        newState={
        ...state,
        currentPage: action.data.currentPage
      };
      return newState;
    case types.Demo_ManagerSelfSer_AsucuessApi:
      newState={
        ...state,
        array:action.data.array,
        sumPage:action.data.sumPage,
        currentPage:action.data.currentPage
      };
      return newState;
    default:
      return state;
  }
};
export default managerSelfSer;

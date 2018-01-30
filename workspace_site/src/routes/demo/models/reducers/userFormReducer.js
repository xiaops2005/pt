/**
 * Created by hp on 2017/6/15.
 */
import * as types from '../actions/ActionTypes';
const data = [];
const pagination=[];
const userFormReducer = (state = {} , action) => {
  // console.log(action)
  switch (action.type) {
    case types.Demo_USER_QUERY:
      console.log(types.Demo_USER_QUERY);
      debugger;
      return {...action.data};

    default:return state;
  }
};
export default userFormReducer;

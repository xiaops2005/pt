/**
 * Created by admin on 2017/6/30.
 */
import * as types from '../actions/ActionTypes';
const modalViewer=(state={
                     name:"",
                     password:'',
                     sex:''
                   },action)=>{

    if(action.type==types.changeInfo){
      return {
          ...state,
          name:action.data.name,
          password:action.data.password,
          sex:action.data.sex
        }
     }
     return state;
}
export default modalViewer;

import {combineReducers} from 'redux'
import {routerReducer} from 'react-router-redux';
import uiReducer from '../routes/ui/models/reducers/uiReducer';
import bdDict from '../routes/cost/page/dict/models/reducers/bdDict';
import importTemplate from '../routes/cost/page/import-template/models/reducers/importTemplate';

import supplier from '../routes/demo/models/reducers/supplier';
import query from '../routes/demo/models/reducers/query';
import account from '../routes/demo/models/reducers/SettingsAccount'
import userReducer from '../routes/demo/models/reducers/userFormReducer';
import managerSelfSer from '../routes/demo/models/reducers/managerSelfSer';
import modalViewer from '../routes/demo/models/reducers/modalViewer';
// import settings from './settings';


export const makeRootReducer = (asyncReducers) => {
  return combineReducers({
    routing: routerReducer,
    ui: uiReducer,
    ...asyncReducers,
    bdDict: bdDict,
    importTemplate: importTemplate
  })
};

export const injectReducer = (store, {key, reducer}) => {
  if (Object.hasOwnProperty.call(store.asyncReducers, key)) return;

  store.asyncReducers[key] = reducer;
  store.replaceReducer(makeRootReducer(store.asyncReducers))
};

export default makeRootReducer;

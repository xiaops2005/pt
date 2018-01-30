import * as actionTypes from "../actions/ActionTypes";

const importTemplate = (state = {dataSource: []}, action) => {
  switch (action.type) {
    case actionTypes.Query_Data_Source:
      return Object.assign({}, state, {doQuery: true});
    case actionTypes.Get_Template_Columns:
      if (!action.error) {
        const selectedDataSource = action.payload.filter(item => item.pos).sort((a, b) => a.pos - b.pos);
        let dataSource = [];
        dataSource = dataSource.concat(selectedDataSource);
        action.payload.forEach(item => {
          if (selectedDataSource.every(selectedItem => selectedItem.dbColumnName !== item.dbColumnName)) {
            dataSource.push(item);
          }
        });
        dataSource = dataSource.map((item, index) => Object.assign({}, item, {key: index}));
        return Object.assign({}, state, {
          doQuery: false,
          dataSource: dataSource,
          selectedRowKeys: dataSource.filter(item => item.pos).map(item => item.key)
        });
      } else {
        return state;
      }
    case actionTypes.Query_Template_Entity:
      if (!action.error) {
        return Object.assign({}, state, {
          doQuery: false,
          dataSource: action.payload.map((item, index) => Object.assign(item, {key: index})),
          selectedRowKeys: []
        });
      } else {
        return state;
      }
    case actionTypes.Query_Template_Entity_Added:
      if (!action.error) {
        return Object.assign({}, state, {
          templateClasses: action.payload.map((item, index) => Object.assign(item, {key: index}))
        });
      } else {
        return state;
      }
    case actionTypes.Get_Template_Entity:
      if (!action.error) {
        return Object.assign({}, state, {
          dbTableNames: action.payload,
          selectedRowKeys: []
        });
      } else {
        return state;
      }
    case actionTypes.Save_Template_Columns:
    case actionTypes.Save_Template_Entity:
    default:
      return state;
  }
};

export default importTemplate;

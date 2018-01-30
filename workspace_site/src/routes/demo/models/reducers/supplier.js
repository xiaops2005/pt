/**
 * Created by Bojc on 2017/6/6.
 */
import * as types from '../actions/ActionTypes';

const initState={
  productList: [],
  pageInfo: {
    total: 0,
    current: 1
  },
  filter: {
    supplier: '',
    standard: '',
    company: '',
    categoryId: '',
    categoryName:'美容材料',
    sortName:'',
    sort:''
  }
};
const supplier = (state = initState, action) => {

  let newState;
  switch (action.type) {
    case types.Demo_Supplier_InitData:
      var dc = action.data;
      var data = dc.getSinglePrimary();
      var ds = dc.getSingleDataStore();
      newState = {
        ...state,
        productList: data,
        pageInfo: {
          total: ds.getRecordCount(),
          current: ds.getPageNumber()
        }
      };
      return newState;
    case types.Demo_Supplier_SearchData:
      var dc = action.data;
      var data = dc.getSinglePrimary();
      var ds = dc.getSingleDataStore();
      newState = {
        ...state,
        productList: data,
        pageInfo: {
          total: ds.getRecordCount(),
          current: ds.getPageNumber()
        },
        filter: {
          supplier: action.params.supplier,
          standard: action.params.standard,
          company: action.params.company,
          categoryId: action.params.categoryId,
          categoryName:action.params.categoryName,
          sortName:action.params.sortName,
          sort:action.params.sort
        }
      };
      return newState;
    case types.Demo_Supplier_SetFilter:
      newState = {
        ...state,
        filter: {
          supplier: action.data.supplier,
          standard: action.data.standard,
          company: action.data.company,
          categoryId: action.data.categoryId,
          categoryName:action.data.categoryName,
          sortName:action.data.sortName,
          sort:action.data.sort
        }
      };
      return newState;
    default:
      return state;
  }
};
export default supplier;



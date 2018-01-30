
const ACCOUNTCONFIG = {
  documentModel: {
    id: null,
    name: '',
    applyDate: new Date(),
    userName: null,
    reimbursementAmount: '0.00',
    writeDownAmount: '0.00',
    amount: '0.00',
    remark: '',
    details: [],
    userId: null,
    deptId: null,
    matter: '',
    user: []
  },
  left: {
    array: []
  }
}
const documentModel = ACCOUNTCONFIG.documentModel;
const left = ACCOUNTCONFIG.left;

const settings = (state = {documentModel, left}, action) => {
  // console.log(action)
  switch (action.type) {
    case 'ACCOUNT_RIGHT_TOP':
      console.log("Settings....: ACCOUNT_RIGHT_TOP");
      return {
        ...state,
        documentModel: action.documentModel
      };
    case 'showInfo':
      return {...state, documentModel: action.data}
    case 'showLeftInfo':
      return {...state, left: action.data}
    default:
      return state;
  }
};

export default settings;

const excelTemplate=[{
  key: 1,
  name: '收入报表',
  jsonTemplate: "properties/reIncome.json",
  fileList:[],
  action:"upload",
  downloadUrl:"/static/fileTemplate/收入报表.xlsx"
}, {
  key: 2,
  name: '材料明细',
  jsonTemplate: "properties/ReMateDetailTemplate.json",
  fileList:[],
  action:"upload",
  downloadUrl:"/static/fileTemplate/材料明细.xlsx"
},{
  key: 3,
  name: '全成本报表',
  jsonTemplate: "properties/reCost.json",
  fileList:[],
  action:"upload",
  downloadUrl:"/static/fileTemplate/成本报表2016.xlsx"
},{
  key: 4,
  name: '收入明细',
  jsonTemplate: "properties/IncomeDetailBeanTemplate.json",
  fileList:[],
  action:"uploadIncome"
},{
  key: 5,
  name: '资产折旧',
  jsonTemplate: "properties/ReAssetsOriginal.json",
  fileList:[],
  action:"upload",
  downloadUrl:"/static/fileTemplate/折旧明细2015-02.xls"
},{
  key: 6,
  name: '人员经费明细',
  jsonTemplate: "properties/ReWageTemplate.json",
  fileList:[],
  action:"upload",
  downloadUrl:"/static/fileTemplate/人员经费明细.xls"
}]
export default excelTemplate;

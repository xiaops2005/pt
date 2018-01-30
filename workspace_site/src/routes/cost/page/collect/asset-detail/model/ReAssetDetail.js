/**
 * 资产折旧明细数据模型
 */
let ReAssetDetail = function() {
  this.pkAssetsOriginal = '';
  this.pkHospital = '';
  this.accYear = ''; // number(4)!!!
  this.pkDept = '';
  this.pkAssets = '';
  this.depreciation = 0; // 折旧金额
  this.capitalSourceId = '';
  this.ratio = 0; // 自筹比例
  this.bunkfeeTypeId = '';
  this.pkDeptMerge = '';
  this.pkDeptCost = '';
  this.pkChargeClass = '';
  this.isInherit = 0;  // 是否继承数据
  this.isPublic = 0;  // 是否公摊
  this.statusId = '';  //单据状态
  this.creator = 'RA';
  this.creationtime = null;
  this.modifier = '';
  this.modifiedtime = null;
  this.approver = '';
  this.approvetime = null;
  this.approvenote = '';
  this.dr = 0;
  this.ts = null;
};

export {ReAssetDetail}

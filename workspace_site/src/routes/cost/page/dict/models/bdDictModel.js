import DateUtil from "../../../../../constants/DateUtil";
import {queryBdChargesHos} from "./actions/bdDictAction";

let BdChargesHos = function () {
  this.pkChargesHos = "";
  this.pkHospital = "";
  this.chargesCode = "";
  this.chargesName = "";
  this.chargesDesc = "";
  this.chargesType = "";
  this.belongArea = "";
  this.unitName = "";
  this.unitPrice = "";
  this.isOutside = 0;
  this.isCheck = 0;
  this.isStop = 0;
  this.pkChargesSta = '';
  this.pkChargeClass = '';
  this.remark = '';
  this.accYear = '';
  this.creator = '';
  this.modifier = '';
  this.modifiedtime = '';
  this.creationtime = '';
  this.dr = 0;
  this.ts = null;
  this.$$editable = true;
};

let BdHospital = function () {
  this.pkHospital = "";
  this.hospitalCode = "";
  this.hospitalName = "";
  this.hospitalClassId = "";
  this.hospitalTypeId = "";
  this.remark = "";
  this.belongArea = "";
  this.creator = "sw";
  this.creationtime = null;
  this.modifier = "";
  this.modifiedtime = null;
  this.dr = 0;
  this.ts = null;
  this.$$editable = true;
};


let BdDept = function () {
  this.pkDept = "";
  this.pkHospital = "";
  this.deptCode = "";
  this.deptName = "";
  this.deptLevel = null;
  this.pkFather = "";
  this.isLast = '0';
  this.isStop = '0';
  this.belongSystem = "";
  this.deptType = "";
  this.year = null;
  this.remark = "";
  this.creator = "sw";
  this.creationtime = null;
  this.modifier = "";
  this.modifiedtime = null;
  this.dr = 0;
  this.ts = null;
  this.$$editable = true;
};
BdDept.prototype.copyPropValues = function (bdDept) {
  for (let prop in this) {
    if (bdDept.hasOwnProperty(prop)) {
      this[prop] = bdDept[prop];
    }
  }
};

let IdentificationDictModel = function () {
  this.pkMedicalIdentify = "";
  this.identifyCode = "";
  this.identifyName = "";
  this.pkHospital = "";
  this.remark = "";
  this.isStop = "0";
  this.creator = "";
  this.creationTime = "";
  this.modifier = "";
  this.modifiedTime = "";
  this.dr = 0;
  this.ts = "";
  this.$$editable = true;
}
let CategoryDictModel = function () {
  this.pkChargeClass = "";

  this.classCode = "";
  this.className = "";
  this.pkHospital = "";
  this.remark = "";
  this.accYear = "";
  this.creator = "";
  this.creationTime = "";
  this.modifier = "";
  this.creationTime = "";
  this.modifiedTime = "";
  this.isStop = "0";
  this.dr = 0;
  this.ts = "";
  this.$$editable = true;
}

CategoryDictModel.prototype.copyPropValues = function (bdCategory) {
  for (let prop in this) {
    if (bdCategory.hasOwnProperty(prop)) {
      this[prop] = bdCategory[prop];
    }
  }
};


IdentificationDictModel.prototype.copyPropValues = function (bdIdentify) {
  for (let prop in this) {
    if (bdIdentify.hasOwnProperty(prop)) {
      this[prop] = bdIdentify[prop];
    }
  }
};

let BdDeptSta = function () {
  this.pkDeptSta = "";
  this.staCode = "";
  this.staName = "";
  this.remark = null;
  this.isStop = "0";
  this.creator = null;
  this.creationTime = null;
  this.modifier = "";
  this.modifiedtime = null;
  this.dr = 0;
  this.ts = null;
  this.editable = true;
}
BdDeptSta.prototype.copyPropValues = function (BdDeptSta) {
  for (let prop in this) {
    if (BdDeptSta.hasOwnProperty(prop)) {
      this[prop] = BdDeptSta[prop];
    }
  }
};
let BdMaterials = function () {
  this.pkMaterials = "";
  this.pkHospital = "";
  this.materialsCode = "";
  this.materialsName = "";
  this.unitName = "";
  this.materialsDesc = "";
  this.model = "";
  this.unitPrice = null;
  this.isSingle = "";
  this.isStop = "";
  this.pkChargeClass = null;
  this.remark = "";
  this.accYear = null;
  this.creator = "xps";
  this.creationtime = null;
  this.modifier = "";
  this.modifiedtime = null;
  this.dr = 0;
  this.ts = null;
  this.isStop = 0;
  this.isSingle = null;
  this.$$editable = true;
};

BdMaterials.prototype.copyPropValues = function (bdMaterials) {
  for (let prop in this) {
    if (bdMaterials.hasOwnProperty(prop)) {
      this[prop] = bdMaterials[prop];
    }
  }
};

// 执行方法
let ExecuteMethod = function () {
  this.pkExecuteMethod = '';
  this.pkHospital = '';
  this.methodCode = '';
  this.methodName = '';
  this.isStop = 0;
  this.remark = '';
  this.creator = 'bd';
  this.creationtime = null;
  this.modifier = '';
  this.modifiedtime = null;
  this.dr = 0;
  this.ts = null;
  this.$$operationFlag = 'add';	// 新建
}
ExecuteMethod.prototype.copyPropValues = function (bdMaterials) {
  for (let prop in this) {
    if (bdMaterials.hasOwnProperty(prop)) {
      this[prop] = bdMaterials[prop];
    }
  }
};
//资产字典
let BdAssets = function (isStandard) {
  this.pkAssets = "";
  this.pkHospital = "";
  this.assetsCode = "";
  this.assetsName = "";
  this.unitName = "";
  this.assetsType = "";
  this.assetsDesc = "";
  this.model = "";
  this.originalValue = '0';
  this.startDate = null;
  this.durableYears = "";
  this.capitalSourceId = "";
  this.isBunkfeeId = "";
  this.isStop = '0';
  this.pkChargeClass = "";
  this.isStandard = isStandard ? isStandard : 0;
  this.remark = "";
  this.accYear = null;
  this.creator = "sw";
  this.creationtime = null;
  this.modifier = "";
  this.modifiedtime = null;
  this.dr = 0;
  this.ts = null;
  this.$$editable = true;
};

BdAssets.prototype.copyPropValues = function (bdAssets) {
  for (let prop in this) {
    if (bdAssets.hasOwnProperty(prop)) {
      this[prop] = bdAssets[prop];
    }
  }
};

// hia科室
let HiaDept = function () {
  this.pkDeptHia = '';
  this.hiaCode = '';
  this.hiaName = '';
  this.isStop = 0;
  this.remark = '';
  this.belongArea = '';
  this.creator = '';
  this.creationtime = null;
  this.modifier = '';
  this.modifiedtime = null;
  this.dr = 0;
  this.ts = null;
  // this.$$operationFlag = 'add';	// 新建
  this.$$editable = true;
};
HiaDept.prototype.copyPropValues = function (hiaDept) {
  for (let prop in this) {
    if (hiaDept.hasOwnProperty(prop)) {
      this[prop] = hiaDept[prop];
    }
  }
};

export {BdDept}
export {BdHospital}
export {IdentificationDictModel}
export {BdMaterials}
export {CategoryDictModel}
export {BdDeptSta}
export {ExecuteMethod}
export {BdAssets}
export {HiaDept}
export {BdChargesHos}


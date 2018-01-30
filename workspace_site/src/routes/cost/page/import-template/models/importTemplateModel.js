import {CategoryDictModel} from "../../dict/models/bdDictModel";

let FileTemplate = function () {
  this.templateId = "";
  this.hospital = "";
  this.validator = "";
  this.templateClass = "";
  this.templateName = "";
  this.dbTableName = "";
  this.primaryKey = "";
  this.creator = "";
  this.creationtime = null;
  this.$$editable = true;
};

FileTemplate.prototype.copyPropValues = function (fileTemplate) {
  for (let prop in this) {
    if (fileTemplate.hasOwnProperty(prop)) {
      this[prop] = fileTemplate[prop];
    }
  }
};

export {FileTemplate}

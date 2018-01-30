import React from 'react';
import {connect} from "react-redux"
import Header from "../components/Header"
import {
  QueryTemplateEntityAdded,
  queryTemplateColumns,
  queryDataSource
} from '../../models/actions/importTemplateAction';

const mapStateToProps = (state) => {
  return {doQuery: state.importTemplate.doQuery, templateClasses: state.importTemplate.templateClasses};
};

const mapDispatchToProps = (dispatch) => {
  return {
    QueryTemplateEntityAdded: () => {
      dispatch(QueryTemplateEntityAdded());
    },
    queryDataSource: () => {
      dispatch(queryDataSource())
    },
    getTemplateColumns: (templateId) => {
      dispatch(queryTemplateColumns(templateId))
    }
  }
};
const FileTemplate = connect(
  mapStateToProps,
  mapDispatchToProps
)(Header);

export default FileTemplate

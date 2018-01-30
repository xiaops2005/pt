import {connect} from "react-redux"
import Body from "../components/Body"
import {queryDataSource, saveTemplateColumns} from '../../models/actions/importTemplateAction';


const mapStateToProps = (state) => {
  return {
    dataSource: state.importTemplate.dataSource,
    selectedRowKeys: state.importTemplate.selectedRowKeys
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    queryDataSource: () => {
      dispatch(queryDataSource())
    },
    saveTemplateColumns: (templateId, templateColumnsList, succFn, errFn) => {
      dispatch(saveTemplateColumns(templateId, templateColumnsList, succFn, errFn));
    }
  }
};
const ColumnTemplate = connect(
  mapStateToProps,
  mapDispatchToProps
)(Body);

export default ColumnTemplate

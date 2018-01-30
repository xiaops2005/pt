import {connect} from "react-redux"
import Header from "../components/Header"
import {queryTemplateEntity} from '../../models/actions/importTemplateAction';


const mapStateToProps = (state) => {
  return {...state.importTemplate};
};

const mapDispatchToProps = (dispatch) => {
  return {
    queryTemplateEntity: (templateName,dbTableName) => {
      if (templateName !== undefined && dbTableName !== undefined) {
        dispatch(queryTemplateEntity(templateName,dbTableName))
      }
    }
  }
};
const HeaderContainer = connect(
  mapStateToProps,
  mapDispatchToProps
)(Header);

export default HeaderContainer

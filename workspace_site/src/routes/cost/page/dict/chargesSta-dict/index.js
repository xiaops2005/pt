import React from 'react';
import {connect} from 'react-redux';
import Header from './components/Header';
import Body from './components/Body';
import {ChargesStaDictService} from '../../../process/LoadService'
import {queryChargesStaDict} from '../models/actions/bdDictAction'
import './index.css'

const processor = new ChargesStaDictService();

class ChargesStaDict extends React.Component {
  constructor(props) {
    super(props);
  }

  componentWillMount() {
    this.props.query();
  }

  render() {
    return (
      <div>
        <Header {...this.props}/>
        <Body {...this.props}/>
      </div>
    )
  }
}

const mapStateToProps = (state) => {
  return {...state.bdDict}
};
const mapDispatchToProps = (dispatch, state) => {
  return {
    query: (chargesCode,chargesName) => {
     processor.query(chargesCode,chargesName, (data) => {
       dispatch(queryChargesStaDict(data.getSinglePrimary().map((item, index) => Object.assign(item, {key: index}))));
      });
    },
  }
};
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ChargesStaDict)



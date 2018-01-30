import React from 'react';
import {connect} from 'react-redux';
import Header from './components/Header';
import Body from './components/Body';
import {AnalysisCollectService} from "../../../process/LoadService";

const processor = new AnalysisCollectService();

class AnalysisCollect extends React.Component {
  constructor(props) {
    super(props);
    this.state={
      dataSource:[],
      year:"",
      pkHospital:"",
    }
  }

  handleQuery =(year, pkHospital) => {
      this.setState({year, pkHospital});
      processor.query(year,pkHospital,(result)=>{
        this.setState({dataSource:result.getSinglePrimary()})
      })
  }

  handleExport =() =>{
    const {year, pkHospital} = this.state;
    //需要后台添加方法
    let url = `http://localhost:8080/api/????????????/exportExcel/${year}/${pkHospital}`
    console.log('export url=',url);
    window.location.href = url
  }

  render() {
    return (
      <div>
        <Header
          {...this.props}
          onQueryChange={this.handleQuery}
        />
        <Body
          {...this.props}
          onExport={this.handleExport}
          dataSource={this.state.dataSource}
        />
      </div>
    )
  }
}

const mapStateToProps = (state) => {
  return {...state.bdDict}
};
export default connect(
  mapStateToProps
)(AnalysisCollect)



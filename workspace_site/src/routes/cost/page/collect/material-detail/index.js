import React from 'react';
import {connect} from 'react-redux';
import Header from './components/Header';
import Body from './components/Body';
import {
  MaterialsService,
  HospitalService,
  BdDictService,
  ReMateDetailService,
  DeptService,
  ApproveRecordService
} from './../../../process/LoadService'
import {
  queryBdYear,
  queryBdHospitalAll,
  queryBdMaterialsAll,
  queryReMateDetail,
  queryBdDept,
  queryApproveRecord
} from '../../dict/models/actions/bdDictAction'
const processor = new ReMateDetailService();
const approveRecordProcessor = new ApproveRecordService();
const materialsProcessor = new MaterialsService();
const bdProcessor = new BdDictService();
const hospitalProcessor = new HospitalService();
const deptProcessor = new DeptService();

class MaterialsDetail extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      dataSource:[]
    }
  }

  componentWillMount() {
    console.log("index componentWillMount");
    this.props.queryBdDict();
    this.queryReMateDetail();
  }

  queryReMateDetail = (year,pkHospital,materialsDeptCode,materialsDeptName,isCharges,pkMaterials) => {
    let that = this;
    processor.queryReMateDetail(year,pkHospital,materialsDeptCode,materialsDeptName,isCharges,pkMaterials, (data) => {
      // dispatch(queryReMateDetail(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      this.setState({dataSource:data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))})
    });
  }

  exportReMateDetail= (params) => {
    let url = `http://localhost:8080/api/reMateDetailController/exportExcel/${params.year}/${params.pkHospital}/${params.materialsDeptCode}/${params.materialsDeptName}/${params.isCharges}/${params.pkMaterials}`
    console.log('exportReMateDetail url=',url);
    window.location.href = url
  }

  render() {
    console.log("index render state=",this.state);
    return (
      <div className="vh">
        <Header {...this.props}
                onQuery={this.queryReMateDetail}
                wrappedComponentRef={(inst) => this.formRef = inst}/>
        <Body {...this.props}
              dataSource={this.state.dataSource}
              onExport={this.exportReMateDetail}
              header={this.formRef}/>
      </div>
    )
  }
}

const mapStateToProps = (state) => {
  return {...state.bdDict}
};
const mapDispatchToProps = (dispatch, state) => {
  return {
    // queryReMateDetail: (year,pkHospital,materialsDeptCode,materialsDeptName,isCharges,pkMaterials) => {
    //   processor.queryReMateDetail(year,pkHospital,materialsDeptCode,materialsDeptName,isCharges,pkMaterials, (data) => {
    //     dispatch(queryReMateDetail(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
    //   });
    // },
    // exportReMateDetail: (params) => {
    //   let url = `http://localhost:8080/api/reMateDetailController/exportExcel/${params.year}/${params.pkHospital}/${params.materialsDeptCode}/${params.materialsDeptName}/${params.isCharges}/${params.pkMaterials}`
    //   console.log('exportReMateDetail url=',url);
    //   window.location.href = url
    // },
    // queryApproveRecord: () => {
    //   approveRecordProcessor.queryApproveRecord((data) => {
    //     dispatch(queryApproveRecord(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
    //   });
    // },
    queryBdDict: () => {
      bdProcessor.queryBdCode("YEAR", (data) => {
        dispatch(queryBdYear(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
      materialsProcessor.queryBdMaterialsAll((data) => {
        dispatch(queryBdMaterialsAll(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
      deptProcessor.queryBdDept(undefined,'','','','','',(data) => {
        dispatch(queryBdDept(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      });
      // bdProcessor.queryBdCode("IS_SINGLE", (data) => {
      //   dispatch(queryBdIsSingle(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      // });
      hospitalProcessor.queryBdHospitalAll((data) => {
        dispatch(queryBdHospitalAll(data.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))));
      })
    }
  }
};
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(MaterialsDetail)



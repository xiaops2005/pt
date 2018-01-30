import React from 'react';
import {connect} from 'react-redux';
import {mergeDictService, HospitalService, BdDictService} from '../../../process/LoadService';

import {Header} from './components/Header'
import DictBody from '../component/DictBody'
import {MergeDictModel} from "./model/MergeDictModel";
import {EditableCellSelect, EditableCellInput} from '../component/EditableTable'
import {message} from 'antd';

const processor = new mergeDictService();
const hospitalApi = new HospitalService();

class MergeDict extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      dataSource: [],
      hospitals: [],
      yearList: []
    };
    this.isStopOptions = [{name: "是", code:"1"}, {name: "否", code:"0"}];
    this.columns = [
      {
        title: '合并科室编码',
        dataIndex: 'mergeCode',
        width: '15%',
        render: (text, record) => this.renderColumns(text, record, 'mergeCode'),
      }, {
        title: '合并科室名称',
        dataIndex: 'mergeName',
        width: '20%',
        render: (text, record) => this.renderColumns(text, record, 'mergeName'),
      }, {
        title: '所属医院',
        dataIndex: 'pkHospital',
        width: '15%',
        render: (text, record) => this.renderColumnsSelect(text, record, 'pkHospital'),
      }
      , {
        title: '年度',
        dataIndex: 'accYear',
        width: '10%',
        render: (text, record) => <EditableCellSelect
          editable={record.$$editable}
          value={text}
          options={this.props.yearList}
          onChange={value => this.handleChange(value, record.key, 'accYear')}
        />,
      },
      {
        title: '是否停用',
        dataIndex: 'isStop',
        width: '10%',
        render: (text, record) => <EditableCellSelect
          editable={record.$$editable}
          value={text}
          options={this.isStopOptions}
          decodeVal="code"
          decodeDisplay="name"
          onChange={value => this.handleChange(value, record.key, 'isStop')}
        />,
      },
      {
        title: '创建人',
        dataIndex: 'creator',
        width: '10%',
      },
      {
        title: '创建时间',
        dataIndex: 'creationtime',
        width: '10%',
      }
    ];


  }

  renderColumns(text, record, column) {
    return (
      <EditableCellInput
        editable={record.$$editable}
        value={text}
        onChange={value => this.handleChange(value, record.key, column)}
      />
    );
  }


  renderColumnsSelect(text, record, column) {
    return (
      <EditableCellSelect
        editable={record.$$editable}
        value={text}
        options={this.state.hospitals}
        decodeVal="hospitalCode"
        decodeDisplay="hospitalName"
        onChange={value => this.handleChange(value, record.key, column)}
      />
    );
  }

  handleChange = (value, key, column) => {
    const {dataSource} = this.state;
    const target = dataSource.filter(item => key === item.key)[0];
    if (target) {
      target[column] = value;
      this.setState({dataSource: dataSource});
    }
  };

  componentWillMount() {
    hospitalApi.queryBdHospital("", "", "", "", (result) => {
      this.setState({"hospitals": result.getSinglePrimaryList()});
    })

    processor.query(new MergeDictModel(), (result) => {
      this.setState({"dataSource": result.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))});
    })
  }


  handleQueryChange = (SearchCondition) => {
    let obj = {...new MergeDictModel(), ...SearchCondition};
    processor.query(obj, (result) => {
      this.setState({"dataSource": result.getSinglePrimaryList().map((item, index) => Object.assign(item, {key: index}))});
    });
  };

  save = (selectedDataSource) => {
    processor.save(selectedDataSource, (result) => {
      message.success("保存成功！");
    });
  }

  del = (selectedKeys) => {
    const {dataSource} = this.state;
    const ids = dataSource.filter(item => selectedKeys.some(key => item.key === key)).map(item => "'" + item.pkDeptMerge + "'").join(",");
    processor.del(ids, (result) => {
      message.success("删除成功！");
      this.setState({
        dataSource: dataSource.filter((item) => selectedKeys.every((key) => item.key !== key)),
        selectedRowKeys: []
      });
    });
  }

  render() {
    return (

      <div className="vh">
        <Header
          {...this.props}
          hospitals={this.state.hospitals}
          onQueryChange={this.handleQueryChange}/>
        <DictBody
          {...this.props}
          dataSource={this.state.dataSource}
          hospitals={this.state.hospitals}
          columns={this.columns}
          model={MergeDictModel}
          onSave={this.save}
          onDelete={this.del}
        />
      </div>
    )
  }
}

const mapStateToProps = (state) => {
  return {...state.bdDict}
}
export default connect(
  mapStateToProps
)(MergeDict)




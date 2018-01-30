import React from 'react';
import {Form, Input, Button, Row, Col,Select} from 'antd';
import {ProjectCategoryService} from "../../../../process/LoadService";
const FormItem = Form.Item;
const Option = Select.Option;
const processor = new ProjectCategoryService();
class FormSearch extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      isStopList: [],
      hospitalList: []
    }

  }
  // constructor(props) {
  //   super(props)
  //   this.state = {hospitalList:[]}
  // }

  componentWillMount(){
    processor.queryHospitals((result)=>{
      this.setState({hospitalList:result.getSinglePrimary()})
    })
  }



  componentWillReceiveProps(nextProps) {
    this.setState({...nextProps});
  }

  handleSearch = (e) => {
    e.preventDefault();
    let pkHospital = this.props.form.getFieldValue('pkHospital');
    let classCode = this.props.form.getFieldValue('classCode')
    let className = this.props.form.getFieldValue('className')
    let isStop = this.props.form.getFieldValue('isStop')
    this.props.onQueryChange(pkHospital,classCode,className,isStop);
  }




  render() {
    const formItemLayout = {
      labelCol: {
        xs: {span: 0},
        sm: {span: 6},
      },
      wrapperCol: {
        xs: {span: 0},
        sm: {span: 13},
      }
    };
    // const {getFieldDecorator} = this.props.form;
    // const {hospitalList} = this.state;
    const {getFieldDecorator} = this.props.form;
    return (
      <div className="vh">
        <Form onSubmit={this.handleSearch} className="ant-advanced-search-form">
          <Row gutter={24}>
            <Col span={7} >
              <FormItem label="所属医院" {...formItemLayout}>
                {
                  getFieldDecorator('pkHospital', {
                    rules: [
                      // { required: true, message: '请选择所属医院!' },
                    ],
                  })
                  (<Select allowClear>
                    {this.state.hospitalList.map((item) => {
                      return <Option key={item.key}
                                     value={item.pkHospital}>{item.hospitalName}</Option>
                    })}
                  </Select>)
                }
              </FormItem>
            </Col>
            <Col span={7}>
              <FormItem label="大类编码:" {...formItemLayout}>
                {
                  getFieldDecorator('classCode', {
                    rules: [
                      // {required: true, message: '字典名称 is required.'}
                    ]
                  })(
                    <Input/>
                  )
                }
              </FormItem>
            </Col>
            <Col span={7}>
              <FormItem label="大类名称:" {...formItemLayout}>
                {
                  getFieldDecorator('className', {
                    rules: [
                      // {required: true, message: '字典名称 is required.'}
                    ]
                  })(
                    <Input/>
                  )
                }
              </FormItem>
            </Col>

            <Col span={3}>
            </Col>

          </Row>

          <Row gutter={24}>

            <Col span={7}>
              {/*<FormItem label="是否停用" {...formItemLayout}>*/}
                {/*{*/}
                  {/*getFieldDecorator('isStop', {*/}
                    {/*rules: [*/}
                      {/*// { required: true, message: '' },*/}
                    {/*],*/}
                  {/*})*/}
                  {/*(<Select allowClear>*/}
                    {/*{this.state.isStopList.map((item) => {*/}
                      {/*return <Option key={item.key} value={item.codeId}>{item.codeName}</Option>*/}
                    {/*})}*/}

                  {/*</Select>)*/}
                {/*}*/}
              {/*</FormItem>*/}

              <FormItem label="是否停用" {...formItemLayout}>
                {
                  getFieldDecorator('isStop', {
                  })(
                    <Select  allowClear>
                      {
                        this.state.isStopList.map(function (item) {
                          return <Option key={item.key} value={item.codeId}>{item.codeName}</Option>;
                        })
                      }
                    </Select>
                  )
                }
              </FormItem>


            </Col>
            <Col span={7}>
            </Col>
            <Col span={7}>
            </Col>
            <Col span={3}>
              <Button type="primary" onClick={this.handleSearch}>查询</Button>
            </Col>
          </Row>
        </Form>
      </div>
    )
  }
}
export const Header = Form.create()(FormSearch);

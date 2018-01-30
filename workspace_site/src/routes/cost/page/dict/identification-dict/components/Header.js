import React from 'react';
import {Form, Input, Button, Row, Col,Select} from 'antd';
import {IdentificationService} from '../../../../process/LoadService';
const FormItem = Form.Item;
const Option = Select.Option;
const processor = new IdentificationService();

class FormSearch extends React.Component {

  constructor(props) {
    super(props)
    this.state = {hospitalOptions:[],isStopList:[]}
  }

  componentWillMount(){
    processor.queryHospitals((result)=>{
        this.setState({hospitalOptions:result.getSinglePrimary()})
    })
  }

  componentWillReceiveProps(nextProps) {
    this.setState({...nextProps});
  }


  handleSearch = (e) => {
    e.preventDefault();
    let hospital = this.props.form.getFieldValue('hospital');
    let identifyCode = this.props.form.getFieldValue('identifyCode')
    let identifyName = this.props.form.getFieldValue('identifyName')
    let isStop = this.props.form.getFieldValue('isStop')
    this.props.onQueryChange(hospital,identifyCode,identifyName,isStop);
  }

  render() {
    const {getFieldDecorator} = this.props.form;
    const {hospitalOptions} = this.state;
    const formItemLayout = {
      labelCol: {
        xs: {span: 0},
        sm: {span: 6},
      },
      wrapperCol: {
        xs: {span: 0},
        sm: {span: 13},
      },
    };
    return (

      <div className="vh">
        <Form onSubmit={this.handleSearch} className="ant-advanced-search-form">
          <Row gutter={24}>
            <Col span={7}>
              <FormItem label="所属医院" {...formItemLayout}>
                {
                  getFieldDecorator('hospital', {
                    rules: [
                      // {required: true, message: '字典名称 is required.'}
                    ]
                  })(
                    <Select allowClear>
                      {
                        hospitalOptions.map(function (item) {
                          return <Option key={item.pkHospital} value={item.pkHospital}>{item.hospitalName}</Option>;
                        })
                      }
                    </Select>
                  )
                }
              </FormItem>

            </Col>
            <Col span={7}>
              <FormItem label="医护标识编码" {...formItemLayout}>
                {
                  getFieldDecorator('identifyCode', {
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
              <FormItem label="医护标识名称" {...formItemLayout}>
                {
                  getFieldDecorator('identifyName', {
                    rules: [
                      // {required: true, message: '字典名称 is required.'}
                    ]
                  })(
                    <Input/>
                  )
                }
              </FormItem>
            </Col>
            <Col span={3}/>
          </Row>
          <Row gutter={24}>
            <Col span={7}>
              <FormItem label="是否停用" {...formItemLayout}>
                {
                  getFieldDecorator('isStop', {
                  })(
                    <Select  allowClear>
                      {/*<Option value="1">是</Option>*/}
                      {/*<Option value="0">否</Option>*/}
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
            <Col span={7}/>
            <Col span={7}/>
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

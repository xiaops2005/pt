/**
 * Created by viewhigh on 2017/6/1.
 */
import React, {Component} from 'react';
import {Form, Input, Button, DatePicker, Select, Row, Col, Layout, Table, notification, Icon, Popconfirm} from 'antd';
import {connect} from 'react-redux';
import {changeAccountRightTop, LoadDataByInfo, loadDataByInfoList, LoadDataLeft} from 'routes/demo/models/actions/demoAction';
import '../account.css';
import ACCOUNTCONFIG from 'constants/AccountConfig';
import moment from 'moment';
import EditableCell from "./EditableCell";
import EditableCellDate from "./EditableCellDate";
import EditableCellSelect from "./EditableCellSelect";
import EditableCellIcon from "./EditableCellIcon";
import AccountRightProcessor from "./AccountRightProcessor";
import AccountLeftProcessor from './AccountLeftProcessor';
import APPCONFIG from 'constants/Config';


const Option = Select.Option;
const FormItem = Form.Item;
const documentModel = ACCOUNTCONFIG.documentModel;
const dateFormat = 'YYYY-MM-DD';
const processor = new AccountRightProcessor();
const processorLeft = new AccountLeftProcessor();

const openNotificationWithIcon = (type, msg) => {
  notification[type]({
    message: '通知消息',
    description: msg,
    placement: 'bottomRight'
  });
};





class AccountRightTop extends Component {

  constructor(props) {
    super(props);
    this.columns = [{
      title:'billIcon',
      width:'5%',
      render:(text,record,index) => (
        <EditableCellIcon value={text} />
      )
    }
    ,{
      title: 'billClassification',
      dataIndex: 'billClassification',
      width: '15%',
      render: (text, record, index) => (
        <EditableCellSelect
          value={text}
          onChange={this.onCellChange(index, 'billClassification')}
        />
      ),
    }, {
      title: 'billDate',
      dataIndex: 'billDate',
      width: '30%',
      render: (text, record, index) => (
        <EditableCellDate
          value={text}
          onChange={this.onCellChange(index, 'billDate')}
        />
      ),
    }, {
      title: 'amount',
      dataIndex: 'amount',
      width: '30%',
      render: (text, record, index) => (
        <EditableCell
          value={text}
          onChange={this.onCellChange(index, 'amount')}
        />
      )},
      {
      title: 'operation',
      dataIndex: 'operation',
      render: (text, record, index) => {
        return (
          <Popconfirm title="是否删除?" onConfirm={() => this.onDelete(index)}>
            <a href="#"><img src={APPCONFIG.PublicURL+'assets/demo/icon/delete.png'} /></a>
          </Popconfirm>
        );
      },
    }];

    this.state = {
      count: 0,
      users: [],
      model: []
    };

  }

  onCellChange = (index, key) => {
    return (value) => {
      const details = [...this.state.model.details];
      details[index][key] = value;
      this.setState({details});
    };
  }

  onDelete = (index) => {
    const details = [...this.state.model.details];
    details.splice(index, 1);
    this.state.model["details"] = details;
    const dispatch = this.props.dispatch;
    dispatch(changeAccountRightTop(this.state.model))
    this.setState({details});
  }

  getNowDateString() {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
      month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
      strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
  }

  handleAdd = () => {
    const {count} = this.state;
    const newData = {
      billDate: this.getNowDateString(),
      amount: '0',
      billClassification: '1'
    };

    this.state.model.details.push(newData);
    const dispatch = this.props.dispatch;
    dispatch(changeAccountRightTop(this.state.model))
    this.setState({
      count: count + 1,
    });
  }

  componentWillMount() {
    this.ajaxQuery();
  }


  ajaxSave(obj){
    processor.ajaxSave(obj,this.state.model.details,data =>{
      if (data.header.code == 1) {
        openNotificationWithIcon('success', '保存成功!');
        const dispatch = this.props.dispatch;
        dispatch(changeAccountRightTop(documentModel))
        processorLeft.queryLeftData(null,this.props);
      } else {
        openNotificationWithIcon('error', '保存失败!');
      }
    });
  }



  ajaxQuery() {
    processor.ajaxQuery(data => {
      //下面的就是请求来的数据
      var result = data.getSinglePrimaryList();
      console.log(result)
      this.setState({
        users: result
      });
    });
  }


  componentDidMount() {
    this.props.form.validateFields();
  }

  drawUser() {
    const children = [];
    for (let i = 0; i < this.state.users.length; i++) {
      var obj = this.state.users[i];
      children.push(<Option key={obj.id}>{obj.name}</Option>);
    }
    return children;
  }

  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        this.ajaxSave(values);
      }
    });
  }

  render() {
    //渲染基本控件值
    let model = this.getModelValue()
    const {getFieldDecorator} = this.props.form;
    let childrenUser = this.drawUser();

    const columns = this.columns;

    const LayoutHeader = {
      labelCol: { span: 12 },
      wrapperCol: { span: 12 }
    };

    return (
      <div>
        <Form layout="inline" onSubmit={this.handleSubmit}>
          <Layout style={{
            background: 'white',
            borderBottom: ' 1px',
            borderBottomColor: '#e4e5e6',
            borderBottomStyle: 'solid'
          }}>
            <Row type="flex" justify="center" style={{ borderBottom: '1px solid rgb(228, 229, 230)',height: '82px'}}>
              <Col  >
                <FormItem
                >
                  {getFieldDecorator('name', {
                    rules: [{required: true, message: '请输入标题!'}],
                  })(
                    <Input className="colTitleStyle"  placeholder="请输入标题"/>
                  )}
                </FormItem>
              </Col>
            </Row>
            <Row type="flex" justify="start" style={{ borderBottom: '1px solid rgb(228, 229, 230)'}}>
              <Col >
                <FormItem label="报销金额"
                {...LayoutHeader}
                >
                  {getFieldDecorator('reimbursementAmount', {
                    rules: [{required: true, message: '请输入报销金额!'}],
                  })(
                    <Input className="inputNoBorder" style={{fontSize: '18px', color: 'blue'}} size={8}/>
                  )}
                </FormItem>
                <FormItem label="冲销金额"
                 {...LayoutHeader}>
                  {getFieldDecorator('writeDownAmount', {
                    rules: [{required: true, message: '请输入冲销金额!'}],
                  })(
                    <Input className="inputNoBorder" style={{fontSize: '18px', color: 'blue'}} size={8}/>
                  )}
                </FormItem>
                <FormItem label="支付金额"
                 {...LayoutHeader}
                 >
                  {getFieldDecorator('amount', {
                    rules: [{required: true, message: '请输入支付金额!'}],
                  })(
                    <Input className="inputNoBorder" style={{fontSize: '18px', color: 'blue'}} size={8}/>
                  )}
                </FormItem>
              </Col>
            </Row>
            <Row >
              <Col span={12}>
                <FormItem
                  label="申请日期"
                >
                  {getFieldDecorator('applyDate', {
                    rules: [{required: true, message: '请输入申请日期!'}],
                  })(
                    <DatePicker  />
                  )}
                </FormItem>
              </Col>
              <Col span={12} style={{textAlign: 'right'}}>
                <FormItem
                  label="报销人"
                >
                  {getFieldDecorator('userId', {
                    rules: [{required: true, message: '请选择报销人!'}],
                  })(
                    <Select style={{width: '150px'}}>
                      {childrenUser}
                    </Select>
                  )}
                </FormItem>
              </Col>
            </Row>
            <Row >
              <Col span={12}>
                <FormItem
                  label="费用承担部门"
                >
                  {getFieldDecorator('deptId', {
                    rules: [{required: true, message: '请选择部门!'}],
                  })(
                    <Select style={{width: '148px',left:-23}}>
                      <Option value="1">部门1</Option>
                      <Option value="2">部门2</Option>
                      <Option value="3">部门3</Option>
                    </Select>
                  )}
                </FormItem>
              </Col>
              <Col span={12} style={{textAlign: 'right'}}>
                <FormItem
                  label='事项'
                >
                  {getFieldDecorator('matter', {
                    rules: [{required: true, message: '请输入事项!'}],
                  })(
                    <Input   />
                  )}
                </FormItem>
              </Col>
            </Row>
            <Row >
              <Col span={24}>
                <FormItem
                  label='报销事由'
                >
                  {getFieldDecorator('remark', {
                    rules: [{required: true, message: '请输入报销事由!'}],
                  })(
                    <Input   />
                  )}
                </FormItem>
                <FormItem
                  label='主键'
                  style={{display: 'none'}}
                >
                  {getFieldDecorator('id')(
                    <Input   />
                  )}
                </FormItem>
              </Col>
            </Row>


          </Layout>
          <Layout
            style={{background: 'white', marginRight: '10px;', marginTop: '10px'}}>
            <Row style={{marginBottom: '15px', width: '99%'}}>
              <Col span={12}>消息详情({model.details.length})</Col>
              <Col span={12} style={{textAlign: 'right'}}>
                <Button className="editable-add-btn" onClick={this.handleAdd}>导入/新增消息记录</Button>
              </Col>
            </Row>
            <Row style={{width: '99%'}}>
              <Col span={24}>
                <Table
                  columns={columns}
                  dataSource={this.state.model.details}
                  showHeader={false}
                  pagination={false}
                  bordered
                />
              </Col>
            </Row>
            <Row style={{width: '99%'}}>
              <Col span={12}>总金额：{this.getTotalMoney()}元</Col>
              <Col span={12} style={{textAlign: 'right'}}>
                <Button type="primary" htmlType="submit" style={{paddingRight: '10px'}}>保存</Button>
                &nbsp;&nbsp;
                <Button type="primary" htmlType="submit">提交送审</Button>
              </Col>
            </Row>
          </Layout>
        </Form>
      </div>
    )
  }

  getTotalMoney =(e)=>{
    var result = 0;
    for(var i=0;i<this.state.model.details.length;i++){
      result +=  parseInt(this.state.model.details[i].amount);
    }
    return result;
  }

  getModelValue() {
    debugger;
    let {documentModel} = this.props;
    if (!documentModel) {
      documentModel = [];
    }
    this.state.model = documentModel;
    return documentModel;
  }


}

const mapAccountStateToProps = state => ({
  documentModel: state.account.documentModel,
  left: state.account.left
});

export default connect(mapAccountStateToProps)(Form.create({

  mapPropsToFields(props) {
    console.log("mapPropsToFields....")
    console.log(props)
    return {
      amount: {
        ...props.documentModel.amount,
        value: props.documentModel.amount
      },
      writeDownAmount: {
        ...props.documentModel.writeDownAmount,
        value: props.documentModel.writeDownAmount
      },
      reimbursementAmount: {
        ...props.documentModel.reimbursementAmount,
        value: props.documentModel.reimbursementAmount
      },
      name: {
        ...props.documentModel.name,
        value: props.documentModel.name
      },
      id: {
        ...props.documentModel.id,
        value: props.documentModel.id
      },
      applyDate: {
        ...props.documentModel.applyDate,
        value: moment(props.documentModel.applyDate, dateFormat)
      },
      userId: {
        ...props.documentModel.userId,
        value: props.documentModel.userId
      },
      remark: {
        ...props.documentModel.remark,
        value: props.documentModel.remark
      },
      matter: {
        ...props.documentModel.matter,
        value: props.documentModel.matter
      },
      deptId: {
        ...props.documentModel.deptId,
        value: props.documentModel.deptId
      }
    };
  },
  onValuesChange(_, values) {
    console.log(values);
  },
})(AccountRightTop));

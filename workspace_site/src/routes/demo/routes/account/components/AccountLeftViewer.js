import React, {Component} from 'react';
import {Input, Button, Row, Col, Collapse} from 'antd';
import  LiItem from './LiItem';
import {connect} from 'react-redux';
import {changeAccountRightTop,LoadDataByInfo,loadDataByInfoList,LoadDataLeft} from 'routes/demo/models/actions/demoAction';
import AccountLeftProcessor from './AccountLeftProcessor';
import APPCONFIG from 'constants/Config';
const Search = Input.Search;
const Panel = Collapse.Panel;


const processor = new AccountLeftProcessor();

class accountLeft extends Component {

  constructor(props){
    super(props);
    this.state = {
      array:[]
    };
  }

  componentWillMount(){
    processor.queryLeftData(null,this.props);
  }

  /**
   * 渲染列表
   * @returns {Array}
   */
  drawRow() {
    let shows = []
    for (let i = 0; i < this.state.array.length; i++){
      var obj = this.state.array[i];
      shows.push(<a onClick={this.btnShowInfo.bind(this,obj.id)} ><li>
        <LiItem pic={APPCONFIG.PublicURL+'assets/demo/icon/x1.png'} title={obj.name} money={ obj.reimbursementAmount + '元'}/>
      </li></a>)
    }
    return shows;
  }

  /**
   * 查询列表
   * @param value
   */
  btnQuery(value){
    processor.queryLeftData(value,this.props);
  }

  /**
   * 新建
   * @param e
   */
  btnCreateNew = (e) => {
    e.preventDefault();
    processor.btnCreateNew(this.props);
  }

  /**
   * 显示报销单详情
   * @param e
   */
  btnShowInfo = (value) =>{
    processor.btnShowInfo(value,this.props);
  }

  render(){
    console.log("render...")
    let {left} = this.props;
    this.state.array = left.array;

    return <div style={{marginRight: '12px'}}>
      <Row style={{ marginBottom:'0px' }}>
        <Col span={15}>
          <Search
            onSearch={value => this.btnQuery(value)}
          />
        </Col>
        <Col span={6}>
          <Button type="primary" onClick={this.btnCreateNew} >新建</Button>
        </Col>
      </Row>

          <Collapse bordered={false} defaultActiveKey={['1']}>
            <Panel header="我的报销单" key="1">
              <ul>
                {this.drawRow()}
              </ul>
            </Panel>
          </Collapse>

    </div>

  }

}

const mapAccountStateToProps = state => ({
  documentModel: state.account.documentModel,
  left : state.account.left
});

export default connect(mapAccountStateToProps)(accountLeft);

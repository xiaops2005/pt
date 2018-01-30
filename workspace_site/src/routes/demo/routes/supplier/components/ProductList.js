/**
 * Created by Bojc on 2017/6/4.
 */
import React from 'react';
import {Row,Col,Card,Modal,Icon} from 'antd';
import APPCONFIG from 'constants/Config';

class ProductList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      data: this.props.data
    };
  }

  render() {
    const data = this.props.data;
    return (
      <div>
        <Row>
          {
            data.map((item)=> {
              return <CardItem data={item}/>;
            })
          }
        </Row>
      </div>
    );
  }
}

class CardItem extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      display: 'none',
      amount:6
    };
  }

  handleMouseOver = (e)=> {
    e.preventDefault();
    this.setState({
      display: 'block'
    });
  }
  handleMouseOut = (e)=> {
    e.preventDefault();
    this.setState({
      display: 'none'
    });
  }

  MinusClick=()=>{
    if(this.state.amount==0){
      return;
    }
    this.setState({
      amount: this.state.amount-1
    });
  }
  PlusClick=()=>{
    this.setState({
      amount: this.state.amount+1
    });
  }
  OptClick=()=>{
    Modal.info({
      title: '操作事件',
      content: (
        <div>
          <p>具体实现暂未定义</p>
          <p>Sorry</p>
        </div>
      ),
      onOk() {},
    });
  }

  render() {
    const item = this.props.data;
    return (
      <Col span="6">
        <Card title={'编码：'+item.id} bodyStyle={{ padding: 0 }} onMouseOver={this.handleMouseOver}
              onMouseOut={this.handleMouseOut}>
          <div className="custom-image">
            <img alt="example" width="100%" src={APPCONFIG.PublicURL+'assets/demo/supplier/'+item.id+'.jpg'}/>
          </div>
          <div className="custom-card">
            <p>{item.isMajor}</p>
            <p>{item.overdue}</p>
            <p>{item.description}</p>
            <p>
              <span>{item.creationDate}</span>
              <span>{item.nums}/片</span>
            </p>
          </div>
          <div className="div_opt" style={{display:this.state.display}}>
            <ul>
              <li className="icon-m-p">
                <Icon type="minus" style={{ fontSize: 24,color: '#08c'}} onClick={this.MinusClick} />
              </li>
              <li className="li-span">
                <span>{this.state.amount}</span>
              </li>
              <li className="icon-m-p">
                <Icon type="plus" style={{ fontSize: 24,color: '#08c'}} onClick={this.PlusClick}/>
              </li>
              <li className="icon-shopping">
                <Icon type="shopping-cart" style={{ fontSize: 22, color: '#fff',position: 'relative',top: 2 }} onClick={this.OptClick}/>
              </li>
            </ul>
          </div>
        </Card>
      </Col>
    );
  }
}
export default ProductList;

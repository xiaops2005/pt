/**
 * Created by Bojc on 2017/6/1.
 */
import React from 'react';
import {connect} from 'react-redux';
import {BackTop} from 'antd';
import '../supplier.css';
import Category from './CategoryViewer';
import SearchRegion from './SearchRegionViewer'
import ProductList from './ProductList';
import QuickJumperPagination,{SimplePagination} from './Pagination';
import {LoadProductData,GetProductDataByFilter} from './SupplierProcessor';

class Supplier extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      category: {
        id: 10001,
        name: "美容材料",
        curId: 10000,
        list: [
          {
            id: 1,
            name: "面部护理"
          },
          {
            id: 2,
            name: "眼部"
          },
          {
            id: 3,
            name: "耳部"
          },
          {
            id: 4,
            name: "嘴部"
          },
          {
            id: 5,
            name: "手部"
          },
          {
            id: 6,
            name: "全身"
          }
        ]
      }
    };
  }

  componentWillMount() {

    const dispatch = this.props.dispatch;

    let params = {
      supplier: '',
      standard: '',
      company: '',
      categoryId: '',
      pageIndex: 1
    };
    LoadProductData(params,dispatch);
  }

  handleClickSort = (col, sort)=> {
    var params = this.props.filter;
    if(params.sort == sort&&params.sortName == col){
      params.sort = '';
      params.sortName = '';
    }else {
      params.sort = sort;
      params.sortName = col;
    }

    const dispatch = this.props.dispatch;
    GetProductDataByFilter(params,dispatch);
  }

  render() {

    const category = this.state.category;
    const product = this.props.productList;
    const filter = this.props.filter;
    const pageInfo = this.props.pageInfo;

    return (
      <div className="div_supplier">

        <div className="div_left">
          <p className="sys_title">OES4.0</p>
          <Category data={category} dispatch={this.props.dispatch}/>
        </div>

        <div className="div_right">
          <SearchRegion data={filter} dispatch={this.props.dispatch}/>
          <div className="div_content">
            <p style={{'font-weight': 'bold','font-size': '1rem',paddingBottom: '10px'}}>{filter.categoryName}</p>
            <div style={{borderBottom: '1px solid #ccc',height:'37px'}}>
              <ul className="ul_sortcol">
                <li className={filter.sortName=='ID'?'cur':''}>编码
                  <div>
                    <span className={'spasc '+((filter.sortName=='ID'&&filter.sort=='asc')?'active':'')} title="升序" onClick={()=>this.handleClickSort('ID','asc')}>∧</span>
                    <span className={'spdesc '+((filter.sortName=='ID'&&filter.sort=='desc')?'active':'')} title="降序" onClick={()=>this.handleClickSort('ID','desc')}>∨</span>
                  </div>
                </li>
                <li className={filter.sortName=='isMajor'?'cur':''}>名称
                  <div>
                    <span className={'spasc '+((filter.sortName=='isMajor'&&filter.sort=='asc')?'active':'')} title="升序" onClick={()=>this.handleClickSort('isMajor','asc')}>∧</span>
                    <span className={'spdesc '+((filter.sortName=='isMajor'&&filter.sort=='desc')?'active':'')} title="降序" onClick={()=>this.handleClickSort('isMajor','desc')}>∨</span>
                  </div>
                </li>
                <li className={filter.sortName=='creationDate'?'cur':''}>厂商
                  <div>
                    <span className={'spasc '+((filter.sortName=='creationDate'&&filter.sort=='asc')?'active':'')} title="升序" onClick={()=>this.handleClickSort('creationDate','asc')}>∧</span>
                    <span className={'spdesc '+((filter.sortName=='creationDate'&&filter.sort=='desc')?'active':'')} title="降序" onClick={()=>this.handleClickSort('creationDate','desc')}>∨</span>
                  </div>
                </li>
              </ul>
              <SimplePagination data={pageInfo} filter={filter} dispatch={this.props.dispatch}/>
            </div>

            <ProductList data={product}/>
            <QuickJumperPagination data={pageInfo} filter={filter} dispatch={this.props.dispatch}/>
          </div>
        </div>

        <div >
          <BackTop visibilityHeight="0"/>
        </div>
      </div>
    );
  }
}

//export default Page;
const mapStateToProps = (state, ownProps) => ({
  productList: state.supplier.productList,
  pageInfo: state.supplier.pageInfo,
  filter: state.supplier.filter
});

export default connect(
  mapStateToProps
)(Supplier);

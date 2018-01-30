/**
 * Created by hp on 2017/6/15.
 */
import React from 'react';
import { Router } from 'react-router';
import { Form,Table, Input, Button, Icon } from 'antd';
import 'antd/dist/antd.css';
import { Pagination } from 'antd';
import {connect} from 'react-redux';

import HttpUtil from '../../../../../constants/httpUtil';
import  {userQueryAction} from '../../../models/actions/demoAction';
import UserFormView from './UserFormViewer';

class userManagerProcessor{
//取得表格展现的数据
  handleTableChange(pagination,condition,viewer)
  {
    var dc = new window.DataCenter();
    var ds =condition!=null?condition:viewer.props.condition;
    //ds.setRowSetName("com.viewhigh.vadp.framework.demo.entity.DemoUser")
    dc.addDataStore("user", ds);
    dc.setParameter("_boId", "demoUserService");
    dc.setParameter('_methodName', 'getDemoUser');
    dc.setParameter('_methodParameterTypes', 'com.viewhigh.vadp.framework.demo.entity.DemoUser');
    dc.setParameter("_pageNumber",pagination!=null?pagination.current:0);
    dc.setParameter("_pageSize",  pagination!=null?pagination.pageSize:5);
    dc.setParameter("_calc", true);
    dc.setParameter("_parameters", "user");
    HttpUtil.post('/api/commonProcessor/commonMethod',dc,function (data) {
        var dc=data;
        var ds=dc.getSingleDataStore();
        viewer.setState({
            data: dc.getSinglePrimary(),
            pagination:{total:ds.getRecordCount(),pageSize:ds.getPageSize(),current:ds.getPageNumber(),defaultCurrent:0}
        });
    },function(data){
    });
  };
 };

export default userManagerProcessor;

import NetUitl from 'constants/httpUtil';
import {AhandClick,AprePage,AafterPage,AsucuessApi} from 'routes/demo/models/actions/demoAction';
import {message} from 'antd';
export function requestData(currentPage,dispatch,flagInt){
  var dc = new window.DataCenter();
  var ds = new window.DataStore();
  dc.setParameter("_boId", "EmployeeInfoServiceImpl");
  if(flagInt==2){
    dc.setParameter('_methodName', 'getDemoUserListAll');
  }else{
    dc.setParameter('_methodName', 'getDemoUserList');
  }
  dc.setParameter('_methodParameterTypes', 'String,Integer,Integer');
  dc.setParameter("_parameters","userId,currentPage,PageNum");
  dc.setParameter("userId","10afb968-46aa-11e7-bf80-c85b76bc198e");
  dc.setParameter("currentPage",currentPage);
  dc.setParameter("PageNum",6);
  NetUitl.post('/api/commonProcessor/commonMethod',dc,function(data){
    var recodeCou=data.dataStores.result.recordCount;
    var result1=data.getSinglePrimary();
    var tempSumPage=0;
    tempSumPage=Math.floor(recodeCou/6);
    if(recodeCou%6>0){
      tempSumPage+=1;
    }
    if(result1.length>0){
      document.getElementById("flag1").style.display="";
      document.getElementById("flag2").style.display="";
      document.getElementById("flag3").style.display="";
    }
    dispatch(AsucuessApi({array:result1,sumPage:tempSumPage,currentPage:currentPage}));
  },function(){});
}

export  function prePage(tcurrentPage,dispatch,buttonFlag){
       if(tcurrentPage==1){
         message.warning("已经是第一页了,请选择下一页！",1.5);
         return;
        }else{
        var tempPage=tcurrentPage-1;
          requestData(tempPage,dispatch,buttonFlag);
         dispatch(AprePage({currentPage:tempPage}));
       }
  }
  export function afterPage(tcurrentPage,dispatch,sumPage,buttonFlag){
     if(tcurrentPage==sumPage){
       message.warning("已经是最后一页了,请选择上一页！",1.5);
      return;
      }
      var tempPage=tcurrentPage+1;
      requestData(tempPage,dispatch,buttonFlag);
      dispatch(AafterPage({currentPage:tempPage}));
}




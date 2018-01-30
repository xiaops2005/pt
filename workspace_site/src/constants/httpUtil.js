import {Alert} from 'antd';
import ModelMsgBox from 'constants/ModelMsgBox';

const URL = '/api/commonProcessor/commonMethod';

class NetUtil {
  /*
   *  get请求
   *  url:请求地址
   *  data:参数(json对象)
   *  successFun:成功-回调函数
   *  failedFun:失败-回调函数
   * */
  static get(url, params, successFun, failedFun) {
    this.post(url, params, successFun, failedFun);
  }

  static ajaxGet(url, params, successFun, failedFun) {
    fetch(url, {
      method: 'get'
    }).then(function (res) {
      if (res.ok) {
        if (successFun) {
          res.json().then((data) => {
            successFun(data);

          });
        } else {
          return res.json();
        }
      } else {
        return Promise.reject({
          status: res.status,
          statusText: res.statusText,
          url: res.url
        });
      }
    }).catch(function (err) {
      Window.progress.close();
      let title = err.statusText;
      let detail = err.url;
      ModelMsgBox.ErrorMsg(title, detail);
      return;
    });
  }

  static ajaxPost(url, params, successFun, failedFun) {
    console.log("body:::"+JSON.stringify(params))
    fetch(url, {
      method: 'POST',
      credentials: 'include',
      mode: 'cors',
      body:params
    }).then(function (res) {
      if (res.ok) {
        if (successFun) {
          res.json().then((data) => {
            successFun(data);

          });
        } else {
          return res.json();
        }
      } else {
        return Promise.reject({
          status: res.status,
          statusText: res.statusText,
          url: res.url
        });
      }
    }).catch(function (err) {
      Window.progress.close();
      let title = err.statusText;
      let detail = err.url;
      ModelMsgBox.ErrorMsg(title, detail);
      return;
    });
  }


  /*
   *  post请求
   *  url:请求地址
   *  data:参数(DataCenter对象)
   *  successFun:成功-回调函数
   *  failedFun:失败-回调函数
   * */
  static post(url, params, successFun, failedFun) {
    if (!url || url.length == 0) {
      url = URL;
    }
    if (!Window.progress.isProcessing) {
      Window.progress.open();
    }
    //fetch请求
    fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      },
      credentials: 'include',
      mode: 'cors',
      body: params.toJson()//JSON.stringify(params)
    })
      .then((res) => {
        Window.progress.close();
        if (res.ok) {
          if (successFun) {
            res.json().then((data) => {
              if (data.header.code == -1) {
                if (failedFun) {
                  let dc = new window.DataCenter(data);
                  failedFun(dc);
                } else {
                  let title = data.header.message.title;
                  let detail = data.header.message.detail;
                  ModelMsgBox.ErrorMsg(title, detail);
                  return;
                }
              } else {
                let dc = new window.DataCenter(data);
                successFun(dc);
              }
            });
          } else {
            return res.json();
          }
        } else {
          return Promise.reject({
            status: res.status,
            statusText: res.statusText,
            url: res.url
          });
        }
      })
      .catch(err => {
        Window.progress.close();
        let title = err.statusText;
        let detail = err.url;
        ModelMsgBox.ErrorMsg(title, detail);
        return;
      });
  }

  static getUrl() {
    let prol = "http://" + document.location.hostname + ":8080"
    return prol
  }

  static getSpecialUrl() {
    return "http://192.168.1.99:8080";
  }

}


export default NetUtil;


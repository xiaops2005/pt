import {Modal} from 'antd';

class PromiseUtil {

  static doGet = (path, succFn, errFn) => {
    return PromiseUtil.createPromise(path, null, succFn, errFn)
  };
  static doPost = (path, data, succFn, errFn) => {
    return PromiseUtil.createPromise(path, data, succFn, errFn)
  };

  static createPromise = (path, data, succFn, errFn) => {
    if (!Window.progress.isProcessing) {
      Window.progress.open();
    }
    let doHttp;
    let url = `http://${document.location.hostname}:8080${path}`;
    if (data !== null) {
      let formData = new FormData();
      for (let prop in data) {
        formData.append(prop, data[prop]);
      }
      doHttp = fetch(url, {
        method: 'POST',
        headers: {
          "content-type": "application/json;charset=UTF-8"
        },
        body: JSON.stringify(data)
      });
    } else {
      doHttp = fetch(url)
    }
    return new Promise(
      (resolve, reject) => {
        let data;
        doHttp
          .then(function (res) {
            Window.progress.close();
            if (res.ok) {
              res.text().then(function (text) {
                data = text ? JSON.parse(text) : {};
                console.log("typeof SuccFn :"+ typeof succFn)
                if (typeof succFn === "function") {
                  succFn(data);
                }
                console.log(`${url} resolved!`);
                resolve(data);
              });
            } else {
              data = {
                status: res.status,
                statusText: res.statusText
              };
              if (typeof errFn === "function") {
                errFn(data);
              }
              console.log(`${url} rejected!`);
              reject(data);
            }
          }).catch(err => {
          Window.progress.close();
          data = {
            status: err.status,
            statusText: err.statusText
          };
          if (typeof errFn === "function") {
            errFn(data);
          } else {
            Modal.error({
              title: "系统提示",
              content: err.statusText
            });
          }
          console.log(`${url} error!`);
          reject(data);
        });
      }
    )
  };

  static  isPromise = (val) => {
    return val && typeof val.then === 'function';
  };
}

export default PromiseUtil

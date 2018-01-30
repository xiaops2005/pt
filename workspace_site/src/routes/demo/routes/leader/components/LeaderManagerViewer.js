import React, {Component} from 'react';
import ReactEcharts from 'routes/ui/components/ReactECharts/index';
import {Form} from 'antd';
import LeaderFormViewer from './LeaderFormViewer';
import {connect} from 'react-redux';
class LeaderManagerViewer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      chart1: {},
      chart2: {},
      chart3: {},
      chart4: {},
      chart4xAxis: [],
      chart4seriesArr: [],
      chart3xAxis: [],
      chart3seriesArr: [],
      chart2dataArr: [],
      chart1val: 0,
    };

  }


  render() {
    if (JSON.stringify(this.props.data) != "[]") {
      this.state.chart4xAxis = this.props.data.dataStores.result.rowSet.primary[0].chart4.xAxis;
      this.state.chart4seriesArr = this.props.data.dataStores.result.rowSet.primary[0].chart4.seriesArr;
      this.state.chart3xAxis = this.props.data.dataStores.result.rowSet.primary[0].chart3.xAxis;
      this.state.chart3seriesArr = this.props.data.dataStores.result.rowSet.primary[0].chart3.seriesArr;
      this.state.chart2dataArr = this.props.data.dataStores.result.rowSet.primary[0].chart2.dataArr;
      this.state.chart1val = this.props.data.dataStores.result.rowSet.primary[0].chart1;
    }

    this.state.chart1 = {
      title: {
        text: '人力资源预警',
        x: 'center'
      },
      tooltip: {
        formatter: "{b} : {c}%"
      },
      series: [
        {
          name: '',
          type: 'gauge',
          center: ['50%', '60%'],
          detail: {formatter: '{value}%'},
          data: [{value: Number(this.state.chart1val), name: '离职率'}]
        }
      ]
    };

    this.state.chart2 = {
      title: {
        text: '自愿离职分析',
        x: 'center'
      },
      tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c}人({d}%)"
      },
      legend: {
        top: '8%',
        orient: 'horizontal', // 'vertical'
        x: 'center', // 'center' | 'left' | {number},
        y: 'center', // 'center' | 'bottom' | {number}
        data: ['工作压力', '薪资福利', '职业发展', '个人原因', '外部环境']
      },

      series: [
        {
          name: '自愿离职分析',
          type: 'pie',
          radius: '40%',
          center: ['50%', '60%'],
          data: this.state.chart2dataArr,
          itemStyle: {
            normal: {
              label: {
                show: true,
                formatter: "{b}: {c}人({d}%)"
              },
              labelLine: {
                show: true
              }
            },
            emphasis: {
              label: {
                show: false
              }
            }
          }
        }
      ]
    };
    this.state.chart3 = {
      title: {
        text: '人员学历分布',
        x: 'center'
      },
      tooltip: {
        trigger: 'axis',
        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
          type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
      },
      legend: {
        top: '8%',
        orient: 'horizontal', // 'vertical'
        x: 'center', // 'center' | 'left' | {number},
        y: 'center', // 'center' | 'bottom' | {number}
        data: ['研究生', '大学本科', '大专和专科学校', '中专和中技', '技工学校', '无学历数据']
      },

      xAxis: [
        {
          type: 'category',
          data: this.state.chart3xAxis,
          axisLabel: {
            rotate: 30
          }
        }
      ],
      yAxis: [
        {
          type: 'value',
          name: '数量',
          axisLabel: {
            formatter: '{value}人'
          }
        }
      ],
      grid: {
        top: '25%'
      },
      series: this.state.chart3seriesArr
    };

    this.state.chart4 = {
      title: {
        text: '在职人员年龄分布',
        x: 'center'
      },
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'shadow'
        }
      },
      legend: {
        top: '8%',
        orient: 'horizontal', // 'vertical'
        x: 'center', // 'center' | 'left' | {number},
        y: 'center', // 'center' | 'bottom' | {number}
        data: ['总数', '30岁以下', '30岁(含)-40岁', '40岁(含)-50岁', '50岁以上', '平均年龄']
      },
      grid: {
        top: '25%',
        left: '13%',
        right: '14%'

      },
      xAxis: {
        type: 'category',
        data: this.state.chart4xAxis,
        axisLabel: {
          rotate: 30
        }
      },
      yAxis: [
        {
          type: 'value',
          name: '数量',
          axisLabel: {
            formatter: '{value}人'
          }
        },
        {
          type: 'value',
          name: '年龄',
          axisLabel: {
            formatter: '{value}岁'
          }
        }
      ],

      series: this.state.chart4seriesArr

    };

    // const LeaderFormViewTag = Form.create()(LeaderFormViewer);
    return (
      <div>
        <LeaderFormViewer {...this.props} />
        <div className="row">
          <div className="col-xl-6">

            <div className="card card-inverse">
              <div className="card-block">
                <ReactEcharts style={{height: '300px'}} option={this.state.chart1} showLoading={false}/>
              </div>
            </div>
          </div>
          <div className="col-xl-6">
            <div className="card card-inverse">
              <div className="card-block">
                <ReactEcharts style={{height: '300px'}} option={this.state.chart2} showLoading={false}/>
              </div>
            </div>
          </div>
        </div>
        <div className="row">
          <div className="col-xl-6">
            <div className="card card-inverse">
              <div className="card-block">
                <ReactEcharts style={{height: '400px'}} option={this.state.chart3} showLoading={false}/>
              </div>
            </div>
          </div>
          <div className="col-xl-6">
            <div className="card card-inverse">
              <div className="card-block">
                <ReactEcharts style={{height: '400px'}} option={this.state.chart4} showLoading={false}/>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
const mapStateToProps = (state, ownProps) => {
  return {
    data: state.query.data,

  };
}
export default connect(
  mapStateToProps
)(LeaderManagerViewer);

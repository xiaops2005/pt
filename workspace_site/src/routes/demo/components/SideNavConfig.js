const SideNavConfig = {
  favorites: {
    sideCollapsed: false,
    topmenu: {
      key: 'one',
      name: '我的收藏'
    },
    menu: [
      {
        key: 'two',
        name: '我的借款',
        selected: true
      }, {
        key: 'two-1',
        name: '我的报销',
        selected: false
      }, {
        key: 'two-3-2',
        icon: '',
        name: '到期预警',
        selected: false
      }, {
        key: 'two-3-3',
        icon: '',
        name: '我的审批',
        selected: false
      },
      {
        key: 'two-3-4',
        name: '我的消息',
        selected: false
      }, {
        key: 'two-3-5',
        icon: '',
        name: '我的组织',
        selected: false
      }
    ]
  },
  menus: {
    sideCollapsed: false,
    topmenu: {
      key: 'one',
      name: '功能选择'
    },
    menu: [
      {
        key: 'two',
        name: '资金支出控制',
        selected: true
      }, {
        key: 'two-1',
        name: '会计核算',
        selected: false
      }, {
        key: 'two-3-2',
        icon: '',
        name: '采购管理',
        selected: false
      }, {
        key: 'two-3-3',
        icon: '',
        name: '物流管理',
        selected: false
      },
      {
        key: 'two-3-4',
        name: '人力资源管理',
        selected: false
      }, {
        key: 'two-3-5',
        icon: '',
        name: '合同管理',
        selected: false
      }
    ]
  },
  roles: {
    sideCollapsed: false,
    topmenu: {
      key: 'one',
      name: '角色选择'
    },
    menu: [
      {
        key: 'two',
        name: '管理员',
        selected: true
      }, {
        key: 'two-1',
        name: '业务主管',
        selected: false
      }, {
        key: 'two-3-2',
        icon: '',
        name: '财务主管',
        selected: false
      }, {
        key: 'two-3-3',
        icon: '',
        name: '人力主管',
        selected: false
      },
      {
        key: 'two-3-4',
        name: '生产主管',
        selected: false
      }, {
        key: 'two-3-5',
        icon: '',
        name: '销售主管',
        selected: false
      }
    ]

  },
  titles: {
    sideCollapsed: false,
    topmenu: {
      key: 'comp',
      name: '平台Demo'
    },
    menu: [
      // {
      //   key: 'homepage',
      //   icon: 'home',
      //   name: '首页'
      // },
      {
        key: 'oesDemo',
        icon: 'picture',
        name: '样例',
        child: [
          {
            key: 'account',
            name: '我的报销单'
          }, {
            key: 'leader',
            name: '领导自助'
          }, {
            key: 'mangeSelf',
            name: '经理自助'
          }, {
            key: 'supplier',
            name: '供应商'
          }, {
            key: 'tree',
            name: '树样例'
          }, {
            key: 'user',
            name: '员工信息管理'
          }, {
            key: 'upload',
            name: '上传下载'
          }, {
            key: 'formViewer',
            name: '表单样例'
          }, {
            key: 'modalDemo',
            name: 'modal用例'
          }
        ]
      },

    ]
  },
  sideback: {
    sideCollapsed: false,
    menu: [
      {
        key: 'sideback',
        name: '1 弹框返回',
        selected: true
      }, {
        key: 'sideback-1',
        name: '2 弹框返回',
        selected: false
      }, {
        key: 'sideback-3-2',
        icon: '',
        name: '3 弹框返回',
        selected: false
      }, {
        key: 'sideback-3-3',
        icon: '',
        name: '4 弹框返回',
        selected: false
      },
      {
        key: 'sideback-3-4',
        name: '5 弹框返回',
        selected: false
      }, {
        key: 'sideback-3-5',
        icon: '',
        name: '6 弹框返回',
        selected: false
      }
    ]
  },
  taskMore: {
    key: 'taskMore1',
    title: "我的待办任务",
    width: "852px",
    contentMore: [
      {
        key: 'taskMore1-1',
        time: '2017-06-01 刚刚 ',
        content: '[ 审批 ] 科室收入数据提交，待审批。',
        keyword1: '',
        keyword2: '',
      }, {
        key: 'taskMore1-2',
        time: '2017-06-01 08：30',
        content: '[ 审批 ] 医院各类科室直接成本表提交。',
        keyword1: '某某某',
        keyword2: '',
      }, {
        key: 'taskMore1-3',
        time: '2017-05-30 11：15',
        content: '[ 借款预警 ] 手术借款逾期',
        keyword1: ' ',
        keyword2: '2017-6-20',

      }, {
        key: 'taskMore1-4',
        time: '2017-05-29 09：20',
        content: '[ 出差报销 ] 上海出差审批至',
        keyword1: '某某某',
        keyword2: '徐科峰',
      },
      {
        key: 'taskMore1-5',
        time: '2017-05-26 10：30',
        content: '[ 出差报销 ] 广州出差审批至',
        keyword1: '某某某',
        keyword2: '徐科峰',
      }
    ]
  },
  statistic: {
    key: 'statistic1',
    title: "公告（2）",
    width: "452px",
    contentMore: [
      {
        key: 'statistic1-1',
        time: '2017-05-22',
        content: '请各科室提交科室收入数据',
      }, {
        key: 'statistic1-2',
        time: '2017-05-21',
        content: '还没有提交直接成本标的科室尽快进行提交',
      }, {
        key: 'statistic1-3',
        time: '2017-05-20',
        content: '本月临床服务类收益明细表调整说明',

      }, {
        key: 'statistic1-4',
        time: '2017-05-19',
        content: '服务类科室医院成本构成分析明细表审核',
      }, {
        key: 'statistic1-5',
        time: '2017-06-29',
        content: '本月临床服务类收益明细表调整说明',
      }, {
        key: 'statistic1-6',
        time: '2017-06-29',
        content: '服务类科室医院成本构成分析明细表审核',
      }
    ]
  },
  capital: {
    sideCollapsed: false,
    topmenu: {
      key: 'capital',
      name: '资金支出控制'
    },
    menu: [
      {
        key: 'homepage',
        icon: 'home',
        name: '首页'
      }, {
        key: 'capital-1',
        icon: 'bars',
        name: '个人业务办理',
        child: [
          {
            key: 'capital-1-1',
            icon: 'user',
            name: '我的借款'
          }, {
            key: 'capital-1-2',
            icon: '',
            name: '我的报销'
          },
          {
            key: 'capital-1-3',
            name: '到期预警表'
          }
        ]
      },
      {
        key: 'capital-2',
        icon: 'bars',
        name: '业务审批',
        child: [
          {
            key: 'capital-2-1',
            name: '报销审批'
          }, {
            key: 'capital-2-2',
            icon: '',
            name: '借款审批'
          }
        ]
      },
      {
        key: 'capital-3',
        icon: 'bars',
        name: '财务处理'
      },
      {
        key: 'capital-4',
        icon: 'bars',
        name: '报销费用统计'
      },
      {
        key: 'capital-5',
        icon: 'bars',
        name: '预算执行分析'
      },
      {
        key: 'capital-6',
        icon: 'bars',
        name: '科教经费管理'
      },
      {
        key: 'capital-7',
        icon: 'bars',
        name: '信用管理'
      },
      {
        key: 'capital-8',
        icon: 'bars',
        name: '基础设置'
      }
    ]
  },
};

export default SideNavConfig;

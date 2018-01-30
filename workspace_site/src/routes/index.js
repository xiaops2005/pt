import React from "react";
import Loadable from "components/Loadable";

import Login from "./login/";
import UI from "./ui/";
import Designer from "./designer/";
import Home from "./home/";
import PtFans from "./ptfans";
import AdminPage from "./ptfans/admin";


import "./cost/cost-supervise.css";

const AsyncAdmin = Loadable({
  loader: () => import(/* webpackChunkName: "admin" */'./admin/')
});

const AsyncDemo = Loadable({
  loader: () => import('./demo/')
});

const routes = [
  {
    exact: true,
    path: '/',
    component: Login,
  },
  {
    name: "网站管理",
    path: '/admin2',
    component: AsyncAdmin
  },
  {
    name: "网站Demo",
    path: '/demo',
    component: AsyncDemo
  },

  {
    name: "UI规范",
    path: '/ui',
    component: UI
  },
  {
    name: "设计器",
    path: '/designer',
    component: Designer
  },
  {
    name: "PT爱好者",
    path: '/ptfans',
    component: PtFans
  },
  {
    name: "管理",
    path: '/admin',
    component: AdminPage
  },
  {
    exact: true,
    path: '/home',
    component: Home,

  }

];
export  default routes;

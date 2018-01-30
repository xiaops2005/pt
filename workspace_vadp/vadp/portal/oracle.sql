
create table td_pt_domain(
    id varchar2(32) primary key not null,
    created_by varchar2(50),
    create_date timestamp,
    last_modified_by varchar2(32),
    last_modified_date TIMESTAMP,
    address varchar2(200),
    city varchar2(255) DEFAULT NULL,
  contact varchar2(255) DEFAULT NULL,
  district varchar2(255) DEFAULT NULL,
  name varchar2(200) NOT NULL,
  phone varchar2(255) DEFAULT NULL,
  province varchar2(255) DEFAULT NULL
);



INSERT INTO td_pt_domain VALUES ('402882825f0a53b9015f0a53d3630000','system',to_date('2017-10-11 15:26:27','yyyy-mm-dd hh24:mi:ss'),'test1',to_date('2017-10-16 17:16:30','yyyy-mm-dd hh24:mi:ss'),NULL,NULL,NULL,NULL,'默认组织',NULL,NULL);
insert into td_pt_domain values('402882825f0a93d1015f0a9c36ef0001','402882825f0a53b9015f0a53d3910001',to_date('2017-10-11 16:45:31','yyyy-mm-dd hh24:mi:ss'),'test1',to_date('2017-10-16 17:01:48','yyyy-mm-dd hh24:mi:ss'),NULL,NULL,NULL,NULL,'客户A',NULL,NULL);
insert into td_pt_domain values('402889105f2999cb015f2999df730000','test1',to_date('2017-10-17 17:11:11','yyyy-mm-dd hh24:mi:ss'),NULL,to_date('2017-10-17 17:11:11','yyyy-mm-dd hh24:mi:ss'),NULL,NULL,NULL,NULL,'组织0',NULL,NULL);
insert into td_pt_domain values('402889105f2999cb015f2999dfac0001','test1',to_date('2017-10-17 17:11:11','yyyy-mm-dd hh24:mi:ss'),NULL,to_date('2017-10-17 17:11:11','yyyy-mm-dd hh24:mi:ss'),NULL,NULL,NULL,NULL,'组织1',NULL,NULL);
insert into td_pt_domain values('402889105f4d0c73015f4d0e4d930001','402882825f0a53b9015f0a53d3910001',to_date('2017-10-24 14:25:04','yyyy-mm-dd hh24:mi:ss'),NULL,to_date('2017-10-24 14:25:04','yyyy-mm-dd hh24:mi:ss'),NULL,NULL,NULL,NULL,'东软望海',NULL,NULL);


CREATE TABLE td_pt_domain_user (
  id varchar2(32) primary key NOT NULL,
  created_by varchar2(32) NOT NULL,
  created_date timestamp NOT NULL,
  last_modified_by varchar2(50) DEFAULT NULL,
  last_modified_date timestamp DEFAULT NULL,
  domain_id varchar2(255) DEFAULT NULL,
  user_id varchar2(255) DEFAULT NULL

) ;


INSERT INTO td_pt_domain_user VALUES ('402889105f2999cb015f2999dff30003','tests',to_date('2017-10-17 17:11:11','yyyy-mm-dd hh24:mi:ss'),NULL,to_date('2017-10-17 17:11:11','yyyy-mm-dd hh24:mi:ss'),'402889105f2999cb015f2999dfac0001','402882825f0a93d1015f0a95e89f0000');

CREATE TABLE td_pt_menu (
  id varchar2(32) primary key NOT NULL,
  created_by VARCHAR2(32) NOT NULL,
  created_date timestamp NOT NULL,
  last_modified_by varchar2(32) DEFAULT NULL,
  last_modified_date timestamp DEFAULT NULL,
  icon varchar2(255) DEFAULT NULL,
  name varchar2(255) DEFAULT NULL,
  order_num number(11,0) DEFAULT NULL,
  parent_id varchar2(32) DEFAULT NULL,
  parent_name varchar2(255) DEFAULT NULL,
  perms varchar2(255) DEFAULT NULL,
  type number(11,0) DEFAULT NULL,
  url varchar2(255) DEFAULT NULL
) ;
INSERT INTO td_pt_menu VALUES ('402882825f0a53b9015f0a559baa0003','402882825f0a53b9015f0a53d3910001',to_date('2017-10-11 15:28:23','yyyy-mm-dd hh24:mi:ss'),'402882825f0a53b9015f0a53d3910001',to_date('2017-10-19 15:18:42','yyyy-mm-dd hh24:mi:ss'),NULL,'原形应用',NULL,NULL,'',NULL,NULL,'/');
INSERT INTO td_pt_menu VALUES('402882825f0a53b9015f0a56a0eb0004','402882825f0a53b9015f0a53d3910001',to_date('2017-10-11 15:29:30','yyyy-mm-dd hh24:mi:ss'),'402882825f0a53b9015f0a53d3910001',to_date('2017-10-19 15:40:05','yyyy-mm-dd hh24:mi:ss'),NULL,'api控制',NULL,'402882825f0a53b9015f0a559baa0003','根节点',NULL,NULL,'api/admin/');
INSERT INTO td_pt_menu VALUES('402882825f0a53b9015f0a56f69c0005','402882825f0a53b9015f0a53d3910001',to_date('2017-10-11 15:29:52','yyyy-mm-dd hh24:mi:ss'),'402882825f0a53b9015f0a53d3910001',to_date('2017-10-24 14:27:07','yyyy-mm-dd hh24:mi:ss'),NULL,'管理控制台',NULL,'402882825f0a53b9015f0a559baa0003','',NULL,NULL,'/admin/');
INSERT INTO td_pt_menu VALUES('402882825f0a7826015f0a83d30b0000','402882825f0a53b9015f0a53d3910001',to_date('2017-10-11 16:18:52','yyyy-mm-dd hh24:mi:ss'),'402882825f0a53b9015f0a53d3910001',to_date('2017-10-19 15:43:01','yyyy-mm-dd hh24:mi:ss'),NULL,'角色管理api',NULL,'402882825f0a53b9015f0a56a0eb0004','管理控制台','sys:role:list,sys:role:create,sys:role:read,sys:role:update,sys:role:delete',NULL,'/api/admin/role/');
INSERT INTO td_pt_menu VALUES('402882825f0a7826015f0a8f23530004','402882825f0a53b9015f0a53d3910001',to_date('2017-10-11 16:31:14','yyyy-mm-dd hh24:mi:ss'),NULL,to_date('2017-10-11 16:31:14','yyyy-mm-dd hh24:mi:ss'),NULL,'组织管理',NULL,'402882825f0a53b9015f0a56a0eb0004','管理控制台','sys:domain:list,sys:domain:create,sys:domain:read,sys:domain:update,sys:domain:delete',NULL,'/api/admin/domain/');
INSERT INTO td_pt_menu VALUES('402882825f0a7826015f0a9042970005','402882825f0a53b9015f0a53d3910001',to_date('2017-10-11 16:32:27','yyyy-mm-dd hh24:mi:ss'),NULL,to_date('2017-10-11 16:32:27','yyyy-mm-dd hh24:mi:ss'),NULL,'用户管理',NULL,'402882825f0a53b9015f0a56a0eb0004','管理控制台','sys:user:list,sys:user:create,sys:user:read,sys:user:update,sys:user:delete',NULL,'/api/admin/user/');
INSERT INTO td_pt_menu VALUES('402882825f0a7826015f0a911a9b0006','402882825f0a53b9015f0a53d3910001',to_date('2017-10-11 16:33:23','yyyy-mm-dd hh24:mi:ss'),NULL,to_date('2017-10-11 16:33:23','yyyy-mm-dd hh24:mi:ss'),NULL,'菜单管理',NULL,'402882825f0a53b9015f0a56a0eb0004','管理控制台','sys:menu:list,sys:menu:create,sys:menu:read,sys:menu:update,sys:menu:delete',NULL,'/api/admin/menu/');
INSERT INTO td_pt_menu VALUES('402889105f37c22c015f48422309000a','402882825f0a53b9015f0a53d3910001',to_date('2017-10-23 16:03:35','yyyy-mm-dd hh24:mi:ss'),'402882825f0a53b9015f0a53d3910001',to_date('2017-10-23 16:05:32','yyyy-mm-dd hh24:mi:ss'),'plus','菜单管理',NULL,'402882825f0a53b9015f0a56f69c0005',NULL,'*',NULL,'/admin/menus');
INSERT INTO td_pt_menu VALUES('402889105f4d0c73015f4d0dbc1d0000','402882825f0a53b9015f0a53d3910001',to_date('2017-10-24 14:24:27','yyyy-mm-dd hh24:mi:ss'),NULL,to_date('2017-10-24 14:24:27','yyyy-mm-dd hh24:mi:ss'),'appstore','组织管理',NULL,'402882825f0a53b9015f0a56f69c0005',NULL,'sys:domain:list,sys:domain:create,sys:domain:read,sys:domain:update,sys:domain:delete',NULL,'/admin/domains');
INSERT INTO td_pt_menu VALUES('402889105f4d0c73015f4d0ed89c0004','402882825f0a53b9015f0a53d3910001',to_date('2017-10-24 14:25:39','yyyy-mm-dd hh24:mi:ss'),'402882825f0a53b9015f0a53d3910001',to_date('2017-10-24 14:37:07','yyyy-mm-dd hh24:mi:ss'),'trademark','角色管理',NULL,'402882825f0a53b9015f0a56f69c0005',NULL,'sys:role:list;sys:role:create;sys:role:read;sys:role:update;sys:role:delete',NULL,'/admin/roles');
INSERT INTO td_pt_menu VALUES('402889105f4d0c73015f4d1b04940005','402882825f0a53b9015f0a53d3910001',to_date('2017-10-24 14:38:57','yyyy-mm-dd hh24:mi:ss'),'402882825f0a53b9015f0a53d3910001',to_date('2017-10-24 14:39:30','yyyy-mm-dd hh24:mi:ss'),'user-add','用户管理',NULL,'402882825f0a53b9015f0a56f69c0005',NULL,'sys:user:list,sys:user:create,sys:user:read,sys:user:update,sys:user:delete',NULL,'/admin/users');


CREATE TABLE td_pt_role (
  id varchar2(32) primary key NOT NULL,
  created_by varchar2(32) NOT NULL,
  created_date timestamp NOT NULL,
  last_modified_by varchar2(32) DEFAULT NULL,
  last_modified_date timestamp DEFAULT NULL,
  domain_id varchar(255) DEFAULT NULL,
  menu_ids varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  remark varchar(255) DEFAULT NULL,
  status number(4,0) DEFAULT NULL,
  systemed char(1) DEFAULT NULL

) ;
INSERT INTO td_pt_role VALUES ('402882825f0a7826015f0a87f9ba0001','402882825f0a53b9015f0a53d3910001',to_date('2017-10-11 16:23:19','yyyy-mm-dd hh24:mi:ss'),'test1',to_date('2017-10-24 17:29:25','yyyy-mm-dd hh24:mi:ss'),'402882825f0a53b9015f0a53d3630000',NULL,'administrators','超级管理员sss',NULL,'');
INSERT INTO td_pt_role VALUES('402882825f0a7826015f0a8b80ad0002','402882825f0a53b9015f0a53d3910001',to_date('2017-10-11 16:27:15','yyyy-mm-dd hh24:mi:ss'),NULL,to_date('2017-10-11 16:27:15','yyyy-mm-dd hh24:mi:ss'),'402882825f0a53b9015f0a53d3630000',NULL,'managers','组织管理员',1,'');
INSERT INTO td_pt_role VALUES('402882825f0a7826015f0a8c46480003','402882825f0a53b9015f0a53d3910001',to_date('2017-10-11 16:28:06','yyyy-mm-dd hh24:mi:ss'),NULL,to_date('2017-10-11 16:28:06','yyyy-mm-dd hh24:mi:ss'),'402882825f0a53b9015f0a53d3630000',NULL,'users','普通用户',1,'');
INSERT INTO td_pt_role VALUES('402882825f0a93d1015f1375aed00006','402882825f0a53b9015f0a53d3910001',to_date('2017-10-13 10:00:00','yyyy-mm-dd hh24:mi:ss'),NULL,to_date('2017-10-13 10:00:00','yyyy-mm-dd hh24:mi:ss'),NULL,NULL,'hhh',NULL,1,'');
INSERT INTO td_pt_role VALUES('402889105f4d0c73015f4d0e4db20002','402882825f0a53b9015f0a53d3910001',to_date('2017-10-24 14:25:04','yyyy-mm-dd hh24:mi:ss'),NULL,to_date('2017-10-24 14:25:04','yyyy-mm-dd hh24:mi:ss'),'402889105f4d0c73015f4d0e4d930001',NULL,'managers','组织管理员',1,'');
INSERT INTO td_pt_role VALUES('402889105f4d0c73015f4d0e4dc50003','402882825f0a53b9015f0a53d3910001',to_date('2017-10-24 14:25:04','yyyy-mm-dd hh24:mi:ss'),NULL,to_date('2017-10-24 14:25:04','yyyy-mm-dd hh24:mi:ss'),'402889105f4d0c73015f4d0e4d930001',NULL,'users','员工',1,'');

CREATE TABLE td_pt_role_menu (
  id varchar2(32) primary key NOT NULL,
  created_by varchar2(32) NOT NULL,
  created_date timestamp NOT NULL,
  last_modified_by varchar2(32) DEFAULT NULL,
  last_modified_date timestamp DEFAULT NULL,
  menu_id varchar2(255) DEFAULT NULL,
  role_id varchar2(255) DEFAULT NULL
) ;

CREATE TABLE td_pt_user (
  id varchar2(32) primary key NOT NULL,
  created_by varchar2(32) NOT NULL,
  created_date timestamp NOT NULL,
  last_modified_by varchar2(32) DEFAULT NULL,
  last_modified_date timestamp DEFAULT NULL,
  domain_id varchar2(255) DEFAULT NULL,
  email varchar2(255) DEFAULT NULL,
  mobile varchar2(255) DEFAULT NULL,
  password varchar2(100) NOT NULL,
  salt varchar2(255) DEFAULT NULL,
  status number(4) DEFAULT NULL,
  username varchar(50) NOT NULL
) ;



INSERT INTO td_pt_user VALUES ('402882825f0a53b9015f0a53d3910001','system',to_date('2017-10-11 15:26:27','yyyy-mm-dd hh24:mi:ss'),NULL,to_date('2017-10-11 15:26:27','yyyy-mm-dd hh24:mi:ss'),'402882825f0a53b9015f0a53d3630000',NULL,NULL,'ac0e7d037817094e9e0b4441f9bae3209d67b02fa484917065f71b16109a1a78','admin',1,'admin');
INSERT INTO td_pt_user VALUES ('402882825f0a93d1015f0a95e89f0000','402882825f0a53b9015f0a53d3910001',to_date('2017-10-11 16:38:19','yyyy-mm-dd hh24:mi:ss'),NULL,to_date('2017-10-11 16:38:19','yyyy-mm-dd hh24:mi:ss'),'402882825f0a53b9015f0a53d3630000','test1@example.com',NULL,'8bd2a2d6e18774586909be919ec6ae37b84ef9de8c7501bbe416aec9ece38023','test1',1,'test1');
INSERT INTO td_pt_user VALUES ('402882825f0a93d1015f0a9ca9b90002','402882825f0a53b9015f0a53d3910001',to_date('2017-10-11 16:46:00','yyyy-mm-dd hh24:mi:ss'),NULL,to_date('2017-10-11 16:46:00','yyyy-mm-dd hh24:mi:ss'),'402882825f0a93d1015f0a9c36ef0001',NULL,NULL,'b7c1410f16d494c7420dda685b40129ff3df3a5dc62c2d6ccbec38921e72b40c','manager1',NULL,'manager1');
INSERT INTO td_pt_user VALUES ('402882825f0a93d1015f0aa68a4e0003','402882825f0a53b9015f0a53d3910001',to_date('2017-10-11 16:56:47','yyyy-mm-dd hh24:mi:ss'),NULL,to_date('2017-10-11 16:56:47','yyyy-mm-dd hh24:mi:ss'),'402882825f0a93d1015f0a9c36ef0001',NULL,NULL,'d4663ff0bffd10251f1c26a0a79a4708307032ecbb5180c4687319b52060b951','test11',NULL,'test11');
INSERT INTO td_pt_user VALUES ('402882825f0a93d1015f13744d9b0005','402882825f0a53b9015f0a53d3910001',to_date('2017-10-13 09:58:30','yyyy-mm-dd hh24:mi:ss'),NULL,to_date('2017-10-13 09:58:30','yyyy-mm-dd hh24:mi:ss'),'402882825f0a53b9015f0a53d3630000',NULL,NULL,'8d5fbeb1cb70e428e5b39f9a0415bfed31c891840c684af8ff553c356a880b20','ttt',NULL,'ttt');
INSERT INTO td_pt_user VALUES ('402889105f2999cb015f2999dfd50002','tests',to_date('2017-10-17 17:11:11','yyyy-mm-dd hh24:mi:ss'),NULL,to_date('2017-10-17 17:11:11','yyyy-mm-dd hh24:mi:ss'),NULL,NULL,NULL,'ba77a08dfe703be1a0bb4575e24c8b67b2b53b0ded8d706b24ace0237e1b8c77','test2',NULL,'test2');

CREATE TABLE td_pt_user_role (
  id varchar2(32) primary key NOT NULL,
  created_by varchar2(32) NOT NULL,
  created_date timestamp NOT NULL,
  last_modified_by varchar2(32) DEFAULT NULL,
  last_modified_date timestamp DEFAULT NULL,
  domain_id varchar2(255) DEFAULT NULL,
  role_id varchar2(255) DEFAULT NULL,
  user_id varchar2(255) DEFAULT NULL
) ;

CREATE TABLE td_pt_user_token (
  id varchar2(32) primary key NOT NULL,
  created_by varchar2(32) NOT NULL,
  created_date timestamp NOT NULL,
  last_modified_by varchar2(32) DEFAULT NULL,
  last_modified_date timestamp DEFAULT NULL,
  expire_time timestamp DEFAULT NULL,
  token varchar2(255) DEFAULT NULL,
  update_time timestamp DEFAULT NULL,
  user_id varchar2(32) DEFAULT NULL
) ;

INSERT INTO td_pt_user_token VALUES ('402882825f0a53b9015f0a5554ae0002','system',to_date('2017-10-11 15:28:05','yyyy-mm-dd hh24:mi:ss'),NULL,to_date('2017-10-25 10:01:46','yyyy-mm-dd hh24:mi:ss'),to_date('2017-10-25 22:01:46','yyyy-mm-dd hh24:mi:ss'),'cd68f74c7e57ef4a16cb42cb802e9385',to_date('2017-10-25 10:01:46','yyyy-mm-dd hh24:mi:ss'),'402882825f0a53b9015f0a53d3910001');

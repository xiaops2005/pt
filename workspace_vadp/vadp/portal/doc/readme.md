[toc]

# README
## 1. 使用说明

### 1.1 建议运行环境

技术平台部要求jdk1.7，目前产品部门统一jdk1.8,tomcat8及以上

### 1.2 工程导入到开发环境中
current svn 1513

#### 1.2.1 Intellij Idea

操作步骤如下：

> 1. import project vadp-parent   
> 2. 在打开的工作空间中，打开Project Structure,选中modules，点击+，然后将其它所有module import即可

#### 1.2.2 Eclipse

操作步骤如下：

> 1. 在Project Explorer中右键点击 import
> 2. 选择Existing Maven Projects，然后点击Next
> 3. 点击Root Directory 右侧的Browse，然后选择vadp所在文件夹，全选，然后点击完成
> 4. 打开Navigator视图，修改vadp-web/.settings/org.eclipse.wst.common.component，添加如下内容:     
  <wb-resource deploy-path="/WEB-INF/conf" source-path="/src/main/resources/deploy/dev"/>



### 1.3 启动

注意： _目前没有启用springboot内嵌tomcat的方式，必须像普通的web工程一样设定tomcat后才可以使用_

后端请求路径设置为：http://localhost:8080/api/

## 2. 常见问题
### 2.1 Oracle驱动无法正常下载

#### 2.1.1 原因

由于Oracle授权的原因，所以在maven仓库中无法查找和下载Oracle驱动信息，如果需要使用Oracle jdbc驱动，需要手动安装到本地仓库，
然后在使用，安装方式参见1.2。

#### 2.1.2 解决方案

目前可以使用已下载的jar包，jar包相对当前文档位置为./jar/ojdbc6.jar，安装方式如下：

````
假设MAVEN根目录为：${MAVEN_HOME},framework-demo的根目录为：${FRAMEWORK-DEMO},那么在命令行使用如下命令进行安装：

${MAVEN_HOME}/bin/mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.4 -Dpackaging=jar -Dfile=${FRAMEWORK-DEMO}/doc/jar/ojdbc6.jar

````


## 3. 版本变更说明

_记录拿到基础平台部原始版本框架后，产品规划与设计部对原始版本版本的修改内容_

### V0.8

#### _原始版本介绍_
0.8版本中portal并不依赖vadp-parent,只依赖vadp-data

> 各个module工程介绍 

> * vadp-parent:  平台module parent，一般不需要进行修改。  
> * portal:   平台module，权限相关核心依赖。
> * vadp-core:    平台module,核心依赖，一般不需要进行修改。  
> * vadp-data:    vadp-core的依赖，用来进行数据库的操作，一般不需要进行修改。   
> * vadp-dl:  平台module,分布式锁，一般不需要进行修改。   
> * vadp-exception:   平台module,统一异常底层，一般不需要进行修改。
> * vadp-plugin:  平台module，扩展插件依赖底层，一般不需要进行修改。
> * vadp-session:    平台module,分布式session，一般不需要进行修改。
> * vadp-web:    web工程，后端代码在此工程中进行代码编写。

#### _修改内容_
__目前vadp-web中实际上只依赖于portal/vadp-data/vadp-parent/vadp-exception/vadp-core__

* 本次升级只升级后端，前端继续使用版本v0.5
* 整体升级版本号为0.8
* pom.xml文件修改  
  > 1. vadp-parent: 升级springboot版本为1.5.9，依赖版本修改到properties中，添加其它产品jar包依赖；  
  > 2. 其他工程： 比照vadp-parent对pom.xml进行修改；
    
* 只保留portal/vadp-data/vadp-exception/vadp-parent/vadp-web模块，其他模块全部移除； 
* portal  
  > 1. 添加对vadp-parent的依赖，将依赖jar包版本由vadp-parent统一管理； 
* vadp-data  
  > 1. 添加com.viewhigh.ext包，用来存放扩展内容；  
  > 2. 修改BaseHibernateDAO，添加@component注解、添加从env中读取配置文件数据、  
  > 3. 添加根据配置信息是否显示SQL和参数； 
  > 4. 修改LoadConfigFile Line119为if(!StringUtils.isEmpty(pro.getProperty(DataConfConst.CUSTOM_DATASOURCE_NAMES)))，需要去掉.trim()，否则会报空指针异常 ；
  
* vadp-web 
 
  > 1. 修改application.properties为application.yml，并且将相应的动态配置转移到相应的扩展yml中;  
  > 2. 迁移vadp-data.properties：从/WEB-INF/conf下迁移到resources/deploy/${env}/conf下；    
  > 3. Application.java中添加Excel相关@bean;  

 


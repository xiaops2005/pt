package com.viewhigh.vadp.framework.common.controler;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.viewhigh.vadp.framework.common.controler.util.CommonUtil;
import com.viewhigh.vadp.framework.common.controler.util.TypeKit;
import com.viewhigh.vadp.framework.data.persistence.pagination.PageInfo;
import com.viewhigh.vadp.framework.data.persistence.pagination.PageInfoThreadConstant;
import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;
import com.viewhigh.vadp.framework.data.trans.DynamicDataSourceSetting;
import com.viewhigh.vadp.framework.data.trans.TransactionManager;
import com.viewhigh.vadp.framework.ds.BOContext;
import com.viewhigh.vadp.framework.ds.DataCenter;
import com.viewhigh.vadp.framework.ds.Parameter;
import com.viewhigh.vadp.framework.exception.CoreException;
import com.viewhigh.vadp.framework.exception.ServiceException;
import com.viewhigh.vadp.framework.exception.util.ExceptionI18nUtil;
import com.viewhigh.vadp.framework.util.CookieUtil;
import com.viewhigh.vadp.framework.util.encrypt.Base64Encrypt;

@SuppressWarnings("all")
public class BaseProcessor {

	protected final Logger log = LoggerFactory.getLogger(BaseProcessor.class);
	
//	@Autowired(required = false)
//	protected IDynamicDataSourceSwitch dynamicDataSourceSwitch;
	
	@Autowired  
	protected DefaultKaptcha defaultKaptcha; 
	
	/**
	 * @Title: download   
	 * @Description:下载文件
	 * @param: @param filePath
	 * @param: @param response      
	 * @return: void      
	 * @throws
	 */
	public void download(String filePath, HttpServletResponse response) {
		try {
			String path = System.getProperty("web.root");
			File file = new File(path + File.separator + filePath);
			
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName=" + file.getName());
			
			InputStream inputStream = new FileInputStream(file);

			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			os.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title: upload   
	 * @Description:上传文件   
	 * @param: @param file
	 * @param: @param request
	 * @param: @param resultMap      
	 * @return: void      
	 * @throws
	 */
	public void upload(MultipartFile file, HttpServletRequest request, Map<String, Object> resultMap) {
		DataCenter model = new DataCenter();
		resultMap.put("uid", System.currentTimeMillis());
		try{
			if (file == null) {
				resultMap.put("status", "error");
				model.getHeader().setCode(-1);
				resultMap.put("dataCenter", model);
				return;
			}
			
			String s = new Base64Encrypt().Decode(request.getParameter("data"));
			DataCenter dataCenter = DataCenter.parseCenter(s);
			
			Object result = CommonUtil.invokeBoMethod(dataCenter, file);
			
			setResult(model, result);
			
			model.getHeader().setCode(1);
			resultMap.put("status", "success");
			resultMap.put("dataCenter", model);
		}catch(Exception e){
			resultMap.put("status", "error");
			log.error(e.toString());
			pushErrorMsg(model, e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title: setResult   
	 * @Description: 放置结果集给前台   
	 * @param: @param model
	 * @param: @param result      
	 * @return: void      
	 * @throws
	 */
	public void setResult(DataCenter model, Object result) {
		if(result == null){
			model.getHeader().setCode(1);
			return;
		}
		//多返回值处理
		if(result instanceof BOContext){
			BOContext context = (BOContext) result;
			Iterator<Entry<String, Object>> iterator = context.getValue().entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator.next();
				setDataCenter(model, entry.getValue(), entry.getKey());
			}
			return;
		}
		//基本返回值处理
		setDataCenter(model, result, "result");
	}

	/**
	 * @Title: getRequest   
	 * @Description: 获取客户端请求数据
	 * @param: @param input
	 * @param: @return
	 * @param: @throws IOException      
	 * @return: StringBuffer      
	 * @throws
	 */
	public StringBuffer getRequest(InputStream input) throws IOException {
		StringBuffer requestBody = new StringBuffer();
		List<String> readLines = IOUtils.readLines(input,"utf-8");
		for (String val : readLines) {
			requestBody.append(val);
		}
		return requestBody;
	}

	/**
	 * 
	 * @Title: setDataCenter   
	 * @Description: 放置返回结果到DataCenter给客户端  
	 * @param: @param model
	 * @param: @param result
	 * @param: @param key      
	 * @return: void      
	 * @throws
	 */
	public void setDataCenter(DataCenter model, Object result,String key) {
		//返回的数据类型是基本类型 放到body的 Parameters里面 
		if(TypeKit.isPrimitiveType(result)){
			model.getBody().getParameters().put(Parameter.key_result, result);
		}else{
			com.viewhigh.vadp.framework.ds.DataStore bodyValue = new com.viewhigh.vadp.framework.ds.DataStore();
			if(result instanceof List){
				bodyValue.getRowSet().getPrimary().addAll((List) result);
				bodyValue.setRecordCount(((List) result).size());
			}else if(result instanceof QueryResult){
				QueryResult page = (QueryResult) result;
				bodyValue.setPageSize(page.getPageSize());
				bodyValue.setPageNumber(page.getPageNumber());
				bodyValue.setRecordCount(page.getRecordCount());
				bodyValue.getRowSet().getPrimary().add(result);
			} else {
				bodyValue.getRowSet().getPrimary().add(result);
			}
			
			if(bodyValue.getRowSet().getPrimary().size() > 0){
				bodyValue.setRowSetName(bodyValue.getRowSet().getPrimary().get(0).getClass().getName());
			}
			model.getBody().getDataStores().put(key, bodyValue);
		}
	}

	/**
	 * 放置错误信息
	 * @param model
	 * @param e
	 */
	public void pushErrorMsg(DataCenter model, Exception e) {
		model.getHeader().setCode(-1);
		if (e instanceof CoreException) {
			CoreException ex = (CoreException) e;
			pushVadpException(model, ex);
		} else if (e instanceof ServiceException) {
			ServiceException ex = (ServiceException) e;
			pushVadpException(model, ex);
		} else {
			model.getHeader().getMessage().setTitle("12000");
			model.getHeader().getMessage().setDetail(e.toString());
		}
	}

	/**
	 * 放置平台错误信息
	 * @param model
	 * @param e
	 */
	public void pushVadpException(DataCenter model, CoreException ex) {
		model.getHeader().getMessage().setTitle(ex.getCode());
		model.getHeader().getMessage().setDetail(ExceptionI18nUtil.getI18nInfo(ex, null));
	}
	
	/**
	 * @Title: invoke   
	 * @Description: 调用后台业务类
	 * @param: @param request
	 * @param: @param model      
	 * @return: void      
	 * @throws
	 */
	public void invoke(HttpServletRequest request, DataCenter model) {
		//下面注释掉了原来的动态切换数据源，旧的方法依赖spring，新的方法不在依赖,只需要采用DynamicDataSourceSetting.setDataSourceKey方法即可
//		if (dynamicDataSourceSwitch != null) {
//			//设置数据源
//			dynamicDataSourceSwitch.setDataSourceKey(CookieUtil.getCookieByName(request, "_defaultDataSourceKey"));
////			DynamicDataSourceContextHolder.setDataSourceType(CookieUtil.getCookieByName(request, "_defaultDataSourceKey"));
//		}
		//启动事务 
//		TransactionManagerFactory tmf=new TransactionManagerFactory();
		DynamicDataSourceSetting.setDataSourceKey(CookieUtil.getCookieByName(request, "_defaultDataSourceKey"));
//		TransactionManager tmf=new TransactionManager();
		try {
			
//			tmf.beginTransaction();
			StringBuffer requestBody = getRequest(request.getInputStream());
			DataCenter dataCenter = DataCenter.parseCenter(requestBody.toString());
			//放置查询对象
			request.setAttribute("queryResult", dataCenter);
			//添加分页参数到线程变量中，为后边的数据库操作服务
			PageInfo pi=new PageInfo();
			String _pageNumber = dataCenter.getBody().getParameters().get("_pageNumber") + "";
			String _pageSize = dataCenter.getBody().getParameters().get("_pageSize") + "";
			String autoCalc = dataCenter.getBody().getParameters().get("_calc") + "";
			pi.setCalc(dataCenter.getBody().getParameters().get("_calc") + "");
			pi.setPageNum(_pageNumber);
			pi.setPageSize(_pageSize);
			PageInfoThreadConstant.setPageInfo(pi);
			Object result = CommonUtil.invokeBoMethod(dataCenter, null);
			setResult(model, result);
			model.getHeader().setCode(1);
//			tmf.comminTransaction();
			
		} catch (Exception e) {
//			tmf.rollbackTransaction();
			log.error(e.toString());
			pushErrorMsg(model, e);
//			e.printStackTrace();
		}
	}
	
	/**
	 * 生成验证码   
	 * @param: @param httpServletRequest
	 * @param: @param httpServletResponse
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws
	 */
	public void captcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
		byte[] captchaChallengeAsJpeg = null;
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		try {
			// 生产验证码字符串并保存到session中
			String createText = defaultKaptcha.createText();
			httpServletRequest.getSession().setAttribute("_captchaCode", createText);
			// 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
			BufferedImage challenge = defaultKaptcha.createImage(createText);
			ImageIO.write(challenge, "jpg", jpegOutputStream);
		} catch (IllegalArgumentException e) {
			httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		// 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
		captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
		httpServletResponse.setHeader("Cache-Control", "no-store");
		httpServletResponse.setHeader("Pragma", "no-cache");
		httpServletResponse.setDateHeader("Expires", 0);
		httpServletResponse.setContentType("image/jpeg");
		ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
		responseOutputStream.write(captchaChallengeAsJpeg);
		responseOutputStream.flush();
		responseOutputStream.close();
	}
}

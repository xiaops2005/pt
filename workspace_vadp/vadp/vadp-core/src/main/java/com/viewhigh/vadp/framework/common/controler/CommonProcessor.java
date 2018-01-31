package com.viewhigh.vadp.framework.common.controler;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.viewhigh.vadp.framework.ds.DataCenter;
/**
 * 
 * 总控制器
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月16日
 * 修改日期: 2017年06月16日
 */
@SuppressWarnings("all")
@Controller
@RequestMapping("commonProcessor")
public class CommonProcessor extends BaseProcessor {
	
	protected final Logger log = LoggerFactory.getLogger(CommonProcessor.class);
	
	/**
	 * @Title: download   
	 * @Description: 文件下载 
	 * @param: @param fileName
	 * @param: @param request
	 * @param: @param response
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/commonMethodDownload")
	public String download(String filePath, HttpServletRequest request, HttpServletResponse response) {
		super.download(filePath, response);
		return null;
	}


	/**
	 * @Title: commonMethodUpload   
	 * @Description: 文件上传
	 * @param: @param request
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping("commonMethodUpload")
	@ResponseBody
	public Object commonMethodUpload(MultipartFile file,HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<>();
		super.upload(file, request, resultMap);
		return resultMap;
	}

	
	/**
	 * 
	 * @Title: commonMethod   
	 * @Description: 处理请求信息   
	 * @param: @param request
	 * @param: @param response
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping("commonMethod")
	@ResponseBody
	public Object commonMethod(HttpServletRequest request,HttpServletResponse response) {
		DataCenter model = new DataCenter();
		super.invoke(request, model);
		return model;
	}

	/**
	 * 生成验证码
	 * @param: @param httpServletRequest
	 * @param: @param httpServletResponse
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws
	 */
	@RequestMapping("/captcha")
	public void captcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
		super.captcha(httpServletRequest, httpServletResponse);
	}
	

}

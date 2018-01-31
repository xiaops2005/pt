package com.viewhigh.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.viewhigh.service.ReMateDetailService;
@Controller
@RequestMapping("/reMateDetailController")
public class ReMateDetailController {
	
	protected final Logger log = LoggerFactory.getLogger(ReMateDetailController.class);
	
	@Autowired
	ReMateDetailService reMateDetailService;
	
	
	
	@RequestMapping(value = "/exportExcel/{year}/{pkHospital}/{materialsDeptCode}/{materialsDeptName}/{isCharges}/{pkMaterials}")
	public Object exportExcel(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("year") Integer year,
			@PathVariable("pkHospital") String pkHospital,
			@PathVariable("materialsDeptCode") String materialsDeptCode,
			@PathVariable("materialsDeptName") String materialsDeptName,
			@PathVariable("isCharges") String isCharges,
			@PathVariable("pkMaterials") String pkMaterials,
			Model model) {
		reMateDetailService.exportReMateDetail(year, pkHospital, materialsDeptCode, materialsDeptName, isCharges, pkMaterials);
		return null;
	}
	

}

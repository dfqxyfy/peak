/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.eqcode.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.eqcode.entity.PeakEqcode;
import com.thinkgem.jeesite.modules.eqcode.service.PeakEqcodeService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 生成二维码错误Controller
 * @author ccs
 * @version 2017-03-05
 */
@Controller
@RequestMapping(value = "${adminPath}/eqcode/peakEqcode")
public class PeakEqcodeController extends BaseController {

	@Autowired
	private PeakEqcodeService peakEqcodeService;
	
	@ModelAttribute
	public PeakEqcode get(@RequestParam(required=false) String id) {
		PeakEqcode entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = peakEqcodeService.get(id);
		}
		if (entity == null){
			entity = new PeakEqcode();
		}
		return entity;
	}
	
	//@RequiresPermissions("eqcode:peakEqcode:view")
	@RequestMapping(value = {"list", ""})
	public String list(PeakEqcode peakEqcode, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PeakEqcode> page = peakEqcodeService.findPage(new Page<PeakEqcode>(request, response), peakEqcode); 
		model.addAttribute("page", page);
		return "modules/eqcode/peakEqcodeList";
	}

	//@RequiresPermissions("eqcode:peakEqcode:view")
	@RequestMapping(value = "form")
	public String form(PeakEqcode peakEqcode, Model model) {
		model.addAttribute("peakEqcode", peakEqcode);
		return "modules/eqcode/peakEqcodeForm";
	}

	//@RequiresPermissions("eqcode:peakEqcode:edit")
	@RequestMapping(value = "save")
	public String save(PeakEqcode peakEqcode, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, peakEqcode)){
			return form(peakEqcode, model);
		}
		peakEqcodeService.save(peakEqcode);
		addMessage(redirectAttributes, "保存查看错误日志成功");
		return "redirect:"+Global.getAdminPath()+"/eqcode/peakEqcode/?repage";
	}
	
	//@RequiresPermissions("eqcode:peakEqcode:edit")
	@RequestMapping(value = "delete")
	public String delete(PeakEqcode peakEqcode, RedirectAttributes redirectAttributes) {
		peakEqcodeService.delete(peakEqcode);
		addMessage(redirectAttributes, "删除查看错误日志成功");
		return "redirect:"+Global.getAdminPath()+"/eqcode/peakEqcode/?repage";
	}

	@RequestMapping("fileUpload")
	public String  fileUpload(@RequestParam("file") CommonsMultipartFile file) throws IOException {
		//用来检测程序运行时间
		long  startTime=System.currentTimeMillis();
		System.out.println("fileName："+file.getOriginalFilename());
		new Thread(new DealFileRunnable(file,peakEqcodeService)).start();
		long  endTime=System.currentTimeMillis();
		System.out.println("方法一的运行时间："+String.valueOf(endTime-startTime)+"ms");
		return "modules/eqcode/peakEqcodeList";
	}

	class DealFileRunnable implements Runnable{
		private CommonsMultipartFile file;
		private PeakEqcodeService peakEqcodeService;
		public DealFileRunnable(CommonsMultipartFile file,PeakEqcodeService peakEqcodeService){
			this.file = file;
			this.peakEqcodeService = peakEqcodeService;
		}
		@Override
		public void run() {
			peakEqcodeService.dealFile(file);
		}
	}
}
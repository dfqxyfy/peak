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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.eqcode.entity.PeakEqcodeRepeat;
import com.thinkgem.jeesite.modules.eqcode.service.PeakEqcodeRepeatService;

/**
 * 生成二维码错误Controller
 * @author ccs
 * @version 2017-03-05
 */
@Controller
@RequestMapping(value = "${adminPath}/eqcode/peakEqcodeRepeat")
public class PeakEqcodeRepeatController extends BaseController {

	@Autowired
	private PeakEqcodeRepeatService peakEqcodeRepeatService;
	
	@ModelAttribute
	public PeakEqcodeRepeat get(@RequestParam(required=false) String id) {
		PeakEqcodeRepeat entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = peakEqcodeRepeatService.get(id);
		}
		if (entity == null){
			entity = new PeakEqcodeRepeat();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(PeakEqcodeRepeat peakEqcodeRepeat, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PeakEqcodeRepeat> page = peakEqcodeRepeatService.findPage(new Page<PeakEqcodeRepeat>(request, response), peakEqcodeRepeat); 
		model.addAttribute("page", page);
		return "modules/eqcode/peakEqcodeRepeatList";
	}

	@RequestMapping(value = "form")
	public String form(PeakEqcodeRepeat peakEqcodeRepeat, Model model) {
		model.addAttribute("peakEqcodeRepeat", peakEqcodeRepeat);
		return "modules/eqcode/peakEqcodeRepeatForm";
	}

	@RequestMapping(value = "save")
	public String save(PeakEqcodeRepeat peakEqcodeRepeat, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, peakEqcodeRepeat)){
			return form(peakEqcodeRepeat, model);
		}
		peakEqcodeRepeatService.save(peakEqcodeRepeat);
		addMessage(redirectAttributes, "保存查看错误日志成功");
		return "redirect:"+Global.getAdminPath()+"/eqcode/peakEqcodeRepeat/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(PeakEqcodeRepeat peakEqcodeRepeat, RedirectAttributes redirectAttributes) {
		peakEqcodeRepeatService.delete(peakEqcodeRepeat);
		addMessage(redirectAttributes, "删除查看错误日志成功");
		return "redirect:"+Global.getAdminPath()+"/eqcode/peakEqcodeRepeat/?repage";
	}

}
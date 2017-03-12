/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.eqcode.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.eqcode.entity.PeakEqcodeRepeat;
import com.thinkgem.jeesite.modules.eqcode.dao.PeakEqcodeRepeatDao;

/**
 * 生成二维码错误Service
 * @author ccs
 * @version 2017-03-05
 */
@Service
//@Transactional(readOnly = true)
public class PeakEqcodeRepeatService extends CrudService<PeakEqcodeRepeatDao, PeakEqcodeRepeat> {

	public PeakEqcodeRepeat get(String id) {
		return super.get(id);
	}
	
	public List<PeakEqcodeRepeat> findList(PeakEqcodeRepeat peakEqcodeRepeat) {
		return super.findList(peakEqcodeRepeat);
	}
	
	public Page<PeakEqcodeRepeat> findPage(Page<PeakEqcodeRepeat> page, PeakEqcodeRepeat peakEqcodeRepeat) {
		return super.findPage(page, peakEqcodeRepeat);
	}
	
	//@Transactional(readOnly = false)
	public void save(PeakEqcodeRepeat peakEqcodeRepeat) {
		super.save(peakEqcodeRepeat);
	}
	
	@Transactional(readOnly = false)
	public void delete(PeakEqcodeRepeat peakEqcodeRepeat) {
		super.delete(peakEqcodeRepeat);
	}
	
}
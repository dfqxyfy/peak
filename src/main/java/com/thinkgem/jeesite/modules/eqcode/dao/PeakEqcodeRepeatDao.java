/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.eqcode.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.eqcode.entity.PeakEqcodeRepeat;

/**
 * 生成二维码错误DAO接口
 * @author ccs
 * @version 2017-03-05
 */
@MyBatisDao
public interface PeakEqcodeRepeatDao extends CrudDao<PeakEqcodeRepeat> {
	
}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.eqcode.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 生成二维码错误Entity
 * @author ccs
 * @version 2017-03-05
 */
public class PeakEqcodeRepeat extends DataEntity<PeakEqcodeRepeat> {
	
	private static final long serialVersionUID = 1L;
	private String eqcodeText;		// eqcode_text
	private Date createTime;		// create_time
	private Date beginCreateTime;		// 开始 create_time
	private Date endCreateTime;		// 结束 create_time
	
	public PeakEqcodeRepeat() {
		super();
	}

	public PeakEqcodeRepeat(String id){
		super(id);
	}

	@Length(min=0, max=255, message="eqcode_text长度必须介于 0 和 255 之间")
	public String getEqcodeText() {
		return eqcodeText;
	}

	public void setEqcodeText(String eqcodeText) {
		this.eqcodeText = eqcodeText;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getBeginCreateTime() {
		return beginCreateTime;
	}

	public void setBeginCreateTime(Date beginCreateTime) {
		this.beginCreateTime = beginCreateTime;
	}
	
	public Date getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = endCreateTime;
	}
		
}
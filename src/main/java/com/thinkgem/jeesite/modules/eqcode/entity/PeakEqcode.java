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
public class PeakEqcode extends DataEntity<PeakEqcode> {
	
	private static final long serialVersionUID = 1L;
	private String openId;		// open_id
	private String eqcodeText;		// eqcode_text
	private Date createTime;		// create_time
	private Date updateTime;		// update_time
	private Date beginCreateTime;		// 开始 create_time
	private Date endCreateTime;		// 结束 create_time
	private String imgPath;

	public PeakEqcode() {
		super();
	}

	public PeakEqcode(String id){
		super(id);
	}

	@Length(min=0, max=50, message="open_id长度必须介于 0 和 50 之间")
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}
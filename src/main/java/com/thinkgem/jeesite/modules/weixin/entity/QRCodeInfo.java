package com.thinkgem.jeesite.modules.weixin.entity;

import java.io.Serializable;

/**
 * Created by huangweidong on 2017/3/10
 * email:wb-huangweidong@le.com
 */
public class QRCodeInfo implements Serializable {
    private String id;
    private Integer status;
    private String eqCodeText;
    private String openId;
    private String msg;
    private String code;
    private String createTime;
    private String updateTime;
    private String imgPath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEqCodeText() {
        return eqCodeText;
    }

    public void setEqCodeText(String eqCodeText) {
        this.eqCodeText = eqCodeText;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}

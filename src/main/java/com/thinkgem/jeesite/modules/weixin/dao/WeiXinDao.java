package com.thinkgem.jeesite.modules.weixin.dao;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.weixin.entity.QRCodeInfo;

/**
 * Created by huangweidong on 2017/3/10
 * email:wb-huangweidong@le.com
 */
@MyBatisDao
public interface WeiXinDao {
    QRCodeInfo getCodeInfoByOpenId(String openId);

    QRCodeInfo getUnUsedCode();

    int updateCodeToUsed(QRCodeInfo qrCodeInfo);
}

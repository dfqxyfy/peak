package com.thinkgem.jeesite.modules.weixin.service;

import com.thinkgem.jeesite.modules.weixin.dao.WeiXinDao;
import com.thinkgem.jeesite.modules.weixin.entity.QRCodeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huangweidong on 2017/3/10
 * email:wb-huangweidong@le.com
 */
@Service
public class QRCodeService {

    @Autowired
    private WeiXinDao weiXinDao;

    public QRCodeInfo getCodeInfo(String openId) {
        QRCodeInfo qrCodeInfo = weiXinDao.getCodeInfoByOpenId(openId);
        if (qrCodeInfo == null) {
            synchronized (this) {
                qrCodeInfo = weiXinDao.getUnUsedCode();
                if (qrCodeInfo == null) {
                    return null;
                }
                qrCodeInfo.setOpenId(openId);
                weiXinDao.updateCodeToUsed(qrCodeInfo);
            }
        }
        return qrCodeInfo;
    }

}

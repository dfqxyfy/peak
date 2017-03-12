package com.thinkgem.jeesite.modules.weixin.web;

import com.thinkgem.jeesite.modules.weixin.entity.QRCodeInfo;
import com.thinkgem.jeesite.modules.weixin.service.QRCodeService;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huangweidong on 2017/2/8
 * email:wb-huangweidong@le.com
 */
@Controller
public class QRCodeResponseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxMpService wxMpService;

    @Value("${sns_host:http://kh56.61why.com/peak}")
    private String SNS_HOST;

    @Autowired
    private QRCodeService qrCodeService;


    @RequestMapping("getQrCodeImg")
    public String getQrCodeImg(@ModelAttribute("openId") String openId,
                               @ModelAttribute("code") String code,
                               @ModelAttribute("msg") String msg,
                               Model model,
                               HttpServletResponse response) {
        if (StringUtils.isEmpty(code)) {
            getOpenIdFromWeixin(response);
            return "";
        }

        QRCodeInfo qrCodeInfo = null;

        //授权成功
        if (StringUtils.equalsIgnoreCase("1000", code) && StringUtils.isNotEmpty(openId)) {
            qrCodeInfo = qrCodeService.getCodeInfo(openId);
        }

        if (qrCodeInfo == null) {
            qrCodeInfo = new QRCodeInfo();
        }

        qrCodeInfo.setCode(code);
        qrCodeInfo.setMsg(msg);
        model.addAttribute("qrCodeInfo", qrCodeInfo);
        return "modules/weixin/qrcode";
    }


    public void getOpenIdFromWeixin(HttpServletResponse response) {
        //redirectURI 用户授权完成后的重定向链接
        StringBuffer redirectURI = new StringBuffer(SNS_HOST + "/weixin/callback/");
        String authUrl = wxMpService.oauth2buildAuthorizationUrl(redirectURI.toString(), WxConsts.OAUTH2_SCOPE_BASE, null);
       /* 如果用户同意授权，页面将跳转至 redirect_uri/?code=CODE&state=STATE。
        若用户禁止授权，则重定向后不会带上code参数，
        仅会带上state参数 redirect_uri?state=STATE*/
        try {
            response.sendRedirect(authUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过code获得基本用户信息
     */
    @RequestMapping(value = "weixin/callback", method = RequestMethod.GET)
    public String callback(@RequestParam(value = "code", required = false) String code,
                           @RequestParam(value = "state") String state,
                           RedirectAttributes attributes) {

        if (StringUtils.isEmpty(code)) {
            attributes.addFlashAttribute("code", "1001");
            attributes.addFlashAttribute("msg", "请在微信中打开");
        } else {
            WxMpOAuth2AccessToken accessToken;
            try {
                accessToken = this.wxMpService.oauth2getAccessToken(code);
                if (accessToken == null || StringUtils.isEmpty(accessToken.getOpenId())) {
                    attributes.addFlashAttribute("code", "1001");
                    attributes.addFlashAttribute("msg", "请在微信中打开");
                } else {
                    attributes.addFlashAttribute("code", "1000");
                    attributes.addFlashAttribute("msg", "success");
                    attributes.addFlashAttribute("openId", accessToken.getOpenId());
                }
            } catch (WxErrorException e) {
                e.printStackTrace();
            }
        }
        return "redirect:getQrCodeImg";
    }
}

package com.thinkgem.jeesite.modules.weixin.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * weixin mp configuration
 *
 * @author Binary Wang
 */
@Configuration
public class WeixinMpConfiguration {

    @Value("${appid:wx6e286ce712a217b6}")
    private String appid;

    @Value("${appsecret:7a9ea9205329f5359caa2d94c45ab86e}")
    private String appsecret;

    @Value("${token:nt03moa1jndke4pgxovelvw23pxbts15}")
    private String token;

    @Value("${aeskey:1111}")
    private String aesKey;

    @Value("${partener_id:1111}")
    private String partenerId;

    @Value("${partener_key:111}")
    private String partenerKey;

    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
        configStorage.setAppId(this.appid);
        configStorage.setSecret(this.appsecret);
        configStorage.setToken(this.token);
        configStorage.setAesKey(this.aesKey);
        configStorage.setPartnerId(this.partenerId);
        configStorage.setPartnerKey(this.partenerKey);
        return configStorage;
    }

    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

}

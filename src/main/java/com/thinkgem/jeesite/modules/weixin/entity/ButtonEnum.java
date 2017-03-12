package com.thinkgem.jeesite.modules.weixin.entity;

import me.chanjar.weixin.common.api.WxConsts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangweidong on 2017/2/9
 * email:wb-huangweidong@le.com
 */

public enum ButtonEnum {
    QRCode("B11_QRCode", "二维码", WxConsts.BUTTON_CLICK, "M1_SEARCH");

    public String key;
    public String name;
    public String type;
    public String parent;

    ButtonEnum(String key, String name, String type, String parent) {
        this.key = key;
        this.name = name;
        this.type = type;
        this.parent = parent;
    }

    static public String fromCode(String code) {
        for (ButtonEnum button : ButtonEnum.values()) {
            if (button.key.equals(code)) {
                return button.name;
            }
        }
        return "";
    }

    static public List<ButtonEnum> getChild(String parent) {
        List<ButtonEnum> list = new ArrayList<>();
        for (ButtonEnum button : ButtonEnum.values()) {
            if (button.parent.equals(parent)) {
                list.add(button);
            }
        }
        return list;
    }
}

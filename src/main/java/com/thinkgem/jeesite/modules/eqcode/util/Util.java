package com.thinkgem.jeesite.modules.eqcode.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by ccs on 2017/3/5.
 */
public class Util {
    public static String getPostfix(String path) {
        if (path == null || Common.EMPTY.equals(path.trim())) {
            return Common.EMPTY;
        }
        if (path.contains(Common.POINT)) {
            return path.substring(path.lastIndexOf(Common.POINT) + 1, path.length());
        }
        return Common.EMPTY;
    }
}

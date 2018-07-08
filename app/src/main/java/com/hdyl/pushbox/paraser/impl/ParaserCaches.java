package com.hdyl.pushbox.paraser.impl;

import com.hdyl.pushbox.paraser.IParser;
import com.hdyl.pushbox.paraser.SupportExt;
import com.hdyl.pushbox.tools.LogUtils;
import com.hdyl.pushbox.tools.Tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/7/8.
 */

public class ParaserCaches {

    public final static HashMap<String, IParser> sParsers = new HashMap<>();//默认的解析器
    public final static List<String> sSupportList = new ArrayList<>();//支持的格式
    public final static String sSupportString = Tools.list2String(sSupportList);

    static {
        List<String> list = Tools.getClassNames(ParaserCaches.class.getPackage().getName());
        LogUtils.Print("tt",list);
        for (String ss : list) {
            try {
                Class<?> class1 = Class.forName(ss);
                SupportExt ext = class1.getAnnotation(SupportExt.class);

                LogUtils.Print("tt",ext);
                if (ext != null) {
                    String extString = ext.value();
                    sParsers.put(extString, (IParser) class1.newInstance());
                    sSupportList.add(extString);


                    LogUtils.Print("tt",extString);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

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

    public final static HashMap<String, IParser> sParsers;//默认的解析器
    public final static List<String> sSupportList;//支持的格式
    public final static String sSupportString;

//    final static ParaserCaches caches = new ParaserCaches();
//
//    public static ParaserCaches getInstance() {
//        return caches;
//    }

    private static final String TAG = "ParaserCaches";

    static {
        sParsers = new HashMap<>();//默认的解析器
        sSupportList = new ArrayList<>();//支持的格式

        Class[]clazzs=new Class[]{BoxParser.class,LP0Parser.class,SlcParser.class,SokParser.class,XsbParser.class};

        LogUtils.Print(TAG, clazzs);
        for (Class class1 : clazzs) {
            try {
                SupportExt ext = (SupportExt) class1.getAnnotation(SupportExt.class);

                LogUtils.Print(TAG, ext);
                if (ext != null) {
                    String extStrings[] = ext.value();
                    IParser iParser = (IParser) class1.newInstance();
                    for (String key : extStrings) {
                        sParsers.put(key, iParser);
                        sSupportList.add(key);
                    }
                    LogUtils.Print(TAG, extStrings);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        sSupportString = Tools.list2String(sSupportList);
    }
}

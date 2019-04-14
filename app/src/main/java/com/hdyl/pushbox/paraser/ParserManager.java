package com.hdyl.pushbox.paraser;

import com.hdyl.pushbox.paraser.impl.ParaserCaches;
import com.hdyl.pushbox.soko.LevelChooseItem;
import com.hdyl.pushbox.tools.LogUtils;

import java.io.InputStream;

public class ParserManager implements IParser {

    IParser paraser;

    public ParserManager(String name) {
        name = name.toLowerCase();
        int index = name.lastIndexOf(".");
        if (index != -1) {
            String ext=name.substring(index + 1);
            paraser = ParaserCaches.sParsers.get(ext);
            LogUtils.Print("ttt","ext "+paraser);
        }


    }

    @Override
    public LevelChooseItem parse2Item(InputStream is) throws Exception {
        return paraser.parse2Item(is);
    }

}

package com.hdyl.pushbox.setting;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.hdyl.pushbox.R;
import com.hdyl.pushbox.base.App;
import com.hdyl.pushbox.base.ConstData;
import com.hdyl.pushbox.tools.MySharepreferences;
import com.hdyl.pushbox.tools.ScreenSize;

public class SettingConfig {

    public int sleepTime;// 单位毫秒
    public int progressVar;

    public int bgStyle = SKIN_BG_SYSTEM_DEFAULT;// 背景样式

    public String userImg;
    public boolean isPosition;// 是否显示坐标
    public boolean isSkinCover;//皮肤是否覆盖

    public static SettingConfig instence;

    public int skinStyle = 0;// 皮肤样式

    public String skinPath;// 皮肤路径

    public int colorRed;
    public int colorBlue;
    public int colorGreen;

    public static SettingConfig getConfig() {
        if (instence == null) {
            return instence = getSettingConfig();
        }
        return instence;
    }

    final static String fileName = "setting";
    final static String fileKey = "datas";

    public static void saveConfig() {
        MySharepreferences.putString(App.getContext(), fileName, fileKey, JSON.toJSONString(getConfig()));
    }

    public SettingConfig() {
        setPgVar(9);// 默认值 是9,
    }

    public static SettingConfig getSettingConfig() {
        String sssString = MySharepreferences.getString(App.getContext(), fileName, fileKey);
        if (sssString != null) {
            try {
                SettingConfig config = JSON.parseObject(sssString, SettingConfig.class);
                return config;
            } catch (Exception e) {
            }
        }
        return new SettingConfig();
    }

    public String getSkinName() {
        String ss[] = {"经典皮肤", "自定义皮肤"};
        return ss[skinStyle];
    }

    // 交换皮肤
    public void exchangeSkin() {
        skinStyle++;
        skinStyle = skinStyle % SKIN_INT;
        ConstData.loadBitmapsWithConfig();
    }


    final static String sSkinTexts[] = {"自定义图片背景", "自定义颜色背景", "系统1", "系统2", "系统3", "系统4", "系统5", "系统6", "系统7"};

    final static int sSkinRes[] = {0, 0, R.drawable.bg_001, R.drawable.bg_002, R.drawable.bg_003, R.drawable.bg_004, R.drawable.bg_005, R.drawable.bg_006, R.drawable.bg_007};
    public final static int SKIN_BG_USER_PICTURE = 0;
    public final static int SKIN_BG_USER_COLOR = 1;
    private final static int SKIN_BG_SYSTEM_DEFAULT = 7;

    public String getBgName() {
        return sSkinTexts[bgStyle];
    }


    public int getBgResource() {
        return sSkinRes[bgStyle];
    }

    public int getBgStyle() {
        return bgStyle;
    }

    public int getSkinStyle() {
        return skinStyle;
    }

    public String getSkinPath() {
        return skinPath;
    }

    public void setSkinPath(String skinPath) {
        this.skinPath = skinPath;
    }

    public Bitmap getSkinExampleBitmap() {
        if (skinPath == null) {
            return null;
        }
        return ConstData.bitmaps[4];
    }

    public void setSkinStyle(int skinStyle) {
        this.skinStyle = skinStyle;
    }

    public void setBgStyle(int bgStyle) {
        this.bgStyle = bgStyle;
    }

    final static int SKIN_INT = 2;

    // 交换主题
    public void exchangeTheme() {
        bgStyle++;
        bgStyle = bgStyle % sSkinTexts.length;
    }

    public void setPgVar(int progressVar) {
        this.progressVar = progressVar;
        progressVar = progressVar + 1;// 1秒多少步,5-20步
        sleepTime = 1000 / progressVar;
    }

    public void exchangePosition() {
        isPosition = !isPosition;
    }

    public void exchangeCover() {
        isSkinCover = !isSkinCover;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public int getBgColor() {
        return Color.argb(0xff, this.colorRed, this.colorGreen, this.colorBlue);
    }

    // 设置view的类型
    public void setBgWithType(View view) {
        switch (this.getBgStyle()) {
            case SKIN_BG_USER_PICTURE:// 图片背景
                Bitmap bm = BitmapFactory.decodeFile(this.getUserImg(), getOpinion());
                view.setBackgroundDrawable(coverBitmap(bm));
                break;
            case SKIN_BG_USER_COLOR:
                view.setBackgroundDrawable(new ColorDrawable(this.getBgColor()));
                break;
            default:
                Bitmap bitmap = BitmapFactory.decodeResource(App.getContext().getResources(), getBgResource(), getOpinion());
                view.setBackgroundDrawable(coverBitmap(bitmap));
                break;
        }
    }

    private BitmapDrawable coverBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        Bitmap bitmapNew = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(bitmapNew);
        canvas.drawColor(0x50000000);
        return new BitmapDrawable(bitmapNew);
    }


    private BitmapFactory.Options getOpinion() {
        BitmapFactory.Options opo = new BitmapFactory.Options();
        opo.outHeight = ScreenSize.getScreenHeight();
        opo.outWidth = ScreenSize.getScreenWidth();
        return opo;
    }


    public int getProgressVar() {
        return progressVar;
    }
}

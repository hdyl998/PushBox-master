package com.hdyl.pushbox;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.hdyl.pushbox.base.BaseActivity;
import com.hdyl.pushbox.base.ConstData;
import com.hdyl.pushbox.setting.SettingActivity;
import com.hdyl.pushbox.soko.SokoCollectionListActivity;
import com.hdyl.pushbox.tools.MySharepreferences;
import com.hdyl.pushbox.tools.Tools;
import com.hdyl.pushbox.tuijian.TuijianAcitivity;

public class MainActivity extends BaseActivity implements OnClickListener {

    ImageView imageView;
    boolean isSwitchOn;

    TextView tvVersion;

    private void setUI() {
        if (isSwitchOn) {
            imageView.setImageResource(R.drawable.m1);
        } else {
            imageView.setImageResource(R.drawable.m5);
        }
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.iv_music:
                isSwitchOn = !isSwitchOn;

                MySharepreferences.putBoolean(this, "aa", "music", isSwitchOn);

                setUI();

                Intent intent = new Intent(this, SoundService.class);
                intent.putExtra("playing", isSwitchOn);
                startService(intent);

                break;
            case R.id.iv_set:
                startActivity(new Intent(this, TuijianAcitivity.class));
                break;
            case R.id.about:
            case R.id.iv_about:
                ConstData.showAbout(this);
                break;
//            case R.id.iv_old_level:// 其它关卡
//			startActivity(new Intent(this, LevelActivity.class));
//                break;
            case R.id.iv_setting:// 设置
                startActivity(new Intent(this, SettingActivity.class));
                break;
            default:
                startActivity(new Intent(mContext, SokoCollectionListActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, SoundService.class);
        stopService(intent);
        super.onDestroy();
    }



    @Override
    public int[] setClickID() {
        return new int[]{};
    }

    @Override
    protected void initData() {
        findViewById(R.id.textView2).setOnClickListener(this);
        findViewById(R.id.iv_set).setOnClickListener(this);

        findViewById(R.id.about).setOnClickListener(this);
//        findViewById(R.id.iv_old_level).setOnClickListener(this);
        findViewById(R.id.iv_about).setOnClickListener(this);
        findViewById(R.id.iv_setting).setOnClickListener(this);

        tvVersion = (TextView) findViewById(R.id.tvVersion);

        tvVersion.setText("推箱子2018\nV$".replace("$", Tools.getVerName(mContext)));
        boolean isOn = MySharepreferences.getBoolean(this, "aa", "music", false);
        Intent intent = new Intent(this, SoundService.class);
        intent.putExtra("playing", isOn);
        startService(intent);
        isSwitchOn = isOn;

        imageView = (ImageView) findViewById(R.id.iv_music);
        imageView.setOnClickListener(this);
        setUI();


    }


    @Override
    protected int setViewId() {
        return R.layout.acvivity_main;
    }

    @Override
    protected String getPageName() {
        return "欢迎页";
    }

}

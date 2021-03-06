package com.tarardemo.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.tarardemo.R;
import com.tarardemo.base.BaseActivity;
import com.tarardemo.util.BarUtils;

/**
 * Created by Administrator on 2017/8/4 0004.
 */

public class TaraActivity extends BaseActivity {
    private int mAlpha;

    private TextView mTvStatusAlpha;
    private SeekBar sbChangeAlpha;
    private View fakeStatusBar;

    public static void start(Context context) {
        Intent starter = new Intent(context, TaraActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initData(Bundle bundle) {
        mAlpha = 112;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_tara;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        fakeStatusBar = view.findViewById(R.id.fake_status_bar);
        mTvStatusAlpha = (TextView) view.findViewById(R.id.tv_status_alpha);
        sbChangeAlpha = (SeekBar) view.findViewById(R.id.sb_change_alpha);
        view.findViewById(R.id.btn_set_transparent).setOnClickListener(this);
        sbChangeAlpha.setOnSeekBarChangeListener(translucentListener);
        mTvStatusAlpha.setText(String.valueOf(mAlpha));
        updateFakeStatusBar();
    }

    @Override
    public void doBusiness(Context context) {

    }

    @Override
    public void onWidgetClick(View view) {

    }

    private SeekBar.OnSeekBarChangeListener translucentListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            mAlpha = progress;
            mTvStatusAlpha.setText(String.valueOf(mAlpha));
            updateFakeStatusBar();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    public void updateFakeStatusBar() {
        BarUtils.setStatusBarAlpha(this, mAlpha);
    }
}

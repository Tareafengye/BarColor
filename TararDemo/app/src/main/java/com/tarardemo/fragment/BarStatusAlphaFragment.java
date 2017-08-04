package com.tarardemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.tarardemo.ui.MainActivity;
import com.tarardemo.R;
import com.tarardemo.base.BaseFragment;
import com.tarardemo.ui.TaraActivity;
import com.tarardemo.util.BarUtils;


/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/07/01
 *     desc  : Bar工具类Demo
 * </pre>
 */
public class BarStatusAlphaFragment extends BaseFragment<MainActivity> {

    private int mAlpha;

    private TextView mTvStatusAlpha;
    private SeekBar sbChangeAlpha;
    private View fakeStatusBar;


    public static BarStatusAlphaFragment newInstance() {
        return new BarStatusAlphaFragment();
    }

    @Override
    public void initData(Bundle bundle) {
        mAlpha = 112;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_bar_status_alpha;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        fakeStatusBar = view.findViewById(R.id.fake_status_bar);
        mTvStatusAlpha = (TextView) view.findViewById(R.id.tv_status_alpha);
        sbChangeAlpha = (SeekBar) view.findViewById(R.id.sb_change_alpha);
        view.findViewById(R.id.btn_set_transparent).setOnClickListener(this);
        view.findViewById(R.id.btn_set_tara).setOnClickListener(this);
        sbChangeAlpha.setOnSeekBarChangeListener(translucentListener);
        mTvStatusAlpha.setText(String.valueOf(mAlpha));

        updateFakeStatusBar();
    }

    @Override
    public void doBusiness(Context context) {

    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.btn_set_transparent:
                sbChangeAlpha.setProgress(0);
                break;
            case R.id.btn_set_tara:
                TaraActivity.start(getActivity());
                break;
        }
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
//        BarUtils.setStatusBarAlpha(fakeStatusBar);
        BarUtils.setStatusBarAlpha(getActivity(), mAlpha);
//        BarUtils.addMarginTopEqualStatusBarHeight(mTvStatusAlpha);// 其实这个只需要调用一次即可
    }
}

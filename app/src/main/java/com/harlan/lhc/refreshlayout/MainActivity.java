package com.harlan.lhc.refreshlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.FixedBehind)
    Button FixedBehind;
    @BindView(R.id.FixedFront)
    Button FixedFront;
    @BindView(R.id.Scale)
    Button Scale;
    @BindView(R.id.Translate)
    Button Translate;
    private RefreshLayout mRefreshLayout;
    private static boolean isFirstEnter = true;
    private ClassicsHeader mClassicsHeader;
    private ClassicsFooter mClassicsFooter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mRefreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
      /*  mClassicsHeader = (ClassicsHeader) mRefreshLayout.getRefreshHeader();
        mClassicsFooter = (ClassicsFooter) mRefreshLayout.getRefreshFooter();*/
        if (isFirstEnter) {
            isFirstEnter = false;
            mRefreshLayout.autoRefresh();//第一次进入触发自动刷新，演示效果
        }
      //  mRefreshLayout.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));

        //设置 Footer 为 球脉冲
       // mRefreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.FixedFront));
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);
            }
        });
    }

    private void setThemeColor(int colorPrimary, int colorPrimaryDark) {
        mRefreshLayout.setPrimaryColorsId(colorPrimary, android.R.color.white);
    }

    private void setThemefooter(SpinnerStyle spinnerStyle) {
        mClassicsFooter.setSpinnerStyle(spinnerStyle);
    }

    private void setThemeheader(SpinnerStyle spinnerStyle) {
        mClassicsHeader.setSpinnerStyle(spinnerStyle);
    }

    @OnClick({R.id.FixedBehind, R.id.FixedFront, R.id.Scale, R.id.Translate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.FixedBehind:
                setThemeheader(SpinnerStyle.FixedBehind);
                setThemefooter(SpinnerStyle.FixedBehind);
                break;
            case R.id.FixedFront:
                setThemeheader(SpinnerStyle.FixedFront);
                setThemefooter(SpinnerStyle.FixedFront);
                break;
            case R.id.Scale:
                setThemeheader(SpinnerStyle.Scale);
                setThemefooter(SpinnerStyle.Scale);
                break;
            case R.id.Translate:
                setThemeheader(SpinnerStyle.Translate);
                setThemefooter(SpinnerStyle.Translate);
                break;
        }
    }
}

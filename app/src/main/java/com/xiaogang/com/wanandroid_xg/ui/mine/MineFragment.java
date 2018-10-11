package com.xiaogang.com.wanandroid_xg.ui.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseFragment;
import com.xiaogang.com.wanandroid_xg.net.CookiesManager;
import com.xiaogang.com.wanandroid_xg.ui.login.LoginFragment;
import com.xiaogang.com.wanandroid_xg.ui.main.MainFragment;
import com.xiaogang.com.wanandroid_xg.ui.mycollect.MyCollectFragment;
import com.xiaogang.com.wanandroid_xg.ui.search.SearchFragment;
import com.xiaogang.com.wanandroid_xg.utils.Constant;

import java.util.List;

import butterknife.BindView;
import okhttp3.Cookie;

/**
 * author: fangxiaogang
 * date: 2018/9/19
 */

public class MineFragment extends BaseFragment {

    @BindView(R.id.login)
    LinearLayout loginlin;

    @BindView(R.id.out)
    LinearLayout outlin;

    @BindView(R.id.aboutlin)
    LinearLayout maboutlin;

    @BindView(R.id.homesearch)
    ImageView mhomesearch;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView(View view) {

        mhomesearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainFragment) getParentFragment()).startBrotherFragment(SearchFragment.newInstance());
            }
        });

        loginlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainFragment) getParentFragment()).startBrotherFragment(LoginFragment.newInstance());
            }
        });
        outlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SPUtils.getInstance(Constant.SPname).getBoolean(Constant.LOGIN)){
                    ((MainFragment) getParentFragment()).startBrotherFragment(MyCollectFragment.newInstance());
                }else {
                    ((MainFragment) getParentFragment()).startBrotherFragment(LoginFragment.newInstance());
                }
            }
        });


        maboutlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainFragment) getParentFragment()).startBrotherFragment(AboutFragment.newInstance());
            }
        });
    }

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;
    /**
     * 处理回退事件
     *
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "再按一次退出", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}

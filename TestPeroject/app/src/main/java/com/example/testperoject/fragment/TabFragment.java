package com.example.testperoject.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.testperoject.R;
import com.example.testperoject.adapter.MyVPAdapter;
import com.example.testperoject.event.AddPlanEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.util.ErrorDialogManager;

/**
 * 时间： 2019/7/8 10:54
 * 作者： Lee
 * 描述：
 */
public class TabFragment extends Fragment implements View.OnClickListener {

    private LinearLayout mLlShowInfo;
    private LinearLayout mLlFullInfo;
    private boolean isFull = true;
    private String mContent;
    private Button mBtnAddPlan;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();

    }

    private void initView(View view) {
        mLlShowInfo = view.findViewById(R.id.ll_show_info); // 展示布局
        mLlFullInfo = view.findViewById(R.id.ll_full_info); // 填写布局
        view.findViewById(R.id.btn_submit).setOnClickListener(this);
        mBtnAddPlan = view.findViewById(R.id.btn_add_plan);
        mBtnAddPlan.setOnClickListener(this);
    }

    private void initData() {
        // 收到view1 传过来的值
        if (isFull) {
            mLlFullInfo.setVisibility(View.VISIBLE);
            mLlShowInfo.setVisibility(View.GONE);
        } else {
            mLlShowInfo.setVisibility(View.VISIBLE);
            mLlFullInfo.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFull) {
            mLlFullInfo.setVisibility(View.VISIBLE);
            mLlShowInfo.setVisibility(View.GONE);
        } else {
            mLlShowInfo.setVisibility(View.VISIBLE);
            mLlFullInfo.setVisibility(View.GONE);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (isFull) {
                mLlFullInfo.setVisibility(View.VISIBLE);
                mLlShowInfo.setVisibility(View.GONE);
            } else {
                mLlShowInfo.setVisibility(View.VISIBLE);
                mLlFullInfo.setVisibility(View.GONE);
            }
        }
    }

    public void setContent(String tab1Content) {
        this.mContent = tab1Content;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit: // 提交资料
                mLlFullInfo.setVisibility(View.GONE);
                mLlShowInfo.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_add_plan: // 增加方案
                EventBus.getDefault().post(new AddPlanEvent());
                isFull = !isFull;
                mBtnAddPlan.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    public void setType(boolean isFull) {
        this.isFull = isFull;
    }
}

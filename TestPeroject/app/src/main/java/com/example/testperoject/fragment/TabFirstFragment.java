package com.example.testperoject.fragment;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.testperoject.R;
import com.example.testperoject.adapter.MyVPAdapter;
import com.example.testperoject.event.AddPlanEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间： 2019/7/8 10:54
 * 作者： Lee
 * 描述：
 */
public class TabFirstFragment extends Fragment {

    private ViewPager mVpSecond;
    private MyVPAdapter mAdapter;
    private TabLayout mTabSecond;

    private List<String> titles = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

    private int index = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_tab_02, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initData() {

        mAdapter = new MyVPAdapter(getChildFragmentManager());
        TabFragment tabFragment = new TabFragment();
        tabFragment.setType(true);
        titles.add("方案0");
        fragments.add(tabFragment);
        //mAdapter.setTitles(0, "方案0", tabFragment);
        mAdapter.setTitles(titles, fragments);
        mVpSecond.setAdapter(mAdapter);
        mTabSecond.setupWithViewPager(mVpSecond);
        mTabSecond.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void initView(View view) {
        mVpSecond = view.findViewById(R.id.vp_second);
        mTabSecond = view.findViewById(R.id.tab_second);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addPlan(AddPlanEvent event) {
        if (event != null) {
            index++;
            TabFragment tabFragment = new TabFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("index", index);
            tabFragment.setArguments(bundle);
            tabFragment.setType(true);
            titles.add("方案" + index);
            fragments.add(tabFragment);
            mAdapter.setTitles(titles, fragments);
            //mAdapter.setTitles(index, "方案" + index, tabFragment);
            mVpSecond.setCurrentItem(index);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

package com.example.testperoject.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.support.v4.app.Fragment;

import com.example.testperoject.R;
import com.example.testperoject.adapter.MyFirstVPAdapter;
import com.example.testperoject.adapter.MyVPAdapter;
import com.example.testperoject.event.AddPlanEvent;
import com.example.testperoject.fragment.TabFirstFragment;
import com.example.testperoject.fragment.TabFragment;
import com.example.testperoject.fragment.TabSecondFragment;
import com.example.testperoject.fragment.TabThirdFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间： 2019/7/8 9:57
 * 作者： Lee
 * 描述：
 */
public class tabActivity extends AppCompatActivity {

    private TabLayout mTab;
    private ViewPager mVp;
    private MyFirstVPAdapter mFirstAdapter;

    private List<String> titles = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        initView();
        initData();
    }


    private void initView() {
        mTab = findViewById(R.id.serving_tablayout);
        mVp = findViewById(R.id.vp);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initData() {

        fragments.add(new TabFirstFragment());
        fragments.add(new TabSecondFragment());
        fragments.add(new TabThirdFragment());
        for (int i = 0; i < fragments.size(); i++) {
             titles.add("标签" + i);
        }

        mFirstAdapter = new MyFirstVPAdapter(getSupportFragmentManager(), fragments, titles);
        mVp.setAdapter(mFirstAdapter);
        mTab.setupWithViewPager(mVp);
        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);

    }


}

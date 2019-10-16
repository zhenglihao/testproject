package com.example.testperoject.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;

import com.example.testperoject.R;


/**
 * 时间： 2019/10/15 14:48
 * 作者： Lee
 * 描述： ScrollView与嵌套EditText的滑动冲突解决方案
 */
public class NestActicity extends AppCompatActivity {

    ScrollView mScrollView;
    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest);

        mScrollView = findViewById(R.id.scrollView);
        mEditText = findViewById(R.id.mEditText);

        // 复写EditText的onTouch方法
        mEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 当触摸的是EditText & 当前EditText可滚动时，则将事件交给EditText处理；
                if ((v.getId() == R.id.mEditText && canVerticalScroll(mEditText))) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);  // 禁用父类的拦截事件
                    // 否则将事件交由其父类处理
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });

    }


    // 判断当前EditText是否可滚动
    private boolean canVerticalScroll(EditText editText) {

        if (editText.getLineCount() > editText.getMaxLines()) {
            return true;
        }
        return false;
    }

}

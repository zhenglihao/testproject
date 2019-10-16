package com.example.testperoject.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.testperoject.R;
import com.example.testperoject.fragment.dialog.MyDialogFragment;
import com.example.testperoject.fragment.dialog.MyDialogFragment2;

/**
 * 时间： 2019/10/12 16:48
 * 作者： Lee
 * 描述：
 */
public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        initView(savedInstanceState);
    }

    private void initView(final Bundle savedInstanceState) {
        findViewById(R.id.btn_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // final MyDialogFragment myDialogFragment = new MyDialogFragment();
                // 使用方法： 第二参数： 别名
                final MyDialogFragment myDialogFragment = MyDialogFragment.newInstance("哈哈", "嗷嗷");
                myDialogFragment.show(getSupportFragmentManager(), "lee");
                myDialogFragment.setCallbackListener("lee", "鉴定完毕，是帅哥！", new MyDialogFragment.CallbackListener() {
                    @Override
                    public void onSure() {
                        // myDialogFragment.getDialog().dismiss();
                        Toast.makeText(DialogActivity.this, "确定", Toast.LENGTH_SHORT).show();
                         MyDialogFragment2.newInstance().show(getSupportFragmentManager(), "lee2");
                    }

                    @Override
                    public void onCancel() {
                        myDialogFragment.getDialog().dismiss();
                        Toast.makeText(DialogActivity.this, "取消", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }
}

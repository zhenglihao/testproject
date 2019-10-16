package com.example.testperoject.act;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.testperoject.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_open_wechat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openWechat();
            }
        });

    }


    /**
     * 打开微信
     */
    public void openWechat(){

        Intent lan = getPackageManager().getLaunchIntentForPackage("com.tencent.mm");
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(lan.getComponent());
        startActivity(intent);

    }


    /**
     * 打开并且跳转到对应好友聊天界面
     */
    public void openAndjumpQQFriends (){

        String url="mqqwpa://im/chat?chat_type=wpa&uin=501863587";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

}

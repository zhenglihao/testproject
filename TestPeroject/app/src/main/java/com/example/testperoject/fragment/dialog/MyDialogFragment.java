package com.example.testperoject.fragment.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.testperoject.R;

/**
 * 时间： 2019/10/12 16:54
 * 作者： Lee
 * 描述：  对话框
 */
public class MyDialogFragment extends DialogFragment {

    private CallbackListener mCallbackListener;
    private String mTitle;
    private String mContent;
    private String param1;
    private String param2;

    // 调用并传递参数
    public static MyDialogFragment newInstance(String param1, String param2) {
        MyDialogFragment fragment = new MyDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("param1", param1);
        bundle.putString("param2", param2);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        param1 = bundle.getString("param1");
        param2 = bundle.getString("param2");

        // 修改diglog外观样式：
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Mydialog);

        // 在onCreate或者onCreateView都可以
        /*getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.RED));
        getDialog().getWindow().setDimAmount(0.5f);//背景黑暗度
        //点击window外的区域 是否消失
        getDialog().setCanceledOnTouchOutside(canCanceledOnOutside());
        */
    }


    @Override
    public void onStart() {
        super.onStart();
        /*Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                int width = ViewGroup.LayoutParams.MATCH_PARENT;
                int height = ViewGroup.LayoutParams.MATCH_PARENT;
                // 1 设置全屏
                // window.setLayout(width, height);
                // 2. 设置指定大小
                // window.setLayout(750, 600);
                // 3. 设置占用屏幕宽度一定比例
                DisplayMetrics dm = new DisplayMetrics();
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
                dialog.getWindow().setLayout((int) (dm.widthPixels * 0.75), ViewGroup.LayoutParams.WRAP_CONTENT);

            }
        }*/

        // 设置dialog大小和位置
        Window win = getDialog().getWindow();
        WindowManager.LayoutParams params = win.getAttributes();
        params.gravity = Gravity.BOTTOM; // 位置
        //  params.y = DisplayUtils.dip2px(getContext(), 100);// 具体头部距离
        params.width = (ViewGroup.LayoutParams.MATCH_PARENT); // 大小
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        win.setAttributes(params);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
       /* AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle("神灯")
                .setMessage("来选择你要实现的一个愿望把")
                .setPositiveButton("车子", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "车子", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("房子", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "房子", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).create();
        return dialog;*/
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        // 自定义布局：
        //1.必须设置dialog的window背景为透明颜色，不然圆角无效或者是系统默认的颜色
        // getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.from(getActivity()).inflate(R.layout.fragment_dialog, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView mTvTitle = view.findViewById(R.id.tv_title);
        TextView mTvContent = view.findViewById(R.id.tv_content);

        mTvTitle.setText(mTitle);
        mTvContent.setText(mContent + "\n" + "参数1: " + param1 + "  参数2：" + param2);

        view.findViewById(R.id.btn_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallbackListener != null) {
                    mCallbackListener.onSure();
                }
            }
        });
        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallbackListener != null) {
                    mCallbackListener.onCancel();
                }
            }
        });


    }

    public void setCallbackListener(String title, String content, CallbackListener callbackListener) {
        this.mTitle = title;
        this.mContent = content;
        this.mCallbackListener = callbackListener;
    }

    public interface CallbackListener {
        void onSure();

        void onCancel();
    }
}

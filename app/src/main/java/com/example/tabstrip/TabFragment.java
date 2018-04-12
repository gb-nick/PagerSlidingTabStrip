package com.example.tabstrip;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * author: Admin
 * time  : 4/8 0008
 * desc  :
 */

public class TabFragment extends Fragment {
    private TextView tv_content;
    private String content;
    private int icon;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.frag_tab, container, false);
        tv_content= mView.findViewById(R.id.tv_content);
        initData();

        return mView;
    }

    private void initData() {
        content = getArguments().getString("content");
        icon = getArguments().getInt("icon");
        if (icon>0 && !TextUtils.isEmpty(content)){
            tv_content.setCompoundDrawablesWithIntrinsicBounds(0,icon,0,0);
            tv_content.setText(content);
        }else{
            if (!TextUtils.isEmpty(content)){
                tv_content.setText(content);
            }
            if (icon>0){
                tv_content.setCompoundDrawablesWithIntrinsicBounds(0,icon,0,0);
            }
        }
    }
}

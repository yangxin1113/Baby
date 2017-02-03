package com.zyx.baby.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.zyx.baby.R;


/**
 * Created by Administrator on 2016/12/22.
 */

public class WarningFragment extends DialogFragment {

    @BindView(R.id.tv_warning)
    TextView tvWarning;

    private static WarningFragment warningFragment;
    private String warn;

    public static WarningFragment newInstance(String warning) {
        if(warningFragment==null) {
            warningFragment = new WarningFragment();
        }
        Bundle args = new Bundle();
        args.putString("warn", warning);
        warningFragment.setArguments(args);
        return warningFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_warinng, null);
        ButterKnife.bind(this, rootView);
        initData();
        return rootView;
    }

    private void initData() {
        warn = getArguments().getString("warn");
        tvWarning.setText(warn);
    }

    @OnClick(R.id.bt_sure)
    void back() {
        dismiss();
    }

}

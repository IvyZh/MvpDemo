package com.pandaq.mvpdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pandaq.mvpdemo.R;
import com.pandaq.mvpdemo.customview.MenuItem;
import com.pandaq.mvpdemo.enums.ClientType;
import com.pandaq.mvpdemo.presenter.MainActivityPresenter;
import com.pandaq.mvpdemo.view.IViewBind.IMainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by PandaQ on 2016/11/20.
 * email : 767807368@qq.com
 */

public class MainActivity extends AppCompatActivity implements IMainActivity {

    @BindView(R.id.tonews)
    MenuItem mTonews;
    @BindView(R.id.https_friendly)
    MenuItem mHttpsFriendly;
    @BindView(R.id.https_unfriendly)
    MenuItem mHttpsUnfriendly;
    @BindView(R.id.https_result)
    TextView mHttpsResult;
    private MainActivityPresenter mPresenter = new MainActivityPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tonews, R.id.https_friendly, R.id.https_unfriendly})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tonews:
                Intent intent = new Intent(this, NewsListActivity.class);
                startActivity(intent);
                break;
            case R.id.https_friendly:
                get12306Test(ClientType.TYPE_HTTPSUTILS);
                break;
            case R.id.https_unfriendly:
                get12306Test(ClientType.TYPE_OKHTTPCLIENT);
                break;
        }
    }

    @Override
    public void get12306Test(ClientType type) {
        mPresenter.get12306Test(this, type);
    }

    @Override
    public void showResult(String result) {
        mHttpsResult.setText(result);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unsubcription();
    }
}

package com.patelheggere.rajeevconstituency.view.splash;

import android.content.Intent;
import android.os.Handler;

import com.patelheggere.rajeevconstituency.R;
import com.patelheggere.rajeevconstituency.base.BaseActivity;
import com.patelheggere.rajeevconstituency.utils.SharedPrefsHelper;
import com.patelheggere.rajeevconstituency.view.main.MainActivity;

import static com.patelheggere.rajeevconstituency.utils.AppUtils.Constants.THREE_SECOND;

public class SplashActivity extends BaseActivity {

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if(SharedPrefsHelper.getInstance().get("FIRST_TIME", true)) {
                   // Intent i = new Intent(SplashActivity.this, com.patelheggere.rajeevconstituency.view.welcome.WelcomeActivity.class);
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                }

                // close this activity
                finish();

            }


        }, THREE_SECOND);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}

package com.patelheggere.rajeevconstituency.view.main;

import android.net.Uri;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.patelheggere.rajeevconstituency.R;
import com.patelheggere.rajeevconstituency.base.BaseActivity;
import com.patelheggere.rajeevconstituency.view.main.fragments.ContactsFragment;
import com.patelheggere.rajeevconstituency.view.main.fragments.NewsFragment;
import com.patelheggere.rajeevconstituency.view.main.fragments.NotificationFragment;
import com.patelheggere.rajeevconstituency.view.main.fragments.WorkStatusFragment;

public class MainActivity extends BaseActivity implements WorkStatusFragment.OnFragmentInteractionListener,
        NotificationFragment.OnFragmentInteractionListener,
        NewsFragment.OnFragmentInteractionListener, ContactsFragment.OnFragmentInteractionListener{

    private static final String TAG = "MainActivity";
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right);
            Fragment curFrag = fragmentManager.getPrimaryNavigationFragment();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Log.d(TAG, "onNavigationItemSelected: ");
                    if (curFrag != null) {
                        fragmentTransaction.detach(curFrag);
                    }
                    Fragment fragment = fragmentManager.findFragmentByTag("HOME");
                    if (fragment == null) {
                        fragment = new NewsFragment();
                        fragmentTransaction.add(R.id.contentFrame, fragment, "HOME");
                    } else {
                        fragmentTransaction.attach(fragment);
                    }
                    fragmentTransaction.setPrimaryNavigationFragment(fragment);
                    fragmentTransaction.setReorderingAllowed(true);
                    fragmentTransaction.commitNowAllowingStateLoss();

                    return true;

                    case R.id.navigation_work:
                        if (curFrag != null) {
                            fragmentTransaction.detach(curFrag);
                        }
                        fragment = fragmentManager.findFragmentByTag("WORK");
                        if (fragment == null) {
                            fragment = new WorkStatusFragment();
                            fragmentTransaction.add(R.id.contentFrame, fragment, "WORK");
                        } else {
                            fragmentTransaction.attach(fragment);
                        }
                        fragmentTransaction.setPrimaryNavigationFragment(fragment);
                        fragmentTransaction.setReorderingAllowed(true);
                        fragmentTransaction.commitNowAllowingStateLoss();
                        // mTextMessage.setText(R.string.title_dashboard);
                        return true;
                    case R.id.navigation_notifications:
                        if (curFrag != null) {
                            fragmentTransaction.detach(curFrag);
                        }
                        fragment = fragmentManager.findFragmentByTag("NOTI");
                        if (fragment == null) {
                            fragment = new NotificationFragment();
                            fragmentTransaction.add(R.id.contentFrame, fragment, "NOTI");
                        } else {
                            fragmentTransaction.attach(fragment);
                        }
                        fragmentTransaction.setPrimaryNavigationFragment(fragment);
                        fragmentTransaction.setReorderingAllowed(true);
                        fragmentTransaction.commitNowAllowingStateLoss();
                        //mTextMessage.setText(R.string.title_notifications);
                        return true;

                case R.id.navigation_contacts:
                    if (curFrag != null) {
                        fragmentTransaction.detach(curFrag);
                    }
                    fragment = fragmentManager.findFragmentByTag("NOTI");
                    if (fragment == null) {
                        fragment = new ContactsFragment();
                        fragmentTransaction.add(R.id.contentFrame, fragment, "NOTI");
                    } else {
                        fragmentTransaction.attach(fragment);
                    }
                    fragmentTransaction.setPrimaryNavigationFragment(fragment);
                    fragmentTransaction.setReorderingAllowed(true);
                    fragmentTransaction.commitNowAllowingStateLoss();
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;

            }
            return false;
        }
    };


    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

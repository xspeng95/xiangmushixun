package com.example.legendpeng.chatdemo.ui.activity;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.legendpeng.chatdemo.R;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.legendpeng.chatdemo.ui.fragment.AskFragment;
import com.example.legendpeng.chatdemo.ui.fragment.ListenFragment;
import com.example.legendpeng.chatdemo.ui.fragment.MineFragment;
import com.example.legendpeng.chatdemo.ui.fragment.ShopFragment;

public class AskActivity extends AppCompatActivity
{
    private BottomNavigationBar mBottomNavigationBar;
    //private Toolbar mToorBar;
    //private TextView mTextView;
   // private boolean mDrawerLayoutState=false;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);
        findViews();
        BottomNavigationBarInit();
        ToolBarInit();

        replaceFragment(0);
    }
    private void findViews(){
        mBottomNavigationBar=(BottomNavigationBar)findViewById(R.id.bottomnavigationbar);
       // mToorBar=(Toolbar)findViewById(R.id.drawerlayout);
       // mTextView=(TextView)findViewById(R.id.title);
    }
    private void ToolBarInit(){
        //setSupportActionBar(mToorBar);
        //getSupportActionBar().setHomeButtonEnabled( true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("");
       // mTextView.setText("提问");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;

    }
    private void replaceFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new AskFragment();
                break;
            case 1:
                fragment = new ListenFragment();
                break;
            case 2:
                fragment = new ShopFragment();
                break;
            case 3:
                fragment = new MineFragment();
                break;
            default:
        }
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.mainFrameLayout,fragment);
        transaction.commit();
    }
    private void BottomNavigationBarInit(){
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ask_question_w,"提问"))
                .addItem(new BottomNavigationItem(R.drawable.listening_w,"偷听"))
                .addItem(new BottomNavigationItem(R.drawable.bulleted_list_w,"收购"))
                .addItem(new BottomNavigationItem(R.drawable.me_w,"我的"))
                .initialise();




        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
           public void onTabSelected(int position) {
                //setSupportActionBar(mToorBar);
                //               if(position==0||position==1){
//                   mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
//                    getSupportActionBar().setHomeButtonEnabled(true);
//                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//                }
//                if(position==2||position==3){
//                    mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
//                    getSupportActionBar().setHomeButtonEnabled(false);
//                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//                }

                replaceFragment(position);
            }
            @Override
            public void onTabUnselected(int position) {}
            @Override
            public void onTabReselected(int position) {}
        });
    }
}

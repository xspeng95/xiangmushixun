package com.example.legendpeng.chatdemo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.legendpeng.chatdemo.R;


import com.example.legendpeng.chatdemo.ui.fragment.*;
import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.Fragment;
import android.support.annotation.IdRes;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;

import android.widget.RadioButton;

public class AskActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    private RadioGroup mRadioGroup;
    private List<Fragment> fragments = new ArrayList<>();
    private Fragment fragment;
    private FragmentManager fm;
    private FragmentTransaction transaction;
    public RadioButton rb_Ask,rb_Listen,rb_Shop,rb_My;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);
        initView(); //初始化组件
        mRadioGroup.setOnCheckedChangeListener(this); //点击事件
        fragments = getFragments(); //添加布局
        //添加默认布局
        normalFragment();
    }

    //默认布局
    private void normalFragment() {
        fm=getSupportFragmentManager();
        transaction=fm.beginTransaction();
        fragment=fragments.get(0);
        transaction.replace(R.id.mFragment,fragment);
        transaction.commit();
    }
    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.home_tab);
        rb_Ask= (RadioButton) findViewById(R.id.tab_ask);
        rb_Listen= (RadioButton) findViewById(R.id.tab_liston);
        rb_Shop= (RadioButton) findViewById(R.id.tab_bulleted);
        rb_My= (RadioButton) findViewById(R.id.tab_mine);
    }
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
        fm=getSupportFragmentManager();
        transaction=fm.beginTransaction();
        switch (checkedId){
            case R.id.tab_ask:
                fragment=fragments.get(0);
                transaction.replace(R.id.mFragment,fragment);
                break;
            case R.id.tab_liston:
                fragment=fragments.get(1);
                transaction.replace(R.id.mFragment,fragment);

                break;
            case R.id.tab_bulleted:
                fragment=fragments.get(2);
                transaction.replace(R.id.mFragment,fragment);

                break;
            case R.id.tab_mine:
                fragment=fragments.get(3);
                transaction.replace(R.id.mFragment,fragment);

                break;
        }
        setTabState();
        transaction.commit();
    }


    //设置选中和未选择的状态
    private void setTabState() {
        setHomeState();
        setMessageState();
        setFindState();
        setMyState();
    }
    private void setHomeState() {
        if (rb_Ask.isChecked()){
            rb_Ask.setTextColor(ContextCompat.getColor(this,R.color.colorAccent));
        }else{
            rb_Ask.setTextColor(ContextCompat.getColor(this,R.color.colorPrimary));
        }
    }
    private void setMessageState() {
        if (rb_Listen.isChecked()){
            rb_Listen.setTextColor(ContextCompat.getColor(this,R.color.colorAccent));
        }else{
            rb_Listen.setTextColor(ContextCompat.getColor(this,R.color.colorPrimary));
        }
    }
    private void setFindState() {
        if (rb_Shop.isChecked()){
            rb_Shop.setTextColor(ContextCompat.getColor(this,R.color.colorAccent));
        }else{
            rb_Shop.setTextColor(ContextCompat.getColor(this,R.color.colorPrimary));
        }
    }

    private void setMyState() {
        if (rb_My.isChecked()){
            rb_My.setTextColor(ContextCompat.getColor(this,R.color.colorAccent));
        }else{
            rb_My.setTextColor(ContextCompat.getColor(this,R.color.colorPrimary));
        }

    }
    public List<Fragment> getFragments(){
        fragments.add(new AskFragment());
        fragments.add(new ListenFragment());
        fragments.add(new ShopFragment());
        fragments.add(new MineFragment());
        return fragments;
    }
}

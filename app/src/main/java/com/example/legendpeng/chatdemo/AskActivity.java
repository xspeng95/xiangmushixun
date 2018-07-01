package com.example.legendpeng.chatdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AskActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        initView();

    }

    private void initView()
    {
        rg=(RadioGroup)findViewById(R.id.home_tab);//设置页面跳转
        rg.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        switch (rg.getCheckedRadioButtonId()){
            case R.id.tab_ask:
                setContentView(R.layout.activity_ask);
            case R.id.tab_liston:
                setContentView(R.layout.activity_find_pwd);}

    }
}

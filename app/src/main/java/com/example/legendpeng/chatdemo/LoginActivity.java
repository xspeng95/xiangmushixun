package com.example.legendpeng.chatdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private Button rigester_bu;
    private EditText count_text;
    private EditText mima_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rigester_bu=(Button)findViewById(R.id.rigister_b);
        count_text=(EditText)findViewById(R.id.count_tv);
        mima_text=(EditText)findViewById(R.id.mima_tv);
        count_text.setText("账号：");
        mima_text.setText("密码：");

        rigester_bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterView.class);
                startActivity(intent);
            }
        });
        count_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count_text.setText("");
            }
        });
        mima_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mima_text.setText("");
            }
        });
    }
}

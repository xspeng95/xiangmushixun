package com.example.legendpeng.chatdemo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import com.example.legendpeng.chatdemo.R;
import com.example.legendpeng.chatdemo.presenter.ILoginPresenter;
import com.example.legendpeng.chatdemo.presenter.LoginPresenterImpl;
import com.example.legendpeng.chatdemo.ui.iView.ILoginView;

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener{
    private Button rigester_bu;
    private Button signin_btn;
    private EditText count_text;
    private EditText mima_text;
    ILoginPresenter loginPresenter; // MVP模式

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenterImpl(this);  // 绑定presenter

        rigester_bu=(Button)findViewById(R.id.reg_btn);
        rigester_bu.setOnClickListener(this);

        signin_btn=findViewById(R.id.login_btn);
        signin_btn.setOnClickListener(this);

        count_text=(EditText)findViewById(R.id.count_tv);
        count_text.setOnClickListener(this);

        mima_text=(EditText)findViewById(R.id.mima_tv);
        mima_text.setOnClickListener(this);

        count_text.setText("请输入手机号码");
        mima_text.setText("请输入密码");

    }

    @Override
    public void onClick(View view) {
        Log.d("btn", "loginBtn click");
        switch (view.getId()){
            case R.id.login_btn:
                Log.d("btn", "loginBtn click");
                loginPresenter.login(count_text.getText().toString(), mima_text.getText().toString());
                break;
            case R.id.reg_btn:
                Log.d("btn", "regBtn click");
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,RegisterView.class);
                startActivity(intent);
                break;
            case R.id.count_tv:
                count_text.setText("");
                break;
            case R.id.mima_tv:
                mima_text.setText("");
                break;
        }

    }

    @Override
    public void loginSuccess() {
        Log.d("Login", "success"); // 待补充
    }


    @Override
    public void showMessage(String msg) {
        Log.d("Login", msg);    // 待补充
    }
}

package com.example.legendpeng.chatdemo;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by hbh on 2017/2/7.
 */

public class RegisterView extends AppCompatActivity implements View.OnClickListener{
    private EditText phoneNum;
    private EditText validateNum;
    private Button validate_btn;
    private Button register_btn;
    public EventHandler eh;
    private  TimeCount mTimeCount;

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_register_view);
        SMSSDK.initSDK(this, "269d5abf647fa", "f8cfecdcc56965d22f12c86e14b028f0");
        initEvent();
        init();
    }

    private void  initEvent(){
        phoneNum=(EditText)findViewById(R.id.phone_text);
        validateNum=(EditText)findViewById(R.id.conform_text);
        validate_btn=(Button)findViewById(R.id.validate_btn);
        register_btn=(Button)findViewById(R.id.rigister_btn);
        validate_btn.setOnClickListener(this);
        register_btn.setOnClickListener(this);
        mTimeCount = new TimeCount(60000, 1000);
    }
    private void init(){
        eh = new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) { //回调完成

                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) { //提交验证码成功

                        startActivity(new Intent(RegisterView.this, MainActivity.class)); //页面跳转

                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){ //获取验证码成功

                    }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){ //返回支持发送验证码的国家列表

                    }
                }else{
                    ((Throwable)data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }
    @Override
    public void onClick(View v) {
        Toast.makeText(RegisterView.this,"111",Toast.LENGTH_SHORT).show();
        switch (v.getId()){
            case R.id.validate_btn:

                if(!phoneNum.getText().toString().trim().equals("")){
                    if (checkTel(phoneNum.getText().toString().trim())){
                        SMSSDK.getVerificationCode("+86",phoneNum.getText().toString());
                        mTimeCount.start();
                    }else {
                        Toast.makeText(RegisterView.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(RegisterView.this,"请输入手机号",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.rigister_btn:
                if(!phoneNum.getText().toString().trim().equals("")){
                    if(checkTel(phoneNum.getText().toString().trim())){
                        if(!validateNum.getText().toString().trim().equals("")){
                            SMSSDK.submitVerificationCode("+86",phoneNum.getText().toString().trim(),validateNum.getText().toString().trim());

                        }else {
                            Toast.makeText(RegisterView.this,"请输入验证码",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(RegisterView.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegisterView.this,"请输入手机号",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
    /**
     * 正则匹配手机号码
     * @param tel
     * @return
     */
    public boolean checkTel(String tel){
        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
        Matcher matcher = p.matcher(tel);
        return matcher.matches();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }

    /**
     * 计时器
     */
    class TimeCount extends CountDownTimer{

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            validate_btn.setClickable(false);
            validate_btn.setText(l/1000 + "秒后重新获取");
        }

        @Override
        public void onFinish() {
            validate_btn.setClickable(true);
            validate_btn.setText("获取验证码");
        }
    }




}
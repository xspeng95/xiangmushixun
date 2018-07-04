package com.example.legendpeng.chatdemo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.legendpeng.chatdemo.R;
import com.example.legendpeng.chatdemo.presenter.IRegisterPresenter;
import com.example.legendpeng.chatdemo.presenter.RegisterPresenterImpl;
import com.example.legendpeng.chatdemo.ui.iView.IRegisterView;

import android.content.Intent;
import android.widget.Toast;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterView extends AppCompatActivity implements View.OnClickListener, IRegisterView{
    private EditText phoneNum;
    private EditText mimaText;
    private EditText nameText;
    private EditText mimaConfirmText;
    private EditText validateNum;
    private Button validate_btn;
    private Button register_btn;
    public EventHandler eh;
    private  TimeCount mTimeCount;
    IRegisterPresenter iRegisterPresenter;

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_register_view);
        SMSSDK.initSDK(this, "269d5abf647fa", "f8cfecdcc56965d22f12c86e14b028f0");
        initEvent();
        init();
        iRegisterPresenter = new RegisterPresenterImpl(this);
    }

    private void  initEvent(){
        phoneNum=(EditText)findViewById(R.id.phone_text);
        mimaText=findViewById(R.id.mima_text);
        nameText=findViewById(R.id.name_text);
        mimaConfirmText=findViewById(R.id.mimaconform_text);
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

                        //startActivity(new Intent(RegisterView.this, MainActivity.class)); //页面跳转
                        iRegisterPresenter.register(phoneNum.getText().toString(), mimaText.getText().toString(), nameText.getText().toString());

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
                if(phoneNum.getText().toString().trim().equals("")) {
                    Toast.makeText(RegisterView.this,"请输入手机号",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!checkTel(phoneNum.getText().toString().trim())){
                    Toast.makeText(RegisterView.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(validateNum.getText().toString().trim().equals("")) {
                    Toast.makeText(RegisterView.this,"请输入验证码",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mimaText.getText().toString().trim().equals("") || mimaConfirmText.getText().toString().trim().equals("")){
                    Toast.makeText(RegisterView.this,"密码和确认密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!mimaText.getText().toString().equals(mimaConfirmText.getText().toString())){
                    Toast.makeText(RegisterView.this,"密码和确认密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }

                // 测试阶段不做短信验证
//                SMSSDK.submitVerificationCode("+86",phoneNum.getText().toString().trim(),validateNum.getText().toString().trim());

                iRegisterPresenter.register(phoneNum.getText().toString(), mimaText.getText().toString(), nameText.getText().toString());

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

    @Override
    public void registerSuccess() {
        startActivity(new Intent(RegisterView.this, LoginActivity.class)); //页面跳转
    }

    @Override
    public void showMessage(String msg) {
        Log.d("Register", msg);
    }
}
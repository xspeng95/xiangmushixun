package com.example.legendpeng.chatdemo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.legendpeng.chatdemo.R;
public class RegisterView extends AppCompatActivity {

    private EditText username_text;
    private EditText phone_text;
    private EditText conform_text;
    private EditText mima_text;
    private EditText mimaconform_text;
    private Button getinf_bu;
    private  Button rigister_bu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);

        username_text=(EditText)findViewById(R.id.name_text);
        phone_text=(EditText)findViewById(R.id.phone_text);
        conform_text=(EditText)findViewById(R.id.conform_text);
        mima_text=(EditText)findViewById(R.id.mima_text);
        mimaconform_text=(EditText)findViewById(R.id.mimaconform_text);
        getinf_bu=(Button)findViewById(R.id.getinf_bu);
        rigister_bu=(Button)findViewById(R.id.rigister_bu);

    }
}

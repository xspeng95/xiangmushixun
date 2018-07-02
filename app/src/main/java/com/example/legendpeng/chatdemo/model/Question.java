package com.example.legendpeng.chatdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {

    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>(){
        @Override
        public Question createFromParcel(Parcel parcel) {
            return null;
        }

        @Override
        public Question[] newArray(int i) {
            return new Question[0];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

    String questionID; // 问题标识

    String questionContent; // 问题内容

    String questionAnswer; // 回答内容 储存路径

    //String questionerID; // 提问者ID
    User questioner; // 提问者
    User responder; // 回答者




}

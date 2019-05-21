package com.codefair.lawfeedback.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.codefair.lawfeedback.R;

public class ReplyViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_view);

        //TODO
        if (getIntent().getBooleanExtra("isRelatedView", false)) {
            Log.i("ReplyViewActivity", "Related Users Reply List");
        } else {
            Log.i("ReplyViewActivity", "ALl User Reply List");
        }
        Log.i("ReplyViewActivity", String.valueOf(getIntent().getLongExtra("jobId", 0L)) + " " + String.valueOf(getIntent().getBooleanExtra("isRelatedUser", false)));
    }
}

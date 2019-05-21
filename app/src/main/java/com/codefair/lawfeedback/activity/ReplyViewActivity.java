package com.codefair.lawfeedback.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.codefair.lawfeedback.R;
import com.codefair.lawfeedback.listener.SuccessWriteReplyListener;
import com.codefair.lawfeedback.model.WrtieReplyTO;
import com.codefair.lawfeedback.network.RetrofitManager;

public class ReplyViewActivity extends AppCompatActivity implements SuccessWriteReplyListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_view);

        //TODO
        boolean isRelatedUser = getIntent().getBooleanExtra("isRelatedUser", false);
        if (getIntent().getBooleanExtra("isRelatedView", false)) {
            if (!isRelatedUser) {
                findViewById(R.id.writeReplyContent).setVisibility(View.GONE);
            }
        } else {
            if (isRelatedUser) {
                findViewById(R.id.writeReplyContent).setVisibility(View.GONE);
            }
        }

        findViewById(R.id.writeReplyBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long articleId = getIntent().getLongExtra("articleId", 0L);
                long userId = getIntent().getLongExtra("userId", 0L);
                EditText replyContent = findViewById(R.id.writeReplyContent);
                RetrofitManager.getInstance().writeReply(new WrtieReplyTO(articleId, userId, replyContent.getText().toString()));
            }
        });
    }

    @Override
    public void onSuccessWriteReply() {
        //TODO
    }
}

package com.codefair.lawfeedback.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.codefair.lawfeedback.R;
import com.codefair.lawfeedback.listener.SuccessGettingJobListListener;
import com.codefair.lawfeedback.listener.SuccessWriteArticleListener;
import com.codefair.lawfeedback.model.Job;
import com.codefair.lawfeedback.model.WriteArticleTO;
import com.codefair.lawfeedback.network.RetrofitManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import retrofit2.Response;

public class WriteArticleActivity extends AppCompatActivity implements SuccessGettingJobListListener, SuccessWriteArticleListener {

    private ArrayAdapter adapter;
    private Spinner spinner1;
    private Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_article);

        Log.i("WriteArticleActivity", "" + getIntent().getLongExtra("userId", 0L));

        spinner1 = findViewById(R.id.writeArticleJobSpinner1);
        spinner2 = findViewById(R.id.writeArticleJobSpinner2);
        RetrofitManager.getInstance().setOnSuccessGettingJobListListener(this);
        RetrofitManager.getInstance().getJobList();

        RetrofitManager.getInstance().setOnSuccessWriteArticleListener(this);
        Button writeArticleBtn = findViewById(R.id.writeArticleBtn);
        writeArticleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText title = findViewById(R.id.writeArticleTitle);
                EditText content = findViewById(R.id.writeArticleContent);

                List<Long> jobIdList = new ArrayList<>();
                jobIdList.add(((Job) spinner1.getSelectedItem()).getId());
                jobIdList.add(((Job) spinner2.getSelectedItem()).getId());

                //TODO erase
                for (Long jobId : jobIdList) {
                    Log.i("WriteArticleActivity", "Job Id: " + jobId);
                }

                WriteArticleTO writeArticleTO = new WriteArticleTO(getIntent().getLongExtra("userId", 0L), title.getText().toString(), content.getText().toString(), jobIdList);
                RetrofitManager.getInstance().writeArticle(writeArticleTO);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressWarnings("unchecked")
    @Override
    public void onOKResponse(Response<List<Job>> response) {
        List<Job> jobList = response.body();
        if (jobList != null) {
            try {
                for (Job job : jobList) {
                    if (job.getId() == 1L) {
                        jobList.remove(job);
                    }
                }
            } catch (Exception e) {
            }
            if (jobList.size() > 0) {
                jobList.sort(new Comparator<Job>() {
                    @Override
                    public int compare(Job o1, Job o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
            }
            adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, jobList);
            spinner1.setAdapter(adapter);
            spinner2.setAdapter(adapter);
        }
    }

    @Override
    protected void onDestroy() {
        RetrofitManager.getInstance().removeSuccessGettingJobListListener();
        RetrofitManager.getInstance().removeSuccessWriteArticleListener();
        super.onDestroy();
    }

    @Override
    public void onSuccessWriteArticle() {
        //TODO
        Log.i("WriteArticleActivity", "Success Write Article");
    }
}

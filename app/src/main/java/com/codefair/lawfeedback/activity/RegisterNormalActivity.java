package com.codefair.lawfeedback.activity;

import android.content.Intent;
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
import com.codefair.lawfeedback.listener.SuccessRegisterationNormalListener;
import com.codefair.lawfeedback.model.Job;
import com.codefair.lawfeedback.model.User;
import com.codefair.lawfeedback.network.RetrofitManager;

import java.util.Comparator;
import java.util.List;

import retrofit2.Response;

public class RegisterNormalActivity extends AppCompatActivity implements SuccessGettingJobListListener, SuccessRegisterationNormalListener {

    private static String TAG = "RegisterNormalActivity";
    private ArrayAdapter adapter;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_normal);

        spinner = findViewById(R.id.jobSpinner);
        RetrofitManager.getInstance().setOnSuccessGettingJobListListener(this);
        RetrofitManager.getInstance().getJobList();

        RetrofitManager.getInstance().setOnSuccessRegisterationNormalListener(this);

        Button registerNormalButton = findViewById(R.id.registerNormalButton);
        registerNormalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = findViewById(R.id.idText);
                EditText password = findViewById(R.id.passwordText);
                EditText nickname = findViewById(R.id.nicknameText);
                User user = User.createNormalUserModel(email.getText().toString(), password.getText().toString(), nickname.getText().toString(), ((Job) spinner.getSelectedItem()).getId());
                RetrofitManager.getInstance().registerNormal(user);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressWarnings("unchecked")
    @Override
    public void onOKResponse(Response<List<Job>> response) {
        Log.i(TAG, "okResponse");
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
            spinner.setAdapter(adapter);
        }
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");
        RetrofitManager.getInstance().removeSuccessGettingJobListListener();
        RetrofitManager.getInstance().removeSuccessRestrationNormalListener();
        super.onDestroy();
    }

    @Override
    public void onSuccessRegister() {
        Intent registerSuccessIntent = new Intent(RegisterNormalActivity.this, RegisterSuccessActivity.class);
        startActivity(registerSuccessIntent);
    }
}

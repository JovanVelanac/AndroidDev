package com.learning.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {
    private Button startWithFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        startWithFinish = findViewById(R.id.finishWithResult);

        startWithFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishWithResult();
            }
        });
    }

    private void finishWithResult() {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.ACTIVITY2_RESULT_EXTRA, MainActivity.ACTIVITY2_RESULT);
        setResult(RESULT_OK, intent);
        finish();
    }
}

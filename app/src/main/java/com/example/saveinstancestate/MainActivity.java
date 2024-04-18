package com.example.saveinstancestate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static final String resultKey = "result";
    String result;
    StringBuilder stringBuilder = new StringBuilder();
    EditText input;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        textView = findViewById(R.id.result);
        input = findViewById(R.id.input);
        if(savedInstanceState != null){
            stringBuilder.append(savedInstanceState.getString(resultKey));
            setResult(stringBuilder.toString());
        }
    }
    public void convert(View view){
        try {
            String prompt = input.getText().toString();
            double celsius = Double.parseDouble(prompt);
            double fahrenheit = (celsius * 9/5) + 32;
            result = String.format("%.2f Fahrenheit",fahrenheit);
            stringBuilder.append(String.format("%.2f Fahrenheit\n", fahrenheit));
            setResult(stringBuilder.toString());
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            setResult("Error");
        }
    }
    public void setResult(String result){
        if(result != null) {
            textView.setText(result);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(resultKey,stringBuilder.toString());
    }
}
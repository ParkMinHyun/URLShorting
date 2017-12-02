package com.example.parkminhyun.urlshorten;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText inputURL_EditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        propertyInit();

    }

    private void propertyInit() {
        inputURL_EditText = (EditText)findViewById(R.id.editURL);
    }

    public void deleteBtnClicked(View view) {
        inputURL_EditText.setText("");
    }

    public void findFoodStoreBtnClicked(View view) {
    }
}

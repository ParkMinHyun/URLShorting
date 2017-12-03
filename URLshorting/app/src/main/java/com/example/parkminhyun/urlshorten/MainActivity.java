package com.example.parkminhyun.urlshorten;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView log_TextView;
    EditText inputURL_EditText;
    WebView webView;

    URLShorting urlShorting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        propertyInit();

    }

    private void propertyInit() {
        inputURL_EditText = (EditText) findViewById(R.id.editURL);
        log_TextView = (TextView) findViewById(R.id.log);

        urlShorting = new URLShorting();
    }

    public void deleteBtnClicked(View view) {
        inputURL_EditText.setText("");
    }

    public void searchBtnClicked(View view) {
        String inputUrl = inputURL_EditText.getText().toString();

        String text = log_TextView.getText().toString()
                + "입력 URL: " + inputUrl + '\n'
                + "shorten URL: " + urlShorting.shortenURL(inputUrl) + '\n'
                + "=====================================\n";

        log_TextView.setText(text);
    }
}

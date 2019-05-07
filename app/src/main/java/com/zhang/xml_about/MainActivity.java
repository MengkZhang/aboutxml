package com.zhang.xml_about;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void jump(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    public void createXMLOne(View view) {
        jump(CreateXMLOneActivity.class);
    }

    public void createXMLTwo(View view) {
        jump(CreateXMLTwoActivity.class);
    }

    public void parserXMLTwo(View view) {
        jump(XmlParserActivity.class);
    }
}

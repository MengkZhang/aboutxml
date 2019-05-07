package com.zhang.xml_about;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class XmlParserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_parser);
        TextView textView = (TextView) findViewById(R.id.textView);
        StringBuffer sb = new StringBuffer();

        try {
            InputStream inputStream = getAssets().open("weather.xml");
            List<Channel> channels = WeatherParser.parserXml(inputStream);
            Log.e("===z",channels.toString());
            for (Channel channel : channels) {
                sb.append(channel.toString());
            }
            textView.setText("天气" + sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "IO异常", Toast.LENGTH_SHORT).show();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            Toast.makeText(this, "解析异常", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "异常", Toast.LENGTH_SHORT).show();
        }

    }
}

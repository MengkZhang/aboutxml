package com.zhang.xml_about;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成一个如下的XML文件
 * <?xml version="1.0" encoding="utf-8"?>
 * <smss>
 *
 *     <sms>
 *         <name>Mengk</name>
 *         <city>成都<city/>
 *         <age>26岁</age>
 *     </sms>
 *
 *     <sms>
 *         <name>Mengk2</name>
 *         <city>成都2<city/>
 *         <age>26岁2</age>
 *     </sms>
 *
 * </smss>
 */
public class CreateXMLOneActivity extends AppCompatActivity {

    private List<Smss> mSmssList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_xmlone);

        initData();
    }

    private void initData() {
        mSmssList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Smss smss = new Smss();
            smss.setName("Mengk" + i);
            smss.setCity("成都" + i);
            smss.setAge("26岁" + i);
            mSmssList.add(smss);
        }
    }

    /**
     * 点击生成XML文件
     * @param view
     */
    public void createXmlFile(View view) {
        StringBuffer sb = new StringBuffer();
        //[1]生成XML头部信息
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");

        //[2]生成<smss>节点
        sb.append("<smss>");

        //[3]生成sms节点
        for (Smss smss : mSmssList) {
            //[4]生成sms节点
            sb.append("<sms>");

            //[5]生成name 等属性节点
            sb.append("<name>");
            sb.append(smss.getName());
            sb.append("</name>");

            sb.append("<city>");
            sb.append(smss.getCity());
            sb.append("</city>");

            sb.append("<age>");
            sb.append(smss.getCity());
            sb.append("</age>");

            sb.append("</sms>");
        }
        sb.append("</smss>");

        //把数据存储到SD卡中
        try {
            File file = new File(Environment.getExternalStorageDirectory().getPath(), "smsbackup.xml");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(sb.toString().getBytes());
            fos.close();//关闭流
            Toast.makeText(this, "生成成功请到手机根路径下查看", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "文件异常生成失败", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "IO异常生成失败", Toast.LENGTH_SHORT).show();
        }

    }

    class Smss {
        private String name;
        private String city;
        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }

}

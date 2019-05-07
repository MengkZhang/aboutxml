package com.zhang.xml_about;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import android.view.View;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成一个如下的XML文件 用google的API XmlSerializer来生成
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
public class CreateXMLTwoActivity extends AppCompatActivity {

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

        //[1]实例化XmlSerializer
        XmlSerializer xmlSerializer = Xml.newSerializer();

        try {
            //[2]设置参数 存储XML文件到SD卡中
            File file = new File(Environment.getExternalStorageDirectory().getPath(), "smsbackup2.xml");
            FileOutputStream fos = new FileOutputStream(file);
            xmlSerializer.setOutput(fos,"utf-8");

            //[3]写XML文件头部信息
            xmlSerializer.startDocument("utf-8",true);

            //[4]写smss节点
            xmlSerializer.startTag(null,"smss");

            //[5]写sms节点
            for (Smss smss : mSmssList) {

                xmlSerializer.startTag(null,"sms");

                //[6]写name等属性节点
                xmlSerializer.startTag(null,"name");
                xmlSerializer.text(smss.getName());
                xmlSerializer.endTag(null,"name");

                xmlSerializer.startTag(null,"city");
                xmlSerializer.text(smss.getCity());
                xmlSerializer.endTag(null,"city");

                xmlSerializer.startTag(null,"age");
                xmlSerializer.text(smss.getAge());
                xmlSerializer.endTag(null,"age");

                xmlSerializer.endTag(null,"sms");

            }

            xmlSerializer.endTag(null,"smss");

            xmlSerializer.endDocument();

            //关闭流
            fos.close();
            Toast.makeText(this, "写文件成功", Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "文件异常写文件失败", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "IO异常写文件失败", Toast.LENGTH_SHORT).show();
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

package com.hwx.myauth.test;

import org.apache.ibatis.io.Resources;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

@Component
public class JarTest implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("test.application");
        InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
        Properties properties = new Properties();
        properties.load(reader);
        String resoult = properties.getProperty("qqq");
        reader.close();
        inputStream.close();
        System.out.println(resoult);

    }
}

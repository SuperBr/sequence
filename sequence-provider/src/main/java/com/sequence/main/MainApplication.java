package com.sequence.main;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import java.util.Map;
import java.util.Properties;

@SpringBootApplication
public class MainApplication {

    private static Properties properties;

    private static Map map;

    public static void main(String[] args) throws Exception {
        ConfigurableEnvironment en = SpringApplication.run(MainApplication.class).getEnvironment();
        System.getProperties().entrySet().forEach(System.out::println);
        System.getenv().entrySet().forEach(System.out::println);
        en.getPropertySources().forEach(e -> {
                    System.out.println(e);
                    Object obj = en.getPropertySources().get(e.getName()).getSource();
                    if (obj instanceof Properties) {
                        properties = (Properties) obj;
                        properties.entrySet().forEach(System.out::println);
                    }
                    if (obj instanceof Map) {
                        map = (Map) obj;
                        map.entrySet().forEach(System.out::println);
                    }


                }

        );
    }

}
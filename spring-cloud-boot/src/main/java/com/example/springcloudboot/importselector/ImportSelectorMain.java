package com.example.springcloudboot.importselector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableAutoImport
public class ImportSelectorMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext ca = SpringApplication.run(ImportSelectorMain.class, args);
        FirstClass fc = ca.getBean(FirstClass.class);
        System.out.println(fc);
    }
    
}

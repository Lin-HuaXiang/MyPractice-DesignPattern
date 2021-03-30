package com.example.designpatternspider;

import com.example.designpatternspider.selenium.util.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesignPatternSpiderApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DesignPatternSpiderApplication.class, args);
		Main.main(args);
	}

}

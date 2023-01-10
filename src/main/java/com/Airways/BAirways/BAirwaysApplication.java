package com.Airways.BAirways;

import com.Airways.BAirways.Controller.TestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BAirwaysApplication {

	public static void main(String[] args) {
		SpringApplication.run(BAirwaysApplication.class, args);
		TestController testController = new TestController();
		try {
			testController.createDB();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}

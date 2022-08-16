package com.nony.studentgradingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.nony.studentgradingsystem"})
public class StudentGradingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentGradingSystemApplication.class, args);
	}

}

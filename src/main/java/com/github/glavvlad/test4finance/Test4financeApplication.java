package com.github.glavvlad.test4finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(basePackageClasses = {Test4financeApplication.class, Jsr310JpaConverters.class})
@SpringBootApplication
public class Test4financeApplication {

	public static void main(String[] args) {
		SpringApplication.run(Test4financeApplication.class, args);
	}
}

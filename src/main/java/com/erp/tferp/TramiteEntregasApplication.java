package com.erp.tferp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.erp.tferp.*","com.quick.rest.*"})
public class TramiteEntregasApplication {

	public static void main(String[] args) {
		SpringApplication.run(TramiteEntregasApplication.class, args);
	}

}

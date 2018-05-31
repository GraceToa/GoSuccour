package com.gosuccour;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gosuccour.service.IUploadFileService;

@SpringBootApplication
public class GoSuccourApplication implements CommandLineRunner {
	@Autowired
	IUploadFileService uploadFileService;

	public static void main(String[] args) {
		SpringApplication.run(GoSuccourApplication.class, args);
	}
	
	/*cada vez que inicie app se creara file uploads*/
	@Override
	public void run(String... args) throws Exception {
		uploadFileService.deleteAll();
		uploadFileService.init();
		
	}
}

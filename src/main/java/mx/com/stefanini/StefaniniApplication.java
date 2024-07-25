package mx.com.stefanini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class StefaniniApplication {

	public static void main(String[] args) {
		SpringApplication.run(StefaniniApplication.class, args);
	}
	
}

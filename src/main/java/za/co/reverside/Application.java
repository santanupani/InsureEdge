package za.co.reverside;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jms.annotation.EnableJms;



@SpringBootApplication
@EnableJms
@ImportResource(value = {"classpath:security.xml"})
public class Application {
	
	public static void main(String args[]){
		SpringApplication.run(Application.class, args);
	}

}
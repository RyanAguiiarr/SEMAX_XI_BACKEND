package SEMAC_BACKEND.SEMAC_BACKEND;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SemacBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SemacBackendApplication.class, args);
	}

}

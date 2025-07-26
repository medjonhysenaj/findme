package com.automatic.apartament;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApartamentApplicationTests {

	@Test
	void contextLoads() {
	}

}
@Component
public class StartupCheck {
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @PostConstruct
    public void printUrl() {
        System.out.println("✅ DB URL in use: " + dbUrl);
    }
}

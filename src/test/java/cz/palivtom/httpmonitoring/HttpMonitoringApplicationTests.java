package cz.palivtom.httpmonitoring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(profiles = "test")
class HttpMonitoringApplicationTests {

	@Test
	void contextLoads() {
	}

}
package ru.netology.REST;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    public static final int PORT = 8082;
    @Autowired
    TestRestTemplate restTemplate;

    private static GenericContainer<?> devApp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(PORT);
    private static GenericContainer<?> prodApp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(PORT);;

    @BeforeAll
    public static void setUp() {
        devApp.start();
        prodApp.start();
    }

    @Test
    void contextLoadsDevApp() {
        ResponseEntity<String> forDevApp = restTemplate.getForEntity("http://localhost:" + devApp.getMappedPort(PORT), String.class);
        assertEquals(forDevApp.getBody(), "true");
    }
    @Test
    void contextLoadsProdApp() {
        ResponseEntity<String> forProdApp = restTemplate.getForEntity("http://localhost:" + devApp.getMappedPort(PORT), String.class);
        assertEquals(forProdApp.getBody(), "false");
    }

}
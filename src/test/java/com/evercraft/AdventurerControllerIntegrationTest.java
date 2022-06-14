package com.evercraft;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdventurerControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    AdventurerRepository adventurerRepository;

    @Test
    public void createAdventurer() {
        Adventurer adventurer = new Adventurer();

        ResponseEntity<String> response = this.restTemplate.postForEntity("http://localhost:" + port + "/api/v1/adventurers", adventurer, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}


